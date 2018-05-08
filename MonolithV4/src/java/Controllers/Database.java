/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class Database {
    
    public String db = "MonolithV2";
    public String url = "jdbc:mysql://localhost/"+db;
    public String user = "root";
    public String pass = "n0m3l0";
    private Connection conn;

    public Database() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public Connection getConnection(){
        return this.conn;
    }
    
    
    
}
