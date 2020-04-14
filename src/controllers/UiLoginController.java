/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import Services.*;
import Entities.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class UiLoginController implements Initializable {
    public static Utilisateur uh = null ;
    @FXML
    private TextField txtMail;
    @FXML
    private PasswordField txtMdp;
    @FXML
    private Button FacebookSign;
    @FXML
    private StackPane pane;
    @FXML
    private Label labelMail;
    
    public static String mailToVal; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void Login(ActionEvent event) {
        GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
        
        int l = gu.loginU(txtMail.getText(),  txtMdp.getText());
        Alert al = new Alert(Alert.AlertType.WARNING, "Compte désactiver , Call l'administrateur", ButtonType.OK);
        Alert al1 = new Alert(Alert.AlertType.WARNING, "Activer votre Compte Redirection ..", ButtonType.OK);
        Alert al2 = new Alert(Alert.AlertType.ERROR, "Invalid Mail ou Mot de Pass", ButtonType.OK);
        
        System.out.println(txtMail.getText());
        System.out.println("------------");
        System.out.println(txtMdp.getText());
        System.out.println("------------");
        System.out.println(l);
        System.out.println("------------");
        
        if (l == -1) {
            al2.showAndWait();
        } else if (l == -2) {
            al.showAndWait();
        } else if (l == -3) {
            al1.showAndWait();
            Pane newLoadedPane;
            try {
                mailToVal=txtMail.getText();
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UIActivationCompte.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            System.out.println("OK");
            String mail = txtMail.getText();
            String mdp = txtMdp.getText();
            Utilisateur UHC = gu.fetchOneUS(gu.getIdByMail(mail));
            uh = UHC;
            System.out.println(uh);

            /* Historique Mezel
            GestionnaireHistoriqueConnexion ghc = new GestionnaireHistoriqueConnexion();
            ghc.AjouterHC(new HistoriqueConnexion(UHC));
             try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }*/
        }
        txtMail.clear();
        txtMdp.clear();
        
    }
    
    @FXML
    private void VerifMail(KeyEvent event) {
        GestionnaireUtilisateur gus = new GestionnaireUtilisateur();
        /*
                if (gus.checkMail(txtMail.getText())) {
            System.out.println("fezezf");
            labelMail.setVisible(true);
        } else {
            labelMail.setVisible(false);
                        System.out.println("aaaaaa");

            btnCnx.setDisable(true);
        }
        */
    }
    @FXML
    private void CreeCompte(ActionEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UiInscription.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void goBackAdmin(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/AdminLogin.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
