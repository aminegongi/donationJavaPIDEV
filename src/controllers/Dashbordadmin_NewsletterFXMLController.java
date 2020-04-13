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
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class Dashbordadmin_NewsletterFXMLController implements Initializable {
    @FXML
    private StackPane rootPane;
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
    private Button btnNews;
    @FXML
    private TableView<Newsletter> tableViewNews;
    @FXML
    private JFXButton btnSuppNews;
    @FXML
    private JFXTextField tfLibelle;
    @FXML
    private JFXTextField tfObjet;
    @FXML
    private HTMLEditor htmlCorps;
    @FXML
    private JFXButton btnAddNews;
    @FXML
    private TableColumn<Newsletter, Integer> colID;
    @FXML
    private TableColumn<Newsletter, String> colLib;
    @FXML
    private TableColumn<Newsletter, String> colObjet;
    @FXML
    private TableColumn<Newsletter, String> colCorps;
    @FXML
    private TableColumn<Newsletter, String> ColEtat;
    @FXML
    private JFXButton btnSendNews;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affichageTable();
    }    
    
    private void affichageTable(){
        GestionnaireNewsletter gNews = new GestionnaireNewsletter();
        ArrayList<Newsletter> NewsArray =  (ArrayList<Newsletter>) gNews.fetchNews() ; 
        System.out.println(NewsArray);
        ObservableList<Newsletter> data = FXCollections.observableArrayList(NewsArray);
        tableViewNews.setItems(data);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colObjet.setCellValueFactory(new PropertyValueFactory<>("objetMail"));
        colCorps.setCellValueFactory(new PropertyValueFactory<>("coprsMail"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("dateEnvoi"));
        
        tableViewNews.setEditable(true);
        colLib.setCellFactory(TextFieldTableCell.forTableColumn());
        colObjet.setCellFactory(TextFieldTableCell.forTableColumn());
        colCorps.setCellFactory(TextFieldTableCell.forTableColumn());
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

    @FXML
    private void ajouterNews(ActionEvent event) {
        GestionnaireNewsletter gn = new GestionnaireNewsletter();
        Newsletter n = new Newsletter(tfLibelle.getText(), tfObjet.getText(), htmlCorps.getHtmlText());
        gn.AjouterNews(n);
        tfLibelle.clear();
        tfObjet.clear();
        htmlCorps.setHtmlText("");
        affichageTable();
    }
    @FXML
    private void modifLib(TableColumn.CellEditEvent<Newsletter, String> event) {
        Newsletter n = tableViewNews.getSelectionModel().getSelectedItem();
        n.setLibelle(event.getNewValue());
        GestionnaireNewsletter ps = new GestionnaireNewsletter();
        ps.modifierNewsByID(n, n.getId());
    }
    @FXML
    private void modifObj(TableColumn.CellEditEvent<Newsletter, String> event) {
        Newsletter n = tableViewNews.getSelectionModel().getSelectedItem();
        n.setObjetMail(event.getNewValue());
        GestionnaireNewsletter ps = new GestionnaireNewsletter();
        ps.modifierNewsByID(n, n.getId());
    }
    @FXML
    private void modifCorps(TableColumn.CellEditEvent<Newsletter, String> event) {
        Newsletter n = tableViewNews.getSelectionModel().getSelectedItem();
        n.setCoprsMail(event.getNewValue());
        GestionnaireNewsletter gn = new GestionnaireNewsletter();
        gn.modifierNewsByID(n, n.getId());
    }

    @FXML
    private void suppNews(ActionEvent event) {
        Newsletter selectedItem = tableViewNews.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmer ? ");
        alert.setContentText("vous voulez vraiment supprimer : "+selectedItem.getLibelle());   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        GestionnaireNewsletter gn = new GestionnaireNewsletter() ; 
        tableViewNews.getItems().remove(selectedItem);
        gn.supprimerNewsByNews(selectedItem);
        }
        
        }
        else {
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
        }
    }

    @FXML
    private void sendNews(ActionEvent event) {
        Newsletter selectedItem = tableViewNews.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            if(selectedItem.getDateEnvoi()==null){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmer ? ");
                alert.setContentText("vous voulez vraiment envoyer cette Newsletter : "+selectedItem.getLibelle());   
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    GestionnaireNewsletter gn = new GestionnaireNewsletter() ; 
                    gn.sendNews(selectedItem);
                    affichageTable();
                }
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Newsletter déja envoyer");
                alert.showAndWait();
            }

        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Message d'information");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un element à envoyer");
            alert.showAndWait();
        }
    }




}
