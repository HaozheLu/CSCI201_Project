CREATE TABLE CSCI201_Project.Meals (
    idMeals INT AUTO_INCREMENT PRIMARY KEY,
    uname VARCHAR(45) NULL,
    mealName VARCHAR(255),
    consumedCals INT,
    FOREIGN KEY (uname) REFERENCES Users(uname)
);
