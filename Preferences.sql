CREATE TABLE CSCI201_Project.Preferences (
    idPreferences INT AUTO_INCREMENT PRIMARY KEY,
    uname VARCHAR(45) NULL,
    dietary VARCHAR(255),
    maxCals INT,
    exerciseCals INT,
    FOREIGN KEY (uname) REFERENCES Users(uname)
);
