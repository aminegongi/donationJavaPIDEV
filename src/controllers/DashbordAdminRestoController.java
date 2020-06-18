/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import com.esprit.Entities.Utilisateur_Simple;
//import Services.GestionnaireUtilisateur_Simple;
import Entities.*;
import Services.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.valueOf;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class DashbordAdminRestoController implements Initializable {

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
    private JFXListView<TarifResto> listViewTarif;
    private Label labelNbrR;
    @FXML
    private Pane PaneCentrale;
    private JFXPopup popup;
    @FXML
    private JFXListView<DonRestaurant> listViewDonResto;
    @FXML
    private Label lblTarif;
    @FXML
    private Label lblDonResto;
    @FXML
    private JFXListView<RepasServi> listViewRepasServi;
    private int idResto;
    @FXML
    private Label lblRepasServi;
    @FXML
    private Label lblDonEmpty;
    @FXML
    private Label lblRepasEmpty;
    @FXML
    private Label lblTarifEmpty;
    @FXML
    private JFXButton btnGeneral;
    @FXML
    private JFXTextField txtRecherche;
    @FXML
    private JFXButton btnRecherche;
    @FXML
    private JFXComboBox<Label> comboRecherche;
    @FXML
    private Label lblTotalTarif;
    @FXML
    private Label lblTotalDon;
    @FXML
    private Label lblTotalRepas;
    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //************************ComboRecherche******************************************************
        comboRecherche.getItems().add(new Label("Restaurant"));
        comboRecherche.getItems().add(new Label("Utilisateur"));
        comboRecherche.getSelectionModel().selectFirst();
        //********************************************************************************************
            
       
          ServiceTarifResto serTar = new ServiceTarifResto();
          ServiceDonRestaurant serDon = new ServiceDonRestaurant();
          ServiceRepasServi serRep = new ServiceRepasServi();
        try {
            //*******************Stat******************************************************
            float totalTarif = serTar.totalPortefeuille();
            float totalDon = serDon.totalDonation();
            int totalRepas = serRep.totalRepasServi();
            lblTotalTarif.setText("Le total des portefeuillevirtuel : "+Float.toString(totalTarif)+" Dinars");
            lblTotalDon.setText("Le total des donations : "+Float.toString(totalDon)+" Dinars");
            lblTotalRepas.setText("Le total des repas servi : "+Integer.toString(totalRepas));
            lblTotalTarif.setVisible(true);
            lblTotalDon.setVisible(true);
            lblTotalRepas.setVisible(true);
            //*****************************************************************************
            
            //*******************List Tarif************************************************
            List<TarifResto> list = serTar.readAll();
            ObservableList<TarifResto> listTarif = FXCollections.observableArrayList(list);
            lblTarifEmpty.setText("");
            listViewTarif.getItems().addAll(listTarif);
            listViewTarif.setExpanded(true);
            listViewTarif.depthProperty().set(1);
            if(listViewTarif.getItems().size()==0){
                    lblTarifEmpty.setText("Il n'existe aucun repas servi !");
                    lblTarifEmpty.setAlignment(Pos.CENTER);
                    lblTarifEmpty.setVisible(true);
                }else {
                    lblTarifEmpty.setVisible(false);   
                }
           //*******************************************************************************
           //*******************List Don Resto************************************************
           List<DonRestaurant> list1 = serDon.readAll();
            ObservableList<DonRestaurant> listDon = FXCollections.observableArrayList(list1);
            lblDonEmpty.setText("");
            listViewDonResto.getItems().addAll(listDon);
            listViewDonResto.setExpanded(true);
            listViewDonResto.depthProperty().set(1);
            if(listViewDonResto.getItems().size()==0){
                    lblDonEmpty.setText("Il n'existe aucune donation !");
                    lblTotalDon.setText("Le total des donations : 0.0 Dinars");
                    lblDonEmpty.setAlignment(Pos.CENTER);
                    lblDonEmpty.setVisible(true);
                }else {
                    lblDonEmpty.setVisible(false);
                }
            //*******************************************************************************
           //*******************List Repas Servi************************************************
           List<RepasServi> list2 = serRep.readAll();
            ObservableList<RepasServi> listRep = FXCollections.observableArrayList(list2);
            lblRepasEmpty.setText("");
            listViewRepasServi.getItems().addAll(listRep);
            listViewRepasServi.setExpanded(true);
            listViewRepasServi.depthProperty().set(1);
            if(listViewRepasServi.getItems().size()==0){
                    lblRepasEmpty.setText("Il n'existe aucun repas servi !");
                    lblRepasEmpty.setAlignment(Pos.CENTER);
                    lblRepasEmpty.setVisible(true);
                }else {
                    lblRepasEmpty.setVisible(false);
                }
           //*******************************************************************************
          
         
       

        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdminRestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
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

    private void BackToUser(MouseEvent event) {

        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Users.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashbordAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void onSelectResto(ActionEvent event) throws SQLException{
      
        ServiceRepasServi serRep = new ServiceRepasServi();
        labelNbrR.setText(Integer.toString(serRep.nbrRepasServi(listViewTarif.getSelectionModel().getSelectedItem().getIdResto())));
        
    }

    @FXML
    private void onSelectResto(MouseEvent event) throws SQLException {
        ServiceRepasServi serRep = new ServiceRepasServi();
        ServiceDonRestaurant serD = new ServiceDonRestaurant();
        lblTotalTarif.setVisible(false);
            lblTotalDon.setVisible(true);
            lblTotalRepas.setVisible(true);
        //nbrRepasServi = serRep.nbrRepasServi(listViewTarif.getSelectionModel().getSelectedItem().getIdResto());
        try {
        //****************stat**********************************************************
        float totalDon = serD.totalDonationResto(listViewTarif.getSelectionModel().getSelectedItem().getIdResto());
            int totalRepas = serRep.totalRepasServiResto(listViewTarif.getSelectionModel().getSelectedItem().getIdResto());
            lblTotalDon.setText("Le total des donations : "+Float.toString(totalDon)+" Dinars");
            lblTotalRepas.setText("Le total des repas servi : "+Integer.toString(totalRepas));
        //******************************************************************************
            
        //****************listViewDonRestaurant*****************************************    
            List<DonRestaurant> list1 = serD.donationResto(listViewTarif.getSelectionModel().getSelectedItem().getIdResto());
            ObservableList<DonRestaurant> listDon = FXCollections.observableArrayList(list1);
           
                listViewDonResto.getItems().clear();
                listViewDonResto.getItems().addAll(listDon);
                listViewDonResto.setExpanded(true);
                listViewDonResto.depthProperty().set(1);
                
                if(listViewDonResto.getItems().size()==0){
                    lblDonEmpty.setText("Il n'existe aucune donation !");
                    lblTotalDon.setText("Le total des donations : 0.0 Dinars");
                    lblDonEmpty.setAlignment(Pos.CENTER);
                    lblDonEmpty.setVisible(true);
                }else {
                    lblDonEmpty.setVisible(false);
                }
            
                //********************************************************
        //****************listViewDonRestaurant*****************************************    
        
        List<RepasServi> listR = serRep.repasResto(listViewTarif.getSelectionModel().getSelectedItem().getIdResto());
            ObservableList<RepasServi> listRep = FXCollections.observableArrayList(listR);
           
                listViewRepasServi.getItems().clear();
                listViewRepasServi.getItems().addAll(listRep);
                listViewRepasServi.setExpanded(true);
                listViewRepasServi.depthProperty().set(1);
                
                if(listViewRepasServi.getItems().size()==0){
                    lblRepasEmpty.setText("Il n'existe aucun repas servi !");
                    lblRepasEmpty.setAlignment(Pos.CENTER);
                    lblRepasEmpty.setVisible(true);
                }else {
                    lblRepasEmpty.setVisible(false);
                }
            
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdminRestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
   /* private void initPopup(){
        JFXButton b1= new JFXButton("task1");
        JFXButton b2= new JFXButton("task1");
        JFXButton b3= new JFXButton("task1");
        JFXRippler r1= new JFXRippler();
        b1.setPadding(new Insets(10));
        b2.setPadding(new Insets(10));
        b3.setPadding(new Insets(10));
        VBox vBox = new VBox(b1, b2, b3);
        JFXPopup popup = new JFXPopup();
        popup.setUserData(vBox);
        popup.setPopupContent(listViewTarif);
        popup.show(r1, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        
    }
*/
   /* @FXML
    private void popupNow(MouseEvent event) {
        popup.show(r1, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }
    
  */

    @FXML
    private void general(ActionEvent event) {
        listViewTarif.getItems().clear();
        listViewDonResto.getItems().clear();
        listViewRepasServi.getItems().clear();
        //************************ComboRecherche*******************************************************
        comboRecherche.getSelectionModel().selectFirst();
        txtRecherche.setText("");
        //********************************************************************************************
        
       
          ServiceTarifResto serTar = new ServiceTarifResto();
          ServiceDonRestaurant serDon = new ServiceDonRestaurant();
          ServiceRepasServi serRep = new ServiceRepasServi();
        try {
            //*******************Stat******************************************************
            float totalTarif = serTar.totalPortefeuille();
            float totalDon = serDon.totalDonation();
            int totalRepas = serRep.totalRepasServi();
            lblTotalTarif.setText("Le total des portefeuillevirtuel : "+Float.toString(totalTarif)+" Dinars");
            lblTotalDon.setText("Le total des donations : "+Float.toString(totalDon)+" Dinars");
            lblTotalRepas.setText("Le total des repas servi : "+Integer.toString(totalRepas));
            lblTotalTarif.setVisible(true);
            lblTotalDon.setVisible(true);
            lblTotalRepas.setVisible(true);
            //*****************************************************************************
            
            //*******************List Tarif************************************************
            List<TarifResto> list = serTar.readAll();
            ObservableList<TarifResto> listTarif = FXCollections.observableArrayList(list);
            lblTarifEmpty.setText("");
            listViewTarif.getItems().addAll(listTarif);
            listViewTarif.setExpanded(true);
            listViewTarif.depthProperty().set(1);
            if(listViewTarif.getItems().size()==0){
                    lblTarifEmpty.setText("Il n'existe aucun repas servi !");
                    lblTarifEmpty.setAlignment(Pos.CENTER);
                    lblTarifEmpty.setVisible(true);
                }else {
                    lblTarifEmpty.setVisible(false);
                }
           //*******************************************************************************
           //*******************List Don Resto************************************************
           List<DonRestaurant> list1 = serDon.readAll();
            ObservableList<DonRestaurant> listDon = FXCollections.observableArrayList(list1);
            lblDonEmpty.setText("");
            listViewDonResto.getItems().addAll(listDon);
            listViewDonResto.setExpanded(true);
            listViewDonResto.depthProperty().set(1);
            if(listViewDonResto.getItems().size()==0){
                    lblDonEmpty.setText("Il n'existe aucune donation !");
                    lblTotalDon.setText("Le total des donations : 0.0 Dinars");
                    lblDonEmpty.setAlignment(Pos.CENTER);
                    lblDonEmpty.setVisible(true);
                }else {
                    lblDonEmpty.setVisible(false);
                }
            //*******************************************************************************
           //*******************List Repas Servi************************************************
           List<RepasServi> list2 = serRep.readAll();
            ObservableList<RepasServi> listRep = FXCollections.observableArrayList(list2);
            lblRepasEmpty.setText("");
            listViewRepasServi.getItems().addAll(listRep);
            listViewRepasServi.setExpanded(true);
            listViewRepasServi.depthProperty().set(1);
            if(listViewRepasServi.getItems().size()==0){
                    lblRepasEmpty.setText("Il n'existe aucun repas servi !");
                    lblRepasEmpty.setAlignment(Pos.CENTER);
                    lblRepasEmpty.setVisible(true);
                }else {
                    lblRepasEmpty.setVisible(false);
                }
           //*******************************************************************************
          
         
       

        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdminRestoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
     //initPopup();
    }

    private void chercher(ActionEvent event) throws SQLException {
        ServiceTarifResto serTar = new ServiceTarifResto();
        ServiceDonRestaurant serDon = new ServiceDonRestaurant();
        lblTotalTarif.setVisible(false);
            lblTotalDon.setVisible(false);
            lblTotalRepas.setVisible(false);
        int idResto = serTar.getIdRestoFromName(txtRecherche.getText());
        if(comboRecherche.getSelectionModel().getSelectedItem().getText().compareToIgnoreCase("Restaurant")==0){
            if(idResto > 1){
            listViewTarif.getItems().clear();
            List<TarifResto> list = serTar.selectById(idResto);
            ObservableList<TarifResto> listTarif = FXCollections.observableArrayList(list);
            lblTarifEmpty.setText("");
            listViewTarif.getItems().addAll(listTarif);
            listViewTarif.setExpanded(true);
            listViewTarif.depthProperty().set(1);
            if(listViewTarif.getItems().size()==0){
                    lblTarifEmpty.setText("Il n'existe aucun repas servi !");
                    lblTarifEmpty.setAlignment(Pos.CENTER);
                    lblTarifEmpty.setVisible(true);
                }else {
                    lblTarifEmpty.setVisible(false);
                }
        }
        } else {
            listViewDonResto.getItems().clear();
            List<DonRestaurant> listDon = serDon.donationUser(idResto);
            ObservableList<DonRestaurant> listDonation = FXCollections.observableArrayList(listDon);
            lblDonEmpty.setText("");
            listViewDonResto.getItems().addAll(listDonation);
            listViewDonResto.setExpanded(true);
            listViewDonResto.depthProperty().set(1);
            if(listViewDonResto.getItems().size()==0){
                    lblDonEmpty.setText("Il n'existe aucune donation !");
                    lblTotalDon.setText("Le total des donations : 0.0 Dinars");
                    lblDonEmpty.setAlignment(Pos.CENTER);
                    lblDonEmpty.setVisible(true);
                }else {
                    lblDonEmpty.setVisible(false);
                }
        }
    }
   
    @FXML
  public void chercherF() throws SQLException{
           ServiceTarifResto serTar = new ServiceTarifResto();
        ServiceDonRestaurant serDon = new ServiceDonRestaurant();
        int idResto = serTar.getIdRestoFromName(txtRecherche.getText());
        if(comboRecherche.getSelectionModel().getSelectedItem().getText().compareToIgnoreCase("Restaurant")==0){
            //if(idResto > 1){
            listViewTarif.getItems().clear();
            List<TarifResto> list = serTar.readAll();
             List<TarifResto> listFT = new ArrayList<>();
            for(TarifResto t : list){
                if(t.getNomResto().toLowerCase().contains(txtRecherche.getText().toLowerCase())==true){
                listFT.add(t);
                }
            }
            ObservableList<TarifResto> listTarif = FXCollections.observableArrayList(listFT);
            lblTarifEmpty.setText("");
            listViewTarif.getItems().addAll(listTarif);
            listViewTarif.setExpanded(true);
            listViewTarif.depthProperty().set(1);
            if(listViewTarif.getItems().size()==0){
                    lblTarifEmpty.setText("Il n'existe aucun repas servi !");
                    lblTarifEmpty.setAlignment(Pos.CENTER);
                    lblTarifEmpty.setVisible(true);
                }else {
                    lblTarifEmpty.setVisible(false);
               }
        //}
       } else {
            listViewDonResto.getItems().clear();
            List<DonRestaurant> listDon = serDon.readAll();
            List<DonRestaurant> listFD = new ArrayList<>();
            for(DonRestaurant d : listDon){
                if(d.getNomUser().toLowerCase().contains(txtRecherche.getText().toLowerCase())==true){
                listFD.add(d);
                }
            }
            ObservableList<DonRestaurant> listDonation = FXCollections.observableArrayList(listFD);
            lblDonEmpty.setText("");
            listViewDonResto.getItems().addAll(listDonation);
            listViewDonResto.setExpanded(true);
            listViewDonResto.depthProperty().set(1);
            if(listViewDonResto.getItems().size()==0){
                    lblDonEmpty.setText("Il n'existe aucune donation !");
                    lblTotalDon.setText("Le total des donations : 0.0 Dinars");
                    lblDonEmpty.setAlignment(Pos.CENTER);
                    lblDonEmpty.setVisible(true);
                }else {
                    lblDonEmpty.setVisible(false);
                }
        }
}

   


 


}
