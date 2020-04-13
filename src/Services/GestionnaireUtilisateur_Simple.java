/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Utilisateur_Simple;
import Utils.Adresse;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class GestionnaireUtilisateur_Simple extends GestionnaireUtilisateur{
        Connection cnx = DataSource.getInstance().getConnection();
        String role="us";
        
    public  int InscrireUS(Utilisateur_Simple us, int type_verif){
        String qSql = "INSERT INTO utilisateurs(role, mail,mdp, salt, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, prenom, genre, dateNaissance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = null;
                System.out.println("cecec");

        try {
            pst = cnx.prepareStatement(qSql);
            
            pst.setString(1, role);
            pst.setString(2, us.getMail());
            pst.setString(3, us.getMdp());
            pst.setString(4, us.getSalt());
            pst.setString(5, us.getNumTel());
            
            pst.setString(6, us.getAdresse().getPays());
            pst.setString(7, us.getAdresse().getVille());
            
            pst.setString(8, us.getImage());
            pst.setInt(9, us.getPointXP());
            pst.setInt(10, us.getEnabled());
            pst.setString(11, us.getConfirmation_token());
            
            pst.setString(12, us.getNom());
            pst.setString(13, us.getPrenom());
            pst.setString(14, us.getGenre());
            
            pst.setDate(15, new java.sql.Date(us.getDateNaissance().getTime()));
            //System.out.println(us.getDateNaissance());
            //pst.setDate(15, us.getDateNaissance());
            System.out.println("zzzmmmmmmm");
            System.out.println(pst.toString());
            
            pst.executeUpdate();
            System.out.println("US ADD Bravo ");
            
            if(type_verif==1)
                sendMail(us);
            if(type_verif==2)
                sendSMS(us);
            if(type_verif==3)
                sendPhoneCall(us);
            
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("US Add Erreur !!!");
        }
        return 0;
    }
    
    public void modifierUS(Utilisateur_Simple us, int id) {
            String qSql = "UPDATE utilisateurs SET mail=?,mdp=?, numTel=?, A_pays=?, A_ville=?, image=?,nom=?, prenom=?, genre=?, dateNaissance=?  WHERE id=?";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(qSql);
                
                pst.setString(1, us.getMail());
                pst.setString(2, us.getMdp());
                pst.setString(3, us.getNumTel());
                pst.setString(4, us.getAdresse().getPays());
                pst.setString(5, us.getAdresse().getVille());
                pst.setString(6, us.getImage());
                pst.setString(7, us.getNom());
                pst.setString(8, us.getPrenom());
                pst.setString(9, us.getGenre());
                pst.setDate(10, new java.sql.Date(us.getDateNaissance().getTime()));
                
                pst.setInt(11, id);
                pst.executeUpdate();
                System.out.println("US Modif Bravo ");
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
                System.out.println("US Modif Erreur !!!");
            }
    }
    /*
    public void modifier(Utilisateur_Simple us, int id) {
            String qSql = "UPDATE utilisateurs SET mail=?,mdp=?, numTel=?, nom=?,prenom=?, genre=?WHERE id=?";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(qSql);
                
                pst.setString(1, us.getMail());
                pst.setString(2, us.getMdp());
                pst.setString(3, us.getNumTel());

                pst.setString(6, us.getImage());
                pst.setString(7, us.getNom());
                pst.setString(8, us.getPrenom());
                pst.setString(9, us.getGenre());
                pst.setInt(11, id);
                
                pst.executeUpdate();
                System.out.println("US Modif Bravo ");
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
                System.out.println("US Modif Erreur !!!");
            }
    }*/
    
    public List<Utilisateur_Simple> fetchUS (){
        List<Utilisateur_Simple> listP = new ArrayList<>();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, prenom, genre, dateNaissance from utilisateurs where role='"+role+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                Utilisateur_Simple us=new Utilisateur_Simple();
                us.setId(rs.getInt(1));
                us.setMail(rs.getString(2));
                us.setNumTel(rs.getString(3));
                us.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                us.setImage(rs.getString(6));
                us.setPointXP(rs.getInt(7));
                us.setEnabled(rs.getInt(8));
                us.setConfirmation_token(rs.getString(9));
                us.setNom(rs.getString(10));
                us.setPrenom(rs.getString(11));
                us.setGenre(rs.getString(12));
                us.setDateNaissance(rs.getDate(13));
                //us.setDateNaissance(rs.getDate(12));
                
                listP.add(us);
            }
            System.out.println("US Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("US Select Erreur !!!");
        }   
        return listP;
    }
    
    public Utilisateur_Simple fetchOneUS (int id) {
        Utilisateur_Simple OneUS = new Utilisateur_Simple();
        String qSql="select id,mail, numTel, A_pays, A_ville, image, pointXP, enabled, confirm_token,nom, prenom, genre, dateNaissance,dateInscription from utilisateurs where id='"+id+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                OneUS.setId(rs.getInt(1));
                OneUS.setMail(rs.getString(2));
                OneUS.setNumTel(rs.getString(3));
                OneUS.setAdresse(new Adresse(rs.getString(4), rs.getString(5)));
                OneUS.setImage(rs.getString(6));
                OneUS.setPointXP(rs.getInt(7));
                OneUS.setEnabled(rs.getInt(8));
                OneUS.setConfirmation_token(rs.getString(9));
                OneUS.setNom(rs.getString(10));
                OneUS.setPrenom(rs.getString(11));
                OneUS.setGenre(rs.getString(12));
                OneUS.setDateNaissance(rs.getDate(13));
                OneUS.setDateInscription(rs.getDate(14));
            }
            System.out.println("US Select Bravo ");
        } catch ( SQLException ex) {
            System.out.println("US Select Erreur !!!");
        }  
        return OneUS;
    }
    
    public void supprimerUS_I (int id){
        String qSql="DELETE FROM utilisateurs WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("US Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("US Supp Erreur !!!");
        }  
    }
    
}
