Java Repository
===============

This repository contains Java files for a program that interacts with a database. The program allows managing candidates, provinces, and autonomous communities.

Files
-----

*   `Main.java`: The main class that contains the program's entry point and menu functions. Is where we take user inputs to make the required aditions, deletions, or modifications.
*   `DAO` package:
    *   `CandidatDAO.java`: Data Access Object class for managing candidates in the database.
    *   `CandidatureDAO.java`: Data Access Object class for managing candidatures in the database.
    *   `ProvinciesDAO.java`: Data Access Object class for managing provinces in the database.
    *   `Comunitats_autonomesDAO.java`: Data Access Object class for managing autonomous communities in the database.
    *   `MunicipisDAO.java`: Data Access Object class for managing municipalities in the database.
    *   `PersonesDAO.java`: Data Access Object class for managing people in the database.
    *   `DAODB.java`: Data Access Object class for managing the DAO functions for the DAO classes.
*   `Objecte` package:
    *   `Candidat.java`: Class representing a candidate object.
    *   `Candidature.java`: Class representing a candidature object.
    *   `Comunitats_autonomes.java`: Class representing an autonomous community object.
    *   `Municipis.java`: Class representing a municipality object.
    *   `Persones.java`: Class representing a person object.
    *   `Provincies.java`: Class representing a province object.

Usage
-----

1.  Compile the Java files.
2.  Ensure you have a MySQL database set up and running.
3.  Update the database connection details in the `getConnection()` method of the classes.
4.  Run the compiled Java program.
5.  Use the provided menu options to interact with the database:
    *   `1`: Select a table to manage.
    *   `2`: Add, delete, or modify a table entry.
    *   `3`: Exit the program.

Please note that the database connection details (URL, username, and password) may need to be modified according to your specific database setup.


Notes:
------

Aside of making the basic CRUD operations, the program also allows to:

Count the number of candidates in a candidature.