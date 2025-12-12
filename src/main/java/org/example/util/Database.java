package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:h2:mem:logindb;DB_CLOSE_DELAY=-1";

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL, "sa", "");
    }

    public static void init(){
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()){

            String sql = "CREATE TABLE IF NOT EXISTS users ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL" +
                    ")";

            stmt.execute(sql);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
