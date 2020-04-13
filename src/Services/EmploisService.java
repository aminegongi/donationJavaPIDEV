/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Emplois;
import Entities.Utilisateur;
import ISevices.IServiceEmplois;
import Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author heshem
 */
public class EmploisService implements IServiceEmplois{

    
         private Connection con;
    private Statement ste;

    public EmploisService() {
        con = DataSource.getInstance().getConnection();

    }

     
     @Override
    public void ajouter(Emplois t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `donation`.`emplois` (`id`, `titre`, `description`, `Photo`, `salaire`, `emplacement`, `TypeDemploi`, `TypeContrat`, `idcategorie`, `id_utilisateur`) VALUES ( NULL,?, ?, ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, t.getTitre());
    pre.setString(2, t.getDescreption());
    
     pre.setString(3, t.getPhoto());
    pre.setDouble(4, t.getSalaire());
    pre.setString(5, t.getEmplacement());
     pre.setString(6, t.getTypeDemploi());
     pre.setString(7, t.getTypeContrat());
     pre.setInt(8, t.getIdCategorie());
     pre.setInt(9, t.getUser().getId());
     
         System.out.println(pre);
     
    
    pre.executeUpdate();
    smsemploi sms = new smsemploi();
    sms.sendSms("publiaction ajoutee");
    
    sendMail(t.getUser().getMail());
    }
    
  
    
