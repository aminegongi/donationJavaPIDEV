/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Newsletter;
import Services.GestionnaireNewsletter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class DashbordAdminController implements Initializable {
    @FXML
    private Circle btnImage;
    @FXML
    private Button btnDash;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnCagnote;
    @FXML
    private Button btnAide;
    @FXML
    private Button btnEmp;
    @FXML
    private Button btnRestoDon;
    @FXML
    private Button btnRestoOrg;
    @FXML
    private Button btnPub;
    @FXML
    private StackPane rootPane;
    @FXML
    private Button btnNews;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void goToProfile(MouseEvent event) {
    }

    @FXML
    private void goToDash(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToUsers(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Users.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToCagnote(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Cagnotte.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAide(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Aide.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToEmp(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/gestionemploiscategorie.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToRestoDon(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdminResto.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToRestoOrg(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToPub(ActionEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/afficherPublicite.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToNews(ActionEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Dashbordadmin_NewsletterFXML.fxml"));
                rootPane.getChildren().clear();
                rootPane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }
    
}
