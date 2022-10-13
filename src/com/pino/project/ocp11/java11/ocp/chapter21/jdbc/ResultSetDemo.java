package com.pino.project.ocp11.java11.ocp.chapter21.jdbc;

//import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ResultSetDemo
{
    public static void main (String...args)
    {   String sql = "SELECT id, name FROM exhibits";
        Map<Integer, String> idToNameMap = new HashMap<>();

       // try (var ps =  conn.prepareStatement(sql);
        //     ResultSet rs = ps.executeQuery())
        //{    while (rs.next())
          //  {

            //}
        //}
    }
}
