<div align="center">
  <img src="art/logo.png" alt="ChatRoomApp Logo" width="120" style="border-radius: 24px;" />
  <h1>ChatRoomApp</h1>
  <p><strong>A modern, production-grade real-time messaging application for Android.</strong></p>
  <p>Engineered with Jetpack Compose, Material Design 3, Kotlin Coroutines, and Cloud Firestore.</p>

  <p>
    <a href="https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk"><img src="https://img.shields.io/badge/Download_APK-v1.0.0_Release-34A853?style=for-the-badge&logo=android&logoColor=white" alt="Download APK" /></a>
  </p>

  <p>
    <a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/Kotlin-1.9.0-7F52FF.svg?style=flat-square&logo=kotlin&logoColor=white" alt="Kotlin" /></a>
    <a href="https://developer.android.com/jetpack/compose"><img src="https://img.shields.io/badge/Jetpack%20Compose-BOM%202023.03.00-4285F4.svg?style=flat-square&logo=android&logoColor=white" alt="Compose" /></a>
    <a href="https://firebase.google.com/"><img src="https://img.shields.io/badge/Firebase-Auth%20%26%20Firestore-FFCA28.svg?style=flat-square&logo=firebase&logoColor=black" alt="Firebase" /></a>
    <a href="https://m3.material.io/"><img src="https://img.shields.io/badge/Material%20Design-3-6750A4.svg?style=flat-square" alt="Material 3" /></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-gray.svg?style=flat-square" alt="License" /></a>
    <img src="https://img.shields.io/badge/Platform-Android%208.0%2B-blue.svg?style=flat-square&logo=android&logoColor=white" alt="Platform" />
  </p>
</div>

---

## 📌 Overview

**ChatRoomApp** is a native Android communication platform designed following Google's modern Android development guidelines. It delivers synchronous multi-room conversations, topic-based channel organization, real-time message streaming, and account lifecycle security backed by Firebase Cloud Firestore and Firebase Authentication.

---

## 🚀 Key Features

### 🔐 Authentication & Session Security
* **Mandatory Email Verification**: Enforces account validation via email verification tokens before granting entry to chat channels.
* **Full Auth Lifecycle**: Secure user registration, credential verification, persistent sessions, and password recovery.
* **Navigation Guards**: Automated routing redirects unauthenticated or unverified users to verification screens.

### 💬 Real-Time Messaging & Channel Governance
* **Reactive Data Synchronization**: Real-time room and message updates utilizing Cloud Firestore reactive listeners (`callbackFlow`).
* **Topic-Based Categorization**: Filter discussions across designated topics including Kotlin, Jetpack Compose, Firebase, Architecture, and General.
* **Instant Search**: Real-time client-side search across room titles and tags.
* **In-Thread Interaction**: Copy message contents to clipboard with one tap, and author-authorized message deletion.
* **Channel Management**: Create customized rooms with topic tags or delete owned rooms with structured confirmation dialogs.

### ⚡ Architecture & Resilience
* **Offline Persistence**: Cloud Firestore local disk cache maintains message history during intermittent network dropouts.
* **Unidirectional Data Flow (UDF)**: UI states driven by Kotlin Coroutines, `StateFlow`, and Jetpack `ViewModel` architecture.
* **Material Design 3**: Themed with dynamic color tokens, glassmorphism app bar surfaces, and responsive layout primitives.

---

## 📱 Download & Installation

Precompiled APK packages are available directly from the repository releases:

| Version | Architecture | Minimum SDK | Direct Link |
| :--- | :--- | :--- | :--- |
| **v1.0.0 (Release)** | Universal (`arm64-v8a`, `armeabi-v7a`, `x86_64`) | Android 8.0+ (API 26+) | [⬇️ **Download ChatRoomApp.apk**](https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk) |

### Installation Instructions:
1. Download `ChatRoomApp.apk` directly to your Android device or emulator.
2. If prompted by your browser or file manager, enable **"Install unknown apps"** in Android Settings.
3. Open the APK and tap **Install**.
4. Launch the application and register an account with a valid email.
5. Click the verification link sent to your inbox to activate your session.

---

## 🏗️ Architecture & Tech Stack

```
com.example.chatroomapp
├── data/               # Domain models, Result abstractions & Firestore repositories
│   ├── Injection.kt
│   ├── Message.kt
│   ├── MessageRepository.kt
│   ├── Room.kt
│   ├── RoomRepository.kt
│   └── UserRepository.kt
├── screen/             # Jetpack Compose UI screens
│   ├── login.kt
│   ├── SignUp.kt
│   ├── chatrooms.kt
│   └── chat.kt
├── ui/theme/           # Material 3 colors, typography & themes
├── viewmodel/          # StateFlow & Coroutine-driven ViewModels
│   ├── AuthViewModel.kt
│   ├── RoomViewModel.kt
│   └── MessageViewModel.kt
└── MainActivity.kt     # Single Activity host with Navigation Compose
```

| Layer | Implementation |
| :--- | :--- |
| **Language** | Kotlin 1.9.0 |
| **UI Toolkit** | Jetpack Compose (Compiler 1.5.1, BOM 2023.03.00) |
| **Design System** | Material Design 3 |
| **Architecture** | MVVM + Repository Pattern + Unidirectional Data Flow |
| **Concurrency** | Kotlin Coroutines, `StateFlow`, `callbackFlow` |
| **Backend & Auth** | Firebase Authentication, Cloud Firestore |
| **Offline Cache** | Firestore Disk Persistence |
| **Navigation** | AndroidX Navigation Compose |
| **Testing** | JUnit 4 Unit Test Suite |

---

## 🛠️ Building from Source

### Prerequisites
* JDK 17
* Android SDK 34
* Android Studio Iguana (2023.2.1) or newer

### Build Steps
```bash
# 1. Clone the repository
git clone https://github.com/gvrathodd/ChatRoomApp.git
cd ChatRoomApp

# 2. Build Debug APK
./gradlew assembleDebug --no-daemon

# 3. Locate compiled binary
# APK is generated at: app/build/outputs/apk/debug/app-debug.apk
```

---

## 🧪 Testing

Run the local unit test suite covering domain models, result abstractions, and input validation:

```bash
./gradlew testDebugUnitTest --no-daemon
```

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
