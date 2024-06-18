# Smart Job Finder

## Introduction

Welcome to the Smart Job Finder app, a demo project developed as part of the Mobile Application
Development class at FH Joanneum in Kapfenberg. This application serves as a practical platform for
students to explore various aspects of Android application development.

Smart Job Finder is a job search app that allows users to browse and apply for job listings.
Users can view job details, save jobs for later and even post their own job listings.

## Features

* **Home Screen**: Displays a list of job listings.
* **Job BottomSheet**: Shows detailed information about a job listing. Users can apply for a job
  with the Apply button. The Apply opens the email app with the job title as the subject.
* **Profile Screen**: Allows users to view and edit their profile information.
* **Listings Screen**: Displays a list of job listings liked by the user.
* **Splash Screen**: Shows the app logo when the app is launched.
* **Drawer Navigation**: Allows users to navigate to the Profile Screen or Logout.
* **Register Screen**: Allows users to create a new account.
* **Login Screen**: Allows users to log in to their account.
* **Lottie Animations**: The app uses Lottie animations to enhance the user experience.

## Technologies Used

* **Kotlin**: The primary programming language used to develop the app.
* **Jetpack Compose**: A modern toolkit for building native Android UI.
* **Firebase**: A mobile and web application development platform developed by Google.
* **Firebase Authentication**: Provides backend services, easy-to-use SDKs, and ready-made UI
  libraries to authenticate users to your app.
* **Firebase Cloud Firestore**: A flexible, scalable database for mobile, web, and server
  development from Firebase and Google Cloud.
* **Dagger-Hilt**: A dependency injection library for Android that reduces the
  boilerplate of doing manual dependency injection in your project.

## Permissions

The Smart Job Finder uses the location permission to make it easy to fill in the location field
when creating a job listing. It is not mandatory to provide the location permission, the user can
also fill in the location by hand.

## Structure

The project is structured as follows:

* **models**: Contains the data classes used in the app.
* **models/service**: Contains the services interfaces used in the app.
* **models/service/impl**: Contains the services implementations used in the app.
* **models/service/modules**: Contains the Dagger-Hilt modules used in the app.
* **ui**: Contains the Jetpack Compose UI code.
* **ui/screens** : Contains the different screens of the app packaged with the corresponding view
  models.
* **ui/theme**: Contains the theme and colors used in the app.
* **ui/widgets**: Contains the custom widgets used in the app.

## How to Run

Smart Job Finder is a native Android application developed using Kotlin and Jetpack Compose.
The App supports Android Api lvl 29 and above.
To run the Smart Job Finder app on your local machine, follow these steps:

* 1.Clone the repository:

```
git clone https://git-iit.fh-joanneum.at/p60g56/smart-job-finder.git
or
git clone git@git-iit.fh-joanneum.at:p60g56/smart-job-finder.git
```

* 2.Open the project in Android Studio.
* 3.Build and run the project on an emulator or physical device.

### File Headers

The app is not using file headers. In our opinion, file headers are not necessary for this project
since it is a small project and the code is easy to understand and Android Studio provides a lot of
information about the file through the commit history.

### Contributors

* [Alex Quantschnig](https://git-iit.fh-joanneum.at/p60g56)

