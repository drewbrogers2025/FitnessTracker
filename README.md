# KINETIC — Fitness Tracker

A modern Android fitness tracking app built with Jetpack Compose and Material Design 3.

## Features

- **Dashboard** — Personalized home with today's stats, recommended workout, and weekly activity chart
- **Workout Library** — Browse and filter 8+ workouts by category (HIIT, Strength, Cardio, Recovery)
- **Workout Detail** — Full exercise breakdown with sets, reps, and weights
- **Activity Tracking** — Live workout monitoring with heart rate and route visualization
- **Nutrition** — Calorie, macro, and water intake tracking
- **Progress** — Weekly charts and achievement badges
- **Profile** — User stats and settings

## Tech Stack

| | |
|---|---|
| Language | Kotlin 2.0.21 |
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose 2.7.7 |
| Image Loading | Coil 2.7.0 |
| Min SDK | API 33 (Android 13) |
| Target SDK | API 36 (Android 15) |

## Architecture

Single-activity app with route-based Jetpack Navigation Compose. Screens are pure Composables backed by local state. Data is served from a static `WorkoutData` object — ready for Room DB or API integration.

```
com.example.fitnesstracker/
├── MainActivity.kt
├── data/
│   └── Models.kt
├── ui/
│   ├── components/
│   │   └── BottomNavBar.kt
│   ├── screens/
│   │   ├── DashboardScreen.kt
│   │   ├── WorkoutLibraryScreen.kt
│   │   ├── WorkoutDetailScreen.kt
│   │   ├── ActivityTrackingScreen.kt
│   │   ├── NutritionScreen.kt
│   │   ├── ProgressScreen.kt
│   │   └── ProfileScreen.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
```

## Getting Started

1. Clone the repo
2. Open in Android Studio Hedgehog or later
3. Run on a device or emulator with API 33+

## Design

Dark-first theme with a neon green (`#CCFF00`) primary accent and deep charcoal (`#0D0F0C`) background.
