/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajson;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author usuario
 */
public class ModeloReporte {
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String estado_civil;
    Conexion conexion;
    ModeloReporte modelo;

    public ModeloReporte(String nombre, String apellido, String nacionalidad, String estado_civil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.estado_civil=estado_civil;
    }

    public ModeloReporte() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }
    
    public void Obtener(){
        Statement comando;
        ResultSet res;
        modelo =new ModeloReporte();
        String SQL ="select persona_nombre,persona_apellido,persona_nacionalidad,persona_estado_civil from persona" ;
        try {
            conexion = new Conexion();
        comando = conexion.conexion("postgres", "desarrollo").createStatement();
        res = comando.executeQuery(SQL);
        while(res.next()){
           modelo.setNombre(res.getString("persona_nombre"));
           modelo.setApellido(res.getString("persona_apellido"));
           ObtenerNacionalidad(res.getInt("persona_nacionalidad"));
            ObtenerEstadoCivil(res.getInt("persona_estado_civil"));
            System.out.println(modelo.getNombre()+" "+modelo.getApellido()+" "+modelo.getNacionalidad()+" "+modelo.getEstado_civil());
        }
        res.close();
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    }
    public void ObtenerNacionalidad(int valorid) {
        Statement comando;
        ResultSet res;
        String SQL ="Select descripcion from parametros where parametros_id = 2" ;
        try {
            conexion = new Conexion();
        comando = conexion.conexion("postgres", "desarrollo").createStatement();
        res = comando.executeQuery(SQL);
        while(res.next()){
           // System.out.println(res.getString("descripcion"));
            String descripcion =res.getString("descripcion");
            Object ob = null;
            ob = new JSONParser().parse(descripcion);
             JSONArray listado = (JSONArray)ob;
            for(int i=0; i<listado.size();i++){
                JSONObject valor = (JSONObject)listado.get(i);
                Long id=(Long)valor.get("id");
                String estado = (String)valor.get("valor");
               if(id==valorid){
                   modelo.setNacionalidad(estado);
               }   
            }   
        }
        res.close();
        
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }
     public void ObtenerEstadoCivil(int valorid) {
        Statement comando;
        ResultSet res;
        String SQL ="Select descripcion from parametros where parametros_id = 1" ;
        try {
            conexion = new Conexion();
        comando = conexion.conexion("postgres", "desarrollo").createStatement();
        res = comando.executeQuery(SQL);
        while(res.next()){
           // System.out.println(res.getString("descripcion"));
            String descripcion =res.getString("descripcion");
            Object ob = null;
            ob = new JSONParser().parse(descripcion);
             JSONArray listado = (JSONArray)ob;
            for(int i=0; i<listado.size();i++){
                JSONObject valor = (JSONObject)listado.get(i);
                Long id=(Long)valor.get("id");
                String estado = (String)valor.get("valor");
               if(id==valorid){
                   modelo.setEstado_civil(estado);
               }   
            }   
        }
        res.close();
        
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }
     
     public void Generare(){
         try {
             conexion = new Conexion();
//             Connection con = conexion.conexion("postgres","desarrollo");
  System.out.print("prueba 1");
             String master = System.getProperty("user.dir")+"/Reporte/Reporte.jasper";
             JasperReport reporte = (JasperReport)JRLoader.loadObject("Reporte.jasper");
             Map parametro = new HashMap();
              System.out.print("prueba 2");
             parametro.put("Nombre",modelo.getNombre());
             parametro.put("Apellido",modelo.getApellido());
             parametro.put("Nacionalidad",modelo.getNacionalidad());
             parametro.put("Estado_Civil",modelo.getEstado_civil());
             System.out.println(modelo.getNombre()+" "+modelo.getApellido()+" "+modelo.getNacionalidad()+" "+modelo.getEstado_civil());
              System.out.print("prueba 3");
              System.out.println(modelo.getNombre()+" "+modelo.getApellido()+" "+modelo.getNacionalidad()+" "+modelo.getEstado_civil());
             System.out.println(parametro);
             JasperPrint j = JasperFillManager.fillReport(reporte, parametro, new JREmptyDataSource());
//              System.out.print("prueba 4");
             JasperViewer jv = new JasperViewer(j,false);
             jv.setTitle("Reporte prueba");
             jv.setVisible(true);
             
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
           
         }
     }
      public void GenerarReporte(){
         try {
             conexion = new Conexion();
             Connection con = conexion.conexion("postgres","desarrollo");
             JasperReport reporte = (JasperReport)JRLoader.loadObject("Reporte1.jasper"); 
             JasperPrint j = JasperFillManager.fillReport(reporte, null , con);
             JasperViewer jv = new JasperViewer(j,false);
             jv.setTitle("Reporte1");
             jv.setVisible(true);
             
             
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
           
         }
     }
}
    
