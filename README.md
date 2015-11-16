## Restaurant API (vote system)

### Installation

```sh
$ git clone https://github.com/srybakov/restaurant-api restaurant-api
$ cd restaurant-api
$ mvn package
```

### Run

To run application please create local MySql database with 'test' name and root/root credentials.
You can set your own database properties. For that change properties in resource/application.properties file and run
```sh
$ mvn package
```
once again. 
All database schemas will be created automatically.

### Initial data

By default database contains following data:
####Users
 - admin/admin [ADMIN]
 - user1/user1 [USER]
 - user2/user2 [USER]
 - user3/user3 [USER]
 
####Restaurants
  - Dublis
  - Deveti
  - Boff

