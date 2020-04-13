/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Hedi
 */
public class ServiceCategorie {
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceCategorie() {
        
    con=DataSource.getInstance().getConnection();

    }
    
    public void ajouter(Categorie c) throws SQLException {
        
        String sql = "INSERT INTO `categorie` ( `nom` ) VALUES ( ? );";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, c.getNom());
        pre.executeUpdate();
    }
    
    
    public boolean delete(int id) throws SQLException {
    
    String sql = "DELETE FROM categorie WHERE id=?";
    pre = con.prepareStatement(sql);
    pre.setInt(1, id);
    int rowsDeleted = pre.executeUpdate();
    if (rowsDeleted > 0) {
    System.out.println("A categorie was deleted successfully!");
    return true;
}
    return false;
    }
    
    //parametres : id de categorie à modifier, cN: nouvelle categorie
    public boolean update(int id, Categorie cN) throws SQLException {
    
    String sql ="UPDATE categorie SET nom=? WHERE id=?";
    //String sql ="UPDATE categorie SET nom=? WHERE nom=?";
    pre= con.prepareStatement(sql);
    pre.setString(1, cN.getNom());
    pre.setInt(2, id);
    int rowsUpdated = pre.executeUpdate();
    if (rowsUpdated > 0) {
    System.out.println("An existing user was updated successfully!");
    return true;
    }
    return false;
    } 
    
    
    public List<Categorie> readAll() throws SQLException {
    List<Categorie> arr = new ArrayList<>();
    String sql = "SELECT * FROM categorie";
    ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            String nom=rs.getString("nom");
            Categorie c=new Categorie(id, nom);
            arr.add(c);
    }
    
    return arr;
    
}
    
        public  Categorie readById(int id) throws SQLException{
        
        String sql = "SELECT * FROM categorie WHERE id=?";
        pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            
            
            String nom = rs.getString("nom");
            Categorie cat =new Categorie(id,nom);
            System.out.println("la categorie de ID "+id+" existe");
            System.out.println(cat);
            return cat;
            }
        else{
            System.out.println("la categorie de ID "+id+" n'existe pas");
            Categorie cat = new Categorie();
            return cat;
            }
    }

        
        
        
        public  Categorie readByName(String name) throws SQLException{
        
        String sql = "SELECT * FROM categorie WHERE nom=?";
        pre = con.prepareStatement(sql);
        pre.setString(1, name);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            Categorie cat =new Categorie(id,nom);
            System.out.println("la categorie de nom "+name+" existe");
            System.out.println(cat);
            return cat;
            }
        else{
            System.out.println("la categorie de nom "+name+" n'existe pas");
            Categorie cat = new Categorie();
            return cat;
            }
    }




//trier les categories par ordre alphabetique
   public List<Categorie> trierCatAlph(List<Categorie> listCat){
         listCat.sort( 
            new Comparator<Categorie>(){ 
                public int compare(Categorie c1, Categorie c2){      
                            //String firstCharS1 = String.valueOf(c1.getNom().charAt(0));
                            //String firstCharS2 = String.valueOf(c2.getNom().charAt(0));
                            //return firstCharS1.compareTo(firstCharS2);
                            return (c1.getNom()).compareToIgnoreCase(c2.getNom());
                        }
        });
    return listCat;
   }
   
   //trier les categories par ordre d'ajout proche ( id la plus grande = date la plus proche )
   public List<Categorie> trierCatOrdreAjtDec() throws SQLException{
       List<Categorie> arr = new ArrayList<>();
       String sql = "SELECT * FROM categorie ORDER BY id DESC";
           ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            String nom=rs.getString("nom");
            Categorie c=new Categorie(id, nom);
            arr.add(c);
    }
    
    return arr;

   }
   
   
   
   //trier les categories par ordre d'ajout loin ( id la plus petite = date la plus lointaine )
     public List<Categorie> trierCatOrdreAjtCroi() throws SQLException{
       List<Categorie> arr = new ArrayList<>();
       String sql = "SELECT * FROM categorie ORDER BY id ASC";
           ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            String nom=rs.getString("nom");
            Categorie c=new Categorie(id, nom);
            arr.add(c);
    }
    
    return arr;

   } 
   
   
   
   public Categorie derniereCatAjout() throws SQLException{
    
        String sql = "SELECT MAX(id) FROM categorie";
        pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            int id = rs.getInt(1);
           Categorie cat = new Categorie();
           cat = this.readById(id);
           System.out.println("derniere categorie ajouté: "+id);
           
           return cat;
            }
        else{
            System.out.println("probleme");
            Categorie cat = new Categorie();
            return cat;
            }
        
    }


}
