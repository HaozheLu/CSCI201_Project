CREATE TABLE CSCI201_Project.Preferences (
    idPreferences INT AUTO_INCREMENT PRIMARY KEY,
    idUsers INT,
    Dietary VARCHAR(255),
    maxCals INT,
    exerciseCals INT,
    FOREIGN KEY (idUsers) REFERENCES Users(idUsers)
);
