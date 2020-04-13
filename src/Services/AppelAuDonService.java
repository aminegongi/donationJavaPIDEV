/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Utils.DataSource;
import Entities.AppelAuDon;
import Entities.OffreDon;
import Entities.Publicité;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Fourati
 */
public class AppelAuDonService {
    Connection cn = DataSource.getInstance().getConnection(); 
        
        public void ajouter(AppelAuDon od ) {
        
    String requette = "INSERT INTO `publicationdon`(`type`, `titre`, `description`, `datePublication`, `nbreUp`, `nbrePlat`, `etat`, ajoutePar) VALUES (?,?,?,?,?,?,?,?);" ; 
       
            PreparedStatement pst;
            try {
                pst = cn.prepareStatement(requette);
                pst.setString(1,"AppelAuDon");
                pst.setString(2, od.getTitre());
                pst.setString(3, od.getDescription());
                pst.setString(4, od.getDatePublicaton());
                pst.setInt(5, od.getNbreUp());
                pst.setInt(6, od.getNbrePlat());
                pst.setInt(7, od.getEtat());
                pst.setInt(8, od.getAjoutePar());
                
                pst.executeUpdate() ; 
            } catch (SQLException ex) {
                Logger.getLogger(OffreDonService.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        
    }
        
        public void modifier(AppelAuDon p, int id) {
        String qSql = "UPDATE publicationdon SET titre=?,description=?,nbreUp=?,nbrePlat=?,etat=? WHERE (id=? and type='AppelAuDon')";
        PreparedStatement pst = null;
        
        try {
            pst = cn.prepareStatement(qSql);
            pst.setString(1, p.getTitre());
            pst.setString(2, p.getDescription());
            pst.setInt(3, p.getNbreUp());
            pst.setInt(4, p.getNbrePlat());
            pst.setInt(5, p.getEtat());
            pst.setInt(6, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AppelAuDonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
        
    public List<AppelAuDon> afficher(){
    String sql = "SELECT `id`, `titre`, `description`, `datePublication`, `nbreUp`, `nbrePlat`, `etat`, ajoutePar FROM `publicationdon` WHERE type='AppelAuDon'" ;
    List<AppelAuDon> list = new ArrayList<>() ;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
              AppelAuDon o = new AppelAuDon();
              o.setId(rs.getInt(1));
              o.setTitre(rs.getString(2));
              o.setDescription(rs.getString(3));
              o.setDatePublicaton(rs.getString(4));
              o.setNbreUp(rs.getInt(5));
              o.setNbrePlat(rs.getInt(6));
              o.setEtat(rs.getInt(7));
              o.setAjoutePar(rs.getInt(8));
              list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreDonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
    public void supprimer(AppelAuDon o) {

        try {
            String requete = "DELETE FROM `publicationdon` WHERE `id`=? ";

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, o.getId());
            pst.executeUpdate();
            System.out.println("AppelAuDon supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
