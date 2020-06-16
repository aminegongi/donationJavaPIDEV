/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DonRestaurant;
import Entities.RestoUser;
import Utils.DataSource;
import java.sql.Array;
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
    ResultSet rs=ste.executeQuery("select `pays`, `ville`,  `image`, `nom`, `numTel`, `siteWeb`, `pageFB`, `longitude`, `latitude` FROM `fos_user` WHERE `roles` LIKE 'a:1:{i:0;s:8:\"ROLE_RES\";}'");
     while (rs.next()) { 
                 String pays = rs.getString("pays");
                 System.out.println(pays);
                 String ville = rs.getString("ville");
                 String image=rs.getString("image");
                 String nomResto=rs.getString("nom");
                 String numTel=rs.getString("numTel");
                 String siteWeb=rs.getString("siteWeb");
                 String pageFb=rs.getString("pageFB");
                 String longitude=rs.getString("longitude");
                 String latitude=rs.getString("latitude");
                 String coord = longitude+","+latitude;
               RestoUser ru=new RestoUser(nomResto, pays, ville, numTel, siteWeb, pageFb, coord, image);
     arr.add(ru);}
        System.out.println(arr);
    return arr;
    }
    
    
    public String userOrResto(int id) throws SQLException{
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select roles from fos_user WHERE `id` = '" + id + "';");
    rs.next();    
    String role=rs.getString(1);
        System.out.println(role);
    return role;
    }
    
}
