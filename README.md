# android_template

## 🚀 PreetBase – Android Kotlin Template

A scalable Android template built using Kotlin + XML + ViewBinding following MVVM architecture and clean coding principles.

This project is designed to serve as a reusable base for future Android applications while strengthening Kotlin fundamentals.

### 📌 About This Project

PreetBase is a custom-built Android template created to:

   - Master Kotlin by building architecture from the ground up
   - Avoid dependency on third-party starter templates
   - Follow clean, scalable, production-level structure
   - Serve as a reusable foundation for future Android projects

This project reflects strong understanding of Android fundamentals, architecture design, and Kotlin best practices.

 
### 🛠 Tech Stack

   - Language: Kotlin
   - UI: XML + ViewBinding
   - Architecture: MVVM
   - Async Handling: Kotlin Coroutines
   - State Management: Sealed Classes
   - Navigation: Custom centralized Navigator
   - Data Passing: Intent + Parcelable
   - Project Structure: Modular & clean package hierarchy

### 🏗 Architecture Philosophy

This template follows:

   - ✅ MVVM (Model-View-ViewModel)
   - ✅ Repository Pattern
   - ✅ Single Responsibility Principle
   - ✅ Separation of Concerns
   - ✅ Scalable Navigation Design
   - ✅ Reusable Base Components

The goal is not just to “make it work” — but to make it maintainable and extensible.

### 🏗 Architecture Overview

The project follows a clean and modular structure:
```
com.preetbase.app
│
├── base          → BaseActivity, BaseViewModel
├── ui            → Splash, Login, Signup
├── repository    → Business logic layer
├── network       → Network state handling (Sealed class)
├── utils         → Navigator, AppKeys
└── model         → Data models (Parcelable)
```

### ✅ Features Implemented
**🔹 Base Setup**

   - Generic BaseActivity<VB : ViewBinding>
   - Common loading dialog handling
   - Reusable structure for all activities
   - Centralized lifecycle handling

**🔹 ViewModel Layer**

   - BaseViewModel with CoroutineScope
   - Exception handling
   - Clean separation of UI & business logic

**🔹 State Management**

   - NetworkResult sealed class:
      - Loading
      - Success
      - Error

**🔹 Repository Pattern**

   - AuthRepository implementation
   - Simulated suspend API calls
   - Business logic separated from ViewModel

**🔹 Navigation System**

Centralized Navigator utility supports:

   - Simple navigation
   - Finish current activity
   - Clear back stack
   - Passing single value
   - Passing multiple values (vararg Pair)
   - Passing Parcelable object

**🔹 Screens Implemented**

   - Splash Screen (Coroutine-based delay)
   - Login Screen (MVVM integrated)
   - Signup Screen

### 🧠 Kotlin Concepts Applied

   - Generics
   - Sealed Classes
   - Coroutines (launch, delay, suspend)
   - inline + reified
   - vararg
   - Singleton (object)
   - @Parcelize
   - Function references (::inflate)

### 🎯 Project Goals

   - Build a reusable Android template
   - Strengthen Kotlin understanding
   - Follow scalable architecture
   - Avoid third-party template dependency
   - Prepare production-ready foundation

### 🚀 Future Improvements

   - Migrate to StateFlow
   - Add Hilt (Dependency Injection)
   - Retrofit Integration
   - BaseFragment implementation
   - Global error handler
   - Logging system

### 📦 Why This Template?

Instead of using ready-made templates, this project is built from scratch to:
   - Fully understand Kotlin
   - Design flexible navigation
   - Implement clean architecture
   - Create long-term reusable foundation

### 🚀 Latest Enhancements

   - Upgraded Signup screen to full Material Design (TextInputLayout + MaterialButtons)
   - Added Password visibility toggle
   - Implemented Confirm Password match validation
   - Built dynamic Password Strength Indicator (Weak / Medium / Strong)
   - Progress bar with dynamic color change
   - Added DOB DatePicker with 18+ age validation
   - Created reusable utility classes:
   - PasswordStrengthUtil
   - AgeUtil
   - Improved MVVM validation structure using ValidationResult & FieldType
   - Enhanced UX with structured error handling and cleaner UI design


### 📜 License

This project is open for learning and reuse. 