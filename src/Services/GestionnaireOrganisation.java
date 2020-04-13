/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Organisation;
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
public class GestionnaireOrganisation extends GestionnaireUtilisateur{
    Connection cnx = DataSource.getInstance().getConnection();
    String role="org";
    
    public int InscrireORG(Organisation o,int type_verif) {

        String qSql = "INSERT INTO utilisateurs(role, mail,mdp, salt, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, numVisa,pageFB,siteWeb,description,longitude,latitude) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            
            pst.setString(1, role);
            pst.setString(2, o.getMail());
            pst.setString(3, o.getMdp());
            pst.setString(4, o.getSalt());
            pst.setString(5, o.getNumTel());
            pst.setString(6, o.getAdresse().getPays());
            pst.setString(7, o.getAdresse().getVille());
            pst.setString(8, o.getImage());
            pst.setInt(9, o.getPointXP());
            pst.setInt(10, o.getEnabled());
            pst.setString(11, o.getConfirmation_token());
            pst.setString(12, o.getNom());
            
            pst.setString(13, o.getNumVisa());            
            pst.setString(14, o.getPageFb());            
            pst.setString(15, o.getSiteWeb());            
            pst.setString(16, o.getDescription());
            pst.setFloat(17, o.getLongitude());
            pst.setFloat(18, o.getLatitude());
            System.out.println(pst.toString());
            
            pst.executeUpdate();
            System.out.println("ORG ADD Bravo ");
            if(type_verif==1)
                sendMail(o);
            if(type_verif==2)
                sendSMS(o);
            if(type_verif==3)
                sendPhoneCall(o);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("ORG Add Erreur !!!");
        }
        return 0;
    }
    
    public void modifierORG(Organisation o,int id) {

        String qSql = "UPDATE utilisateurs SET mail=?,mdp=?, numTel=?, A_pays=?, A_ville=?, image=?, pointXP=? ,nom=?, numVisa=?,pageFB=?,siteWeb=?,description=?,longitude=?,latitude=? where id='"+id+"' ";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            
            //pst.setString(1, role);
            pst.setString(1, o.getMail());
            pst.setString(2, o.getMdp());
            //pst.setString(4, o.getSalt());
            pst.setString(3, o.getNumTel());
            pst.setString(4, o.getAdresse().getPays());
            pst.setString(5, o.getAdresse().getVille());
            pst.setString(6, o.getImage());
            pst.setInt(7, o.getPointXP());
            //pst.setInt(10, o.getEnabled());
            //pst.setString(11, o.getConfirmation_token());
            pst.setString(8, o.getNom());
            
            pst.setString(9, o.getNumVisa());            
            pst.setString(10, o.getPageFb());            
            pst.setString(11, o.getSiteWeb());            
            pst.setString(12, o.getDescription());
            pst.setFloat(13, o.getLongitude());
            pst.setFloat(14, o.getLatitude());
            
            System.out.println(pst.toString());
            
            pst.executeUpdate();
            System.out.println("ORG modif Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("ORG modif Erreur !!!");
        }
    }

    
    public List<Organisation> fetchOrg (){
        List<Organisation> listP = new ArrayList<>();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, numVisa,pageFB,siteWeb,description,latitude,longitude from utilisateurs where role='"+role+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                Organisation o=new Organisation();
                o.setId(rs.getInt(1));
                o.setMail(rs.getString(2));
                o.setNumTel(rs.getString(3));
                o.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                o.setImage(rs.getString(6));
                o.setPointXP(rs.getInt(7));
                o.setEnabled(rs.getInt(8));
                o.setConfirmation_token(rs.getString(9));
                o.setNom(rs.getString(10));
                o.setNumVisa(rs.getString(11));
                o.setPageFb(rs.getString(12));
                o.setSiteWeb(rs.getString(13));
                o.setDescription(rs.getString(14));
                o.setLatitude(rs.getFloat(15));                
                o.setLongitude(rs.getFloat(16));

                listP.add(o);
            }
            System.out.println("Org Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("Org Select Erreur !!!");
        }   
        return listP;
    }
    
    public Organisation fetchOneOrg (int id){
        Organisation o = new Organisation();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, numVisa,pageFB,siteWeb,description,latitude,longitude from utilisateurs where role='"+role+"' AND id='"+id+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                o.setId(rs.getInt(1));
                o.setMail(rs.getString(2));
                o.setNumTel(rs.getString(3));
                o.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                o.setImage(rs.getString(6));
                o.setPointXP(rs.getInt(7));
                o.setEnabled(rs.getInt(8));
                o.setConfirmation_token(rs.getString(9));
                o.setNom(rs.getString(10));
                o.setNumVisa(rs.getString(11));
                o.setPageFb(rs.getString(12));
                o.setSiteWeb(rs.getString(13));
                o.setDescription(rs.getString(14));
                o.setLatitude(rs.getFloat(15));                
                o.setLongitude(rs.getFloat(16));
            }
            System.out.println("Org Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("Org Select Erreur !!!");
        }   
        return o;
    }
    
    public void supprimerOrg_I (int id){
        String qSql="DELETE FROM utilisateurs WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Org Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("Org Supp Erreur !!!");
        }  
    }
    
}
