/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.cagnotte;
import Entities.donation;
import Utils.CagnotteMailing;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class GestionnaireCagnotte {
    Connection cn = DataSource.getInstance().getConnection();
    
    public void ajouterDemande(cagnotte c){
        c.setDate_de_creation(Date.valueOf(LocalDate.now()));
        c.setEtat(0);
        String requete = "INSERT INTO cagnotte (nom, id_categorie, date_de_creation, date_de_debut, date_de_fin, montant_demande, montant_actuel, id_proprietaire, id_organisation, etat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setInt(2, c.getId_categorie());
            pst.setDate(3, c.getDate_de_creation());
            pst.setDate(4, c.getDate_de_debut());
            pst.setDate(5, c.getDate_de_fin());
            pst.setFloat(6, c.getMontant_demande());
            pst.setFloat(7, c.getMontant_actuel());
            pst.setInt(8, c.getId_proprietaire());
            pst.setInt(9, c.getId_organisation());
            pst.setInt(10, c.getEtat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Demande ajoutée!");
    }
    
    public void modifierDemande(cagnotte c){
        String requete = "UPDATE cagnotte SET nom = ?, id_categorie = ?, date_de_debut = ?, date_de_fin = ?, montant_demande = ?, montant_actuel = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setInt(2, c.getId_categorie());
            pst.setDate(3, c.getDate_de_debut());
            pst.setDate(4, c.getDate_de_fin());
            pst.setFloat(5, c.getMontant_demande());
            pst.setFloat(6, c.getMontant_actuel());
            pst.setInt(7, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Demande modifiée!");
    }
    
    public void supprimerCagnotte(int id){
        String requete = "DELETE FROM cagnotte WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Demande supprimée!");
    }
    
    public void validerDemande(cagnotte c){
        c.setEtat(1);
        c.setId_organisation(2); //Tempo
        String requete = "UPDATE cagnotte SET etat = ?, id_organisation = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, c.getEtat());
            pst.setInt(2, c.getId_organisation());
            pst.setInt(3, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Demande validée!");
    }
    
    public void pauseCagnotte(cagnotte c){
        c.setEtat(0);
        String requete = "UPDATE cagnotte SET etat = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, c.getEtat());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cagnotte paused!");
    }
    
    public void ajouterDon(cagnotte c, String nom, float x, String methode){
        //ajouter participation
        if(c.getEtat() == 0){
            System.out.println("Don non ajouté: Cagnotte fermée!");
            return;
        }
        float y = getMontantActuel(c);
        System.out.println("Montant actuel: " + c.getMontant_actuel());
        c.setMontant_actuel(y + x);
        c.setEtat(siMontantAcheve(c));
        String requete = "UPDATE cagnotte SET montant_actuel = ?, etat = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setFloat(1, c.getMontant_actuel());
            pst.setInt(2, c.getEtat());
            pst.setInt(3, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(x + " DT on été ajouté!");
        GestionnaireDonation gd = new GestionnaireDonation();
        donation d = new donation(c.getId(), 1, nom, x, methode);
        gd.ajouterDonation(d);
        CagnotteMailing mail = new CagnotteMailing();
        try {
            mail.sendMail("protodanger@gmail.com");
        } catch (Exception ex) {
        }
    }
    
    public List<cagnotte> getCagnottes(){
        List<cagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM cagnotte";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cagnotte c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
                list.add(c);
            }
            System.out.println("List des cagnottes à été crée!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<cagnotte> getCagnottesSearch(String search_key){
        List<cagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM cagnotte WHERE nom LIKE ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, "%"+search_key+"%");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cagnotte c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
                list.add(c);
            }
            System.out.println("List des cagnottes à été crée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List<cagnotte> getCagnottesEtatFalse(){
        List<cagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM cagnotte WHERE etat = 0";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cagnotte c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
                list.add(c);
            }
            System.out.println("List des cagnottes à été crée!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<cagnotte> getCagnottesEtatTrue(){
        List<cagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM cagnotte WHERE etat = 1";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cagnotte c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
                list.add(c);
            }
            System.out.println("List des cagnottes à été crée!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<cagnotte> getCagnottesEtatTrueSearch(String search_key){
        List<cagnotte> list = new ArrayList<>();
        String requete = "SELECT * FROM cagnotte WHERE etat = 1 AND nom LIKE ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, "%"+search_key+"%");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                cagnotte c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
                list.add(c);
            }
            System.out.println("List des cagnottes à été crée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List<String> getCagnottesNames(){
        List<String> list = new ArrayList<>();
        String requete = "SELECT nom FROM cagnotte";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String c = rs.getString(1);          
                list.add(c);
            }
            System.out.println("List des nom des cagnottes à été crée!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<String> getCagnottesNamesEtatFalse(){
        List<String> list = new ArrayList<>();
        String requete = "SELECT nom FROM cagnotte WHERE etat = 0";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String c = rs.getString(1);          
                list.add(c);
            }
            System.out.println("List des nom des cagnottes à été crée!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    

    
    public cagnotte getCagnotteFromName(String nom){
        String requete = "SELECT * FROM cagnotte WHERE nom = ?";
        cagnotte c = null;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            rs.next();
            c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
            System.out.println("Cagnotte à été trouvé par nom!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    public cagnotte getCagnotteFromId(int id){
        String requete = "SELECT * FROM cagnotte WHERE id = ?";
        cagnotte c = null;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            c = new cagnotte(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));                
            System.out.println("Cagnotte à été trouvé!");
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public float getMontantActuel(cagnotte c){
        String requete = "SELECT montant_actuel FROM cagnotte WHERE id = ?";
        float y = 0;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setFloat(1, c.getId());
            ResultSet rs = pst.executeQuery();
            rs.next();
            y = rs.getFloat(1);                
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }
    
    public int siMontantAcheve(cagnotte c){
        System.out.println("Montant actuel: " + c.getMontant_actuel());
        String requete = "SELECT montant_demande FROM cagnotte WHERE id = ?";
        float montant_demande = 0;
        float montant_actuel = 0;
        int etat = 1;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, c.getId());
            ResultSet rs = pst.executeQuery();
            rs.next();
            montant_demande = rs.getFloat(1);                
            montant_actuel = c.getMontant_actuel();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCagnotte.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (montant_demande <= montant_actuel){
            etat = 0;
        }
        System.out.println("Returned etat: " + etat);
        return etat;
    }
    
    public HashMap<String, Integer> nbMontantCategorie() {
        GestionnaireCategorie gc = new GestionnaireCategorie();
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT id_categorie,SUM(montant_actuel) FROM `cagnotte` GROUP BY id_categorie";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                String st2 = gc.getCategorieNomFromId(rs.getInt(1));
                hm.put(st2, rs.getInt(2));
                System.out.println("String: " + st2 + ", Int: " + rs.getInt(2));
            }
        } catch (SQLException ex) {
        }
        return hm;
    }
    
    public float getMontantCollecte(){
        String qSql = "SELECT SUM(montant_actuel) FROM `cagnotte`";
        float t = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            rs.next();
            t = rs.getFloat(1);
        } catch (SQLException ex) {
        }
        return t;
    }
    
    public String getOrganisationActive(){
        GestionnaireCategorie gc = new GestionnaireCategorie();
        String qSql = "SELECT id_organisation,count(id_organisation) FROM `cagnotte` where id_organisation <> 0 GROUP BY id_organisation";
        int t = 0;
        int maxId = 0;
        int maxCount = 0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next()){
                if ((rs.getInt(2) >= maxCount) && (rs.getInt(2) != 0)){
                    maxId = rs.getInt(1);
                    maxCount = rs.getInt(2);
                    System.out.println("Max id: " + maxId);
                    System.out.println("Max count: " + maxCount);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
        return gc.getCategorieNomFromId(maxId); //to be edited
    }
    
    public void checkIfCagnotteExpiredAndClose(){
        String requete = "UPDATE cagnotte SET etat = 0 WHERE date_de_debut > date_de_fin";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Liste checked if expired.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
