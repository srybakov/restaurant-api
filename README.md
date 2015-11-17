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

To run application
```sh
$ java -jar target/restaurant-1.0-SNAPSHOT.jar
```
During application startup all database schemas will be created automatically.

### Initial data

By default database contains the following data:
####Users
 - admin/admin [ADMIN]
 - user1/user1 [USER]
 - user2/user2 [USER]
 - user3/user3 [USER]
 
The only 'user2' and 'user3' have remained vote. By default all other users have voted already. If you want to test vote system with more users please create additional users for that. 

####Restaurants
  - Dublis (0 votes by default)
  - Deveti (3 votes by default)
  - Boff (2 votes by default)

### API
 - Get most voted restaurant for today (user/admin access only) - returns a name and number of votes of a most voted restaurant for today or error message in case if no votes were received for today.
 - Get most voted restaurant (user/admin access only) - returns a name and number of votes of a most voted restaurant for all time or error message in case if no votes were received for all time.
 - Vote (user/admin access only) - vote for a restaurant. One user can vote the only one time per day.
 - Create restaurant (admin access only) - allow admin to create a new restaurant
 - Input restaurant menu (admin access only) - allow admin to input a restaurant menu for today.
 - Create user (admin access only) - admin can create a new user in vote system.

### API Examples (curl)
Get most voted restaurant:
```sh
$ curl -u user1:user1 http://localhost:8080/user/getMostVotedRestaurant

#Output example: {"message":"","restaurantName":"Deveti","voteNumber":3}
```

Get most voted restaurant for today:
```sh
$ curl -u user1:user1 http://localhost:8080/user/getMostVotedForTodayRestaurant

#Output example: {"message":"","restaurantName":"Boff","voteNumber":2}
```

Vote (Windows curl sytax):
```sh
curl -u user3:user3 -X POST -H "Content-Type: application/json" -d "{\"restaurantName\":\"Dublis\"}" http://localhost:8080/user/vote

#Output example: {"code":200,"message":"User has been voted successfully for restaurant 'Dublis'"}
```

Create user (Windows curl syntax) 
```sh
curl -u admin:admin -X POST -H "Content-Type: application/json" -d "{\"userName\":\"userName\",\"password\":\"password\",\"roles\":[\"USER\"]}" http://localhost:8080/admin/createUser

#Output example: {"code":200,"message":"User with specified parameters successfully created"}
```

Create restaurant (Windows curl syntax)
```sh
curl -u admin:admin -X POST -H "Content-Type: application/json" -d "{\"restaurantName\":\"New best restaurant\"}" http://localhost:8080/admin/createRestaurant

#Output example: {"code":200,"message":"Restaurant 'New best restaurant' created successful"}
```

Input menu (Windows curl syntax)
```sh
curl -u admin:admin -X POST -H "Content-Type: application/json" -d "{\"restaurantName\":\"Boff\", \"menu\":{\"Dish1\":100,\"Dish2\":200}}" http://localhost:8080/admin/inputCurrentMenuForRestaurant

#Output example: {"code":200,"message":"Input menu request successful. Restaurant name 'Boff'. Menu: '{Dish1=100.0, Dish2=200.0}'"}
```


