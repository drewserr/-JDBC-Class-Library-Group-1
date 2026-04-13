z`# JDBC Connection Setup - Documentation

**Author:** Andrew Laboy  
**Version:** 1.0  
**Server:** localhost\SQLEXPRESS  
**Authentication:** Windows Authentication  
**Database:** Northwinds2024Student  

---

## Overview

These classes handle connecting to SQL Server Express using Windows Authentication. No username or password is needed — it uses your Windows login automatically.

---

## Classes

### ConnectionConfig.java
Stores the server, instance, and database name. Default values are set to localhost\SQLEXPRESS and Northwinds2024Student.

Key method:
- `getConnectionUrl()` — builds the full JDBC URL with integratedSecurity=true

### DatabaseConnection.java
Handles opening, reusing, and closing connections. Includes a pool of 5 connections.

Key methods:
- `getConnection()` — gets a connection (reuses from pool or creates new)
- `releaseConnection(conn)` — returns connection to pool
- `closeAllConnections()` — shuts down everything
- `testConnection()` — quick check if database is reachable

### DatabaseException.java / ConnectionException.java
Custom exceptions for database and connection errors.

---

## How to Use

```java
// Connect with defaults (localhost\SQLEXPRESS, Northwinds2024Student)
DatabaseConnection db = new DatabaseConnection();

if (db.testConnection()) {
    System.out.println("Connected!");
}

Connection conn = db.getConnection();
// ... run queries ...
db.releaseConnection(conn);

db.closeAllConnections();
```

---

## Required Files in lib/

- `mssql-jdbc-13.4.0.jre11.jar` — the JDBC driver
- `mssql-jdbc_auth-13.4.0.x64.dll` — required for Windows Authentication

Both must be in the `lib/` folder for the project to work.

---

## How to Compile and Run

```
javac -cp "lib/*" -d out src/main/java/jdbc/connection/*.java src/main/java/jdbc/exception/*.java src/test/java/jdbc/DatabaseConnectionTest.java

java -cp "out;lib/*" jdbc.DatabaseConnectionTest
```

