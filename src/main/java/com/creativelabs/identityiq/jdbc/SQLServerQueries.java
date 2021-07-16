package com.creativelabs.identityiq.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLServerQueries {
    public Statement openConnection(String dbURL, String user, String password) {
        Statement statement = null;
        try {
            DriverManager.getConnection(dbURL, user, password);
            statement = DriverManager.getConnection(dbURL, user, password).createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statement;
    }

    public void closeConnection(Statement statement) throws SQLException {
        Connection connection = statement.getConnection();
        try {
             if (connection != null && !connection.isClosed()) {
                 statement.close();
                 connection.close();
             }
             } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> activeManagerQuery(Statement statement) throws SQLException {
        String sqlQuery = "SELECT [firstname],[lastname],[manager_status] FROM [identityiq].[identityiq].[spt_identity] " +
                "where manager_status = 1 and inactive = 0";
        ResultSet rs = statement.executeQuery(sqlQuery);
        List<String> list = new ArrayList<>();
        while(rs.next()) {
            list.add(rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME"));
        }
        rs.close();
        return list;
    }

    public List<String> applicationWithoutOwnerQuery(Statement statement) throws SQLException {
        String sqlQuery = "SELECT [name] FROM [identityiq].[identityiq].[spt_application] where owner is NULL";
        ResultSet rs = statement.executeQuery(sqlQuery);
        List<String> list = new ArrayList<>();
        while(rs.next()) {
            list.add(rs.getString("NAME") );
        }
        rs.close();
        return list;
    }

    public List<String> businessRolesWithOwner(Statement statement) throws SQLException {
        String sqlQuery = "SELECT [name] FROM [identityiq].[identityiq].[spt_bundle] where type = 'business' and owner is not NULL";
        ResultSet rs = statement.executeQuery(sqlQuery);
        List<String> list = new ArrayList<>();
        while(rs.next()) {
            list.add(rs.getString("NAME") );
        }
        rs.close();
        return list;
    }

    public static void main(String[] args) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=identityiq";
        String user = "sa";
        String pass = "march";
        SQLServerQueries queries = new SQLServerQueries();
        Statement statement = queries.openConnection(dbURL, user, pass);
        System.out.println(queries.activeManagerQuery(statement));
        System.out.println(queries.applicationWithoutOwnerQuery(statement));
        System.out.println(queries.businessRolesWithOwner(statement));
        queries.closeConnection(statement);
    }


    }

