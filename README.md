## Restaurant API (vote system)

### User story
Design and implement a JSON API using Hibernate/Spring/SpringMVC without frontend.
 
The task is:
 
Build a voting system for deciding where to have lunch.
 
2 types of users: admin and regular users
Admin can input a restorant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restorant provides new menu each day.

### Installation

```sh
$ git clone https://github.com/srybakov/restaurant-api restaurant-api
$ cd restaurant-api
$ mvn package
```

### Run

Before run please create local MySql database with 'test' name and root/root credentials.
You can set your own database properties. For that change properties in resource/application.properties file and run
```sh
$ mvn package
```
once again. 
All database schemas will be created automatically.

To run application
```sh
$ java -jar target/restaurant-1.0-SNAPSHOT.jar
```
### Initial data

By default database contains the following data:
####Users
 - admin/admin [ADMIN]
 - user1/user1 [USER]
 - user2/user2 [USER]
 - user3/user3 [USER]
 
####Restaurants
  - Dublis
  - Deveti
  - Boff

The only 'user3' have remained vote. All other users voted by default. If you additionaly want to test vote system please create additional users for that. 

### API
 - Get most voted restaurant for today (user access only) - returns a name and number of votes of a most voted restaurant for today or error message in case if no votes were received for today.
 - Get most voted restaurant (user access only) - returns a name and number of votes of a most voted restaurant for all time or error message in case if no votes were received for all time.
 - Vote (user access only) - vote for a restaurant. One user can vote the only one time per day.
 - Create restaurant (admin access only) - allow admin to create a new restaurant
 - Input restaurant menu (admin access only) - allow admin to input a restaurant menu for today.
 - Create user (admin access only) - admin can create a new user in vote system.

### Examples (curl)


