package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/8/2024, Monday
 **/
public class SingletonDBConnection {

    static  String DB_URL =null;

    //  Database credentials
    static  String USER = null;
    static  String PASS = null;
    private static Connection conn=null;


//    private SingletonDBConn(){}

    public static Connection getSingletonConn() throws SQLException {

        ResourceBundle bundle = ResourceBundle.getBundle("database/DB");
        DB_URL=bundle.getString("DB_URL");
        USER=bundle.getString("USER");
        PASS=bundle.getString("PASS");
        if (conn==null) {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

        }

        return conn;
    }
}
