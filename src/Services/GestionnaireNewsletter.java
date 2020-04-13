/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Newsletter;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Amine Gongi
 */
public class GestionnaireNewsletter {

    Connection cnx = DataSource.getInstance().getConnection();

    public void AjouterNews(Newsletter n) {

        String qSql = "INSERT INTO newsletter (`libelle`, `objetMail`, `corpsMail`) VALUES (?,?,?)";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setString(1, n.getLibelle());
            pst.setString(2, n.getObjetMail());
            pst.setString(3, n.getCoprsMail());

            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("News ADD Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("News Add Erreur !!!");
        }
    }

    public void modifierNewsByID(Newsletter n, int id) {

        String qSql = "UPDATE `newsletter` SET `libelle`=?,`objetMail`=?,`corpsMail`=? WHERE id=?";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setString(1, n.getLibelle());
            pst.setString(2, n.getObjetMail());
            pst.setString(3, n.getCoprsMail());
            
            pst.setInt(4,id);

            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("News modif Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("News modif Erreur !!!");
        }
    }
    
    public void modifierNewsByNews(Newsletter oldNews, Newsletter newNews) {

        String qSql = "UPDATE `newsletter` SET `libelle`=?,`objetMail`=?,`corpsMail`=? WHERE id=?";

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setString(1, newNews.getLibelle());
            pst.setString(2, newNews.getObjetMail());
            pst.setString(3, newNews.getCoprsMail());
            
            pst.setInt(4,oldNews.getId());

            System.out.println(pst);
            pst.executeUpdate();
            System.out.println("News modif Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("News modif Erreur !!!");
        }
    }

    public List<Newsletter> fetchNews() {
        List<Newsletter> listNews = new ArrayList<>();
        String qSql = "select * from newsletter ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setId(rs.getInt(1));
                n.setLibelle(rs.getString(2));
                n.setObjetMail(rs.getString(3));
                n.setCoprsMail(rs.getString(4));
                n.setDateEnvoi(rs.getDate(5));
                n.setDateAjout(rs.getDate(6));

                listNews.add(n);
            }
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return listNews;
    }

    public Newsletter fetchOneNewsById(int id) {
        Newsletter n = new Newsletter();
        String qSql = "select * from newsletter where id='" + id + "'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                n.setId(rs.getInt(1));
                n.setLibelle(rs.getString(2));
                n.setObjetMail(rs.getString(3));
                n.setCoprsMail(rs.getString(4));
                n.setDateEnvoi(rs.getDate(5));
                n.setDateAjout(rs.getDate(6));

            }
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return n;
    }

    public Newsletter fetchLastNews() {
        Newsletter n = new Newsletter();
        String qSql = "select * from newsletter Order By dateAjout DESC LIMIT 1 ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                n.setId(rs.getInt(1));
                n.setLibelle(rs.getString(2));
                n.setObjetMail(rs.getString(3));
                n.setCoprsMail(rs.getString(4));
                n.setDateEnvoi(rs.getDate(5));
                n.setDateAjout(rs.getDate(6));

            }
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return n;
    }

    public List<Newsletter> fetchNewsbyLibelle(String lib) {
        List<Newsletter> listNews = new ArrayList<>();
        String qSql = "select * from newsletter where libelle like '" + "%" + lib + "%" + "' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setId(rs.getInt(1));
                n.setLibelle(rs.getString(2));
                n.setObjetMail(rs.getString(3));
                n.setCoprsMail(rs.getString(4));
                n.setDateEnvoi(rs.getDate(5));
                n.setDateAjout(rs.getDate(6));

                listNews.add(n);
            }
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return listNews;
    }

    /**
     *
     * Une méthode qui vous permet de rechercher dans n'importe quel champs
     *
     * @param param peut être libelle , objetMail ,dateAjout , dateEnvoi
     * @param chaine Strig to find
     * @return List<Newsletter>
     */
    public List<Newsletter> fetchNewsbyParam(String param, String chaine) {
        List<Newsletter> listNews = new ArrayList<>();
        String qSql = "select * from newsletter where " + param + " like '" + "%" + chaine + "%" + "'  ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setId(rs.getInt(1));
                n.setLibelle(rs.getString(2));
                n.setObjetMail(rs.getString(3));
                n.setCoprsMail(rs.getString(4));
                n.setDateEnvoi(rs.getDate(5));
                n.setDateAjout(rs.getDate(6));

                listNews.add(n);
            }
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return listNews;
    }

    public void supprimerNewsByNews(Newsletter n) {

        String qSql = "DELETE FROM newsletter WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, n.getId());
            pst.executeUpdate();
            System.out.println("News Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Supp Erreur !!!");
        }
    }

    public void supprimerNewsById(int id) {

        String qSql = "DELETE FROM newsletter WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("News Supp Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Supp Erreur !!!");
        }
    }
    
    public void sendNews(Newsletter n){
        if(sendMail(n)){
            String qSql = "UPDATE `newsletter` SET dateEnvoi=SYSDATE() WHERE id=?";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(qSql);
                pst.setInt(1,n.getId());
                System.out.println(pst);
                pst.executeUpdate();
                System.out.println("News envoi Bravo ");
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
                System.out.println("News envoi Erreur !!!");
            }
        }
    }
    
    public boolean sendMail(Newsletter news) {
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
        String myAccountEmail = "aminegongiesprit@gmail.com"; //DoNation.Pidev@gmail.com
        //Your gmail password
        String password = "Amine1998"; //Donation2020

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        System.out.println(getInscriNews());
        //Prepare email message
        //Message message = prepareMessage(session, myAccountEmail, user.getMail(), user);
        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        InternetAddress[] iAdressArray = InternetAddress.parse(getInscriNews());
        message.setRecipients(Message.RecipientType.TO, iAdressArray);
        message.setSubject(news.getObjetMail());
        
        String htmlCode = news.getCoprsMail();

            message.setContent(htmlCode, "text/html");
            //Send mail
            Transport.send(message);
            System.out.println("News send OK");
            return true;
        } catch (Exception ex) {
            System.out.println("News send Problem");
            return false;
        }
    }
    
    public String getInscriNews(){
        String newsInscri = "";
        String qSql = "SELECT mailNews FROM `inscrinewsletter`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                newsInscri+=rs.getString(1)+",";
            }
            newsInscri=newsInscri.substring(0, newsInscri.length() - 1);
            System.out.println("News Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("News Select Erreur !!!");
        }
        return newsInscri;
    }
}
