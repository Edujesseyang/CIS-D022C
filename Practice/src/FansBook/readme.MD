# Program Documentation for FansBook Launcher

## Overview
The `FansBook` application is a simple social platform program that allows users to create accounts, log in, and interact with other users. It provides basic functionalities such as creating new users, logging in, editing profiles, searching for other users, and managing a friend list. The main program logic is contained within the `Launcher` class.

## Main Components
### 1. **Launcher Class**
This is the main class that runs the program and handles user interactions through a menu-driven interface.

### 2. **User Class**
Represents individual users in the system with attributes such as name, password, contact information, and personal details. Users can also have a list of friends.

### 3. **UserDB Class**
Serves as a database to store and manage `User` objects. Provides methods for adding, finding, and checking for the existence of users.

## Key Functionalities
### 1. **Main Menu Loop**
The program starts with a main menu that offers the following options:
- **Log in**: Authenticates existing users.
- **Sign up**: Allows new users to create an account.
- **Help**: Provides a brief overview of the program.
- **Exit**: Exits the program.

### 2. **User Authentication**
The `login` method prompts the user for a username and password. If the credentials match an existing user, the login is successful; otherwise, an error message is shown.

### 3. **User Profile Management**
Logged-in users can access the `loginMenu`, which provides the following options:
- **Edit Profile**: Change password, personal information, or contact details.
- **Search Another User**: Look up other users by username and potentially add them to the friend list.
- **Print User Information**: View the current user's profile.
- **Friend List Management**: Access the `friendMenu` to view, delete, or view details of friends.
- **Log Out**: End the session.

### 4. **Friend List Management**
The `friendMenu` allows users to:
- View their friend list.
- Delete a friend by name.
- View information about a specific friend.

### 5. **User Search and Interaction**
The `searchUser` method enables users to search for other registered users by their username. If found, users can:
- Add the user to their friend list.
- View the user's profile.

### 6. **Profile Editing**
The `editInfo` method provides options to change the password, edit personal information, or update contact details.

### 7. **User Creation**
The `createUser` method facilitates the creation of new user accounts with a unique username and a password that does not exceed 10 characters.

### 8. **Help Section**
The `help` method displays a description of the program's capabilities and how users can interact with it.

## Code Structure
### Methods and Descriptions
- **`public static void main(String[] args)`**: The entry point of the program that initializes the default users and runs the main menu loop.
- **`public static int selection(int numOfChoice)`**: Captures user input and ensures it is within the range of available menu options.
- **`public static void login(Scanner sc)`**: Handles user login by checking usernames and passwords.
- **`public static void loginMenu(Scanner sc)`**: Provides options for logged-in users to interact with their accounts.
- **`public static void friendMenu(Scanner sc)`**: Manages friend list interactions.
- **`public static void searchUser(Scanner sc)`**: Searches for a user and allows interactions like adding them as a friend.
- **`public static void editInfo(Scanner sc)`**: Enables users to update their profile details.
- **`public static void logout()`**: Logs the user out and resets the current user.
- **`public static void createUser(Scanner sc)`**: Guides new users through account creation.
- **`public static void help()`**: Displays program help information.

## Usage Example
1. **Sign Up**: Enter your desired username and password (max 10 characters).
2. **Log In**: Provide your username and password to access your profile.
3. **Edit Profile**: Choose to change your password or update your information.
4. **Search Users**: Search for friends and add them to your friend list.
5. **Manage Friends**: View, delete, or check friend details.
6. **Log Out**: End your session when done.

## Default Users for Testing
- **Chloe**: A user with basic profile information.
- **Lucky**: A cat profile for testing.
- **Jesse**: A software engineering student.
- **Mike**: A user who enjoys running.

## Notes
- Passwords are limited to a maximum of 10 characters.
- The program currently runs in a simple console environment.

This documentation should provide sufficient understanding for further development, testing, and usage of the `FansBook` program.
