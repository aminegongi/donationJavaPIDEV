/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.Adresse;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Utilisateur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Amine Gongi
 */
public class GestionnaireUtilisateur {

    Connection cnx = DataSource.getInstance().getConnection();
    
    /** 
   * Novant aman les ektbohom haka
   * <p>
   * <b> ROLE_US </b> => user simple
   * <p>
   * <b> ROLE_ORG </b> => organisaation
   * <p>
   * <b> ROLE_ENT </b> => entreprise
   * <p>
   * <b> ROLE_RES </b> => restaurant
   * <p>
   */
    public List<Utilisateur> fetchUserByRole (String role){
        List<Utilisateur> listP = new ArrayList<>();
        String qSql="select "
                + "`id`, " //1
                + "`username`, " //2
                + "`username_canonical`, " //3
                + "`email`, " //4
                + "`email_canonical`, " //5
                + "`enabled`, " //6 
                + "`salt`," //7 
                + "`password`, " //8
                + "`last_login`, " //9
                + "`confirmation_token`, " //10
                + "`password_requested_at`, " //11
                + "`roles`," //12
                + " `nom`, " //13
                + "`numTel`, " //14
                + "`pays`, " //15
                + "`ville`, " //16
                + "`image`, " //17
                + "`pointXP`, " //18
                + "`prenom`, " //19
                + "`genre`," //20
                + " `dateNaissance`, " //21
                + "`pageFB`, " //22
                + "`siteWeb`, " //23
                + "`description`, " //24
                + "`longitude`, " //25
                + "`latitude`," //26 
                + " `yesNews` " //27
                + "from fos_user where roles like '%"+role+"%' ";
                //select id,email, numTel,pays, ville, image, pointXP ,nom, prenom, genre, dateNaissance from fos_user where roles like '%ROLE_US%'

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                Utilisateur u=new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                u.setEnabled(rs.getInt(6));
                u.setPassword(rs.getString(8));
                u.setRoles(role);
                u.setNom(rs.getString(13));
                u.setNumTel(rs.getString(14));
                u.setAdresse(new Adresse(rs.getString(15), rs.getString(16)));
                u.setImage(rs.getString(17));
                u.setPrenom(rs.getString(19));
                u.setGenre(rs.getString(20));
                u.setDateNaissance(rs.getDate(21));
                //System.out.println(rs.getString(21));
                
                u.setPageFB(rs.getString(22));
                u.setSiteWeb(rs.getString(23));
                u.setDescription(rs.getString(24));
                u.setLatitude(rs.getFloat(25));                
                u.setLongitude(rs.getFloat(26));
                u.setYesNews(rs.getInt(27));

                listP.add(u);
            }
            System.out.println("User Select Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
        return listP;
    }
    
    
    
    
    
    
    
    public void setImage(String img,int id){
            String qSql = "UPDATE utilisateurs SET image=? WHERE id=?";
            PreparedStatement pst = null;
            try {
                pst = cnx.prepareStatement(qSql);
                
                pst.setString(1, img);

                pst.setInt(2, id);
                pst.executeUpdate();
                System.out.println("US Modif Bravo ");
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
                System.out.println("US Modif Erreur !!!");
            }
    }
    
