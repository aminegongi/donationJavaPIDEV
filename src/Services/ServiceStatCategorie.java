/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.StatCategorie;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hedi
 */
public class ServiceStatCategorie {
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceStatCategorie() {
        con=DataSource.getInstance().getConnection();
    }
    
    //retourne nombre des demandes pour chaque categorie (parametre : id categorie ) 
    public int nbrDmndCat(int id) throws SQLException{
     String sql = "SELECT COUNT(*) AS nombre FROM demande_aide WHERE id_categorie=?";
     pre= con.prepareStatement(sql);
     pre.setInt(1, id);
     ResultSet rs = pre.executeQuery();
        if (rs.next()){
            int totale = rs.getInt("nombre");
            System.out.println("totale: "+totale);
            return totale;
            }
        else{
            System.out.println("la categorie de ID "+id+" n'existe pas");
            return 0;
            }
    }
    
    
    //retourne liste des objets StatCategorie
    public List<StatCategorie> nbrDmndAllCat(List<Categorie> listCat) throws SQLException{
     List<StatCategorie> arr = new ArrayList<>();
        for ( Categorie cat : listCat ){
        
            int id = cat.getId();
            String nom = cat.getNom();
            int nbrDmnd = this.nbrDmndCat(id);
            StatCategorie statObj = new StatCategorie(nom,nbrDmnd);
            arr.add(statObj);
        }
    
    return arr;
    }
    
}
