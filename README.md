# Identity IQ JDBC project

### 1. Project description
Project provides connection with SQL Server database and allows performing SQL queries. In order to open connection with database you need to provide database url, username and password. There is also JdbcSQLServerConnectionInfo which provides info about connection. 

### 2. Project methods
Methods are stored in SQLServerQueries class:
1. openConnection - establishes connection with database and returns statement.
2. closeConnection - closes connection and statement.
3. activeManagerQuery - returns list of active managers.
4. applicationWithoutOwnerQuery - returns list of applications without owner.
5. businessRolesWithOwner - returns list of roles with owner.

### 3. Requirements
Technologies used in project:
```bash
1) Java 8
2) Gradle 6.8
3) SQL Server 2019
```

### 4. Troubleshooting
If you encounter any problems regarding operation, please let me know. 

