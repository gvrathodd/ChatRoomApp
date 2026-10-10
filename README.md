# ChatRoomApp

[**Download ChatRoomApp APK (v1.0.0)**](https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk)

---

<div align="center">
  <img src="art/logo.png" alt="ChatRoomApp Logo" width="128" />
  <h2>ChatRoomApp</h2>
  <p>A real-time messaging application for Android built with Jetpack Compose, Material Design 3, and Firebase.</p>

  <p>
    <a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/Kotlin-1.9.0-7F52FF.svg?style=flat-square&logo=kotlin&logoColor=white" alt="Kotlin" /></a>
    <a href="https://developer.android.com/jetpack/compose"><img src="https://img.shields.io/badge/Jetpack%20Compose-BOM%202023.03.00-4285F4.svg?style=flat-square&logo=android&logoColor=white" alt="Compose" /></a>
    <a href="https://firebase.google.com/"><img src="https://img.shields.io/badge/Firebase-Auth%20%26%20Firestore-FFCA28.svg?style=flat-square&logo=firebase&logoColor=black" alt="Firebase" /></a>
    <a href="https://m3.material.io/"><img src="https://img.shields.io/badge/Material%20Design-3-6750A4.svg?style=flat-square" alt="Material 3" /></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-gray.svg?style=flat-square" alt="License" /></a>
  </p>
</div>

---

## Overview

ChatRoomApp is a native Android communication platform designed with modern Android development practices. It provides synchronized multi-room discussions, channel filtering, and end-to-end user account lifecycle management powered by Cloud Firestore and Firebase Authentication.

---

## Features

### Authentication and Account Security
* **Mandatory Email Verification**: Enforces account validation through email verification tokens before permitting access to active channels.
* **Authentication Lifecycle**: Supports user registration, credential verification, persistent sessions, and secure password recovery.
* **Session Guarding**: Automated routing prevents unauthenticated or unverified users from accessing discussion rooms.

### Real-Time Messaging and Rooms
* **Live Synchronization**: Real-time room and message updates utilizing Cloud Firestore reactive listeners (`callbackFlow`).
* **Topic-Based Channels**: Filter rooms across designated technology topics including Kotlin, Jetpack Compose, Firebase, Architecture, and General Discussions.
* **Search and Discovery**: Real-time search across room titles and topic tags.
* **Message Management**: In-thread message interaction with clipboard copying and sender-authorized message deletion.
* **Channel Governance**: Create rooms with customized metadata, or delete owned rooms with structured confirmation dialogs.

### Architecture and Offline Support
* **Offline Persistence**: Cloud Firestore local cache integration maintains readability during intermittent connectivity.
* **Reactive Architecture**: Unidirectional Data Flow (UDF) implemented using Kotlin Coroutines, StateFlow, and Jetpack ViewModels.

---

## Architecture and Technology Stack

| Component | Implementation |
| :--- | :--- |
| **Language** | Kotlin 1.9.0 |
| **UI Framework** | Jetpack Compose (Compiler 1.5.1, BOM 2023.03.00) |
| **Design System** | Material Design 3 |
| **Architecture Pattern** | Model-View-ViewModel (MVVM) with Repository Pattern |
| **Asynchronous Concurrency** | Kotlin Coroutines, StateFlow, callbackFlow |
| **Backend Services** | Firebase Authentication, Cloud Firestore |
| **Local Cache** | Firestore Disk Persistence |
| **Navigation** | AndroidX Navigation Compose |
| **Testing** | JUnit 4 Unit Test Suite |

---

## Installation

### Direct APK Download
Precompiled binaries are available directly via the repository:
* [Download Latest APK (v1.0.0)](https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk)

### Installation Steps
1. Download the APK file directly to your target Android device (Android 8.0 / API 26 or higher).
2. Allow installation from unknown sources in Android system settings if prompted.
3. Open the downloaded package and select **Install**.
4. Launch the application and register an account using a valid email address.
5. Confirm the verification email sent to your inbox to activate the session.

---

## Building from Source

### Prerequisites
* JDK 17
* Android SDK 34
* Android Studio Iguana or newer

### Build Instructions
```bash
# Clone the repository
git clone https://github.com/gvrathodd/ChatRoomApp.git
cd ChatRoomApp

# Assemble debug binary
./gradlew assembleDebug --no-daemon
```

The output binary will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## Test Execution

Run the local unit test suite covering domain models, result abstractions, search indexing, and input validation:

```bash
./gradlew testDebugUnitTest --no-daemon
```

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
