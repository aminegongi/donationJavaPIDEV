/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Utils.DataSource;
import Entities.AppelAuDon;

import Entities.OffreDon;
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
public class OffreDonService  {
        Connection cn = DataSource.getInstance().getConnection(); 
        
        public void ajouter(OffreDon od ){
        
    String requette = "INSERT INTO `publicationdon`(`type`, `titre`, `description`, `datePublication`, `nbreUp`, `etat` , ajoutePar) VALUES (?,?,?,?,?,?,?);" ; 
       
            PreparedStatement pst;
            try {
                pst = cn.prepareStatement(requette);
                pst.setString(1,"OffreDon");
                pst.setString(2, od.getTitre());
                pst.setString(3, od.getDescription());
                pst.setString(4, od.getDatePublicaton());
                pst.setInt(5, od.getNbreUp());
                pst.setInt(6, od.getEtat());
                pst.setInt(7, od.getAjoutePar());
                
                pst.executeUpdate() ; 
            } catch (SQLException ex) {
                Logger.getLogger(OffreDonService.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        
    }
    public void modifier(OffreDon p, int id) {
        String qSql = "UPDATE publicationdon SET titre=?,description=?,nbreUp=?,etat=? WHERE (id=? and type='OffreDon')";
        PreparedStatement pst = null;
        
        try {
            pst = cn.prepareStatement(qSql);
            pst.setString(1, p.getTitre());
            pst.setString(2, p.getDescription());
            pst.setInt(3, p.getNbreUp());
            pst.setInt(4, p.getEtat());
            pst.setInt(5, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OffreDonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }     
    
    
    
    public List<OffreDon> afficher(){
    String sql = "SELECT `id`, `titre`, `description`, `datePublication`, `nbreUp`, `etat` , ajoutePar FROM `publicationdon` WHERE type='OffreDon'" ;
    List<OffreDon> list = new ArrayList<>() ;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                OffreDon o = new OffreDon();
              o.setId(rs.getInt(1));
              o.setTitre(rs.getString(2));
              o.setDescription(rs.getString(3));
              o.setDatePublicaton(rs.getString(4));
              o.setNbreUp(rs.getInt(5));
              o.setEtat(rs.getInt(6));
              o.setAjoutePar(rs.getInt(7));

              
              list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreDonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    public List<OffreDon> trierParDate(String type){
    List<OffreDon> list = this.afficher();
    if(type=="cr")
        list.sort((OffreDon of1 ,OffreDon of2) -> of1.getDatePublicaton().compareToIgnoreCase(of2.getDatePublicaton()) );
    else
        list.sort((OffreDon of1 ,OffreDon of2) -> of2.getDatePublicaton().compareToIgnoreCase(of1.getDatePublicaton()) );

            return list;
    }
    public List<OffreDon> trierParTitre(String type){
    List<OffreDon> list = this.afficher();
    if(type=="cr")
        list.sort((OffreDon of1 ,OffreDon of2) -> of1.getTitre().compareToIgnoreCase(of2.getTitre()) );
    else
        list.sort((OffreDon of1 ,OffreDon of2) -> of2.getTitre().compareToIgnoreCase(of1.getTitre()) );

            return list;
    }
    public List<OffreDon> trierParNbreUp(String type){
    List<OffreDon> list = this.afficher();
    if(type=="cr")
        list.sort((OffreDon of1 ,OffreDon of2) -> of1.getNbreUp()-(of2.getNbreUp()) );
    else
        list.sort((OffreDon of1 ,OffreDon of2) -> of2.getNbreUp()-(of1.getNbreUp()) );

            return list;
    }
    
    
    public void supprimer(OffreDon o) {

        try {
            String requete = "DELETE FROM `publicationdon` WHERE `id`=? ";

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, o.getId());
            pst.executeUpdate();
            System.out.println("offreDon supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
