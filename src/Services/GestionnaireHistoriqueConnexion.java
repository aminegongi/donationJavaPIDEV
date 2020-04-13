/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import Entities.HistoriqueConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.*;

import Entities.Utilisateur;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.*;

/**
 *
 * @author Amine Gongi
 */
public class GestionnaireHistoriqueConnexion {

    Connection cnx = DataSource.getInstance().getConnection();

    public void AjouterHC(HistoriqueConnexion hc) {
        String qSql = "INSERT INTO historiqueconnexionuser (idUtilisateur, ipAdresse) VALUES (?,?)";
        PreparedStatement pst = null;
        try {
            pst = cnx.prepareStatement(qSql);

            pst.setInt(1, hc.getUser().getId());
            pst.setString(2, hc.getIpAdresse());

            System.out.println(pst.toString());

            pst.executeUpdate();
            System.out.println("HC ADD Bravo ");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println("HC Add Erreur !!!");
        }
    }

    public List<HistoriqueConnexion> fetchHC() {
        List<HistoriqueConnexion> listHC = new ArrayList<>();
        //SELECT utilisateurs.id , utilisateurs.nom , utilisateurs.mail , historiqueconnexionuser.ipAdresse , historiqueconnexionuser.date FROM `historiqueconnexionuser` INNER join utilisateurs ON historiqueconnexionuser.idUtilisateur=utilisateurs.id 
        //String qSql="select * from historiqueconnexionuser ";
        String qSql = "SELECT utilisateurs.id , utilisateurs.nom , utilisateurs.mail , historiqueconnexionuser.id , historiqueconnexionuser.ipAdresse , historiqueconnexionuser.date FROM `historiqueconnexionuser` INNER join utilisateurs ON historiqueconnexionuser.idUtilisateur=utilisateurs.id ";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                HistoriqueConnexion hc = new HistoriqueConnexion();

                Utilisateur usTmp = new Utilisateur();

                usTmp.setId(rs.getInt(1));
                usTmp.setNom(rs.getString(2));
                usTmp.setEmail(rs.getString(3));

                hc.setUser(usTmp);
                hc.setId(rs.getInt(4));
                hc.setIpAdresse(rs.getString(5));
                hc.setDateCnx(rs.getTimestamp(6));

                listHC.add(hc);
            }
            System.out.println("HC Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("HC Select Erreur !!!");
        }
        return listHC;
    }

    public HistoriqueConnexion fetchOneUsHC(Utilisateur us) {
        HistoriqueConnexion hc = new HistoriqueConnexion();
        int id = us.getId();
        //String qSql = "select * from historiqueconnexionuser where id='" + id + "'";
        String qSql = "SELECT utilisateurs.id , utilisateurs.nom , utilisateurs.mail , historiqueconnexionuser.id , historiqueconnexionuser.ipAdresse , historiqueconnexionuser.date FROM `historiqueconnexionuser` INNER join utilisateurs ON historiqueconnexionuser.idUtilisateur=utilisateurs.id ORDER BY historiqueconnexionuser.date ASC  where idUtilisateur='" + id + "' LIMIT 1 ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                Utilisateur usTmp = new Utilisateur();

                usTmp.setId(rs.getInt(1));
                usTmp.setNom(rs.getString(2));
                usTmp.setEmail(rs.getString(3));

                hc.setUser(usTmp);
                hc.setId(rs.getInt(4));
                hc.setIpAdresse(rs.getString(5));
                hc.setDateCnx(rs.getTimestamp(6));

            }
            System.out.println("Hc one Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("Hc one Select Erreur !!!");
        }
        return hc;
    }

    public List<HistoriqueConnexion> fetchAllHCUser(Utilisateur us) {
        List<HistoriqueConnexion> listHC = new ArrayList<>();
        //SELECT utilisateurs.id , utilisateurs.nom , utilisateurs.mail , historiqueconnexionuser.ipAdresse , historiqueconnexionuser.date FROM `historiqueconnexionuser` INNER join utilisateurs ON historiqueconnexionuser.idUtilisateur=utilisateurs.id 
        //String qSql="select * from historiqueconnexionuser ";
        int id = us.getId();
        String qSql = "SELECT utilisateurs.id , utilisateurs.nom , utilisateurs.mail , historiqueconnexionuser.id , historiqueconnexionuser.ipAdresse , historiqueconnexionuser.date FROM `historiqueconnexionuser` INNER join utilisateurs ON historiqueconnexionuser.idUtilisateur=utilisateurs.id where idUtilisateur='" + id + "' ";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qSql);
            while (rs.next()) {
                HistoriqueConnexion hc = new HistoriqueConnexion();

                Utilisateur usTmp = new Utilisateur();

                usTmp.setId(rs.getInt(1));
                usTmp.setNom(rs.getString(2));
                usTmp.setEmail(rs.getString(3));

                hc.setUser(usTmp);
                hc.setId(rs.getInt(4));
                hc.setIpAdresse(rs.getString(5));
                hc.setDateCnx(rs.getTimestamp(6));

                listHC.add(hc);
            }
            System.out.println("HCs User Select Bravo ");
        } catch (SQLException ex) {
            System.out.println("HCs User Select Erreur !!!");
        }
        return listHC;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public void RapportHCbyUser(Utilisateur us) {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        } catch (DocumentException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<HistoriqueConnexion> lHc = fetchAllHCUser(us);
        document.open();
        
        
        Font f24 = FontFactory.getFont(FontFactory.HELVETICA, 24f);
        Font f16 = FontFactory.getFont(FontFactory.HELVETICA, 14f);
        Font f12 = FontFactory.getFont(FontFactory.HELVETICA, 10f);

        try {
            document.add(new Paragraph("Votre Historique de Connexion \n\n", f24));
            document.add(new Paragraph("Monsieur/Madame " + us.getNom() + " identifant " + us.getId() + " suite a votre demande voici votre historique de connexion :  \n ", f16));
            document.add(new Paragraph("Nombre Total de connexion : " + lHc.size() + "\n ", f16));

        } catch (DocumentException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        PdfPTable table = new PdfPTable(4);
        try {
            table.setWidths(new float[]{8, 35, 35, 35});
        } catch (DocumentException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.addCell("");
        table.addCell("Ip Adresse");
        table.addCell("Pays , Région");
        table.addCell("Date");

        for (int i = 0; i < lHc.size(); i++) {
            table.addCell("" + (i + 1));
            table.addCell(lHc.get(i).getIpAdresse());
            System.out.println(lHc.get(i).getIpAdresse());
            JSONObject json = null;
            try {
                json = readJsonFromUrl("https://ipapi.co/" + lHc.get(i).getIpAdresse() + "/json/");
            } catch (IOException ex) {
                Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println();

            table.addCell(json.get("country_name").toString() + "," + json.get("city").toString());
            table.addCell(lHc.get(i).getDateCnx().toString());
        }

        try {
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            document.add(new Paragraph("\nPour assurer une meilleur transparence entre nous DoNation vous donne accés a tous vos données , et ceci on conformité avec la loi de vie privéé ", f12));

        } catch (DocumentException ex) {
            Logger.getLogger(GestionnaireHistoriqueConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.close();
    }
}
