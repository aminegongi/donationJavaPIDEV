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
public class ServiceRepasServi implements IService<RepasServi> {

    private Connection con;
    private Statement ste;

    public ServiceRepasServi() {
        con = DataSource.getInstance().getConnection();

    }

   
    @Override
    public void ajouter(RepasServi r) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `RepasServi` ( `idResto`, `date`) VALUES ( ?, ?);");
    pre.setInt(1, r.getIdResto());
    pre.setTimestamp(2, r.getDate());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(RepasServi r) throws SQLException {
        try{
        PreparedStatement pre=con.prepareStatement("DELETE FROM `RepasServi` WHERE `idResto` = ? AND `date` = ? ORDER BY `idResto` DESC LIMIT 1 ;");
        pre.setInt(1, r.getIdResto());
        pre.setTimestamp(2, r.getDate());
        pre.execute();
    return true;
    }catch(SQLException Ex){
        System.out.println("erreur delete");
        return false;
        }
    }

    @Override
    public boolean update(RepasServi r) throws SQLException {
        float nPF;
        nPF = calculeNewPortefeuille(r);
        
        try{
    PreparedStatement pre=con.prepareStatement("UPDATE `TarifResto` SET `portefeuilleVirtuel`=? WHERE `idResto` = ?;");
    pre.setFloat(1, nPF);
    pre.setInt(2, r.getIdResto());
    pre.execute();
    return true;
        }catch(SQLException Ex){
            System.out.println("erreur update");
            return false;
        }
    }

    @Override
    public List<RepasServi> readAll() throws SQLException {
    List<RepasServi> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from RepasServi");
     while (rs.next()) {                
               int idResto=rs.getInt(1);
               Timestamp date=rs.getTimestamp(2);
               RepasServi r=new RepasServi(idResto, date);
     arr.add(r);
     }
    return arr;
    }
    
    
    public boolean updatePV(RepasServi r) throws SQLException {
        float nPV;
        if ((selectPortefeuille(r.getIdResto()) >= selectTarifExist(r.getIdResto())) && (selectTarifExist(r.getIdResto()) != -1) ){
         nPV = selectPortefeuille(r.getIdResto()) - selectTarifExist(r.getIdResto()) ;
        
        
    PreparedStatement pre=con.prepareStatement("UPDATE `PiDev`.`TarifResto` SET `portefeuilleVirtuel`=? WHERE `idResto` = ?;");
    pre.setFloat(1, nPV);
    pre.setInt(2, r.getIdResto());
    pre.execute();
    
    return true;
    
      
        } else {
        
        System.out.println("erreur mise à jour portefeuille virtuel");
           return false;
       }
}
    
    
    public float selectPortefeuille(int idResto) throws SQLException{
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `portefeuilleVirtuel` from TarifResto WHERE `idResto` = '" + idResto + "';");
        if (rs.next()){    
               float portefeuilleVirtuel=rs.getFloat(1);
               return portefeuilleVirtuel;
        }else {
            return -1;
        }
        
    }
    
    
   /* public float selectTarif(int idResto) throws SQLException{
        ResultSet rs=ste.executeQuery("select `tarif` from TarifResto WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               float tarif=rs.getFloat(1);
               return tarif;
     
    }
   */
    
    
    public boolean tarifExist(int idResto) throws SQLException{
        ResultSet rs=ste.executeQuery("select * from TarifResto WHERE `idResto` = '" + idResto + "';");       
        if(rs.next()){
            return true;
        } else {
            return false;
        }
    }
    
    
    public float selectTarifExist(int idResto) throws SQLException{
      if (tarifExist(idResto)==true){
        
        ResultSet rs=ste.executeQuery("select `tarif` from TarifResto WHERE `idResto` = '" + idResto + "';");       
        rs.next();
            float tarif = rs.getFloat(1);
            return tarif;
        
      } else {
          return -1;
      }
    }
    
     public float calculeNewPortefeuille (RepasServi r) throws SQLException{
         float pv;
         pv = selectPortefeuille(r.getIdResto());
         pv-= selectTarifExist(r.getIdResto());
         return pv;
    }
     
     
     public boolean RepasAservir(int idResto) throws SQLException{
         float pv;
         pv = selectPortefeuille(idResto);
         if (selectTarifExist(idResto) != -1){
         pv-= selectTarifExist(idResto);
         if (pv<0){
             System.out.println("Solde insuffisant!");
             return false;
         }else {
             System.out.println("vous pouvez servir un repas!");
             return true;
         }
         }else {
             System.out.println("Tarif n'existe pas!");
             return false;
         }
    }
     
      public String getName (int id)  throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `nom` from `utilisateurs` WHERE `id` = '" + id + "';");
        rs.next();    
               String Nom=rs.getString(1);
               return Nom;
     
    }
      
      
      public int nbrRepasServi(int idResto) throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select COUNT(*) from `RepasServi` WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               int nbrRS =rs.getInt(1);
               return nbrRS;
     
    }
      
         public List<RepasServi> repasResto(int idRestoR) throws SQLException {
    List<RepasServi> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from RepasServi WHERE `idResto` = '" + idRestoR + "';" );
     while (rs.next()) {                
               
               int idResto=rs.getInt(1);
               Timestamp date=rs.getTimestamp(2);
               RepasServi d=new RepasServi(idResto, date);
     arr.add(d);
     }
    return arr;
    }
      
         
         
         public int totalRepasServi() throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select COUNT(*) from `RepasServi`");
        rs.next();    
               int total =rs.getInt(1);
               return total;
     
    }
         
          public int totalRepasServiResto(int idResto) throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select COUNT(*) from `RepasServi` WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               int total =rs.getInt(1);
               return total;
     
    }
     
        //     float tR;
       // if (selectTarifExist(d.getIdResto()) != -1){
         //   tR = selectTarifExist(d.getIdResto());
}
