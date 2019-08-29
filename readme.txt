***SFTDB Readme***

###Files
/source
---sftdb.java
/executable
---sftdb.jar
readme.txt


###How to Run
-> Run the executable jar file by entering the following code to the command line:
	java -jar sftdb.jar
	
  *Make sure you have java installed and added to the path environment in your computer, otherwise you cannot start the program
  *Also make sure you are in the right directory in order to run the jar file
	
-> When the program started you must see the menu as below.

Welcome to SFTDB
(1) Create a type
(2) Delete a type
(3) List all types
(4) Create a record
(5) Delete a record
(6) Search for a record(by primary key)
(7) List all records of a type
(8) Exit
Please choose what operation you want to do above (1-8).

-> There are 8 different options in this menu.

(1) Create a type
--> When you enter 1, the program wants from you to enter type name, type's field count and field names respectively.
After getting the data, cntMan.dat file will be updated to store type values.

(2) Delete a type
-->When you enter 2, the program wants from you to enter a type name to delete. After getting the type name, the program will
delete the type and update cntMan.dat file. type_name.dat will be removed.

(3) List all types
-->When you enter 3, the program will list all types' names.

(4) Create a record
-->When you enter 4, the program wants from you to enter the type name that you want to create a record. After that the program
wants you to enter data to the fields of the selected type. After that type_name.dat file will be updated.

(5) Delete a record
-->When you enter 5, the program wants from you to enter the type name that you want to delete a record. After that the program
wants you to enter the primary key of the record and delete the record. type_name.dat file will be updated.

(6) Search for a record(by primary key)
-->When you enter 6, the program wants from you to enter the type name that you want to list record. After that the program wants
from you to enter primary key of the record you are looking for. If the record exist, the program will print it.

(7) List all records of a type
-->When you enter 7, the program wants from you to enter th type name and list all records of that type.

(8) Exit
-->When you enter 8, the program will be closed.