    public void ajouter1(Emplois p) throws SQLException
    {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `donation`.`emplois`  (`id`, `titre`, `description`, `Photo`, `salaire`, `emplacement`, `TypeDemploi`, `TypeContrat`, `idcategorie`, `id_utilisateur`) VALUES (NULL,?,?,?,?,?,?,?,?,?);");
    pre.setString(1, p.getTitre());
    pre.setString(2, p.getDescreption()); 
     pre.setString(3, p.getPhoto());
    pre.setDouble(4, p.getSalaire());
    pre.setString(5, p.getEmplacement());
    pre.setString(6, p.getTypeDemploi());
    pre.setString(7, p.getTypeContrat());
    pre.setInt(8, p.getIdCategorie());
    pre.setInt(9, p.getUser().getId());
    
    pre.executeUpdate();   
     smsemploi sms = new smsemploi();
    sms.sendSms("Bonjour, Votre publiaction est bien ajoutée");
    }

    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `donation`.`emplois` where id =?");
        pre.setInt(1, id);
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("emplois was deleted successfully!");
        }
        return true;
    }

    @Override
    public boolean update(Emplois t,int id) throws SQLException {
      String sql = "UPDATE emplois SET titre=?, description=?, Photo=?, salaire=?, emplacement=?, TypeDemploi=?, TypeContrat=? WHERE id=? ";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getTitre());
        statement.setString(2, t.getDescreption());
        statement.setString(3, t.getPhoto());
        statement.setDouble(4, t.getSalaire());
        statement.setString(5, t.getEmplacement());
        statement.setString(6, t.getTypeDemploi());
        statement.setString(7, t.getTypeContrat());
        statement.setInt(8, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing emplois was updated successfully!");
        }
        return true;
    }

    @Override
    public List<Emplois> readAll() throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie);
     arr.add(p);
     }
    return arr;    }
    
    public List<Emplois> readAll1() throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
               Utilisateur  user= gu.fetchOneUS(rs.getInt("id_utilisateur"));
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie,user);
     arr.add(p);
     }
    return arr;    }
    
    
    
    
     public List<Emplois> readAllbyid(int idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois where id_utilisateur = '"+idu+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie);
     arr.add(p);
     }
    return arr;    } 
     
     
     
     
     
     
     
      public List<Emplois> readidmax(int idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT MAX(id) FROM emplois");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie);
     arr.add(p);
     }
    return arr;    }
     
     
     public List<Emplois> readAllbytype(String idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois where TypeDemploi = '"+idu+"'");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
               Utilisateur  user= gu.fetchOneUS(rs.getInt("id_utilisateur"));
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie,user);
     arr.add(p);
     }
    return arr;    }
      public List<Emplois> readAllbyemplacement(String idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois where emplacement = '"+idu+"'");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
               Utilisateur  user= gu.fetchOneUS(rs.getInt("id_utilisateur"));
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie,user);
     arr.add(p);
     }
    return arr;    }
      public List<Emplois> readAllbytypecontrat(String idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from emplois where TypeContrat = '"+idu+"'");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
               Utilisateur  user= gu.fetchOneUS(rs.getInt("id_utilisateur"));
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie,user);
     arr.add(p);
     }
    return arr;    }
     
    
    public Boolean findById(int id) throws SQLException {
        String req = "select * from emplois where id = ?";

        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            System.out.println("emplois touvé !");
            return true;
            
        }
        System.out.println("emplois non touvé !");
        return false;
    }
    
    public List<Emplois> trierTitre(int t) throws SQLException{
    List<Emplois> list = this.readAll();
    if(t==0)
        list.sort((Emplois o1 ,Emplois o2)->o1.getTitre().compareToIgnoreCase(o2.getTitre()));
    else
        list.sort((Emplois o1 ,Emplois o2)->o2.getTitre().compareToIgnoreCase(o1.getTitre()));
        return list; 
    }
    
    
    public List<Emplois> trierid(int t) throws SQLException{
    List<Emplois> list = this.readAll1();
    if(t==0)
        list.sort((Emplois o1 ,Emplois o2)->o1.getId()-(o2.getId()));
    else
     list.sort((Emplois o1 ,Emplois o2)->o2.getId()-(o1.getId()));
        return list; 
    }
      
      
    public List<Emplois> triersalaire(int t) throws SQLException{
    List<Emplois> list = this.readAll1();
    if(t==0)
        list.sort((Emplois o1 ,Emplois o2)->(int) (o1.getSalaire()-(o2.getSalaire())));
    else
     list.sort((Emplois o1 ,Emplois o2)->(int) (o2.getSalaire()-(o1.getSalaire())));
        return list; 
    }
    
        public static void sendMail(String mail) {
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
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));

        message.setSubject("publication ajoutée");
        
        String htmlCode = "<h1> Merci </h1> ";
        
            
            
            message.setContent(htmlCode, "text/html");
            //Send mail
            Transport.send(message);
            System.out.println("Mail Confirm key OK");

        } catch (Exception ex) {
            System.out.println("Mail Confirm key Problem");
        }
    }
    
        
        //SELECT count(*) FROM `utilisateurs` GROUP BY role
    public HashMap<String, Integer> nbComptetypeemploi() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT TypeDemploi,count(*) FROM `emplois` GROUP BY TypeDemploi";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                hm.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
        }
        return hm;
    }  
    
    
    
    
       public HashMap<String, Integer> nbCompteemplacement() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT emplacement,count(*) FROM `emplois` GROUP BY emplacement";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                
                hm.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
        }
        return hm;
    } 
    
    
    
    
    
        public HashMap<String, Integer> nbComptetypecontrat() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "SELECT TypeContrat,count(*) FROM `emplois` GROUP BY TypeContrat";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                hm.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
        }
        return hm;
    }
    
       
        public HashMap<String, Integer> nbComptesalaire() {
        HashMap<String, Integer> hm = new HashMap<>();
        String qSql = "select case when salaire between 0 and 500 then '0-500' when salaire between 500 and 1000 then '501-1000' when salaire between 1000 and 2000 then '1001-2000' when salaire between 2000 and 50000 then '2001-50000' else 'OTHERS' end as `Range`, count(*) as `Count` from emplois group by `Range`";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                hm.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
        }
        return hm;
    }
    
    
        
    public List<Emplois> rechercheByAll(String idu) throws SQLException {
    List<Emplois> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `emplois` WHERE  (titre LIKE '%"+idu+"%' OR emplacement LIKE '%"+idu+"%' OR description LIKE '%"+idu+"%' OR salaire LIKE '%"+idu+"%' OR TypeDemploi LIKE '%"+idu+"%' OR TypeContrat LIKE '%"+idu+"%')");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String description=rs.getString("description");
               String photo=rs.getString("Photo");
               double salaire=rs.getDouble("salaire");
               String emplacement=rs.getString("emplacement");
               String TypeDemploi=rs.getString("TypeDemploi");
               String TypeContrat=rs.getString("TypeContrat");
               int IdCategorie=rs.getInt("idcategorie");
               GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
               Utilisateur  user= gu.fetchOneUS(rs.getInt("id_utilisateur"));
               Emplois p=new Emplois(id, titre, description, photo, salaire, emplacement, TypeDemploi, TypeContrat,IdCategorie,user);
     arr.add(p);
     }
    return arr;    }
    private static class string {

        public string() {
        }
    }

}
