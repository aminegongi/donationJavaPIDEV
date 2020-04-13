/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.DemandeAide;
import Entities.Participation;
import Entities.UserTest;
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
public class ServiceParticipation {
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    
    public ServiceParticipation() {
        
    con=DataSource.getInstance().getConnection();

    }
     
    public void ajouter(Participation p) throws SQLException {
        
    String sql = "INSERT INTO `participation` ( `id_demande`, `id_user` ) VALUES ( ?, ? );";
    PreparedStatement pre = con.prepareStatement(sql);
    pre.setInt(1, p.getIdDemande());
    pre.setInt(2, p.getIdUser());
    pre.executeUpdate();
    //modification de la table demande si etat = visible( 0 participations)
    int idDmnd = p.getIdDemande();
    ServiceDemandeAide ser = new ServiceDemandeAide();
    DemandeAide d = ser.readById(idDmnd);
    if( d.getEtat() == Etat.VISIBLE)
    {   System.out.println("demande avant update");
        System.out.println(ser.readById(idDmnd));
       d.setEtat(Etat.VALIDE);
       ser.update(idDmnd, d);
       System.out.println("demande apres update");
       System.out.println(ser.readById(idDmnd));
        
    }
    }
    
    //RIEN A MODIFIER POUR LE MOMENT    
    //id = id de  participation à modifier, pN = nouvelle participation
    public boolean update(int id, Participation pN) throws SQLException {
    
    String sql ="UPDATE participation SET id_demande=?, id_user=? WHERE id=?";
    pre= con.prepareStatement(sql);
    pre.setInt(1, pN.getIdDemande());
    pre.setInt(2, pN.getIdUser());
    pre.setInt(3, id);
    
    int rowsUpdated = pre.executeUpdate();
    if (rowsUpdated > 0) {
    System.out.println("An existing participation was updated successfully!");
    return true;
    }
    return false;
    }
    
    
    public boolean delete(int idDemande, int idUser) throws SQLException {
    
    String sql = "DELETE FROM participation WHERE id_demande=? AND id_user=?";
    pre = con.prepareStatement(sql);
    pre.setInt(1, idDemande);
    //pre.setInt(2, idUser);
    //pourtest
    pre.setInt(2, UserTest.id);
    int rowsDeleted = pre.executeUpdate();
    if (rowsDeleted > 0) {
    System.out.println("A participation was deleted successfully!");
    return true;
}
    return false;
    }
    
    
    public List<Participation> readAll() throws SQLException {
    List<Participation> arr = new ArrayList<>();
    String sql = "SELECT * FROM participation";
    ste = con.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    
    while(rs.next()){
            int idDemande=rs.getInt(1);
            int idUser=rs.getInt(2);
            Participation p =new Participation(idDemande, idUser);
            arr.add(p);
    }
    
    return arr;
    
}
    
    
    //retourne les participations d'un user et prend l'id user comme parametre
    public List<Participation> ParticipationsUser(int id) throws SQLException{
    List<Participation> arr = new ArrayList<>();
    String sql = "SELECT * FROM participation WHERE id_user=?";
    pre = con.prepareStatement(sql);
    //pre.setInt(1, id);
    pre.setInt(1, UserTest.id);
    ResultSet rs = pre.executeQuery();
    while(rs.next()){
            int idDemande=rs.getInt(1);
            int idUser=rs.getInt(2);
            Participation p =new Participation(idDemande, idUser);
            arr.add(p);
    }
    
    return arr;
    }
    
    //retourne les participations pour une demande et prend l'id demande comme parametre
    public List<Participation> ParticipationsDemande(int id) throws SQLException{
    List<Participation> arr = new ArrayList<>();
    String sql = "SELECT * FROM participation WHERE id_demande=?";
    pre = con.prepareStatement(sql);
    pre.setInt(1, id);
    ResultSet rs = pre.executeQuery();
    while(rs.next()){
            int idDemande=rs.getInt(1);
            int idUser=rs.getInt(2);
            Participation p =new Participation(idDemande, idUser);
            arr.add(p);
    }
    
    return arr;
    }
    
    
    //retourne le nombre des participants(participations) pour une demande
    public int nbrParticipationsDemande(int id) throws SQLException{
    List<Participation> arr = this.ParticipationsDemande(id);
    System.out.println("nbr des participants pour demande de ID  "+id+" est: "+arr.size());
    return arr.size();
    }
    
}
