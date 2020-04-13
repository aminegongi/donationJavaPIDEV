/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DonRestaurant;
import Entities.RestoUser;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safratix
 */
public class ServiceRestoUser {
    private Connection con;
    private Statement ste;

    public ServiceRestoUser() {
        con = DataSource.getInstance().getConnection();

    }
    
     
    public List<RestoUser> readAll() throws SQLException {
    List<RestoUser> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select `A_pays`, `A_ville`, `image`, `nom`, `numTel`, `siteWeb`, `pageFB`, `longitude`, `latitude` FROM `utilisateurs` WHERE `role` LIKE 'resto'");
     while (rs.next()) {    
                 String pays=rs.getString(1);
                 String ville=rs.getString(2);
                 String image=rs.getString(3);
                 String nomResto=rs.getString(4);
                 String numTel=rs.getString(5);
                 String siteWeb=rs.getString(6);
                 String pageFb=rs.getString(7);
                 String longitude=rs.getString(8);
                 String latitude=rs.getString(9);
                 String coord = longitude+","+latitude;
               RestoUser ru=new RestoUser(nomResto, pays, ville, numTel, siteWeb, pageFb, coord, image);
     arr.add(ru);}
     
    return arr;
    }
    
    
    public String userOrResto(int id) throws SQLException{
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select role from utilisateurs WHERE `id` = '" + id + "';");
    rs.next();    
    String role=rs.getString(1);
    return role;
    }
    
}
