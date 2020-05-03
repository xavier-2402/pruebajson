/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajson;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




/**
 *
 * @author Alumno
 */
public class Pruebajson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        
        Conexion conexion = new Conexion();
        ModeloReporte modelo = new ModeloReporte();
//        modelo.ObtenerNacionalidad();
      //  modelo.Obtener();
        modelo.GenerarReporte();
//        Statement comando;
//        ResultSet res;
//        String SQL ="Select descripcion from parametros where parametros_id = 2" ;
//        try {
//        comando = conexion.conexion("postgres", "desarrollo").createStatement();
//        res = comando.executeQuery(SQL);
//        while(res.next()){
//           // System.out.println(res.getString("descripcion"));
//            String descripcion =res.getString("descripcion");
//            Object ob = null;
//            ob = new JSONParser().parse(descripcion);
//             JSONArray listado = (JSONArray)ob;
//            for(int i=0; i<listado.size();i++){
//                JSONObject valor = (JSONObject)listado.get(i);
//                Long id=(Long)valor.get("id");
//                String estado = (String)valor.get("valor");
//               if(id==3){
//                   System.out.println(estado);
//               }   
//            }   
//        }
//        res.close();
        
        
//            ob = new JSONParser().parse("[{\"id\":1,\"valor\":\"soltero\"},{\"id\":2,\"valor\":\"casado\"},{\"id\":3,\"valor\":\"divorciado\"},{\"id\":4,\"valor\":\"union libre\"}]");
//            JSONArray listadoEstadoCivil = (JSONArray)ob;
//            for(int i=0; i<listadoEstadoCivil.size();i++){
//                JSONObject estadocivil = (JSONObject)listadoEstadoCivil.get(i);
//                Long id=(Long)estadocivil.get("id");
//                String estado = (String)estadocivil.get("valor");
//               if(id==2){
//                   System.out.println(estado);
//               }
//                
//            }
        
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }
    
    
}
