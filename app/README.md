1 - Home und Likes sind laut Android Richtilinien nicht auf korrekter Position. 
    AppBar, TopBar, TabBar, Content, BottomBar, NavigationBar

2 - Welche Settings sollen implementiert werden (Notification, Dark mode etc.)

3 - Welche Activties kommen in den "Burger Menü"



# Smart Job Finder
##Introduction 
Welcome to the Smart Job Finder app, a demo project developed as part of the Mobile Application Development class at FH Joanneum in Kapfenberg. This application serves as a practical platform for students to explore various aspects of Android application development.

##Features

##Structure
manifest/
└── AndroidManifest.xml
java/
└── com/example.smart_job_finder_v2/
    └── models/
        └── Job 
    └── ui/
        └── screens/
            ├── DetailsScreen.kt # Displays detailed job information.
            ├── HomeScreen.kt # Main screen showing the list of jobs.
            └── SettingsScreen.kt # Allows users to configure app settings.
    └── theme/
        └──Color.kt #
        └──Theme.kt # 
        └── Type.kt #
    └── widgets
        └── BottomBar.kt
        └── JobItemView.kt
    └── MainActivity
    └── Navigation.kt
    └── Screen  

##Technologies Used
###Kotlin: The primary programming language for Android development.
###Jetpack Compose: Used for building native UI components in a declarative style.
###Android Navigation Component: Manages app navigation within a single activity.

##How to Run
To run the Smart Job Finder app on your local machine, follow these steps:

###1.Clone the repository:
```
git clone https://github.com/QuantschnigAlex/Smart-Job-Finder-V2.git
```
###2.Open the project in Android Studio.
###3.Build the project using the Build > Make Project menu option.
###4.Run the application on an emulator or physical device via Run > Run 'app'.

##Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

###1.Fork the Project
###2.Create your Feature Branch
```
git checkout -b feature/NewFeature`
```
###3.Commit your Changes
```
git commit -m 'Add some NewFeature')
```
###4.Push to the Branch
```
git push origin feature/NewFeature
```
###5.Open a Pull Request

##Contact
###Alex Quantschnig
###Stefan Jovic 
