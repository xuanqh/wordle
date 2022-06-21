# Wordle-Android
Wordle Application for coding demo.

## Mobile App Architecture

## Introduction

Mobile application follows the design M-V-VM design pattern.

The project's architecture and technologies can be represented on the illustration:


## View

The view is responsible for defining the structure, layout, and appearance of what the user sees on the screen.

## View-Model

The view model acts as an intermediary between the view and the model, and is responsible for handling the view logic. Typically, the view model interacts with the model by invoking methods in the model classes. The view model then provides data from the model in a form that the view can easily use. The view model retrieves data from the model and then makes the data available to the view.

## Model

This is a part of the model in MVVM. This is responsible to get the data request and communicate to remote and local data sources.

Files/Folders hierarchy structure

## Introduction

Implementation of M-V-VM design pattern. We would keep grouping project files based on features. As it makes the code more understandable and readable.

## Main package

com.restaff.wordle

## Base package

com.restaff.wordle.base
It contains base classes like screen, components, etc.

## Data package

com.restaff.wordle.data
It contains all the models and services of app.

## Domain package
com.restaff.wordle.domain
The Domain layer contains all the domain rules of app.

## Feature package
com.restaff.wordle.presentation.{feature_name}.*
This contains features of the application. We should follow for each screen:

- views
- viewmodels
- activities
- fragments
- adapters

For example: We have word game features. Then we should have the file and folder hierarchy:
com.restaff.wordle.presentation.wordgame
 - CharView.kt
 - WordGameAdapter.kt
 - WordleGameActivity.kt
 - WordleGameViewModel.kt

## Build and Installation
- Step1: Download and decompress the project source code.
- Step2: Download and install Android Studio.
- Step3: Run Android Studio.
- Step4: On Android Studio then Click Open Android project then navigation to project folder to import the project.
- Step5: Run App

Or to build and run the desktop application, run the following command
# ./gradlew run
