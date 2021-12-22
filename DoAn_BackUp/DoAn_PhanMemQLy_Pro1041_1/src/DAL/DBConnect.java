/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Duy
 */
public class DBConnect {
    static Connection connect;
    private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl="jdbc:sqlserver://localhost:1433;database=DoAn_PRO1041";
    private static String username="sa";
    private static String password="yourStrong(!)Password";
    
    public DBConnect(){
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connect;
    }
    
    public static Connection getConnect() throws Exception {
    Connection connect = null;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }
}
