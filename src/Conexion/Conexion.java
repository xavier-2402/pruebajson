package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
     
    Connection conexion;
    
    public Conexion(){
             
    } 
     public Connection conexion(String usuario,String password)
    {
         
           String url="jdbc:postgresql://localhost:5432/marylove";
           
           try{
               Class.forName("org.postgresql.Driver");
               conexion = DriverManager.getConnection(url,usuario,password);
              
           }
           catch(Exception e)
           {
              JOptionPane.showMessageDialog(null,"PROBLEMAS DE CONEXION "+e);
           }
           
           return conexion;
         
    }
}
