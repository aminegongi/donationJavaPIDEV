/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.DemandeAide;
import Entities.UserTest;
import static Entities.UserTest.id;
import Utils.DataSource;
import Utils.Etat;
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

public class ServiceDemandeAide {
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceDemandeAide() {
        
    con=DataSource.getInstance().getConnection();

    }
    
    public void ajouter(DemandeAide d) throws SQLException {
        
    String sql = "INSERT INTO `demande` ( `id_categorie`, `id_user`, `titre`, `description`, `etat`, `nb_reactions` ) VALUES ( ?, ?, ?, ?, ?, ? );";
    PreparedStatement pre = con.prepareStatement(sql);
    pre.setInt(1, d.getIdCategorie());
    pre.setInt(2, d.getIdUser());
    pre.setString(3, d.getTitre());
    pre.setString(4, d.getDescription());
    pre.setString(5, d.getEtatAsString());
    //pre.setInt(6, d.getNbParticipants());
    pre.setInt(6, d.getNbReactions());
    //pre.setInt(8, d.getNbCommentaires());
    pre.executeUpdate();
    }
        
    //id = id de  demande à modifier, dN = nouvelle demande
    public boolean update(int id, DemandeAide dN) throws SQLException {
    
    //String sql ="UPDATE categorie SET nom=? WHERE id=?";
    String sql ="UPDATE demande SET id_categorie=?, id_user=?, titre=?, description=?, etat=?, nb_reactions=? WHERE id=?";
    pre= con.prepareStatement(sql);
    pre.setInt(1, dN.getIdCategorie());
    pre.setInt(2, dN.getIdUser());
    pre.setString(3, dN.getTitre());
    pre.setString(4, dN.getDescription());
    pre.setString(5, dN.getEtatAsString());
    //pre.setInt(6, dN.getNbParticipants());
    pre.setInt(6, dN.getNbReactions());
    //pre.setInt(8, dN.getNbCommentaires());
    pre.setInt(7, id);
    
    int rowsUpdated = pre.executeUpdate();
    if (rowsUpdated > 0) {
    System.out.println("An existing demande was updated successfully!");
    return true;
    }
    return false;
    }
    
    
    public boolean delete(int id) throws SQLException {
    
    String sql = "DELETE FROM demande WHERE id=?";
    pre = con.prepareStatement(sql);
    pre.setInt(1, id);
    int rowsDeleted = pre.executeUpdate();
    if (rowsDeleted > 0) {
    System.out.println("A demande was deleted successfully!");
    return true;
}
    return false;
    }
    
    
    public List<DemandeAide> readAll() throws SQLException {
    List<DemandeAide> arr = new ArrayList<>();
    String sql = "SELECT * FROM demande";
    ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(id, idCategorie, idUser,  titre,  description, etat,  nbReactions);
            arr.add(d);
    }
    
