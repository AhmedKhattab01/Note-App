# Easy Memo Note App

![App Logo](app_logo.png)

Easy Memo Note App is an Android application developed in Kotlin that allows users to create and manage their personal memos and notes conveniently. The app provides a user-friendly interface and various features to enhance the note-taking experience.

## Technologies Used

The Easy Memo Note App utilizes the following technologies and libraries:

- **Kotlin**: The primary programming language used for developing the app, known for its conciseness and interoperability with Java.
- **Navigation Component**: This library helps with implementing navigation and managing fragment transactions within the app.
- **Dependency Injection with Hilt**: Hilt is a dependency injection library for Android apps that simplifies the process of managing dependencies and promotes a modular and testable architecture.
- **MVVM**: The app architecture follows the Model-View-ViewModel pattern, which separates the user interface (View) from the data and business logic (Model) using ViewModels.
- **Single Activity Multiple Fragments**: The app follows a single-activity architecture, where different screens are represented by fragments, allowing for better navigation and modularization.
- **Lottie Animation**: Lottie is an animation library that enables the integration of beautiful and engaging animations into the app, enhancing the user experience.
- **Room Database**: Room is a persistence library provided by Android Jetpack, offering an abstraction layer over SQLite and facilitating the management of local data storage.
- **Repository Pattern**: The app implements the repository pattern, providing a clean separation between the data sources and the rest of the app, making it easier to manage and retrieve data.
- **Shared Preferences**: Shared Preferences is a lightweight data storage mechanism provided by Android, used in the app to store user preferences and small amounts of app-related data.

## Screenshots

![Screenshot 1](screenshot1.png)
*Caption: Add new memo screen.*

![Screenshot 2](screenshot2.png)
*Caption: Memo list screen.*

## Installation

The Easy Memo Note App is available for download on the [Google Play Store](https://play.google.com/store/apps/details?id=com.example.easymemonoteapp). Simply follow the link and install it on your Android device to start using it.

## How to Use

1. Upon launching the app, you will be greeted with the memo list screen, displaying all your existing memos.
2. To create a new memo, tap on the "Add Memo" button and enter the desired text in the provided input field.
3. Once you have entered your memo, tap on the "Save" button to save it. The memo will be added to the list.
4. To view or edit a memo, simply tap on it in the list. This will open the memo details screen, where you can make changes or delete the memo.
5. To delete a memo, tap on the trash icon in the memo details screen, confirm the deletion, and the memo will be removed from the list.
6. You can also modify the app's settings by accessing the options menu, which can be accessed by tapping on the three-dot icon in the top right corner of the memo list screen.

## Feedback and Contributions

If you encounter any issues while using the Easy Memo Note App or have any suggestions for improvements, please feel free to open an issue on the [GitHub repository](https://github.com/your-username/easy-memo-note-app). Contributions are also welcome, and pull requests will be reviewed and considered.

## License

The Easy Memo Note App is released under the [MIT License](LICENSE).
