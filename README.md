<div align="center">

# 🎵 Android Songs App

### iTunes-powered music explorer built as a Moises Code Challenge

</div>

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple?style=plastic&logo=kotlin)
![Platform](https://img.shields.io/badge/Android-Jetpack%20Compose-green?style=plastic&logo=android)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-blue?style=plastic)
![Coroutines](https://img.shields.io/badge/Async-Coroutines%20%2B%20Flow-orange?style=plastic)
![Room](https://img.shields.io/badge/Database-Room-3DDC84?style=plastic)
![Paging](https://img.shields.io/badge/Pagination-Paging3-blue?style=plastic)
![Status](https://img.shields.io/badge/Status-Completed-00C2A8?style=plastic)
![License](https://img.shields.io/badge/license-MIT-blue.svg?style=plastic)

</div>

---

<p align="center">
A production-oriented Android app that searches songs via the iTunes API, caches results locally with Room, and plays audio previews — built with a modular Clean Architecture to showcase scalable, testable, real-world Android engineering.
</p>

---

<div align="center">
  <img src="https://github.com/Laura-Oliveira/Android-Phone-Code-Challenge/blob/develop/app/src/assets/readme.png" width="800"/>
  <br/><br/>
  <video src="https://github.com/user-attachments/assets/5e844c0a-7a23-43bb-88dd-43b28e85e02d" 
         controls autoplay loop muted width="400">
  </video>
</div>

<!--
 <img src="https://github.com/Laura-Oliveira/Android-Phone-Code-Challenge/blob/develop/app/src/assets/readme.png" width="800"/>
 -->

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Data Flow](#-data-flow)
- [Project Structure](#-project-structure)
- [Features](#-features)
- [Engineering Decisions](#-engineering-decisions)
- [Testing](#-testing)
- [Running the App](#-running-the-app)
- [Known Limitations](#-known-limitations)
- [Planned Improvements](#-planned-improvements)
- [About the Developer](#-about-the-developer)

---

## 🚀 Overview

This app allows users to:

- 🔍 Search songs through the iTunes Search API with infinite scroll pagination
- 🎧 Play 30-second audio previews with a dedicated player UI
- 📀 Browse album details for any track
- 💾 Access recently searched songs offline via a local Room cache

The primary goal was not only to fulfill the challenge requirements, but to demonstrate **production-ready architectural thinking**: clean module boundaries, reactive data flow, offline resilience, and code that is straightforward to test and extend.

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin 2.0 |
| UI | Jetpack Compose |
| Architecture | MVVM + Clean Architecture |
| Async | Coroutines + Flow |
| Pagination | Paging 3 |
| Navigation | Custom Navigator abstraction |
| Local DB | Room |
| Networking | Retrofit |
| DI | Hilt |
| Build | Gradle KTS + Version Catalog |
| IDE | Android Studio Otter 2025.2.1 Patch 1 |

---

## 🏗 Architecture

The project follows a **multi-module Clean Architecture** where each module has a single, well-defined responsibility. Dependencies flow strictly inward — feature modules depend on core modules, never the reverse.

```
app  ──▶  playlist (feature)  ──▶  core-navigation
                              ──▶  core-network
                              ──▶  core-model
```

### Modules

| Module | Responsibility |
|---|---|
| `app` | Application entry point, Hilt setup, Splash, Theme |
| `playlist` | Feature module: song list, player, album, ViewModels, Repository impl |
| `core-navigation` | Navigation abstraction: `Navigator`, `Routes`, `FeatureGraph` |
| `core-network` | Data layer: Retrofit API, Paging, Room DB, DTOs |
| `core-model` | Shared domain models (`Song`) consumed by all modules |

### Layers

**Presentation** — `playlist` module  
Jetpack Compose screens and components, ViewModels managing UI state via `StateFlow`, feature-scoped DI graphs.

**Domain** — `core-model` + `core-navigation`  
Pure Kotlin models with no Android dependencies. Navigation contracts defined as interfaces, keeping the feature module decoupled from any concrete navigation implementation.

**Data** — `core-network`  
Split into `remote/` (Retrofit + `SongsPagingSource`), `local/` (Room + `SearchSongsUseCase`), and `model/` (DTOs + `SongEntity`). Repository implementations live in the `playlist` module to avoid leaking feature concerns into core.

### Architecture Diagram

![architecture](https://github.com/Laura-Oliveira/Android-Phone-Code-Challenge/blob/develop/app/src/assets/architecture.svg)

---

## 🔄 Data Flow

Understanding how data moves through the app is key to understanding the architecture:

```
User types query
      │
      ▼
SongsViewModel
      │  calls
      ▼
SongsRepositoryImpl
      │
      ├──▶ [Network available]
      │         Retrofit ──▶ iTunes API
      │         SongsPagingSource ──▶ Room (writes cache)
      │
      └──▶ [Network unavailable]
                Room local DB ──▶ returns cached results
      │
      ▼
Paging 3 PagingData<Song>
      │
      ▼
Compose UI (LazyColumn via collectAsLazyPagingItems)
```

Room acts as the **single source of truth**. The UI never reads directly from the network response — it always reads from the local database, which is kept in sync by the repository layer.

---

## 📂 Project Structure

<details>
<summary>Expand full structure</summary>

```
app/
├── manifests/
└── kotlin+java/
    └── com.challenge/
        ├── MyApp.kt
        └── core/
            ├── ui.theme/
            │   ├── Color.kt
            │   ├── Theme.kt
            │   └── Type.kt
            └── SplashScreen.kt

core-navigation/
└── kotlin+java/
    └── com.navigation/
        ├── AppNavigation.kt
        ├── FeatureGraph.kt
        ├── NavigatorInterface.kt
        ├── NavigatorInterfaceImpl.kt
        └── Routes.kt

core-model/
└── kotlin+java/
    └── com.model/
        └── Song.kt

core-network/
└── kotlin+java/
    └── com.network/
        ├── local/
        │   ├── AppDatabase.kt
        │   ├── OfflineData.kt
        │   └── SearchSongsUseCase.kt
        ├── model/
        │   ├── ItunesSongsDto.kt
        │   ├── SongDao.kt
        │   └── SongEntity.kt
        └── remote/
            ├── ItunesAPI.kt
            ├── ItunesResponse.kt
            ├── NetworkModule.kt
            └── SongsPagingSource.kt

playlist/
└── kotlin+java/
    ├── album/
    │   ├── AlbumScreen.kt
    │   └── AlbumViewModel.kt
    ├── musicPlayer/
    │   ├── ui/
    │   │   ├── NowPlaying.kt
    │   │   └── PlayerScreen.kt
    │   └── PlayerViewModel.kt
    ├── navigation/
    │   └── PlaylistFeatureGraph.kt
    ├── repository/
    │   └── RepositoryModule.kt
    ├── songs/
    │   ├── repository/
    │   │   ├── SongsRepository.kt
    │   │   ├── SongsRepositoryImpl.kt
    │   │   └── SongsViewModelRepository.kt
    │   ├── ui/
    │   │   ├── SearchBar.kt
    │   │   ├── SongItem.kt
    │   │   ├── SongsListScreenContent.kt
    │   │   └── SongsScreen.kt
    │   └── SongsViewModel.kt
    └── ui.theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt

gradle/
├── build.gradle.kts (Project)
├── build.gradle.kts (app, playlist, core-*)
├── settings.gradle.kts
└── libs.versions.toml
```

</details>

---

## ✨ Features

- ▶️ Animated splash screen
- 🔍 Song search with real-time query handling
- 📄 Infinite scroll via Paging 3
- 🎵 Song list with artwork, title, and artist
- 🎧 Audio preview player with playback controls
- 📀 Album detail screen
- 💾 Offline access to recently searched songs
- ❌ Graceful error states and loading indicators

---

## 🧠 Engineering Decisions

### Why Paging 3 instead of loading all results at once?

The iTunes API does not return a total result count, making cursor-based or total-pages pagination impossible. Paging 3's `PagingSource` handles this gracefully — it loads data incrementally on demand without needing to know the dataset size upfront. The trade-off is increased ViewModel complexity (`PagingData` is not a plain list), which was an acceptable cost for the memory and UX gains.

### Why Room as the single source of truth instead of just a cache?

Keeping Room as the authoritative data source decouples the UI entirely from network availability. The UI observes a `Flow` from Room; the repository writes to Room after each API response. This means offline behavior is deterministic by design, not an afterthought. The trade-off is that remote-to-local sync logic must be handled explicitly — this is currently done manually in the repository (see Known Limitations).

### Why keep Repository implementations inside `playlist` instead of `core-network`?

The `SongsRepository` interface is domain-level knowledge, but its implementation is a feature concern — it knows which API endpoints and which DB tables to use for songs. Placing the implementation in `playlist` keeps `core-network` as a pure infrastructure module with no knowledge of features, making it reusable if new feature modules are added later.

### Why a custom Navigator abstraction instead of using Compose Navigation directly?

Wrapping navigation behind `NavigatorInterface` means `playlist` ViewModels can trigger navigation without importing any Compose Navigation dependency. This keeps business logic testable without a UI runtime and makes it trivial to swap the navigation library if needed.

---

## 🧪 Testing

Unit tests cover the ViewModel and UseCase layers with a focus on data flow correctness.

| Layer | Framework | Focus |
|---|---|---|
| ViewModel | JUnit 4 + Turbine | State transitions, loading/error flows |
| UseCases | MockK | Business logic, repository contract |

**Run all tests:**

```bash
./gradlew test
```

**Run with coverage report:**

```bash
./gradlew testDebugUnitTest jacocoTestReport
```

> UI and integration tests are listed under Planned Improvements.

---

## ▶️ Running the App

<details>
<summary>Click to expand setup instructions</summary>

### Requirements

- Android Studio Otter 2025.2.1 or later
- JDK 17+
- Android SDK 34
- Min SDK: 26 (Android 8.0)

### Steps

**1. Clone the repository**

```bash
git clone https://github.com/Laura-Oliveira/Android-Phone-Code-Challenge.git
cd Android-Phone-Code-Challenge
```

**2. Open in Android Studio**

File → Open → select the cloned folder. Let Gradle sync complete.

**3. Run**

Select a device (emulator or physical, API 26+) and click **Run ▶️**.

No API keys or environment variables are required — the iTunes Search API is public.

### Common issues

| Problem | Fix |
|---|---|
| Gradle sync fails | Check JDK version: File → Project Structure → SDK Location |
| App crashes on launch | Ensure emulator/device is API 26 or higher |
| No results returned | Verify internet connectivity; iTunes API requires network access |

</details>

---

## ⚠️ Known Limitations

These are intentional trade-offs made within the challenge timeframe, not oversights:

- **`SearchSongsUseCase` lives in `core-network`** — it should sit in a dedicated `core-domain` module. It was placed here to avoid the overhead of a fifth module within the challenge scope.

- **No `RemoteMediator`** — syncing between Paging 3's remote source and Room is handled manually in the repository. This can produce duplicate entries in edge cases (e.g., the same song appearing across two page boundaries). `RemoteMediator` is the correct long-term solution.

- **Basic `MediaPlayer` for audio** — the current player implementation uses Android's `MediaPlayer`, which lacks background playback, audio focus management, and notification controls. `ExoPlayer` + `MediaSession` would be required for production.

- **No cache invalidation strategy** — cached songs persist indefinitely. A production app would need TTL-based or user-triggered cache eviction.

---

## 🚀 Planned Improvements

- [ ] Full audio playback with `ExoPlayer` + `MediaSession` (background + notifications)
- [ ] `RemoteMediator` for proper Paging 3 + Room integration
- [ ] UI/integration tests with Espresso and Compose Test
- [ ] GitHub Actions CI pipeline (build + test on PR)
- [ ] Accessibility audit (content descriptions, touch targets, screen reader support)
- [ ] Pull-to-refresh on the songs list
- [ ] Transition animations between screens

---

## 👩🏻‍💻 About the Developer

**Laura Oliveira**  
Android Engineer | Kotlin Developer  

I build modern Android applications focusing on:

- Clean Architecture  
- Scalable systems  
- High-quality user experiences  

---

## 🔗 Connect with Me

🌐 **Portfolio:** https://laura-oliveira.github.io/  

📧 **Email:** laura.oliveira.tech@gmail.com  

---

⭐ If this project was useful or interesting, feel free to star the repository.

---

`#Android` `#Kotlin` `#JetpackCompose` `#CleanArchitecture` `#MobileDevelopment`
