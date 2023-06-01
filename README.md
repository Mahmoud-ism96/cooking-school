# Cooking School

Cooking School is an Android application designed to provide users with a comprehensive cooking experience. With a wide range of features and functionalities, Cooking School aims to inspire users in the kitchen, help them discover new recipes, and make their cooking journey enjoyable and convenient.


## Table of Contents
- [Screenshots](#screenshots)
- [Features](#features)
- [API Reference](#api-reference)
- [Technologies Used](#technologies-used)
- [MVP Architecture Pattern](#mvp-architecture-pattern)
- [Installation](#installation)
- [Contributing](#contributing)
- [License](#license)

## Screenshots
![Untitled-1 copy 2](https://github.com/Mahmoud-ism96/my-cook/assets/66648923/990e5169-062d-4ef6-bf35-f47366031826)

## Features

- **Meal of the Day**: Users can view an arbitrary meal for inspiration.
- **Search Functionality**: Users can search for meals based on country, ingredient, and category.
- **Categories**: Display a list of available categories for users to choose from.
- **Popular Meals by Country**: Users can explore and discover popular meals from different countries.
- **Favorite Meals**: Users can add meals to their favorites list and remove them. Local storage is implemented using Room for seamless offline access.
- **Data Synchronization and Backup**: User data, including favorite meals and weekly meal plans, can be synchronized and backed up using Firebase Realtime Database.
- **Weekly Meal Planning**: Users can plan and organize their meals for the current week.
- **Offline Support**: Users can view their favorite meals and the current week's meal plan even without an internet connection.
- **Authentication**:
  - Simple Login and Sign Up: Users can create an account and log in using their email and password. Firebase Authentication handles the authentication process.
  - Social Networking Authentication: Users can choose to log in using their social networking accounts such as Facebook, Google, or Twitter. Firebase Authentication is integrated for this feature.
  - Guest Mode: Users can access limited features of the app without authentication, including viewing categories, searching for meals, and exploring the meal of the day.

## API Reference
```https://themealdb.com/api.php```

## Technologies Used

- **Java**: The main programming language used for the Android application.
- **Lottie**: Used for adding beautiful and engaging animations to the UI.
- **Retrofit**: A type-safe HTTP client for making API requests and handling responses.
- **Glide**: A fast and efficient image loading library for loading recipe images.
- **Room**: An Android library for local data storage and managing SQLite databases.
- **Firebase Authentication**: Used for user authentication and managing user accounts.
- **FireStore Database**: Used for storing and retrieving recipe data.
- **Circle ImageView**: A custom ImageView library for displaying circular images.
- **Carousel Layout Manager**: Used for creating a carousel-like layout for displaying cooking tutorials.
- **YouTube Player API**: Integrated for playing cooking tutorial videos within the app.
- **RxJava**: A reactive programming library used for asynchronous and event-based programming.

## MVP Architecture Pattern
Cooking School follows the Model-View-Presenter (MVP) architecture pattern for separating the presentation layer from the business logic and data layer. The MVP pattern consists of three main components:

**Model**: The data layer of the application that handles data operations such as retrieving and storing data. In Cooking School, the model component is responsible for retrieving recipe data from the API and storing user data using Room and Firebase Realtime Database.

**View**: The user interface layer that displays data and handles user input. In Cooking School, the view component is responsible for displaying recipe and user data to the user and handling user interactions.

**Presenter**: The middleman between the view and the model component that handles business logic and acts as a mediator between the two. In Cooking School, the presenter component is responsible for retrieving recipe data from the model component and passing it to the view component for display. The presenter also handles user input from the view and updates the model accordingly.

The MVP pattern provides several benefits, such as:

Separation of concerns: The MVP pattern separates the presentation layer from the business logic and data layer, making it easier to maintain and modify the application.

**Testability**: The MVP pattern makes it easier to test the application since the business logic is separated from the view layer.

**Scalability**: The MVP pattern makes it easier to add new features and modify existing ones since the code is organized into separate components.

Cooking School's implementation of theMVP pattern can be seen in the code structure, where each activity or fragment has a corresponding presenter and model class. The presenter class handles business logic and communicates with the model to retrieve or store data, while the view class handles UI updates and user interactions and communicates with the presenter.

Overall, the MVP pattern helps Cooking School to provide a scalable, testable, and maintainable architecture, making it easier to develop and improve the application over time.

## Installation

1. Clone the repository:
   
   ```git clone https://github.com/your-username/cooking-school.git```

2. Open the project in Android Studio.

3. Build and run the project on an Android emulator or a physical device.

## Contributing

Contributions to Cooking School are welcome! If you would like to contribute to the project, please follow these steps:

1. Fork the repository and clone it to your local machine.

2. Create a new branch for your feature or bug fix.

3. Make your changes and ensure that the project builds successfully.

4. Commit your changes with descriptive commit messages.

5. Push your branch to your forked repository.

6. Open a pull request, describing your changes and the motivation behind them.
