# Rest Backend APIs For Sakilla DataBase

Welcome to the Sakila Database Backend APIs!

This project provides REST APIs for accessing data in the Sakila Database. The Sakila Database is a sample database used by many developers to learn and practice working with databases. This project provides APIs for interacting with various tables in the Sakila Database such as Actor, Customer, Category, City, Film, Inventory, Language, Payment, Rental, Staff and Store.

****

## Documentation
[Postman RESTful API] (https://documenter.getpostman.com/view/26734931/2s93Y3uLe4)

## Technology Used
- JAX-RS (Jersey)
- JSON-B
- JAX-B
- Maven
- Lombok
- MapStruct
- Tomcat
- Jakarta persistance (Hibernate)
- MySql
- Postman

****

## Getting Started
To use this project.
- you need to download the Sakila Database from this link:
```
    https://www.sqliz.com/sakila/installation/ 
```
for more details about it you can visit this link
```
    https://downloads.mysql.com/docs/sakila-en.pdf
```
- clone the project
```
https://github.com/MarowaAdel28/Rest-Sakila-Backend-APIs.git
```
- go to the project directory
```
    cd Rest-Sakila-Backend-APIs
```
- Create db user and set the username and password values in the persistence.xml.
- Create db named sakila in your MySql Server.
- Run your tomcat apache server and then change the configuration of tomcat in pom.xml.
- Finally, deploy the application using the following maven command.

```
    mvn clean install tomcat7:deploy
```
After that, you can start using the APIs by sending HTTP requests to the endpoints specified below.

****

## Endpoints

The following endpoints are available in this project and the base url:

```
    http://localhost:8070/apis/rest
```

### Actor Resource
- `GET /actors` - Get all actors.
- `GET /actors/{id}` - Get actor by its ID.
- `GET /actors/search?name={name}` - search actors by its name.
- `POST /actors` - Add a new actor.
- `PUT /actors/{id}` - Edit existing actor by its ID.
- `DELETE /actore/{id}` - delete actor by its ID

### ActorFilm Resource
- `GET /actor_films/{id}` - Get all films for specific actor by ActorID.
- `GET /actor_films/filter?actorId={id}&language={languageId}` - Get all actor films by language.
- `GET /actor_films/filter?actorId={id}&rating={rating}` - Get all actor films by rating.

### Customer Resource
- `GET /customers` - Get all customers.
- `GET /customers/{id}` - Get customer by its ID.
- `GET /customers/active` - Get all active customers.
- `GET /customers/inactive` - Get all inactive customers.
- `GET /customers/{id}/rentals` - Get rental list for customer by its ID.
- `GET /customers/{id}/payment` - Get payment list for customer by its ID.
- `GET /customers/search?name={name}` - Search customers by its name.
- `POST /customers` - Add a new customer.
- `PUT /customers/{id}` - Edit existing customer by its ID.
- `DELETE //customers/{id}` - Delete customer by its ID. 

### Category Resource
- `GET /categories` - List all categories.
- `GET /categories/{id}` - Get category by its ID.
- `GET /categories/search?name={name}` - search category by its name.
- `POST /categories` - Add a new category.
- `PUT /categories/{id}` - Edit existing category by its ID.
- `DELETE /categories/{id}` - Delete category by its ID. 

### CategoryFilm Resource
- `GET /category_films/{id}` - Get list of films by category.

### City Resource
- `GET /cities/{id}` - Retrieve a city by its ID.
- `GET /cities` - Retrieve a list of all cities.

### Country Resource
- `GET /countries/{id}` - Retrieve a country by its ID.
- `GET /countries` -  Retrieve a list of all countries.
- `GET /countries/{id}/cities` - Retrieve a list of cities in a country by its ID.

### Film Resource
- `POST /films` - Add a new film.
- `DELETE /films/{id}` - Delete film by its ID.
- `GET /films` - Retrieve a list of films.
- `GET /films/{id}` - Retrieve a film by its ID.

### FilmText Resource
- `GET /films_text` - Retrieve a list of film text.
- `GET /films_text/{id}` - Retrieve a film text by its ID.

### Inventory Resource
- `GET /inventories` - Get all inventory
- `GET /inventories/{id}` - Get inventory item by ID
- `GET /inventories/filter?filmId={id}` - Get all inventory items for a specific film
- `GET /inventories/filter?store={id}` - Get all inventory items for a specific store
- `DELETE /inventories/{id}` - Delete inventory by ID

### Language Resource
- `GET /languages` - Get all languages
- `GET /languages/{id}` - Get language by ID
- `POST /languages` - Add a new language
- `PUT /languages/{id}` - Update language by its ID
- `DELETE /languages/{id}` - Delete language by its ID

### Payment Resource
- `GET /payments` - Get all payments
- `GET /payments/{id}` - Get payment by ID
- `DELETE /payments/{id}` - Delete payment by ID

### Rental Resource
- `GET /rentals` - Get all rentals
- `GET /rentals/{id}` - Get rental by ID
- `POST /rentals` - Add new rental
- `PUT /rentals/id` - Update rental by ID
- `GET /rentals/{id}/payment` - Get list of payment for rental by rentalID
- `DELETE /rentals/{id}` - Delete rental by ID

### Staff Resource
- `GET /staffs` - Get all staff members
- `GET /staffs/{id}` - Get staff member by ID
- `POST /staffs` - Add new staff member
- `PUT /staffs/{id}` - Update staff member by ID
- `DELETE /staffs/{id}` - Delete staff member by ID

### Store Resource
- `GET /stores` - Get all stores
- `GET /stores/{id}` - Get store by ID
- `GET /stores/{id}/staff` - Get staff list for a specific store
- `GET /stores/{id}/inventory` - Get inventory list for a specific store
- `GET /stores/{id}/customers` - Get customer list for a specific store
- `POST /stores` - Add new store
- `DELETE /stores/{id}`: Delete store by ID
