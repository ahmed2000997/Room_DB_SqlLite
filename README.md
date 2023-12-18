What is SQLite Room?
SQLite Room is an Android Library that provides an abstraction layer over SQLite to facilitate database operations and management in Android applications.

How to Use SQLite Room in Your Project:

Add the Room dependency to your project's build.gradle file:

gradle
Copy code
dependencies {
    implementation "androidx.room:room-runtime:2.4.0"
    annotationProcessor "androidx.room:room-compiler:2.4.0"
}
Person Class (Entity):
Annotated with @Entity(tableName ="Person"), this class represents a table in the database named "Person".
Attributes like id, name, and age correspond to columns in the table.
@PrimaryKey(autoGenerate = true) annotates the id field as the primary key with auto-generation.
PersonDAO Interface:
Annotated with @Dao, this interface defines database operations through annotated methods.
Methods like addPerson, updatePerson, deletePerson, and getAllPerson annotate SQL queries.
PersonDatabase Class:
An abstract class extending RoomDatabase represents the Room database.
Annotated with @Database(entities = {Person.class}, version = 1), it defines the entities and database version.
Contains an abstract method getPersonDAO() returning the PersonDAO interface.
MainActivity:
Handles the UI interactions and database operations.
Initializes the Room database (PersonDatabase) using Room.databaseBuilder().
Defines a Callback to handle database creation and opening.
Sets OnClickListener for buttons (save and get) to add and retrieve data respectively.
addPersonInBackground() and getaddPersonInBackground():
Utilizes ExecutorService to run database operations asynchronously.
Performs database operations like adding a person (addPerson) and retrieving all persons (getAllPerson).
Uses a Handler to update the UI with fetched data using Toast.
Improvements:
Ensure unique references for name and age EditText fields in onClick method (age = age.getText().toString()).
Add error handling for database operations and consider closing the ExecutorService after use.
This code demonstrates how to create a SQLite Room database, define entities, perform CRUD operations using DAO methods, and handle database operations asynchronously in an Android app. Ensure proper error handling and resource management for robustness and efficiency.
