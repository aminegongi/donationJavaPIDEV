/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.donation;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import javax.mail.*;
//import javax.mail.internet.*;

/**
 *
 * @author Ahmed
 */
public class GestionnaireDonation {
    Connection cn = DataSource.getInstance().getConnection();

    public void ajouterDonation(donation d){
        d.setDate(Date.valueOf(LocalDate.now()));
        String requete = "INSERT INTO donations (id_cagnotte, id_utilisateur, nom, montant, date_don, methode) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, d.getId_cagnotte());
            pst.setInt(2, d.getId_utilisateur());
            pst.setString(3, d.getNom());
            pst.setFloat(4, d.getMontant());
            pst.setDate(5, d.getDate());
            pst.setString(6, d.getMethode());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //sendMail(d);
        System.out.println("Donation ajoutée!");
        
    }
    
    public void modifierDonation(donation d){
        String requete = "UPDATE donations SET id = ?, id_cagnotte = ?, id_utilisateur = ?, date_don = ?, methode = ? WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.setInt(2, d.getId_cagnotte());
            pst.setInt(3, d.getId_utilisateur());
            pst.setDate(4, d.getDate());
            pst.setString(5, d.getMethode());
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println("Donation modifé!");
    }
    
    public void supprimerDonation(int id){
        String requete = "DELETE FROM donations WHERE id = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println("Donation supprimée!");
    }
    
    public List<donation> getDonations(){
        List<donation> list = new ArrayList<>();
        String requete = "SELECT * FROM donations";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                donation d = new donation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));                
                list.add(d);
            }
            System.out.println("List des dons à été crée!");
        } catch (SQLException ex) {
        }
        return list;
    }
    
    public donation getDonation(int id){
        String requete = "SELECT * FROM donation WHERE id = ?";
        donation d = null;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            d = new donation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5));
            System.out.println("Donation à été trouvé!");
        } catch (SQLException ex) {
        }
        return d;
    }
    /*
        public static void sendMail(donation don) {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "aminegongiesprit@gmail.com";
        //Your gmail password
        String password = "Amine1998";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        //Message message = prepareMessage(session, myAccountEmail, user.getMail(), user);
        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("ahmed.zeghibi@esprit.tn"));

        message.setSubject("Donation , Votre don");
        
        String htmlCode = "<h1> Merci pour votre don. </h1>";
        
            
            
            message.setContent(htmlCode, "text/html");
            //Send mail
            Transport.send(message);
           

        } catch (Exception ex) {

            System.out.println("opioooioi");
        }
    }
    */

}
