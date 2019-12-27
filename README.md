# enotes :notebook_with_decorative_cover:
**Enotes** is a web application for creating and managing personal notes. 

## Subject area
A user can create his account using the registration form. Then he will be able to log in with it. The
application will provide high notes management system. Notes have headers and bodies. In addition, they have priority marks, which can be Low, Middle or High.

## Definitions
- **User** - a customer of Enotes. To become a user you need to create an account using the registration form or using Rest API. Users can create and manage their notes, combine them into notebooks, etc;
- **Note** - a simple note, which includes header, body and priority mark (high, middle, low). It can be created, modified and deleted by a user. Also can be combined into a notebook;
- **Comment** - a comment for a note. Users can comment notes. Comments can be edited and deleted.
-**NotesGalleryView** - the main page for every user, this page shows all user's notes. Using this page users can view, edit and delete notes.

## Tech part :mountain_cableway:
- The main project language is Java. The project includes such Java frameworks and libraries: JDBC, Spring Boot, Lombok, AspectJ, Log4J2. For unit tests, the project uses JUnit, H2 database. Also, the project uses Thymeleaf;
- To represent front part I use Bootstrap 4 and jQuery (Bootstrap need it for some features);
- The project builds on the MVC pattern. But it also contains other patterns, such as Strategy, DAO, etc.
- To build the project I use Maven (at the first versions of the applications there was Gradle, but I decided to switch to Maven);
- The project uses MySQL Database as main, and H2 for testing. The main DB script [dbSetUp.sql](dbdbSetUp.sql);
- The project uses Apache Tomcat;

## Author :person_with_blond_hair:
name: Yevgeniy Kiprenko 
email: zhenyakiprenko@gmail.com
telegram: https://t.me/Jus7XV

**Note:** this README file will be updated regularly, to include more project information. I have a plan to add **diagrams** here.
