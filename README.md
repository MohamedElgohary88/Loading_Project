# Loading-Project

## Project Overview
Download image 
![Screenshot (11)](https://user-images.githubusercontent.com/87489620/200378905-00166331-285f-4d01-a407-b1ba57ae1449.png)
Downloading ..
![Screenshot (10)](https://user-images.githubusercontent.com/87489620/200378947-532daa1a-53df-4a92-8692-a3c73a604007.png)

Download Success
![Screenshot (9)](https://user-images.githubusercontent.com/87489620/200378993-83f28fa3-b116-40f7-ac46-aa1f43605c58.png)

In this project students will create an app to download a file from the Internet by clicking on a custom-built button where:

width of the button gets animated from left to right
text gets changed based on different states of the button
circle gets be animated from 0 to 360 degrees
A notification will be sent once the download is complete. When a user clicks on the notification, the user lands on detail activity and the notification gets dismissed. In detail activity, the status of the download will be displayed and animated via MotionLayout upon opening the activity.

## Project Steps
Create a radio list of the following options where one of them can be selected for downloading:
https://github.com/bumptech/glide
https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter
https://github.com/square/retrofit
Create a custom loading button by extending View class and assigning custom attributes to it
Animate properties of the custom button once it’s clicked
Add the custom button to the main screen, set on click listener and call download() function with selected Url
If there is no selected option, display a Toast to let the user know to select one.
Once the download is complete, send a notification with custom style and design
Add a button with action to the notification, that opens a detailed screen of a downloaded repository
Create the details screen and display the name of the repository and status of the download
Use declarative XML with motionLayout to coordinate animations across the views on the detail screen
Add a button to the detail screen to return back to the main screen.
## build by Mohamed Elgohary 
