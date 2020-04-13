/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISevices;

import Entities.RepasServi;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author safratix
 */
public class BrainStorming {
    
    
    private Connection con;
    private Statement ste;

    public BrainStorming() {
        con = DataSource.getInstance().getConnection();

    }
    
    public float selectPortefeuille(int idResto) throws SQLException{
        ResultSet rs=ste.executeQuery("select `portefeuilleVirtuel` from TarifResto WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               float portefeuilleVirtuel=rs.getFloat(1);
               return portefeuilleVirtuel;
     
    }
    
    
    public float selectTarif(int idResto) throws SQLException{
        ResultSet rs=ste.executeQuery("select `tarif` from TarifResto WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               float tarif=rs.getFloat(1);
               return tarif;
     
    }
    
    
     public float calculeNewPortefeuille (int idResto) throws SQLException{
         float pv;
         pv = selectPortefeuille(idResto);
         pv-= selectTarif(idResto);
         return pv;
    }
    
    public boolean RepasAservir(int idResto) throws SQLException{
         float pv;
         pv = selectPortefeuille(idResto);
         pv-= selectTarif(idResto);
         if (pv<0){
             return false;
         }else {
             return true;
         }
    }
    
    
    public int getIdRestoFromName(String nomResto){
        String nomRecherché = nomResto;
        String nomDansLaTable;
        return -1;
    }
    
    
     public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
}
