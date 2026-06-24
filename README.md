# 🎯 Android Compose Drag-and-Drop

[![](https://jitpack.io/v/Nico-T-Ihle/Android-Compose-Drag-and-Drop.svg)](https://jitpack.io/#Nico-T-Ihle/Android-Compose-Drag-and-Drop)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Android](https://img.shields.io/badge/Android-14+-green)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-purple)

Ein einfaches und leistungsstarkes Jetpack Compose Drag-and-Drop Framework für LazyList und LazyGrid — auf Android und Desktop.

## 🎬 Features

- Drag-and-Drop Reordering in LazyColumn/LazyRow
-  Grid-Support (LazyVerticalGrid, LazyHorizontalGrid)
-  Desktop & Android kompatibel
-  Smooth animations & haptic feedback
-  Einfache API (nur 1-2 Modifier!)
-  Minimal boilerplate code

## 📦 Installation

### 1. Repositories hinzufügen (Project-Level `build.gradle.kts`)

```kotlin
allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
```

### 2. Dependency hinzufügen (App-Level `build.gradle.kts`)

```kotlin
dependencies {
    // Drag and Drop
    implementation("com.github.Nico-T-Ihle:Android-Compose-Drag-and-Drop:v1.0.0")
    
    // Required
    implementation("androidx.compose.foundation:foundation:1.6.0")
    implementation("androidx.compose.material3:material3:1.1.0")
}
```

**[→ Aktuelle Version checken](https://jitpack.io/#Nico-T-Ihle/Android-Compose-Drag-and-Drop)**

## 🚀 Quick Start

### Einfache Liste

```kotlin
@Composable
fun DragDropListExample() {
    var items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .dragDropReorderable(
                items = items,
                onMove = { from, to -> 
                    items = items.toMutableList().apply {
                        add(to, removeAt(from))
                    }
                }
            )
    ) {
        itemsIndexed(items) { index, item ->
            DragDropItem(
                key = item,
                content = {
                    ListItemCard(text = item)
                }
            )
        }
    }
}

@Composable
fun ListItemCard(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
```

### Grid mit Drag-Drop

```kotlin
@Composable
fun DragDropGridExample() {
    var items by remember { mutableStateOf(gridItems) }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .dragDropReorderable(
                items = items,
                onMove = { from, to ->
                    items = items.toMutableList().apply {
                        add(to, removeAt(from))
                    }
                }
            )
    ) {
        itemsIndexed(items) { index, item ->
            DragDropGridItem(item = item)
        }
    }
}
```

## 📚 API Reference

### `dragDropReorderable` Modifier

```kotlin
fun <T> Modifier.dragDropReorderable(
    items: List<T>,
    onMove: (from: Int, to: Int) -> Unit,
    draggedItemOpacity: Float = 0.5f,
    enableHaptics: Boolean = true
): Modifier
```

| Parameter | Typ | Default | Beschreibung |
|-----------|-----|---------|-------------|
| `items` | `List<T>` | — | Liste der Elemente |
| `onMove` | `(Int, Int) -> Unit` | — | Callback bei Drag-Bewegung |
| `draggedItemOpacity` | `Float` | `0.5f` | Opacity des gezogenen Elements |
| `enableHaptics` | `Boolean` | `true` | Haptic Feedback aktivieren |

### `DragDropItem` Composable

```kotlin
@Composable
fun <T> DragDropItem(
    key: T,
    isDragging: Boolean = false,
    content: @Composable () -> Unit
)
```

## 🎨 Anpassungen

### Styling während Drag

```kotlin
@Composable
fun ListItemCard(text: String, isDragging: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .scale(if (isDragging) 1.05f else 1f)
            .shadow(if (isDragging) 12.dp else 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text, modifier = Modifier.padding(16.dp))
    }
}
```

### Drag-Feedback anpassen

```kotlin
dragDropReorderable(
    items = items,
    onMove = { from, to -> /* ... */ },
    draggedItemOpacity = 0.3f,
    enableHaptics = true
)
```

## 🧪 Testing

Mit Docker (Unit Tests):

```dockerfile
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    git

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

WORKDIR /app
COPY . .

RUN chmod +x gradlew

CMD ["./gradlew", "test", "--stacktrace"]
```

```bash
docker build -t dnd-test .
docker run --rm -v $(pwd):/app dnd-test
```

## 🐛 Troubleshooting

### Items bewegen sich nicht
-  Stelle sicher, dass `key` eindeutig ist
-  `LazyColumn/LazyRow` braucht `itemsIndexed()`
-  Modifier-Reihenfolge: `dragDropReorderable` vor anderen Modifiern

### Haptics funktioniert nicht
-  Permission in `AndroidManifest.xml`: `<uses-permission android:name="android.permission.VIBRATE" />`
-  `enableHaptics = false` zum Deaktivieren

### Performance-Probleme bei großen Listen
-  Nutze `remember { mutableStateOf(...) }` richtig
-  Compose-Recomposition minimieren

## 📖 Weitere Ressourcen

- [Jetpack Compose Docs](https://developer.android.com/compose)
- [LazyColumn API](https://developer.android.com/jetpack/compose/lists)
- [Modifier API](https://developer.android.com/jetpack/compose/modifiers)

## 🤝 Contributing

Pull Requests sind willkommen! Bitte:

1. Fork das Projekt
2. Feature Branch erstellen (`git checkout -b feature/xy`)
3. Changes committen (`git commit -am 'Add xy'`)
4. Push zum Branch (`git push origin feature/xy`)
5. Pull Request erstellen

## 📄 License

MIT License — siehe [LICENSE](LICENSE) für Details

## 👤 Autor

**Nico T. Ihle**  
- GitHub: [@Nico-T-Ihle](https://github.com/Nico-T-Ihle)

---

**⭐ Wenn dir das Projekt gefällt, gib einen Star!**
