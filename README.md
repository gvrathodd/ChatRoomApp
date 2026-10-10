# 📱 ChatRoomApp

<div align="center">

> ### 🚀 [⬇️ Click Here to Download ChatRoomApp APK (Direct Download)](https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk)
> **Latest Build:** `v1.0.0` | **Architecture:** Universal Android (API 26+) | **Format:** `.apk`

<br/>

<img src="art/logo.png" alt="ChatRoomApp Logo" width="140" style="border-radius: 28px; box-shadow: 0 8px 24px rgba(79, 55, 138, 0.4);" />

### Modern, Real-Time Android Chat Application
*Built with Jetpack Compose, Material 3, and Cloud Firestore*

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202023.03.00-4285F4.svg?logo=android&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Firebase](https://img.shields.io/badge/Firebase-Auth%20%26%20Firestore-FFCA28.svg?logo=firebase&logoColor=black)](https://firebase.google.com/)
[![Material 3](https://img.shields.io/badge/Material%203-Stitch%20Theme-8250DF.svg?logo=materialdesign&logoColor=white)](https://m3.material.io/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

</div>

---

## 💰 Cost & Pricing Guarantee ($0 / Completely Free)

> [!NOTE]
> **This application costs $0 to run and operate.**

* **Firebase Authentication**: 100% free and unlimited for Email & Password accounts, verification emails, and password resets on the Firebase Spark (Free) plan.
* **Cloud Firestore**: Operates within Firebase's generous perpetual free tier:
  * **50,000 document reads** per day
  * **20,000 document writes** per day
  * **20,000 document deletes** per day
  * **1 GB stored database data**
* **No Credit Card Required**: Firebase Spark plan is strictly capped at free limits. Firebase will **never** charge you money unless you explicitly attach billing credentials and manually upgrade to the pay-as-you-go Blaze plan.

---

## ✨ Features

### 🔐 1. Genuine Email Verification & Authentication
* **Strict Email Verification**: Registration automatically sends a verification link to your email and requires confirmation before granting access.
* **Protected Login**: Unverified logins are blocked with clear feedback prompting the user to verify their inbox.
* **Password Reset**: Integrated *"Forgot password?"* workflow triggering standard Firebase password reset emails.
* **Session Management**: Secure, session-aware app routing remembering verified users between app launches.

### 💬 2. Real-Time Chat Rooms
* **Topic Channels**: Organize discussions across tags like *Android & Kotlin*, *Compose UI*, *Firebase*, *Architecture*, and *General*.
* **Live Search**: Instant keyword search filtering rooms and tags in real time.
* **Room Creation & Deletion**: Create custom discussion rooms with descriptions and delete rooms with a confirmation dialog.
* **Cloud Firestore Streams**: Instant updates powered by Kotlin Coroutines `callbackFlow` listeners.

### 📨 3. Interactive Messaging Experience
* **Live Chat Stream**: Real-time message synchronization with automatic auto-scrolling to the latest incoming messages.
* **Message Interaction Modal**: Tap any message to:
  * 📋 **Copy Text** directly to the Android system clipboard.
  * 🗑️ **Delete Message** (for messages sent by you) with instant Firestore removal.
* **Clean Message Bubbles**: Sender attribution, timestamp formatting, and delivered receipts (`done_all`).
* **Empty State Illustrations**: Helpful prompts when rooms have no messages yet or search queries yield no results.

### ⚡ 4. Offline Persistence & Reliability
* **Firestore Disk Caching**: Enabled local database disk persistence ensuring instant screen loads even on poor or intermittent mobile networks.
* **Resilient Deserialization**: Safe mapping with Firestore document IDs and zero null-pointer crashes.

---

## 📦 How to Install the APK

1. **Download**: Tap the [Direct Download Link](https://github.com/gvrathodd/ChatRoomApp/raw/main/releases/ChatRoomApp.apk) on your Android device.
2. **Allow Installation**: If prompted, enable **"Install unknown apps"** for your browser in Android Settings.
3. **Open & Install**: Open the downloaded `ChatRoomApp.apk` file and tap **Install**.
4. **Sign Up & Verify**: Create an account with your email, click the verification link in your inbox, and sign in!

---

## 🛠️ Tech Stack & Architecture

| Layer | Technologies |
| :--- | :--- |
| **Language** | Kotlin 1.9.0 |
| **UI Framework** | Jetpack Compose (Compiler 1.5.1, BOM 2023.03.00) |
| **Design System** | Material Design 3 (Google Stitch Purple Theme) |
| **Architecture** | MVVM (Model-View-ViewModel) + Clean Data Repositories |
| **Reactive Streams** | Kotlin Coroutines, StateFlow, callbackFlow, LiveData |
| **Backend & DB** | Firebase Authentication + Cloud Firestore |
| **Local Cache** | Cloud Firestore Offline Disk Persistence |
| **Navigation** | AndroidX Navigation Compose |
| **Testing** | JUnit 4 Unit Tests Suite |

---

## 🧪 Automated Testing

Run the full local unit test suite covering data models, `Result` error handling, category filtering, search algorithms, and form validations:

```powershell
./gradlew testDebugUnitTest --no-daemon
```

---

## 🏗️ Build from Source

### Prerequisites
* Android Studio Iguana / Jellyfish or newer
* JDK 17
* Android SDK 34

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/gvrathodd/ChatRoomApp.git
   cd ChatRoomApp
   ```
2. Build the Debug APK:
   ```powershell
   ./gradlew assembleDebug --no-daemon
   ```
3. The APK will be generated at:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
