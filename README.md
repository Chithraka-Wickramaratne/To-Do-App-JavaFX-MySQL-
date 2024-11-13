# To-Do App (JavaFX + MySQL)

A feature-rich To-Do application built with JavaFX and MySQL, designed with the Model-View-Controller (MVC) architecture. Users can register, log in, and manage their to-do tasks, all while ensuring data persistence through MySQL integration.

## Features
- **User Registration and Authentication:**
  - Users can register with their username, email, and password.
  - Auto-generated user ID is assigned to each registered user.
  - Users can log in with their username and password.
  
- **To-Do Management:**
  - **Add Tasks:** Users can add new tasks, which will appear in the task list on the left side of the interface.
  - **Update Tasks:** Users can select a task to edit and update it.
  - **Delete Tasks:** Users can delete selected tasks.

## Technologies Used
- **JavaFX**: For building the graphical user interface.
- **MySQL**: For storing user data and to-do tasks persistently.
- **MVC Architecture**: For clear separation of concerns (Model, View, Controller).

## Setup Instructions

### Prerequisites
- Install [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or later).
- Install [MySQL](https://dev.mysql.com/downloads/installer/) (Make sure MySQL server is running).
- Set up a MySQL database with the following tables:
  - **Users** (user_id, user_name, email, password)
  - **Todos** (id, user_id, description)

### Steps to Run the Application

1. **Clone this repository:**
   ```bash
   git clone https://github.com/Chithraka-Wickramaratne/To-Do-App-JavaFX-MySQL-.git
   cd To-Do-App-JavaFX-MySQL
