# TodoList-Web-App

# What I learned:

- backend: Java Servlets, MVC design, Postgresql querries and database Connection, online deployment to Heroku;
- frontend: HTML, CSS, JSP & JSTL, Bootstrap 4 & a bit of Javascript;

# How it works:

Entities:

User - identified by:

- uuid - randomly generated at the moment of the creation of the account associated with it;
- email - the email used for the creation of the account;
- pwd - the password used;

The above attributes are added to the DB in a table called login, table that has 3 columns: 
id (type UUID, PK), email (type VARCHAR 32), pwd (type VARCHAR 64).

Todo - identified by:

- id - which is the uuid of the user above;
- name - text that represents the item on the list;
- category - text that represents the category the name (item) mentioned above is included in.


The above attributes are added to the DB in a table called list, table that has 3 columns: 
id (type UUID, PK in this table, FK constraint with the id column from the login table), name  (type text), category (type text).


How does it work:

When a user enters the email and password associated with the account, in the login page, he/she is redirected to the homepage of the app.

In the back, the credentials (email, password) are checked against the existing email value and the hashed value of the password, in the database and if a match exists, then a new instance of that user, including the fields email, password and uuid is created, if not the user is redirected to the login and asked to retry or create an account. This new instance with all its fields, is set/put on session.

After the step above, when the user gets redirected to the homepage, the user can visualise the todo items associated with its uuid from the table list from the DB, the user can add or delete a todo list item.

Listing Todo:
-> Using the uuid of the particular user that is on session (is logged in), when the user is redirected to the homepage, the items from the list table in the DB, that are associated with its uuid (the uuid of the user on session matches the uuid in the id column in the list table), are querried and for each entry where the uuid in the id column, matches the uuid of the user on session, a Todo object is created (that has String type fields: name & category & due date) and then each one of the Todo objects is added to a List<Todo>. This list is iterated through and each Todo item is displayed.
-> The table can be sorted, by clicking on each column name;
-> The user can search in the table, using the search box available at the top, to quickly identify a Todo, by the details/text available in the "Description" column.

Adding Todo:
Using the uuid of the particular user that is on session (is logged in), when the user clicks on the Add Todo button, and after the user inputs the name of the item and category, the app adds the strings in the columns name & category & date and the user uuid to the id column in the list table in the database.

Deleting Todo:
Using the uuid of the particular user that is on session (is logged in), when the user clicks on the Delete button, the app checks/selects that particular todo item from the list table in the database, where the uuid in column id matches the uuid of the user on session and the id of the Todo from the ArrayList matches the id of the Todo in the database, and deletes that particular entry.

Edit Todo:
Using the uuid of the particular user that is on session (is logged in), when the user clicks on the Edit button, that is to the left of the Delete button, a user can edit the details of a particular Todo item, change that will cascade to the database.

User Actions:
A user also can delete or edit his account details by accessing the Account menu at the top of the page (in the nav bar in the navigation.jspf).
