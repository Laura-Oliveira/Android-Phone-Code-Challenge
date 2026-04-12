<div align="center"> 
	
# 🎵 Android Songs App – Moises Code Challenge

</div>

<div align="center">

![Status](https://img.shields.io/badge/Status-Development-00C2A8?style=plastic)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple?style=plastic&logo=kotlin)
![Platform](https://img.shields.io/badge/Android-Jetpack%20Compose-green?style=plastic&logo=android)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-blue?style=plastic)

</div>

<div align="center">

![Coroutines](https://img.shields.io/badge/Coroutines-Flow-orange?style=plastic)
![Room](https://img.shields.io/badge/Database-Room-3DDC84?style=plastic)
![Paging](https://img.shields.io/badge/Pagination-Paging3-blue?style=plastic)
![License](https://img.shields.io/badge/license-MIT-blue.svg?style=plastic)

</div>

---

<p align="center">
  <img src="https://github.com/Laura-Oliveira/Android-Phone-Code-Challenge/blob/develop/app/src/assets/readme.png" width="800"/>
</p>

---

## 📑 Table of Contents

- [🚀 Project Overview](#-project-overview)
- [🎯 Challenge Goals](#-challenge-goals)
- [🛠 Tech Stack](#-tech-stack)
- [🏗 Architecture](#-architecture)
- [📂 Project Structure](#-project-structure)
- [✨ Features](#-features)
- [📡 API Integration](#-api-integration)
- [💾 Offline-First Strategy](#-offline-first-strategy)
- [🧪 Testing](#-testing)
- [▶️ Running the App](#️-running-the-app)
- [🧠 Engineering Decisions](#-engineering-decisions)
- [🚀 Improvements](#-improvements)
- [🌍 Why this Project Matters](#-why-this-project-matters)
- [👩🏻‍💻 About the Developer](#-about-the-developer)
- [🔗 Connect with Me](#-connect-with-me)

---

## 🚀 Project Overview

This project is an Android application built as part of a technical challenge inspired by real-world product requirements.

The app allows users to:

- 🔍 Search songs using the iTunes API  
- 🎧 View song details and play previews  
- 📀 Explore album information  
- 💾 Access recently played songs (offline-first experience)

The focus of this project is not only functionality, but **production-ready architecture and engineering quality**.

---

## 🎯 Challenge Goals

This project was designed to demonstrate:

- Clean and scalable Android architecture
- Real-world data flow (API + Cache)
- Modern Android development practices
- High-quality, maintainable Kotlin code
- Strong UX with responsive UI states

---

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM + Clean Architecture**
- **Coroutines & Flow**
- **Paging 3**
- **Navigator**
- **Room Database**
- **Retrofit**
- **Hilt (Dependency Injection)**
- **Git/Github**
- **Android Studio Otter | 2025.2.1 Patch 1**

---

## 🏗 Architecture

The project follows **MVVM + Modularization + Clean Architecture principles**, ensuring separation of concerns and scalability.

### Layers:

- **Modules**
  - app
  - playlist
- **Presentation**
  - Compose UI
  - ViewModels
- **Domain**
  - Business Models
- **Data**
  - Repository Implementations
  - API (Remote)
  - Room (Local)

<!--
	  - UseCases
-->
---

## 📂 Project Structure

```
app/
├── core/
│   ├── navigation/
│   └── ui.theme/
│   ├── SplashScreen.kt
│
playlist/
├── songsPlaylist/
│   ├── SongsListScreen.kt
│   └── SongsAlbumScreen.kt
│   └── SongsViewModel.kt
│
├── musicPlayer/
│   ├── musicPlayerScreen.kt
│   └── musicPlayerViewModel.kt
│
├── data/
│   ├── remote/
│   	└── APIItunes.kt
│   	└── RetrofitAPI.kt
│   └── local/
│   	└── OfflineData.kt
│
└── gradle/
  	└── build.gradle.kts (Project :Songs_App)
  	└── build.gradle.kts (Module :app)
  	└── build.gradle.kts (Module :playlist)
  	└── settings.gradle.kts (Project Settings) 
  	└── libs.version.toml (Version Catalog "libs")  

```

---

## ✨ Features

- ▶️ Animated splash screen
- 🎵 Songs list 
- 🎵 Song details (player UI)  
- 📀 Album view  
- ⚡ Smooth UI with Compose  
- ❌ Error handling & loading states  
- 💾 Recently played songs (cached)

<!-- 

- 🔍 Song search with pagination   
- 🧾 Bottom sheet for actions  
- 🔄 Pull to refresh *(optional enhancement)* 


-->
---

## 📡 API Integration

The app integrates with the **iTunes Search API**, providing:

- Song search by text input
- Pagination support via offset
- Lightweight media previews

---

## 💾 Offline-First Strategy

The app implements an **offline-first approach**:

- API results are cached in **Room**
- Local database acts as **single source of truth**
- Recently played songs are always available offline

👉 This mimics real-world production apps and improves UX reliability.

---

## 🧪 Testing

Basic unit tests were implemented for:

- ViewModel logic  
- UseCases  

The goal was to validate:

- Data flow correctness  
- Business logic reliability  

---

## ▶️ Running the App

<details>
<summary>Click to expand instructions</summary>

### 1️⃣ Clone the repository

```bash
git clone https://github.com/your-repo/android-songs-app.git
```

### 2️⃣ Open in Android Studio

- Use latest stable version

### 3️⃣ Build and Run

- Select emulator or physical device
- Click Run ▶️

</details>

---

## 🧠 Engineering Decisions

- **MVVM + Clean Architecture**  
  → Ensures scalability and testability  

- **Paging 3**  
  → Efficient large data handling  

- **Room as Source of Truth**  
  → Enables offline-first UX  

- **Coroutines + Flow**  
  → Reactive and efficient async handling  

- **Complementary Color System (Teal + Coral)**  
  → Enhances visual hierarchy and interaction feedback  

---

## 🚀 Improvements

If more time was available, I would implement:

- Full media playback using ExoPlayer  
- Advanced caching with RemoteMediator  
- UI animations and transitions  
- Accessibility improvements  
- Increased test coverage (UI tests)  

---

## 🌍 Why this Project Matters

This project reflects how modern Android applications are built in real-world environments.

It demonstrates:

- 🧠 Strong architectural thinking  
- 📱 Production-ready Android skills  
- ✨ Attention to user experience  
- 🔧 Clean and maintainable code  

These are key qualities valued by product-driven engineering teams.

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

⭐ If you found this project interesting, feel free to **star the repository!**

---

#Android #Kotlin #JetpackCompose #CleanArchitecture #MobileDevelopment
