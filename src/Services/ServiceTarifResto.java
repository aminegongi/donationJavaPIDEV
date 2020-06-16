/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import ISevices.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class ServiceTarifResto implements IService<TarifResto> {

    private Connection con;
    private Statement ste;

    public ServiceTarifResto() {
        con = DataSource.getInstance().getConnection();

    }

   
    @Override
    public void ajouter(TarifResto t) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `tarif_resto` ( `idResto`, `tarif`) VALUES ( ?, ?);");
    pre.setInt(1, t.getIdResto());
    pre.setFloat(2, t.getTarif());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(TarifResto t) throws SQLException {
        try{
        PreparedStatement pre=con.prepareStatement("DELETE FROM `tarif_resto` WHERE `idResto` = ? ;");
        pre.setInt(1, t.getIdResto());
        pre.execute();
    return true;
    }catch(SQLException Ex){
        System.out.println("erreur delete");
        return false;
        }
    }

    @Override
    public boolean update(TarifResto t) throws SQLException {
    try{
    PreparedStatement pre=con.prepareStatement("UPDATE `tarif_resto` SET `tarif`=? WHERE `idResto` = ?;");
    pre.setFloat(1, t.getTarif());
    pre.setInt(2, t.getIdResto());
    pre.execute();
    return true;
        }catch(SQLException Ex){
            System.out.println("erreur update");
            return false;
        }
    }

    @Override
    public List<TarifResto> readAll() throws SQLException {
    List<TarifResto> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from tarif_resto");
     while (rs.next()) {                
                int idResto=rs.getInt(1);
               String nomResto = getName(idResto);
               float tarif=rs.getFloat(2);
               float portefeuilleVirtuel=rs.getFloat(3);
               TarifResto t=new TarifResto(idResto, nomResto, tarif, portefeuilleVirtuel);
     arr.add(t);
     }
    return arr;
    }
    
    
    
    
    
    //Métiers
    
    
    public boolean tarifExist(int idResto) throws SQLException{
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from tarif_resto WHERE `idResto` = '" + idResto + "';");       
        if(rs.next()){
            System.out.println("Ce restaurant a déjà un tarif!");
            return true;
        } else {
            return false;
        }
    }
    
    
    public float selectPortefeuille(int idResto) throws SQLException{
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `portefeuille_virtuel` from tarif_resto WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               float portefeuilleVirtuel=rs.getFloat(1);
               return portefeuilleVirtuel;
     
    }
    
    
    public float selectTarifExist(int idResto) throws SQLException{
      if (tarifExist(idResto)==true){
        
        ResultSet rs=ste.executeQuery("select `tarif` from tarif_resto WHERE `idResto` = '" + idResto + "';");       
        rs.next();
            float tarif = rs.getFloat(1);
            return tarif;
        
      } else {
          return -1;
      }
    }
    
    
    public String getName (int id)  throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `nom` from `fos_user` WHERE `id` = '" + id + "';");
        rs.next();    
               String Nom=rs.getString(1);
               return Nom;
     
    }
    
    
    public int getIdRestoFromName(String nomResto) throws SQLException{
        String nomRecherche = nomResto;
        String nomDansLaTable;
        
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `id`,`nom` from `fos_user`;");
        while (rs.next()) {   
                int idResto = rs.getInt(1);
                nomDansLaTable=rs.getString(2);
                if(nomDansLaTable.equalsIgnoreCase(nomRecherche)){
                return idResto;
                }
    }
                return -1;
                
    }
    
    
     public List<TarifResto> selectById(int idRestoR) throws SQLException {
    List<TarifResto> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from TarifResto WHERE `idResto` = '" + idRestoR + "';");
     while (rs.next()){                 
               int idResto=rs.getInt(1);
               String nomResto = getName(idResto);
               float tarif=rs.getFloat(2);
               float portefeuilleVirtuel=rs.getFloat(3);
               TarifResto t=new TarifResto(idResto, nomResto, tarif, portefeuilleVirtuel);
            arr.add(t);   
     }
     
    return arr;
    }
    
    
    
     public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
    
    public int nbrTotalResto() throws SQLException{
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select COUNT(*) from `donationW`.`tarif_resto`");
        rs.next();    
               int nbrRS =rs.getInt(1);
               return nbrRS;
     
    }
    
    
    public float totalPortefeuille() throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select SUM(portefeuille_virtuel)  from `tarif_resto`");
        rs.next();    
               float total =rs.getFloat(1);
               return total;
     
    }
    
    /*public float calculeNewPortefeuille (TarifResto t) throws SQLException{
         float pv;
         pv = selectPortefeuille(t.getIdResto());
         pv+= t.getTarif();
    }*/
}

