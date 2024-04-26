### Java Servlets Overview
List of .java files compiled for backend, along with arguments required from frontend, when called. 

## LoginServlet.java
# REQUIRES: (`loginUsername' or 'loginEmail') and `loginPassword' from request, called using POST

- Upon successful login, redirects to login_success page and passes username in URL. A failure redirects to login page and passes errorName=`wrong_credential' in URL. 

## SignupServlet.java
# REQUIRES: `firstName', `lastName', 'username', 'email', 'password' from request, called using POST
NOTE: Assumes that Confirm Email/Confirm Password is done on the front-end. 
- Checks that username/email aren't already in the database. If they are, redirects to login page and uses errorName=`duplicate'
- Upon successful login, redirects to login_success page and passes username in URL. A failure redirects to login page and passes errorName in URL. 

## DataEntry.java
# REQUIRES: `exerciseCals',`maxCals', `restriction' called using POST
NOTE: Restriction should be a string, such as "Vegetarian", "Pescetarian", etc. 

- Enters (dietary=restriction, maxcals=maxCals, exerciseCals=exerciseCals) into the database. 

## MealEntry.java
# REQUIRES: `mealName' and 'caloriesConsumed' called using POST
- 

## ExerciseEntry.java
# REQUIRES: `workoutName' and 'caloriesBurned' called using POST
- 

## Recommendation.java
# REQUIRES: 
- 
