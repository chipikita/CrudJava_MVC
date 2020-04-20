
package modelo;

import java.sql.*;


public class Conexao {
   
    Connection con;
    
    public Connection getConnection(){
        String url="jdbc:mysql://localhost:3306/crud_mvc";
        String user="root";
        String pass="123***abc";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println(e);
        }
        return con;
    }
}
