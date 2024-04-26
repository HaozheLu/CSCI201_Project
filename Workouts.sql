CREATE TABLE CSCI201_Project.Workouts (
    idWorkouts INT AUTO_INCREMENT PRIMARY KEY,
    uname VARCHAR(45) NULL,
    workoutName VARCHAR(255),
    burnedCals INT,
    FOREIGN KEY (uname) REFERENCES Users(uname)
);