/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DemandeAide;
import Entities.Signalement;
import Utils.DataSource;
import Utils.Etat;
import Utils.RaisonSignale;
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
public class ServiceSignalement {

    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceSignalement() {
    con=DataSource.getInstance().getConnection();
    }
    
    
    public void ajouter(Signalement s) throws SQLException {
        
    String sql = "INSERT INTO `signalement` ( `id_demande`, `id_user`, `raison`, `description` ) VALUES ( ?, ?, ?, ? );";
    PreparedStatement pre = con.prepareStatement(sql);
    pre.setInt(1, s.getIdDemande());
    pre.setInt(2, s.getIdUser());
    pre.setString(3, s.getRaisonAsString());
    pre.setString(4, s.getDescription());
    pre.executeUpdate();
    
    //modification de la table demande ( etat = signalee )
    int idDmnd = s.getIdDemande();
    ServiceDemandeAide ser = new ServiceDemandeAide();
    DemandeAide d = ser.readById(idDmnd);
    if( this.nbrSignalementDmnd(idDmnd) > 5 )
    {   System.out.println("demande avant update");
        System.out.println(ser.readById(idDmnd));
       d.setEtat(Etat.SIGNALEE);
       ser.update(idDmnd, d);
       System.out.println("demande apres update");
       System.out.println(ser.readById(idDmnd));
        
    }

    }
    
    //retourne les signalements d'une demande et prend l'id de demande comme parametre
    public List<Signalement> listSignalementDmnd(int id) throws SQLException{
    List<Signalement> arr = new ArrayList<>();
    String sql = "SELECT * FROM signalement WHERE id_demande=?";
    pre = con.prepareStatement(sql);
    pre.setInt(1, id);
    ResultSet rs = pre.executeQuery();
    while(rs.next()){
            int idDemande=rs.getInt(1);
            int idUser=rs.getInt(2);
            RaisonSignale raison =RaisonSignale.valueOf(rs.getString("raison"));
            String description =rs.getString(4);
            Signalement s =new Signalement(idDemande, idUser, raison, description);
            
            arr.add(s);
    }
    
    return arr;
    }

    //retourne nombre de signalement pour une demande
    public int nbrSignalementDmnd(int id) throws SQLException{
        int nbr = this.listSignalementDmnd(id).size();
        return nbr;
    }
}