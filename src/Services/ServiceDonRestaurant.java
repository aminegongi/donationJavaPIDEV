/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import ISevices.*;
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
public class ServiceDonRestaurant implements IService<DonRestaurant> {

    private Connection con;
    private Statement ste;

    public ServiceDonRestaurant() {
        con = DataSource.getInstance().getConnection();

    }

   
    @Override
    public void ajouter(DonRestaurant d) throws SQLException
    {
    
    PreparedStatement pre=con.prepareStatement("INSERT INTO `don_restaurant` ( `idDon`, `idResto`, `idUser`, `montant`, `date`) VALUES (?, ?, ?, ?, ?);");
    pre.setInt(1, d.getIdDon());
    pre.setInt(2, d.getIdResto());
    pre.setInt(3, d.getIdUser());
    pre.setFloat(4, d.getMontant());
    pre.setTimestamp(5, d.getDate());
    pre.executeUpdate();
    
    }
            

    @Override
    public boolean delete(DonRestaurant d) throws SQLException {
        try{
        PreparedStatement pre=con.prepareStatement("DELETE FROM `don_restaurant` WHERE `idDon` = ?;");
        pre.setInt(1, d.getIdDon());
        pre.execute();
    return true;
    }catch(SQLException Ex){
        System.out.println("erreur");
        return false;
        }
        
    }

    @Override
    public boolean update(DonRestaurant d) throws SQLException {
        try{
    PreparedStatement pre=con.prepareStatement("UPDATE `don_restaurant` SET `idUser`=?,`montant`=?,`date`=? WHERE `idDon` = ?;");
    pre.setInt(1, d.getIdUser());
    pre.setFloat(2, d.getMontant());
    pre.setTimestamp(3, d.getDate());
    pre.setInt(4, d.getIdDon());
    pre.execute();
    return true;
        }catch(SQLException Ex){
            System.out.println("erreur update");
            return false;
        }
    }

    @Override
    public List<DonRestaurant> readAll() throws SQLException {
    List<DonRestaurant> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don_restaurant");
     while (rs.next()) {                
               int idDon=rs.getInt(1);
               int idResto=rs.getInt(2);
               String nomResto = getName(idResto);
               int idUser=rs.getInt(3);
               String nomUser = getName(idUser);
               float montant=rs.getFloat(4);
               Timestamp date=rs.getTimestamp(5);
               DonRestaurant d=new DonRestaurant(idDon, idResto, nomResto, idUser, nomUser, montant, date);
     arr.add(d);
     }
    return arr;
    }
    
    
    
    public boolean updatePV(DonRestaurant d) throws SQLException {
        float nPV;
        boolean bienpasse = false;
      //  if ((selectPortefeuille(d) >= selectTarifExist(d.getIdResto())) && (selectTarifExist(d.getIdResto()) != -1) ){
        //    System.out.println("erreur recherche");
          //  return false;
       // } else {
      
        nPV = selectPortefeuille(d) + d.getMontant();
        
        
    PreparedStatement pre=con.prepareStatement("UPDATE `tarif_resto` SET `portefeuille_virtuel`=? WHERE `idResto` = ?;");
    pre.setFloat(1, nPV);
    pre.setInt(2, d.getIdResto());
    pre.execute();
    bienpasse = true;
    return bienpasse;
        
        
 //       }
}

    
    
    //métier
    
    
    
    public float selectPortefeuille(DonRestaurant d) throws SQLException{
      
    ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `portefeuille_virtuel` from tarif_resto WHERE `idResto` = '" + d.getIdResto() + "';");
        if (rs.next()){    
               float portefeuilleVirtuel=rs.getFloat(1);
               return portefeuilleVirtuel;
        }else {
            return -1;
        }
        
    }
    
    
    
     public boolean tarifExist(int idResto) throws SQLException{
        ResultSet rs=ste.executeQuery("select * from tarif_resto WHERE `idResto` = '" + idResto + "';");       
        if(rs.next()){
            return true;
        } else {
            return false;
        }
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
   
    
    
     public float calculeNewPortefeuille (DonRestaurant d) throws SQLException{
         float pv;
         pv = selectPortefeuille(d);
         return pv;
    }
     
     
     //afficher la liste des restaurants auquel un utilisateur a fait une donation
     
     public List<DonRestaurant> donationUser(int idUserR) throws SQLException {
    List<DonRestaurant> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don_restaurant WHERE `idUser` = '" + idUserR + "';" );
     while (rs.next()) {                
               int idDon=rs.getInt(1);
               int idResto=rs.getInt(2);
               String nomResto = getName(idResto);
               int idUser=rs.getInt(3);
               String nomUser = getName(idUser);
               float montant=rs.getFloat(4);
               Timestamp date=rs.getTimestamp(5);
               DonRestaurant d=new DonRestaurant(idDon, idResto, nomResto, idUser, nomUser, montant, date);
     arr.add(d);
     }
    return arr;
    }
     
     //afficher la liste des utilisateurs qui ont donné à un restaurant
     
      public List<DonRestaurant> donationResto(int idRestoR) throws SQLException {
    List<DonRestaurant> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from don_restaurant WHERE `idResto` = '" + idRestoR + "';" );
     while (rs.next()) {                
               int idDon=rs.getInt(1);
               int idResto=rs.getInt(2);
               String nomResto=getName(idResto);
               int idUser=rs.getInt(3);
               String nomUser=getName(idUser);
               float montant=rs.getFloat(4);
               Timestamp date=rs.getTimestamp(5);
               DonRestaurant d=new DonRestaurant(idDon, idResto, nomResto, idUser, nomUser , montant, date);
     arr.add(d);
     }
    return arr;
    }
      
      
       public String getName (int id)  throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `nom`,`prenom` from `fos_user` WHERE `id` = '" + id + "';");
        rs.next();    
                String retour;
                String Nom=rs.getString(1);
                String Prenom=rs.getString(2);
                retour = Nom+" "+Prenom;
               return retour;
     
    }
       
       public int getIdUserFromName(String nomUser) throws SQLException{
        String nomRecherche = nomUser;
        String nomDansLaTable;
        
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select `id`,`nom` from `fos_user`;");
        while (rs.next()) {   
                int idUser = rs.getInt(1);
                nomDansLaTable=rs.getString(2);
                if(nomDansLaTable.equalsIgnoreCase(nomRecherche)){
                return idUser;
                }
    }
                return -1;
                
    }
       
       public float totalDonation() throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select SUM(montant)  from `don_restaurant`");
        rs.next();    
               float total =rs.getFloat(1);
               return total;
     
    }
        public float totalDonationResto(int idResto) throws SQLException {
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select SUM(montant)  from `don_restaurant` WHERE `idResto` = '" + idResto + "';");
        rs.next();    
               float total =rs.getFloat(1);
               return total;
     
    }
      public int getIdUserFromMail(String mail)  {
           
        try {
            ste=con.createStatement();
            ResultSet rs;
            rs = ste.executeQuery("select `id` from `fos_user` WHERE `email` = '" + mail + "';");
            rs.next();    
               int idUser=rs.getInt(1);
               return idUser;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonRestaurant.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
      }
     
      
}
