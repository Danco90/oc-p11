package com.pino.project.ocp11.java11.ocp.chapter21.jdbc;

import java.sql.*;

public class SetupDerbyDatabase
{
    public static void main(String[] args) throws Exception
    {

        String url = "jdbc:derby://localhost:1527/zoo;create=true";
        //Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/zoo",
          //      "Admin", //user
            //    "Admin"   //pwd
        //);   
        try(Connection conn = DriverManager.getConnection(url))
        {
            // run(conn,"DROP TABLE names");
            // run(conn,"DROP TABLE exhibits");
            /*run(conn, "CREATE TABLE exhibits ("
                    + "id INTEGER PRIMARY KEY, " +
                      "name VARCHAR(255), " +
                      "num_acres DECIMAL(4,1))");

            run(conn, "CREATE TABLE names (" +
                      "id INTEGER PRIMARY KEY, " +
                      "species_id integer REFERENCES exhibits (id), " +
                      "name VARCHAR(255))");
             */
          /*  run(conn,"INSERT INTO exhibits VALUES (1, 'African Elephant', 7.5)");
            run(conn,"INSERT INTO exhibits VALUES (2, 'Zebra', 1.2)");

            run(conn,"INSERT INTO names VALUES (1, 1, 'Elsa')");
            run(conn,"INSERT INTO names VALUES (2, 2, 'Zelda')");
            run(conn,"INSERT INTO names VALUES (3, 1, 'Ester')");
            run(conn,"INSERT INTO names VALUES (4, 1, 'Eddie')");
            run(conn,"INSERT INTO names VALUES (5, 2, 'Zoe')");
             */
            printCount(conn, "SELECT count(*) FROM names");

            var insertSql = "INSERT INTO exhibits VALUES (6, 'Deer', 3.0)";
            var updateSql = "UPDATE exhibits SET name = '' WHERE name = 'None' " ;
            var deleteSql = "DELETE FROM exhibits WHERE id = 6 " ;
            //run(conn,insertSql);
            run(conn,updateSql);
            run(conn,deleteSql);

        }
    }

    private static void run(Connection conn, String sql) throws SQLException
    {
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            int result = ps.executeUpdate();
            System.out.println(result);
        }
    }
    
    private static void printCount(Connection conn, String sql) throws SQLException
    {
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
        }

    }
}
