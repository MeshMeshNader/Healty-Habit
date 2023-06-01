# Healthy Habit - Weekly Meal Planner

**Healthy Habit** is an Android mobile application designed to assist users in planning their weekly meals. It offers a range of features to make meal planning convenient and enjoyable. The application utilizes the [TheMealDB API](https://themealdb.com/api.php) as a source of meal information. 

## Features

### Meal of the Day

Get inspired by exploring a different meal suggestion every day. The **Meal of the Day** feature presents users with an arbitrary meal choice to provide them with new ideas for their weekly meal plans.

### Meal Search

Easily find meals based on specific criteria to suit your preferences and dietary needs. Users can search for meals using various options such as country, ingredient, or category. The search functionality allows for personalized meal discovery.

### Categories

Discover a wide variety of meal categories through the **Categories** feature. Users can explore different culinary themes and choose from a range of options that cater to their tastes. This helps in narrowing down meal options based on specific preferences.

### Popular Meals by Country

Experience popular meals from different countries around the world. The application showcases meals that are representative of various cuisines, enabling users to explore and appreciate diverse flavors.

### Favorite Meals

Save your favorite meals for quick and easy access. Users can add meals to their favorites or remove them as needed. The application employs local storage using Room, ensuring that favorite meals are readily available even without an internet connection.

### Weekly Meal Plan

Plan your meals for the week ahead with the **Weekly Meal Plan** feature. Users can view and add meals to their weekly meal plan, providing an organized approach to meal preparation and ensuring a well-balanced diet.

### Offline Support

Even without network connectivity, **Healthy Habit** allows users to access their favorite meals and view their current week's meal plan. This offline support ensures that users can manage their meals seamlessly, regardless of their internet connection status.

### Authentication

Securely authenticate to access additional features and personalize your experience. **Healthy Habit** offers simple login and sign-up functionality. Users can choose to authenticate using their social networking accounts such as Facebook, Google, or Twitter. Firebase Authentication is utilized to streamline the login process.

### Guest Mode

For users who prefer not to log in, the application provides a guest mode. This allows guests to access basic features such as viewing categories, searching for meals, and discovering the meal of the day without the need for authentication.

### Meal Details

Explore comprehensive details about each meal. Users can access the following information for a selected meal:
- Name of the meal
- Meal image
- Origin country
- Ingredients (preferably with accompanying images)
- Preparation steps
- Embedded video demonstration
- Add or remove the meal from favorites with a single tap

### Splash Screen

The application greets users with an engaging splash screen featuring captivating animations created using Lottie. This visually appealing element enhances the user experience and sets a positive tone from the moment the app is launched.

## Technologies Used

The Healthy Habit application incorporates the following technologies and concepts:

- Android development using Java
- MVP (Model-View-Presenter) architectural pattern for improved code organization and maintainability
- TheMealDB API for accessing meal data
- Room database for efficient local storage of favorite meals
- Firebase Authentication for secure user authentication and social login capabilities
- SharedPreferences for caching user data to enable seamless access after successful login
- Lottie for implementing an eye-catching splash screen animation

## MVP Design

The MVP (Model-View-Presenter) architectural pattern has been adopted to structure the Healthy Habit application. This pattern provides clear separation of concerns, making the codebase more modular, maintain
