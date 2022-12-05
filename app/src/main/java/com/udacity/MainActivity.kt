package com.udacity

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.udacity.NotificationHelper.NOTIFICATION_CHANNEL_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fileName = "filename"
    private var downloadID: Long = 0
    private lateinit var notificationManager: NotificationManager
    private lateinit var action: NotificationCompat.Action

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        checkAndRequestPermissions()
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        loading_button.setOnClickListener {
            radioChoice()
        }
        NotificationHelper.createNotificationChannel(NOTIFICATION_CHANNEL_ID, this)
    }
    /*mohamed elgohary*/
    //  *****  Receiver  ***** //
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val cursor =
                downloadManager.query(id?.let { DownloadManager.Query().setFilterById(it) })

            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    context?.let {
                        NotificationHelper
                            .sendNotification(it, fileName, SUCCESS)
                    }
                } else {
                    context?.let {
                        NotificationHelper
                            .sendNotification(it, fileName, FAILED)
                    }
                }
            }
        }
    }
    /*mohamed elgohary*/
    //  *****  checkAndRequestPermissions  ***** //
    private fun checkAndRequestPermissions() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) { // requestPermissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        }
    }

    //  *****  Download Function ***** //
    private fun download(url: String) {
        val request =
            DownloadManager.Request(Uri.parse(url))
                .setTitle(getString(R.string.app_name))
                .setDescription(getString(R.string.app_description))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        loading_button.setState(ButtonState.Loading)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
    }
    /*mohamed elgohary*/
    //  *****  Radio Choice Function  ***** //
    private fun radioChoice() {
        if (radio_group.checkedRadioButtonId == -1) {
            Toast.makeText(this, getString(R.string.please_select), Toast.LENGTH_LONG).show()
        } else {
            val radioSelected = radio_group.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(radioSelected)

            if (radioButton == glide_radio_button) {
                fileName = getString(R.string.glide_image_loading)
                download(GLIDE)
            } else if (radioButton == load_app_radio_button) {
                fileName = getString(R.string.load_app_)
                download(LOADING)
            } else {
                fileName = getString(R.string.retrofit_type_safe_http_client)
                download(RETROFIT)
            }
        }
    }
    /*mohamed elgohary*/
    //  *****  Companion Object  ***** //
    companion object {
        const val GLIDE = "https://github.com/bumptech/glide/archive/refs/heads/master.zip"
        const val LOADING =
            "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip"
        const val RETROFIT = "https://github.com/square/retrofit/archive/heads/master.zip"
        const val REQUEST_CODE_PERMISSION = 101
        const val SUCCESS = "SUCCESS!"
        const val FAILED = "FAIL!"
        private const val CHANNEL_ID = "channelId"
        private val CHANNEL_NAME = "channel_name"
    }
}
