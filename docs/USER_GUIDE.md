# How to Use the JDBC Library

## Setup
1. Install Java from https://adoptium.net/
2. Clone the repo
3. Put the JDBC driver jar and auth dll in the lib/ folder
4. Make sure SQL Server Express is running

## Running the UI
Compile everything:
```
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar" -d out src\main\java\jdbc\connection\ConnectionConfig.java src\main\java\jdbc\connection\DatabaseConnection.java src\main\java\jdbc\exception\DatabaseException.java src\main\java\jdbc\exception\ConnectionException.java src\main\java\jdbc\dao\GenericDAO.java src\main\java\jdbc\dao\CustomerDAO.java src\main\java\jdbc\dao\ProductDAO.java src\main\java\jdbc\model\CustomerModel.java src\main\java\jdbc\model\ProductModel.java src\main\java\jdbc\ui\MainApp.java
```

Run:
```
java -cp "out;lib\mssql-jdbc-13.4.0.jre11.jar" "-Djava.library.path=lib" jdbc.ui.MainApp
```

## Running the Tests
Compile:
```
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar" -d out src\main\java\jdbc\connection\ConnectionConfig.java src\main\java\jdbc\connection\DatabaseConnection.java src\main\java\jdbc\exception\DatabaseException.java src\main\java\jdbc\exception\ConnectionException.java src\main\java\jdbc\dao\GenericDAO.java src\main\java\jdbc\dao\CustomerDAO.java src\main\java\jdbc\dao\ProductDAO.java src\main\java\jdbc\model\CustomerModel.java src\main\java\jdbc\model\ProductModel.java src\test\java\jdbc\CRUDIntegrationTest.java
```

Run:
```
java -cp "out;lib\mssql-jdbc-13.4.0.jre11.jar" "-Djava.library.path=lib" jdbc.CRUDIntegrationTest
```

## Using the UI
- The app has two tabs: Customers and Products
- Click a row to see the details in the form
- Fill in the form and click Add to add a new record
- Select a row and click Delete to remove it
- Click Refresh to reload the data
