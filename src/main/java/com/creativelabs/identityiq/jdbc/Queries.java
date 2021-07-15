package com.creativelabs.identityiq.jdbc;

import java.sql.*;

public class Queries {

    public void performQuery(String sqlQuery) {
        Connection conn = null;

        try {

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=identityiq";
            String user = "sa";
            String pass = "march";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
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

    public static void main(String[] args) {
        Queries queries = new Queries();
        String activeManagerQuery = "SELECT [firstname],[lastname],[manager_status] FROM [identityiq].[identityiq].[spt_identity] " +
                "where manager_status = 1 and inactive = 0";
        String applicationWithoutOwner = "SELECT [name] FROM [identityiq].[identityiq].[spt_application] where owner is NULL";
        queries.performQuery(activeManagerQuery);
    }
}
