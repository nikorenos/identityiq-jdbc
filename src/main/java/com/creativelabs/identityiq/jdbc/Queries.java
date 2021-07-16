package com.creativelabs.identityiq.jdbc;

import java.sql.*;

public class Queries {

    public void activeManagerQuery(String sqlQuery) {
        Connection conn = null;
        try {

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=identityiq";
            String user = "sa";
            String pass = "march";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                Statement statement = DriverManager.getConnection(dbURL, user, pass).createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);

                while(rs.next()) {
                    System.out.println(rs.getString("FIRSTNAME") + ", " +
                                    rs.getString("LASTNAME"));
                }
                rs.close();
                statement.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void applicationWithoutOwnerQuery(String sqlQuery) {
        Connection conn = null;
        try {

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=identityiq";
            String user = "sa";
            String pass = "march";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                Statement statement = DriverManager.getConnection(dbURL, user, pass).createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);

                while(rs.next()) {
                    System.out.println(rs.getString("NAME"));
                }
                rs.close();
                statement.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Queries queries = new Queries();
        String activeManager = "SELECT [firstname],[lastname],[manager_status] FROM [identityiq].[identityiq].[spt_identity] " +
                "where manager_status = 1 and inactive = 0";
        String applicationWithoutOwner = "SELECT [name] FROM [identityiq].[identityiq].[spt_application] where owner is NULL";
        System.out.println("Active managers:");
        queries.activeManagerQuery(activeManager);
        System.out.println("Application without owner:");
        queries.applicationWithoutOwnerQuery(applicationWithoutOwner);
    }
}
