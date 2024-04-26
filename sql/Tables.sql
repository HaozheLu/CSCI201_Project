-- Update Users table
CREATE TABLE CSCI201_Project.Users (
  idUsers INT NOT NULL AUTO_INCREMENT,
  uname VARCHAR(45),
  pword VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  fname VARCHAR(45) NULL,
  lname VARCHAR(45) NULL,
  PRIMARY KEY (idUsers)
);

-- Update Preferences table with idUsers as foreign key
CREATE TABLE CSCI201_Project.Preferences (
    idPreferences INT AUTO_INCREMENT PRIMARY KEY,
    idUsers INT,
    dietary VARCHAR(255),
    maxCals INT,
    exerciseCals INT,
    FOREIGN KEY (idUsers) REFERENCES Users(idUsers)
);

-- Update Meals table with idUsers as foreign key
CREATE TABLE CSCI201_Project.Meals (
    idMeals INT AUTO_INCREMENT PRIMARY KEY,
    idUsers INT,
    mealName VARCHAR(255),
    consumedCals INT,
    FOREIGN KEY (idUsers) REFERENCES Users(idUsers)
);

-- Update Workouts table with idUsers as foreign key
CREATE TABLE CSCI201_Project.Workouts (
    idWorkouts INT AUTO_INCREMENT PRIMARY KEY,
    idUsers INT,
    workoutName VARCHAR(255),
    burnedCals INT,
    FOREIGN KEY (idUsers) REFERENCES Users(idUsers)
);
