package com.udacity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        file_name_textView.text = intent.getStringExtra("FILE_NAME")
        status_textView.text = intent.getStringExtra("STATUS")
        okay_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }

}
