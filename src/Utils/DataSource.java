/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Amine Gongi
 */

/*
design patten singleton = bonne pratique 
How ?
constructeur privée + attribut static type de la classe + methode static type de la classe + condition verification attribut null ou non
*/
public class DataSource {
    Connection cnx;
    String url="jdbc:mysql://localhost:3306/donationw";
    String login="root";
    String mdp="";
    static DataSource instance;

    private DataSource() {
        System.out.println(url + login + mdp);
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connection Bravo !");
        } catch (SQLException ex) {
            System.out.println("No Connection Erreur !");
        }
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
    public static DataSource getInstance() {
        if(instance == null)
            instance=new DataSource();
        return instance;
    }
    
    
    
    
}