    // JAVA return -1= login Mail MDP incorrect / -2=compte disabled /-3=compte non encore activer / else ID user
    // Mysql champ val 0= disabled par admin / 1=activer /-1=non encore activer 
    public boolean  mailMdp(String mail, String mdp){
        String qSql = "select password from fos_user where email='" + mail + "' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            //pst.setString(1, mail);
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                System.out.println("*********dsds*************");
                if(BCrypt.checkpw(mdp, rs.getString(1).replace("$2y", "$2a") ))
                    return true;
            }
            System.out.println("U login Bravo ");
        } 
        catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("U login Erreur !!!");
        }
        return false;
    }
    public int loginU(String mail, String mdp) {
        Boolean m=mailMdp(mail, mdp);
        System.out.println("**********************");
        System.out.println(m);
        if(m){
            String qSql = "select id,email,enabled from fos_user where email='" + mail + "' Limit 1 ";
            try {
                Statement st = cnx.createStatement();
                //pst.setString(1, mail);
                st.executeQuery(qSql);
                ResultSet rs = st.executeQuery(qSql);
                while (rs.next()) {
                    System.out.println("******************rss****");
                    System.out.println(rs.getInt(3));
                    if (rs.getInt(3) == 0) {
                        return -2;
                    } else if (rs.getInt(3) == -1) {
                        return -3;
                    } else {
                        return rs.getInt(1);
                    }
                }
                System.out.println("U login Bravo ");
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
                System.out.println("U login Erreur !!!");
            }
            return -1;            
        }
        return -1;

    }

    public boolean checkMail(String mail) {
        String qSql = "select email from fos_user where email='" + mail + "' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            //pst.setString(1, mail);
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                return true;
            }
            System.out.println("U checkMail Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("U checkMail Erreur !!!");
        }
        return false;
    }

    public int getIdByMail(String mail) {
        String qSql = "select id from fos_user where email='" + mail + "' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            //pst.setString(1, mail);
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                return rs.getInt(1);
            }
            System.out.println("U checkMail Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("U checkMail Erreur !!!");
        }
        return -1;
    }
    
    public String getroleById(int id) {
        String qSql = "select roles from fos_user where id='" + id + "' Limit 1 ";
        try {
            Statement st = cnx.createStatement();
            //pst.setString(1, mail);
            st.executeQuery(qSql);
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                return rs.getString(1);
            }
            System.out.println("U role Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("U role Erreur !!!");
        }
        return "error";
    }

    public boolean desactiverCompte(int id) {
        String qSql = "UPDATE utilisateurs SET enabled=0  WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Utilisateur desactiver Bravo ");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Utilisateur desactiver Erreur !!!");
        }

        return false;
    }

    public boolean activerCompte(int id) {
        String qSql = "UPDATE utilisateurs SET enabled=1  WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Utilisateur activer Bravo ");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Utilisateur activer Erreur !!!");
        }

        return false;
    }

    public boolean activerCompteKeyMail(String mail, String key) {
        String qSql = "UPDATE utilisateurs SET enabled=1  WHERE mail=? AND confirm_token=?";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setString(1, mail);
            pst.setString(2, key);
            pst.executeUpdate();
            int x = pst.getUpdateCount();
            System.out.println(x);
            if (x > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Utilisateur activer Erreur !!!");
        }

        return false;
    }

    public float checkXP(int id) {
        float x = -1;
        String qSql = "select pointXP from utilisateurs where id='" + id + "' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                x = rs.getFloat(1);
            }
        } catch (SQLException ex) {
        }
        return x;
    }
    
    public Utilisateur fetchOneUS (int id){
        Utilisateur u = new Utilisateur();
        String qSql="select "
                + "`id`, " //1
                + "`username`, " //2
                + "`username_canonical`, " //3
                + "`email`, " //4
                + "`email_canonical`, " //5
                + "`enabled`, " //6 
                + "`salt`," //7 
                + "`password`, " //8
                + "`last_login`, " //9
                + "`confirmation_token`, " //10
                + "`password_requested_at`, " //11
                + "`roles`," //12
                + " `nom`, " //13
                + "`numTel`, " //14
                + "`pays`, " //15
                + "`ville`, " //16
                + "`image`, " //17
                + "`pointXP`, " //18
                + "`prenom`, " //19
                + "`genre`," //20
                + " `dateNaissance`, " //21
                + "`pageFB`, " //22
                + "`siteWeb`, " //23
                + "`description`, " //24
                + "`longitude`, " //25
                + "`latitude`," //26 
                + " `yesNews` " //27
                + "from fos_user where id='"+id+"' ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while(rs.next())
            {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                u.setEnabled(rs.getInt(6));
                u.setPassword(rs.getString(8));

                if(rs.getString(12).contains("US")) 
                    u.setRoles("ROLE_US");
                else if(rs.getString(12).contains("ORG")) 
                    u.setRoles("ROLE_ORG");
                else if(rs.getString(12).contains("RES")) 
                    u.setRoles("ROLE_RES");
                else if(rs.getString(12).contains("ENT")) 
                    u.setRoles("ROLE_ENT");
                else if(rs.getString(12).contains("ADMIN")) 
                    u.setRoles("admin");
                
                u.setNom(rs.getString(13));
                u.setNumTel(rs.getString(14));
                u.setAdresse(new Adresse(rs.getString(15), rs.getString(16)));
                u.setImage(rs.getString(17));
                u.setPrenom(rs.getString(19));
                u.setGenre(rs.getString(20));
                u.setDateNaissance(rs.getDate(21));
                //System.out.println(rs.getString(21));
                
                u.setPageFB(rs.getString(22));
                u.setSiteWeb(rs.getString(23));
                u.setDescription(rs.getString(24));
                u.setLatitude(rs.getFloat(25));                
                u.setLongitude(rs.getFloat(26));
                u.setYesNews(rs.getInt(27));
                
                //us.setDateNaissance(rs.getDate(12));
                
            }
            System.out.println("One U Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("One U Select Erreur !!!");
        }   
        return u;
    }
    
    public boolean pointXp(int id, float montant) {
        String qSql;
        if (checkXP(id) + montant > 0) {
            qSql = "UPDATE utilisateurs SET pointXp=pointXp+" + montant + "  WHERE id=?";
        } else {
            qSql = "UPDATE utilisateurs SET pointXp=0  WHERE id=?";
        }

        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Utilisateur XP ++ Bravo ");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("Utilisateur XP ++ Erreur !!!");
        }

        return false;
    }

    //SELECT count(*) FROM `utilisateurs` GROUP BY role
    public HashMap<String, Integer> nbCompteRole() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT roles,count(*) FROM `fos_user` GROUP BY roles";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                if(rs.getString(1).contains("US")) 
                    hm.put("us", rs.getInt(2));
                else if(rs.getString(1).contains("ORG")) 
                    hm.put("org", rs.getInt(2));
                else if(rs.getString(1).contains("RES")) 
                    hm.put("resto", rs.getInt(2));
                else if(rs.getString(1).contains("ENT")) 
                    hm.put("ent", rs.getInt(2));
                
            }
        } catch (SQLException ex) {
        }
        return hm;
    }

    public static void sendMail(Utilisateur user) {
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
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Donation , Bienvenu");
        
        String htmlCode = "<h1> Bienvenu Dans Donation </h1> <br/> il ne vous reste qu'une étape à faire avant d'etre parmis notre grande communauté <br/> <h2><b>votre code confirmation :" + user.getConfirmation_token() + "</b></h2>";

            message.setContent(htmlCode, "text/html");
            //Send mail
            Transport.send(message);
            System.out.println("Mail Confirm key OK");

        } catch (Exception ex) {
            System.out.println("Mail Confirm key Problem");
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Utilisateur user) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Donation , Votre Compte");
            String htmlCode = "<h1> Merci </h1> <br/> <h2><b>votre code confirmation :" + user.getConfirmation_token() + "</b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {

        }
        return null;
    }
    
    public static void sendSMS(Utilisateur user) {
        System.out.println("Preparing to send SMS");
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
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getNumTel()+"@sms.clicksend.com"));
        message.setSubject("Donation");
        
        String htmlCode = "<h1> Hi "+user.getNom()+" <br/>Merci </h1> <br/> <b>votre code de confirmation :" + user.getConfirmation_token() + "</b>";
        
            
            
            message.setContent(htmlCode, "text/html");
            //Send mail
            Transport.send(message);
            System.out.println("SMS Confirm key OK");

        } catch (Exception ex) {
            System.out.println("SMS Confirm key Problem");
        }
    }
    
    public static void sendPhoneCall(Utilisateur user) {
        System.out.println("Preparing to send Phone call");
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
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getNumTel()+"@voice.clicksend.com"));
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("amine.gongi@esprit.tn"));
        message.setSubject("");
        String code = user.getConfirmation_token().replaceAll(".", "$0 ");
        String code2 = code.replaceAll(".", "$0 ");
        String code3 = code2.replaceAll(".", "$0 ");
        String htmlCode = "Bienvenu dans Donation <br/><br/> <br/><br/><br/> Votre code d'activation est le : " + code3 + "<br/><br/><br/><br/><br/> je répète le code une autre fois <br/><br/><br/><br/>  Votre code d'activation est le : " + code3 + "<br/><br/> bienvenu dans Donation.";
            
        System.out.println(htmlCode);
            
        message.setContent(htmlCode, "text/html");
        
        //Send mail
        Transport.send(message);
        System.out.println("Phone call Confirm key OK");

        } catch (Exception ex) {
            System.out.println("Phone call Confirm key Problem");
        }
    }
    
    public String dddd(){
        String x = "";
        String qSql = "select mail,dateInscription from utilisateurs ORDER by id DESC LIMIT 1";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                x = rs.getString(1)+", le "+rs.getDate(2).toString();
            }
        } catch (SQLException ex) {
        }
        return x;
    }
    
    public String actifss(){
        String x = "";
        String qSql = "select mail,dateInscription from utilisateurs ORDER by pointXp DESC LIMIT 1";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                x = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return x;
    }
    
    //SELECT count(*),dateInscription from utilisateurs GROUP BY Date(dateInscription)
    
    public HashMap<String, Integer> nbCompteparInscri() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT count(*),dateInscription from utilisateurs GROUP BY Date(dateInscription)";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                hm.put(rs.getDate(2).toString(),rs.getInt(1));
            }
        } catch (SQLException ex) {
        }
        return hm;
    }
}
