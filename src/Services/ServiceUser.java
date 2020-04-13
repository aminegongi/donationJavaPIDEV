/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hedi
 */
public class ServiceUser {
    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceUser() {
     con=DataSource.getInstance().getConnection();   
    }
    
    public User readById(int id) throws SQLException{
        String sql = "SELECT * FROM utilisateurs WHERE id=?";
        pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()){
            
            
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String mail = rs.getString("mail");
            User user =new User(id,nom,prenom,mail);
            System.out.println(user);
            return user;
            }
        else{
            System.out.println("user de ID  "+id+" n'existe pas");
            User user =new User();
            return user;
            }
    }
    
    
}
