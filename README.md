# Chat Genie 1.0

## A two clients chatting application

### Developed by

Syed Faateh Sultan Kazmi (BCSF18M022)

### Usage

Please create a MySQL database by name "chat_genie" and import the file "chat_genie.sql" in PHPMyAdmin (in XAMPP) and connect IDE to database before starting this program.

### Details

This Java assignment is created using socket programming and JDBC. 

Basically, when program starts from Driver.java, it launches a server from Server.java in a thread and launch the WelcomeScreen.java which will give the login and signup forms. All the logins and signup actions are validated and handled by class WelcomeScreenHandler.java. It will use the class UserDAO.java which will connect to MySQL database. If the connection fails, it will popup a dialog box and exit after 3 seconds. If connection succeeds, it will query the database for corresponding user actions. After two users login, welcome screen will disappear and two chatboxes will be opened which will take communicate with each other. For some fun effects, I've created a RandomColorGenerator.java class which will randomly colorize the chatboxes and message bubbles.

Thank You!

- Assignment Submitted to Sir Anzar Ahmed on December 21, 2020