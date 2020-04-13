/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Entreprise;
import Entities.Organisation;
import Entities.Restaurant;
import Entities.Utilisateur;
import Entities.Utilisateur_Simple;
import Services.GestionnaireEntreprise;
import Services.GestionnaireOrganisation;
import Services.GestionnaireRestaurant;
import Services.GestionnaireUtilisateur;
import Services.GestionnaireUtilisateur_Simple;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class HomeFXMLController implements Initializable {
    @FXML
    private Polygon cagnotePoly;
    @FXML
    private Polygon aidePoly;
    @FXML
    private Polygon emploiPoly;
    @FXML
    private Polygon restau1Poly;
    @FXML
    private Polygon restau2Poly;
    
    private boolean flag=false ;
    @FXML
    private Pane rootPane;
    
    static Utilisateur_Simple u=null ;
    static Organisation o=null ;
    static Entreprise e=null ;
    static Restaurant r=null ;
    static int isUser;
    static String isUserRole;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(UiLoginController.uh.getId());
        isUser =UiLoginController.uh.getId();
        GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
        isUserRole=gu.getroleById(isUser);

        switch(isUserRole){
            case "us":
                GestionnaireUtilisateur_Simple guss= new GestionnaireUtilisateur_Simple();
                u= guss.fetchOneUS(isUser);
                //restau1Poly.setVisible(false);
                //restau2Poly.setVisible(false);
                break;
            case "org":
                GestionnaireOrganisation go = new GestionnaireOrganisation();
                o= go.fetchOneOrg(isUser);
                break;
            case "ent":
                GestionnaireEntreprise  ge = new GestionnaireEntreprise();
                e= ge.fetchOneENT(isUser);
                //restau1Poly.setVisible(false);
                //estau2Poly.setVisible(false);
                aidePoly.setVisible(false);
                break;
            case "resto":
                GestionnaireRestaurant gr = new GestionnaireRestaurant();
                r= gr.fetchOneResto(isUser);
                break;
            case "error":
                System.out.println("Error user get");
                break;
        }
        
    }    

    

    @FXML
    private void changeColorCagnotte(MouseEvent event) {
        if(flag ==false)
        {
        cagnotePoly.setOpacity(0.7);
            System.out.println("s");
         flag=true; 
        }
        
    }

    @FXML
    private void changeColorAide(MouseEvent event) {
         if(flag ==false)
        {
        aidePoly.setOpacity(0.7);
            System.out.println("s");

         flag=true; 
        }
    }

    @FXML
    private void changeColorEmploi(MouseEvent event) {
         if(flag ==false)
        {
        emploiPoly.setOpacity(0.7);
            System.out.println("s");

         flag=true; 
        }
    }

    @FXML
    private void changeColorRestau1(MouseEvent event) {
         if(flag ==false)
        {
        restau1Poly.setOpacity(0.7);
            System.out.println("s");

         flag=true; 
        }
        
    }

    @FXML
    private void changeColorRestau2(MouseEvent event) {
         if(flag ==false)
        {
        restau2Poly.setOpacity(0.7);
            System.out.println("s");

         flag=true; 
        }
        
    }

    @FXML
    private void setColorNeutre(MouseEvent event) {
        if(flag==true){
            cagnotePoly.setOpacity(0.5);
            aidePoly.setOpacity(0.5);
            restau1Poly.setOpacity(0.5);
            restau2Poly.setOpacity(0.5);
            emploiPoly.setOpacity(0.5);
            System.out.println("a");

            flag = false ;
        }
            
    }

    @FXML
    private void goToCagnotte(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionCagnotte.fxml"));
//                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void goToAide(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionAide.fxml"));
//                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void goToEmploi(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionEmploi.fxml"));
//                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void goToRestauDon(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionRestauDon.fxml"));
//                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void goToRestauOrg(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionRestauOrg.fxml"));
//                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void goToProfil(MouseEvent event) {
                Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/profilUser.fxml"));
                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


   
    
}
