/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Entities.Emplois;
import Entities.Categorie_Emplois;
import ISevices.IServiceEmplois;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ISevices.IServiceCategorie_Emplois;

/**
 *
 * @author heshem
 */
 
public class Categorie_EmploisService implements IServiceCategorie_Emplois{

    
    private Connection con;
    private Statement ste;

    public Categorie_EmploisService() {
        con = DataSource.getInstance().getConnection();

    }
    
    

    @Override
    public void ajouterCategorieEmplois(Categorie_Emplois p) throws SQLException {
PreparedStatement pre=con.prepareStatement("INSERT INTO `donationw`.`categorie_emploi` (`id`, `titre_categorie`, `description_categorie`) VALUES ( NULL,?, ?);");
    pre.setString(1, p.getTitre());
    pre.setString(2, p.getDescription());
    
//    pre.setString(3, p.getPhoto());
//    pre.setDouble(4, p.getSalaire());
//    pre.setString(5, p.getEmplacement());
//    pre.setString(6, p.getTypeDemploi());
//    pre.setString(7, p.getTypeContrat());
    
    pre.executeUpdate();    }

    @Override
    public boolean deleteCategorieEmplois(int id) throws SQLException {
    
        PreparedStatement pre = con.prepareStatement("DELETE FROM `donationw`.`categorie_emploi` where id =?");
        pre.setInt(1, id);
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("emplois was deleted successfully!");
        }
        return true;
    
    }

    @Override
    public boolean updateCategorieEmplois(Categorie_Emplois t, int id) throws SQLException {
String sql = "UPDATE categorie_emploi SET titre_categorie=?, description_categorie=? WHERE id=? ";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getTitre());
        statement.setString(2, t.getDescription());
//        statement.setString(3, t.getPhoto());
//        statement.setDouble(4, t.getSalaire());
//        statement.setString(5, t.getEmplacement());
//        statement.setString(6, t.getTypeDemploi());
//        statement.setString(7, t.getTypeContrat());
        statement.setInt(3, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing emplois was updated successfully!");
        }
        return true;
    }

    @Override
    public List<Categorie_Emplois> readAllCategorieEmplois() throws SQLException {
      List<Categorie_Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from categorie_emploi");
     while (rs.next()) {                
               int id_categorie_emplois=rs.getInt(1);
               String titre_categorie=rs.getString("titre_categorie");
               String description_categorie=rs.getString("description_categorie");
//               String photo=rs.getString("Photo");
//               double salaire=rs.getDouble("salaire");
//               String emplacement=rs.getString("emplacement");
//               String TypeDemploi=rs.getString("TypeDemploi");
//               String TypeContrat=rs.getString("TypeContrat");
               Categorie_Emplois p=new Categorie_Emplois(id_categorie_emplois,titre_categorie,description_categorie);
     arr.add(p);
     }
    return arr;    } 
    
    
    public  Categorie_Emplois readByName(String titre_categorie) throws SQLException{
        
        
        
        String sql = "SELECT * FROM categorie_emploi WHERE titre_categorie=?";
        PreparedStatement pre = con.prepareStatement(sql);
        
        pre.setString(1,titre_categorie);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            
            int id = rs.getInt(1);
            String nom = rs.getString("titre_categorie");
            Categorie_Emplois cat =new Categorie_Emplois(id,titre_categorie,"");
            System.out.println("la categorie de nom "+titre_categorie+" existe");
            System.out.println(cat);
            return cat;
            }
        else{
            System.out.println("la categorie de nom "+titre_categorie+" n'existe pas");
            Categorie_Emplois cat = new Categorie_Emplois("aa","bb");
            return cat;
            }
        
    }
    
    
    
}