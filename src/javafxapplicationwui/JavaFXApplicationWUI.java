/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationwui;

import Entities.*;
import Services.*;
import Utils.*;
import java.sql.Timestamp;

import java.util.Date;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Amine Gongi
 */
public class JavaFXApplicationWUI extends Application {
    private double xOffset =0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {

        
        
        //Scénario Newsletter
        
        /*
         Newsletter news = new Newsletter("Test1", "Objet du mai TEst1", "corps du mail Test1");
         Newsletter newsModif = new Newsletter("TestMo", "Objet du mai TestMo", "corps du mail TestMo");

         GestionnaireNewsletter gNews = new GestionnaireNewsletter();
         gNews.AjouterNews(news);
         Newsletter lastAddNews = gNews.fetchLastNews();
         gNews.modifierNewsByNews(lastAddNews, newsModif);
         gNews.modifierNewsByID(newsModif, 3);
         System.out.println(gNews.fetchNews());
         System.out.println(gNews.fetchNewsbyParam("libelle", "Te"));
         gNews.sendNews(news);
        // gNews.supprimerNewsById(1);
         */
        
        //Scénario Utilisateur Simple
        /*
         Utilisateur_Simple usTest = new Utilisateur_Simple("gongi", "homme", new Date(2006, 2, 7), "amine98gongi@gmail.com", "1234", "salt", "+21655685313", new Adresse("Tunisie", "tunis"), "image", 121, "amine");
         Utilisateur_Simple usModif = new Utilisateur_Simple("John", "Femme", new Date(2009, 2, 7), "amine@gmail.com", "1234", "salt", "+21655685313", new Adresse("Tunisie", "tunis"), "image", 121, "Mike");
         GestionnaireUtilisateur_Simple gUs = new GestionnaireUtilisateur_Simple();
         gUs.InscrireUS(usTest);
         System.out.println(gUs.fetchUS());
         int idUs = gUs.getIdByMail("amine98gongi@gmail.com");
         System.out.println(gUs.fetchOneUS(idUs));
         gUs.modifierUS(usModif, idUs);
         System.out.println(gUs.fetchOneUS(idUs));
         gUs.supprimerUS_I(idUs);
         */
        
        
        //Scénario Organisation
        /*
        Organisation orgTest = new Organisation("ORG1231", "www.faceboook.com/TEST", "wwww.test1.com", "DescTest1", (float) 12.44444, (float) 34.555555, "DonationOrgTest1@gmail.com", "1234", "salt", "+21655685313", new Adresse("Tunisie", "tunis"), "image", 0, "Test Org 1");
        Organisation orgTestModif = new Organisation("ORG1213", "www.faceboook.com/TEST", "wwww.test1.com", "DescTest1", (float) 12.44444, (float) 34.555555, "DonationOrgModifTest1@gmail.com", "1234", "salt", "+2169898989", new Adresse("Tunisie", "tunis"), "image", 0, "Test Org Modif 1");
        GestionnaireOrganisation gOrg = new GestionnaireOrganisation();

        gOrg.InscrireORG(orgTest);
        System.out.println(gOrg.fetchOrg());
        int idOrg = gOrg.getIdByMail("DonationOrgTest1@gmail.com");
        System.out.println(gOrg.fetchOneOrg(idOrg));
        gOrg.modifierORG(orgTestModif, idOrg);
        System.out.println(gOrg.fetchOneOrg(idOrg));
        //gOrg.supprimerOrg_I(idOrg);
        */
        
        //Scénarion Login + HC 
        /*
        GestionnaireOrganisation gOrg = new GestionnaireOrganisation();
        int login = gOrg.loginU("mail.com@om8", "aaza1");
        if(login==-1){
            System.out.println("mot de passe ou @ mail incorrect");
        }
        else{
            Organisation orgTest = gOrg.fetchOneOrg(9);
            GestionnaireHistoriqueConnexion ghc= new GestionnaireHistoriqueConnexion();
            HistoriqueConnexion hc = new HistoriqueConnexion(orgTest);  //utilisateur inscrit 
            ghc.AjouterHC(hc);  
            ghc.RapportHCbyUser(orgTest);
        }
          */
        
        
        //Graphique UI login Inscri + Activation
        
        Parent root = FXMLLoader.load(getClass().getResource("/views/UiLogin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/gestionemploiscategorie.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_NewsletterFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/afficherPublicite.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Cagnotte.fxml"));

        Scene scene = new Scene(root);
        Image image =new Image(getClass().getResourceAsStream("/images/Novant logo.png"));
        stage.getIcons().add(image);
        stage.setTitle("Donation");
        stage.setResizable(false); // taille fixe

         
         /*stage.initStyle(StageStyle.UTILITY);
         root.setOnMousePressed((MouseEvent event)-> {
             xOffset = event.getSceneX();
             yOffset = event.getSceneY();
         });
         
         root.setOnMouseDragged((MouseEvent event)-> {
             stage.setX(event.getSceneX()-xOffset);
             stage.setY(event.getSceneY()-xOffset);
         });*/
         
         stage.setScene(scene);
         stage.show();
         
         //Utilisateur u = new Utilisateur("amine.gongi@esprit.tn", "aa", "ccc", "ddd");
         //GestionnaireUtilisateur g = new GestionnaireUtilisateur();
         //g.sendMail(u);
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
