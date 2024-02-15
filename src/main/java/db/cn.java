/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Esau
 */
public class cn {
    
     private Connection con;

    public cn() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab01f", "root", "");
            System.out.println("conecto bien????");
        } catch (Exception e) {
            System.err.println("Error: " + e);
            throw e;
        }
    }

     public void cerrarConexion() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
            System.out.println("Conexión cerrada con éxito.");
        }
    }
    public Connection getConnection() {
        
        return con;
    }

    
}
