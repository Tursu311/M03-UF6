Java Repository
===============

This repository contains Java files for a program that interacts with a database. The program allows managing candidates, provinces, and autonomous communities.

Files
-----

*   `Main.java`: The main class that contains the program's entry point and menu functions.
*   `DAO` package:
    *   `CandidatDAO.java`: Data Access Object class for managing candidates in the database.
    *   `CandidatureDAO.java`: Data Access Object class for managing candidatures in the database.
    *   `ProvinciesDAO.java`: Data Access Object class for managing provinces in the database.
    *   `Comunitats_autonomesDAO.java`: Data Access Object class for managing autonomous communities in the database.
*   `Objecte` package:
    *   `Candidat.java`: Class representing a candidate object.
    *   `Comunitats_autonomes.java`: Class representing an autonomous community object.
    *   `Provincies.java`: Class representing a province object.

Usage
-----

1.  Compile the Java files.
2.  Ensure you have a MySQL database set up and running.
3.  Update the database connection details in the `getConnection()` method of the `Main` class.
4.  Run the compiled Java program.
5.  Use the provided menu options to interact with the database:
    *   Option 1: Candidatures - Perform various operations related to candidatures.
    *   Option 2: Provincies - Perform various operations related to provinces.
    *   Option 3: Comunitats Autonomes - Perform various operations related to autonomous communities.
    *   Option 4: Exit the program.

Please note that the database connection details (URL, username, and password) may need to be modified according to your specific database setup.