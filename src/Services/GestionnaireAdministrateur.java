/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Administrateur;
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
public class GestionnaireAdministrateur {
    Connection cnx = DataSource.getInstance().getConnection();
    
    public void ajouterAdmin(Administrateur a) {
        String qSql = "INSERT INTO administrateur(mail, mdp, salt, nom, username) VALUES (?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setString(1, a.getMail());
            pst.setString(2, a.getMdp());
            pst.setString(3, a.getSalt());
            pst.setString(4, a.getNom());
            pst.setString(5, a.getUsername());
            pst.executeUpdate();
            System.out.println("Admin ADD Bravo ");
        } catch (SQLException ex) {
            System.out.println("Admin Add Erreur !!!");
        }
    }
    
    public void modifierAdmin(Administrateur a, int id) {
        String qSql = "UPDATE administrateur SET mail=?,mdp=?,nom=?,username=? WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setString(1, a.getMail());
            pst.setString(2, a.getMdp());
            pst.setString(3, a.getNom());
            pst.setString(4, a.getUsername());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Admin Modif Bravo ");
        } catch (SQLException ex) {
            System.out.println("Admin Modif Erreur !!!");
        }
    }
    
    public List<Administrateur> fetchAdministrateurs (){
        List<Administrateur> listP = new ArrayList<>();
        String qSql="select * from administrateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                Administrateur a=new Administrateur();
                a.setId(rs.getInt(1));
                a.setMail(rs.getString(2));
                //a.setMdp(rs.getString(3));
                //a.setSalt(rs.getString(4));
                a.setNom(rs.getString(5));
                a.setUsername(rs.getString(6));
                listP.add(a);
            }
            System.out.println("Admin Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("Admin Select Erreur !!!");
        }   
        return listP;
    }
    
    public void supprimerPersonne_I (int id){
        String qSql="DELETE FROM administrateur WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Admin Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("Admin Supp Erreur !!!");
        }  
    }
    
    public boolean checkMail(String userName){
        String qSql="select mail from administrateur where username='"+userName+"' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                return true;
            }
            System.out.println("Admin checkuserName Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Admin checkuserName Erreur !!!");
        }
        return false;
    }
    
    public int getidd(String userName){
        String qSql="select id from administrateur where username='"+userName+"' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                return rs.getInt(1);
            }
            System.out.println("Admin checkuserName Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Admin checkuserName Erreur !!!");
        }
        return -1;
    }
    
    public boolean checkUsername(String mail){
        String qSql="select mail from administrateur where mail='"+mail+"' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                return true;
            }
            System.out.println("Admin checkMail Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Admin checkMail Erreur !!!");
        }
        return false;
    }
    
    public boolean checkLogin(String u,String p){
        String qSql="select mail from administrateur where username='"+u+"' and mdp='"+p+"' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                return true;
            }
            System.out.println("Admin checkMail Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Admin checkMail Erreur !!!");
        }
        return false;
    }
}