    return arr;
    
}
    
    
    //retourne que les demandes non signalées
    public List<DemandeAide> readNotSign() throws SQLException {
    List<DemandeAide> arr = new ArrayList<>();
    String sql = "SELECT * FROM demande where etat <> 'SIGNALEE'";
    ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(id, idCategorie, idUser,  titre,  description, etat,  nbReactions);
            arr.add(d);
    }
    
    return arr;
    
} 
    
    
    
    
        //retourne que les demandes non signalées avec idUserReel from table
    public List<DemandeAide> readNotSignSql() throws SQLException {
    List<DemandeAide> arr = new ArrayList<>();
    String sql = "SELECT * FROM demande where etat <> 'SIGNALEE'";
    ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int id=rs.getInt(1);
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(titre, idCategorie, idUser,  id,  description, etat,  nbReactions);
            arr.add(d);
    }
    
    return arr;
    
} 
    
        //idUser from table not userTest
        public  DemandeAide readByIdSql(int id) throws SQLException{
        
        String sql = "SELECT * FROM demande WHERE id=?";
        pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(titre, idCategorie, idUser,  id,  description, etat,  nbReactions);
            System.out.println("la demande de ID "+id+" existe");
            System.out.println(d);
            return d;
            }
        else{
            System.out.println("la demande de ID "+id+" n'existe pas");
            DemandeAide d = new DemandeAide();
            return d;
            }
    }
    
    
    
    
    
    
    public  DemandeAide readById(int id) throws SQLException{
        
        String sql = "SELECT * FROM demande WHERE id=?";
        pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(id, idCategorie, idUser,  titre,  description, etat,  nbReactions);
            System.out.println("la demande de ID "+id+" existe");
            System.out.println(d);
            return d;
            }
        else{
            System.out.println("la demande de ID "+id+" n'existe pas");
            DemandeAide d = new DemandeAide();
            return d;
            }
    }
    
   
    public void reagir(int id) throws SQLException{
    DemandeAide dA = this.readByIdSql(id);
    int nbReactions=dA.getNbReactions();
    nbReactions++;   
    int idCategorie=dA.getIdCategorie();
    int idUser=dA.getIdUser();
    String titre=dA.getTitre();
    String description=dA.getDescription();
    Etat etat=dA.getEtat();
    //int nbParticipants=dA.getNbParticipants();
    //int nbCommentaires=dA.getNbCommentaires();
    //Creation d'un objet demande contenant les memes infos sauf nbReactions+1
    DemandeAide dN = new DemandeAide(titre, idUser, idCategorie, description, etat, nbReactions);
    this.update(id, dN);
    
    }
    
    /*
    public void commenter(int id) throws SQLException{
    DemandeAide dA = this.readById(id);
    int nbCommentaires=dA.getNbCommentaires();
    nbCommentaires++;   
    String titre=dA.getTitre();
    String description=dA.getDescription();
    Etat etat=dA.getEtat();
    int nbParticipants=dA.getNbParticipants();
    int nbReactions=dA.getNbReactions();
    //Creation d'un objet demande contenant les memes infos sauf nbCommentaires+1
    DemandeAide dN = new DemandeAide(titre, description, etat, nbParticipants, nbReactions, nbCommentaires);
    this.update(id, dN);
    
    }*/
    /*
    public void signaler(int id){
    
        //change l'etat si etat!= signalee
        //incremente le nb des signales
        deux choix :
               - Ajouter field "nb_signales" (!=0 si etat = Etat.SIGNALEE)
                   ou bien
               - Table Demande_signalee pour savoir qui  a signalé la demande et ajouter des descriptions...etc
        
    }
   
    */
    
    public DemandeAide derniereDmndAjout() throws SQLException{
    
        String sql = "SELECT MAX(id) FROM demande";
        pre = con.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            int id = rs.getInt(1);
            DemandeAide dmnd = new DemandeAide();
           dmnd = this.readById(id);
           System.out.println(id);
           System.out.println(id);
           return dmnd;
            }
        else{
            System.out.println("probleme");
            DemandeAide dmnd = new DemandeAide();
            return dmnd;
            }
        
    }
    
    
    
    
    
    //read all demande by id user
    public  List<DemandeAide> readByIdUser() throws SQLException{
        List<DemandeAide> arr = new ArrayList<>();
        String sql = "SELECT * FROM demande WHERE id_user=?";
        pre = con.prepareStatement(sql);
        pre.setInt(1, UserTest.id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()){
            int id=rs.getInt(1);
            int idCategorie=rs.getInt(2);
            int idUser=rs.getInt(3);
            String titre=rs.getString("titre");
            String description=rs.getString(5);
            Etat etat=Etat.valueOf(rs.getString("etat"));
            //int nbParticipants=rs.getInt(7);
            int nbReactions=rs.getInt(7);
            //int nbCommentaires=rs.getInt(9);
            DemandeAide d =new DemandeAide(id, idCategorie, idUser,  titre,  description, etat,  nbReactions);
            
            arr.add(d);
        }
            return arr;
            }

    
    
    
    
   
}

