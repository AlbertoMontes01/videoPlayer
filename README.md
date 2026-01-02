# Android Video Catalog Player

A modern Android application built to explore **video catalog browsing and advanced playback features**, using **Jetpack Compose**, **Clean Architecture**, and **Media3 (ExoPlayer)**.

This project is designed as a **learning-oriented but production-style app**, focusing on proper architecture, lifecycle management, and real-world video playback scenarios such as fullscreen playback, mini-player behavior, and Picture-in-Picture (PiP).

---

## 🎯 Purpose

The main goal of this project is to:

- Display a **catalog of videos** fetched from a remote API (Pexels)
- Allow users to **select a video** and navigate to a detail screen
- Play videos using **ExoPlayer (Media3)**
- Support:
  - Inline playback
  - Fullscreen playback
  - Video minimization
  - Picture-in-Picture (PiP) mode
- Practice **modern Android development patterns** and proper lifecycle handling

---

## 🧱 Architecture

The project follows **Clean Architecture** principles with **MVVM**:


### Key architectural decisions

- **Single-Activity architecture**
- **Navigation Compose**
- **Unidirectional data flow**
- **State-driven UI**
- **Single shared ExoPlayer instance**
- **Dependency Injection with Hilt**

This structure helps keep the codebase:
- Testable
- Scalable
- Easy to reason about
- Close to real-world Android projects

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Media3 / ExoPlayer**
- **Navigation Compose**
- **Hilt (Dependency Injection)**
- **Coroutines & Flow**
- **Pexels Video API**

---

## 🎬 Video Playback Features

The app focuses heavily on **video playback behavior**, including:

- Playback powered by **Media3 (ExoPlayer)**
- Inline video playback inside a detail screen
- Transition to **fullscreen playback**
- Support for **mini-player behavior**
- **Picture-in-Picture (PiP)** mode
- Correct handling of:
  - Activity & lifecycle events
  - App backgrounding
  - Configuration changes

Special care is taken to avoid:
- Multiple player instances
- Memory leaks
- Audio playback conflicts
- Lifecycle-related crashes

---

## 🧭 App Flow

1. **Video Catalog Screen**
   - Displays a scrollable list of videos from the API

2. **Video Detail Screen**
   - Shows video metadata (title, description, etc.)
   - Displays an embedded video player at the top
   - Text-only content below the player

3. **Fullscreen Player**
   - Expands the video into immersive fullscreen mode

4. **Picture-in-Picture (PiP)**
   - Allows the video to continue playing while the app is minimized

---

## 🧪 Learning Goals

This project is intentionally built to practice and understand:

- Media3 / ExoPlayer internals
- Video playback lifecycles
- Picture-in-Picture implementation
- Compose + ExoPlayer integration
- Navigation between playback states
- Clean Architecture in a real UI-driven app
- Dependency Injection with Hilt

---

## 🚧 Project Status

🟡 **In active development**

Features are implemented incrementally with a focus on correctness, clarity, and best practices rather than speed.

---

## 📌 Notes

This project is intended for:

- Android developers learning advanced media playback
- Practicing modern Android architecture
- Serving as a reference implementation for:
  - Media3 + Compose
  - Video catalogs
  - PiP handling

---

## 📄 License

This project is created for **educational purposes**.
