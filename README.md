# Compose Kotlin Android Drag and Drop

This library provides easy-to-use Compose methods for enabling drag-and-drop functionality in Android and Desktop applications. With this library, you can easily implement drag-and-drop reordering in a LazyList and LazyGrid using a powerful Jetpack Compose modifier. Whether you're working on a mobile app or a desktop application, this library offers a seamless solution for managing dynamic lists and grids with intuitive drag-and-drop interactions.

It simplifies the process of reordering items in a list or grid, providing a smooth and user-friendly experience. Perfect for developers looking for an efficient, cross-platform way to handle drag-and-drop reordering in Jetpack Compose applications, this library ensures that you can implement complex interactions with minimal effort.
## Features

- **Modern UI Components**: Components such as buttons, text fields, and more, specifically built for Jetpack Compose.
- **Seamless Integration with Jetpack Compose**: The library is fully compatible with Jetpack Compose and integrates smoothly into projects.
- **Extensible and Customizable Components**: All UI components are designed to be easily extended and customized to meet project-specific requirements.

---

## Installation and Setup

### 1. Gradle Integration

#### A. Add Dependencies

To add the library to your project, follow these steps:

1. Open your **project-level `build.gradle`** file (not the app-level one).
2. Make sure you include Maven Central Repository if it is not already there.

```gradle
allprojects {
    repositories {
        google()
        mavenCentral() // Add this if it's not already there
    }
}
