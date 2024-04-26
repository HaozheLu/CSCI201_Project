### Users SQL Table 

In MySQL, execute queries given by Users.sql (first), and then the others. The tables are normalized using `uname`, which should be unique when logging in. 

## Users.sql: 
- `idUsers`: Primary key, used to index the table
- `uname`: username
- `pword`: password
- `email`: registered email
- `fname`: User's first name
- `lname`: User's last name

## Preferences.sql:
- `idPreferences`: Primary key, used to index the table
- `idUsers`: same as Users.sql, designated foreign key
- `dietary`: dietary restrictionn description
- `maxCals`: ideal maximum calories remaining
- `exerciseCals`: ideal minimum calories left to be burned

## Meals.sql
- `idMeals` Primary key, used to index the table
- `idUsers`: same as Users.sql, designated foreign key
- `mealName`: name of the meal, ex. *Breakfast*, *Snack*, ...
- `consumedCals`: calories consumed in this meal


## Workouts.sql
- `idWorkouts`: Primary key, used to index the table
- `idUsers`: same as Users.sql, designated foreign key
- `workoutName`: name of the workout, ex. *Legs*, *Cardio*, ...
- `burnedCals`: number of calories burned in workout
