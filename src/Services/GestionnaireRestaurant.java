/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Restaurant;
import Utils.Adresse;
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
 * @author Amine Gongi
 */
public class GestionnaireRestaurant extends GestionnaireUtilisateur{
    Connection cnx = DataSource.getInstance().getConnection();
    String role="resto";
    
    public int InscrireResto(Restaurant r) {
        String qSql = "INSERT INTO utilisateurs(role, mail,mdp, salt, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, porteFeuilleV,pageFB,siteWeb,description,longitude,latitude) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            
            pst.setString(1, role);
            pst.setString(2, r.getMail());
            pst.setString(3, r.getMdp());
            pst.setString(4, r.getSalt());
            pst.setString(5, r.getNumTel());
            pst.setString(6, r.getAdresse().getPays());
            pst.setString(7, r.getAdresse().getVille());
            pst.setString(8, r.getImage());
            pst.setInt(9, r.getPointXP());
            pst.setInt(10, r.getEnabled());
            pst.setString(11, r.getConfirmation_token());
            pst.setString(12, r.getNom());
            
            pst.setFloat(13, r.getPorteFeuilleV());
            pst.setString(14, r.getPageFb());            
            pst.setString(15, r.getSiteWeb());            
            pst.setString(16, r.getDescription());
            pst.setFloat(17, r.getLongitude());
            pst.setFloat(18, r.getLatitude());
            System.out.println(r);
            System.out.println(pst.toString());
            
            pst.executeUpdate();
            System.out.println("RESTO ADD Bravo ");
            sendMail(r);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("RESTO Add Erreur !!!");
        }
        return 0;
    }
    
    public void modifierResto(Restaurant r,int id) {

        String qSql = "UPDATE utilisateurs SET mail=?,mdp=?, numTel=?, A_pays=?, A_ville=?, image=?, pointXP=? ,nom=?, porteFeuilleV=?,pageFB=?,siteWeb=?,description=?,longitude=?,latitude=? where id='"+id+"' ";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            
            //pst.setString(1, role);
            pst.setString(1, r.getMail());
            pst.setString(2, r.getMdp());
            //pst.setString(4, r.getSalt());
            pst.setString(3, r.getNumTel());
            pst.setString(4, r.getAdresse().getPays());
            pst.setString(5, r.getAdresse().getVille());
            pst.setString(6, r.getImage());
            pst.setInt(7, r.getPointXP());
            //pst.setInt(10, r.getEnabled());
            //pst.setString(11, r.getConfirmation_token());
            pst.setString(8, r.getNom());
            pst.setFloat(9, r.getPorteFeuilleV());
            pst.setString(10, r.getPageFb());            
            pst.setString(11, r.getSiteWeb());            
            pst.setString(12, r.getDescription());
            pst.setFloat(13, r.getLongitude());
            pst.setFloat(14, r.getLatitude());
            
            System.out.println(pst.toString());
            
            pst.executeUpdate();
            System.out.println("RESTO modif Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("RESTO modif Erreur !!!");
        }
    }

    
    public List<Restaurant> fetchResto (){
        List<Restaurant> listP = new ArrayList<>();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, porteFeuilleV,pageFB,siteWeb,description,latitude,longitude from utilisateurs where role='"+role+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                Restaurant r=new Restaurant();
                r.setId(rs.getInt(1));
                r.setMail(rs.getString(2));
                r.setNumTel(rs.getString(3));
                r.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                r.setImage(rs.getString(6));
                r.setPointXP(rs.getInt(7));
                r.setEnabled(rs.getInt(8));
                r.setConfirmation_token(rs.getString(9));
                r.setNom(rs.getString(10));
                
                r.setPorteFeuilleV(rs.getFloat(11));
                r.setPageFb(rs.getString(12));
                r.setSiteWeb(rs.getString(13));
                r.setDescription(rs.getString(14));
                r.setLatitude(rs.getFloat(15));                
                r.setLongitude(rs.getFloat(16));

                listP.add(r);
            }
            System.out.println("RESTO Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("RESTO Select Erreur !!!");
        }   
        return listP;
    }
    
    public Restaurant fetchOneResto (int id){
        Restaurant r = new Restaurant();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, porteFeuilleV,pageFB,siteWeb,description,latitude,longitude from utilisateurs where role='"+role+"' AND id='"+id+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                r.setId(rs.getInt(1));
                r.setMail(rs.getString(2));
                r.setNumTel(rs.getString(3));
                r.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                r.setImage(rs.getString(6));
                r.setPointXP(rs.getInt(7));
                r.setEnabled(rs.getInt(8));
                r.setConfirmation_token(rs.getString(9));
                r.setNom(rs.getString(10));
                r.setPorteFeuilleV(rs.getFloat(11));
                r.setPageFb(rs.getString(12));
                r.setSiteWeb(rs.getString(13));
                r.setDescription(rs.getString(14));
                r.setLatitude(rs.getFloat(15));                
                r.setLongitude(rs.getFloat(16));
            }
            System.out.println("RESTO Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("RESTO Select Erreur !!!");
        }   
        return r;
    }
    
    public void supprimerResto_I (int id){
        String qSql="DELETE FROM utilisateurs WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("RESTO Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("RESTO Supp Erreur !!!");
        }  
    }
    
}
