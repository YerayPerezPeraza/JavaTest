# JavaTest_Yeray
## Documentation JavaTest
## Details of the environment
+ Java versión: 11.0.2 
+ SDK: 11
+ External dependencies: com.google.code.gson:gson:2.8.5
+ IDE: IntelliJ IDEA Community Edition 2019.1.3
+ Documentation generated by JavaDoc in the JavaDoc directory
+ Git-repository: https://github.com/YerayPerezPeraza/JavaTest

## Class User

This class defines objects, which contain the information of different users.
This class has as fields the keys of the json file.
### List of fields: 
+ private String name;
+ private String surname;
+	private Boolean active;
+ private String email;
+ private String city;
+ private String creationDate;
### Class methods

+ builder:
~~~~
public Users() 
~~~~
constructor of the class without specifying parameters.
~~~~
public Users(String name, String surname, Boolean active, String email, String city, String creationDate).
~~~~
Constructor of the Users class specifying the user data.
- Methods getter y setter.

Allow to return or change the attribute values of the object.

## Class ProcessJava

This class defines objects with Java to analyze a Json file to obtain relevant information.
### Class methods

~~~~
public static void main(String[] args) 
~~~~
This is the main method where we process the different options that the user has and we call the different methods that allow the user to interact and see the information contained in the Json.
~~~~
LoadUser(java.lang.String file)	
~~~~
In this method instance Gson read the file and store it in an ArrayList of the user class.

~~~~
newUser(java.lang.String file)	
~~~~
Method to add a new user to the Json file.

~~~~
organizeUserForDate(java.lang.String file)
~~~~
In this method we organize the users by first creation date ascending and secondly descending.

~~~~
searchCity(char cityToSearch, java.lang.String file)	
~~~~
This method searches for all cities that start with a letter that is written.

~~~~
SearchActivite(java.lang.String file)
~~~~
This method lists the active users and the amount of them.
