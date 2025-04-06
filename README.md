# Compose Kotlin Android Drag and Drop

This library provides easy-to-use Compose methods for enabling drag-and-drop functionality in Android and Desktop applications. With this library, you can easily implement drag-and-drop reordering in a LazyList and LazyGrid using a powerful Jetpack Compose modifier. Whether you're working on a mobile app or a desktop application, this library offers a seamless solution for managing dynamic lists and grids with intuitive drag-and-drop interactions.

It simplifies the process of reordering items in a list or grid, providing a smooth and user-friendly experience. Perfect for developers looking for an efficient, cross-platform way to handle drag-and-drop reordering in Jetpack Compose applications, this library ensures that you can implement complex interactions with minimal effort.


## Installation and Setup

To add the library to your project, follow these steps:

1. Open your **project-level `build.gradle`** file (not the app-level one).
2. Make sure you include Maven Central Repository if it is not already there.

```gradle
allprojects {
    repositories {
        google()
        mavenCentral() // Add this if it's not already there
        maven { url = uri("https://jitpack.io") }
    }
}

```
3.  Open your **project-level `build.gradle`** file and add:
```
dependencies {
 // Drag and Drop library
    implementation("com.github.Nico-T-Ihle:Android-Compose-Drag-and-Drop:PLEASE_CHECK_OUT_THE_CURRENT_VERSION")
}
```
### [Checkout this link](https://jitpack.io/#Nico-T-Ihle/Android-Compose-Drag-and-Drop) for the current version state.

4. After you added it everything then type:
```
mvn clean install
```

5. Integration once you have set up the library as described above, you can directly invoke ExampleDragAndDropList() in your Compose UI code. For example:

```
@Composable
fun MyDragAndDropTest() {
    ExampleDragAndDropList()
}
```
This will display a sample list where you can test the drag-and-drop functionality.
