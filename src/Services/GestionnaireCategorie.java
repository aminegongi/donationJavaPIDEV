/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.categorieCagnotte;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class GestionnaireCategorie {
    Connection cn = DataSource.getInstance().getConnection();

    public void ajouterCategorie(categorieCagnotte c){
        String requete = "INSERT INTO categoriecagnotte  (nom, description) VALUES (?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println("Categorie ajoutée!");
    }
    
    public void modifierCategorie(categorieCagnotte c){
        String requete = "UPDATE categoriecagnotte  SET nom = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setInt(3, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println("Categorie modifée!");
    }
    
    public void supprimerCategorie(int id){
        String requete = "DELETE FROM categorie WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println("Categorie supprimée!");
    }
    
    public List<categorieCagnotte> getCategories(){
        List<categorieCagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM categoriecagnotte ";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                categorieCagnotte c = new categorieCagnotte(rs.getInt(1), rs.getString(2), rs.getString(3));                
                list.add(c);
            }
            System.out.println("List des categories à été crée!");
        } catch (SQLException ex) {
        }
        return list;
    }
    
    public categorieCagnotte getCategorie(int id){
        String requete = "SELECT * FROM categoriecagnotte  WHERE id = ?";
        categorieCagnotte c = null;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            c = new categorieCagnotte(rs.getInt(1), rs.getString(2), rs.getString(3));
            System.out.println("Categorie à été trouvé!");
        } catch (SQLException ex) {
        }
        return c;
    }
    
    public int getCategorieIdFromName(String nom){
        String requete = "SELECT * FROM categoriecagnotte  WHERE nom = ?";
        int i = 0;
        System.out.println("Nom:"+ nom);
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            rs.next();
            i = rs.getInt(1);
            System.out.println("Id categorie à été trouvé par nom!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public String getCategorieNomFromId(int id){
        String requete = "SELECT nom FROM categoriecagnotte  WHERE id = ?";
        String st ="";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            st = rs.getString(1);
            System.out.println("Nom categorie à été trouvé par nom!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return st;
    }
}
