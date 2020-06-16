/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.*;
import Services.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import static java.awt.Color.green;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Ahmed Fourati
 */
public class GestionRestauDonController implements Initializable {
    @FXML
    private StackPane rootPaneM;
    @FXML
    private BorderPane border_pane;
    @FXML
    private StackPane stackSide;
    @FXML
    private Pane stackSide2;
    @FXML
    private Pane paneSide;
    private boolean flag ;
    @FXML
    private JFXTextField txtMontant;
    @FXML
    private JFXButton btnDonner;
    @FXML
    private Label lblSolde;
    @FXML
    private JFXButton btnServir;
    
    int idResto = UiLoginController.uh.getId();
    
    @FXML
    private JFXTextField txtMail;
    @FXML
    private Label lblMessage;
    @FXML
    private Pane paneMessage;
    @FXML
    private JFXButton btnMessage;
    @FXML
    private Pane paneModifTarif;
    @FXML
    private Label lblModifTarif;
    @FXML
    private JFXButton btnModifTarif;
    @FXML
    private JFXButton btnTarif;
    @FXML
    private JFXButton btnAnnulerModifTarif;
    @FXML
    private JFXTextField txtTarif;
    @FXML
    private JFXButton btnAjouterTarif;
    @FXML
    private AnchorPane anchorPaneUser;
    @FXML
    private AnchorPane anchorPaneResto;
    @FXML
    private FlowPane flowPaneUser;
    @FXML
    private Pane paneBlack;
    @FXML
    private Pane paneWeb;
    @FXML
    private JFXButton btnRetourWeb;
    @FXML
    private ScrollPane scrollPaneUser;
    @FXML
    private StackPane stackPaneDialog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        stackSide2.setStyle("-fx-background-color: #2CB687;");
        paneMessage.setVisible(false);
        paneModifTarif.setVisible(false);
        paneBlack.setVisible(false);
        paneWeb.setVisible(false);
        //*****************init color solde******************************
         ServiceTarifResto serTar = new ServiceTarifResto();
         ServiceRepasServi serRep = new ServiceRepasServi();
        float solde = 0;
        try {
            if(serTar.tarifExist(idResto)==true){
            solde = serTar.selectPortefeuille(idResto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblSolde.setText(String.valueOf(solde)+" Dinars");
        try {
            if (serRep.RepasAservir(idResto) == true){
                lblSolde.setStyle("-fx-background-color: #2cb687;");
            }else {
                lblSolde.setStyle("-fx-background-color: #FF0000;");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //**********************************************************************************
        //********************choix de pane a afficher********************************************
        ServiceRestoUser ru = new ServiceRestoUser(); 
        try {
            if(ru.userOrResto(idResto).contains("ROLE_RES")== true){
                anchorPaneResto.setVisible(true);
                anchorPaneUser.setVisible(false);
                btnTarif.setVisible(true);
            }
            if(ru.userOrResto(idResto).contains("ROLE_US")== true){
                anchorPaneResto.setVisible(false);
                anchorPaneUser.setVisible(true);
                btnTarif.setVisible(false);
                affichageUser();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

     @FXML
    private void OpenSidebar(MouseEvent event) {
        if(!flag){
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
            paneSide.setStyle("-fx-background-color: #2CB687;");
            
            
        }

    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
        if(flag){
            flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
            stackSide2.setStyle("-fx-background-color: #2CB687;");
          
        
        }
    }

    @FXML
    private boolean Donner(ActionEvent event) throws SQLException {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date=new java.sql.Timestamp(millis);
        ServiceDonRestaurant serDon = new ServiceDonRestaurant(); 
        if(txtMontant.getText().compareTo("")==0 || serDon.getIdUserFromMail(txtMail.getText())==-1){
             stackPaneDialog.setVisible(true);
            JFXDialogLayout content = new JFXDialogLayout();
            content.getStyleClass().add("dialogErreur");
            content.setHeading(new Text("Oops"));
            content.setBody(new Text("Vérifier votre saisie s'il vous plait !"));
            JFXDialog dialog = new JFXDialog(stackPaneDialog, content, JFXDialog.DialogTransition.CENTER);
            dialog.setMaxSize(400, 200);
            dialog.setMinSize(400, 200);
            dialog.setPrefSize(400, 200);
            JFXButton btn = new JFXButton("Okey");
            btn.getStyleClass().add("buttonDialog");
            btn.setMaxSize(100, 40);
            btn.setMinSize(100, 40);
            btn.setPrefSize(100, 40);
            btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event){
                dialog.close();
                stackPaneDialog.setVisible(false);
            }
             
             
         });
            
            stackPaneDialog.setOnMouseClicked(new EventHandler<MouseEvent>(){

          @Override
          public void handle(MouseEvent arg0) {
            
              dialog.close();
                stackPaneDialog.setVisible(false);
             }

      });
            content.setActions(btn);
            dialog.show();
            return true;
            
        }
        
        int idUser = serDon.getIdUserFromMail(txtMail.getText());
        float Montant = Float.parseFloat(txtMontant.getText());
        
        DonRestaurant d = new DonRestaurant(idResto, idUser, (float) Montant, date);
        serDon.ajouter(d);
        serDon.updatePV(d);
        //************************mettre à jour le Solde************************
        ServiceTarifResto serTar = new ServiceTarifResto();
        ServiceRepasServi serRep = new ServiceRepasServi();
        float solde = 0;
        try {
            solde = serTar.selectPortefeuille(idResto);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblSolde.setText(String.valueOf(solde)+" Dinars");
        try {
            if (serRep.RepasAservir(idResto) == true){
                lblSolde.setStyle("-fx-background-color: #2cb687;");
            }else {
                lblSolde.setStyle("-fx-background-color: #FF0000;");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        
        paneMessage.setVisible(true);
        lblMessage.setText("Donnation effectué !");
        return true;
    }

    @FXML
    private void servir(ActionEvent event) throws SQLException {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date=new java.sql.Timestamp(millis);
        ServiceRepasServi serRep = new ServiceRepasServi();
        RepasServi r = new RepasServi(idResto, date);
        if (serRep.RepasAservir(idResto) == true){
            serRep.ajouter(r);
            serRep.update(r);
            paneMessage.setVisible(true);
            lblMessage.setText("Repas servi !");
            } else {
            //affichage de l'impossibilité de servir un repas
            paneMessage.setVisible(true);
            lblMessage.setText("Votre solde est insuffisant !");
        }
        //************************mettre à jour le Solde************************
        ServiceTarifResto serTar = new ServiceTarifResto();
        float solde = 0;
        try {
            solde = serTar.selectPortefeuille(idResto);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblSolde.setText(String.valueOf(solde)+" Dinars");
        try {
            if (serRep.RepasAservir(idResto) == true){
                lblSolde.setStyle("-fx-background-color: #2cb687;");
            }else {
                lblSolde.setStyle("-fx-background-color: #FF0000;");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
    }

    @FXML
    private void fermerPaneMessage(ActionEvent event) {
        paneMessage.setVisible(false);
        lblMessage.setText("");
        txtMontant.setText("");
        txtMail.setText("");
    }

    @FXML
    private void modifierTarif(ActionEvent event) throws SQLException {
        ServiceTarifResto serTar = new ServiceTarifResto();
        ServiceRepasServi serRep = new ServiceRepasServi();
        if (serTar.tarifExist(idResto)==true){
        TarifResto t =new TarifResto(idResto, Float.parseFloat(txtTarif.getText()));
        serTar.update(t);
        paneModifTarif.setVisible(false);
        txtTarif.setText("");
        } else {
            TarifResto t =new TarifResto(idResto, Float.parseFloat(txtTarif.getText()));
            serTar.ajouter(t);
            paneModifTarif.setVisible(false);
            txtTarif.setText("");
        }
        try {
            if (serRep.RepasAservir(idResto) == true){
                lblSolde.setStyle("-fx-background-color: #2cb687;");
            }else {
                lblSolde.setStyle("-fx-background-color: #FF0000;");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionRestauDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void activerModifTarif(ActionEvent event) throws SQLException {
        ServiceTarifResto serTar = new ServiceTarifResto();
        if (serTar.tarifExist(idResto)==false){
        btnAjouterTarif.setVisible(true);
        btnModifTarif.setVisible(false);
        } else {
            btnAjouterTarif.setVisible(false);
            btnModifTarif.setVisible(true);
        }
        paneModifTarif.setVisible(true);
    }

    @FXML
    private void annulerModifierTarif(ActionEvent event) {
        paneModifTarif.setVisible(false);
        txtTarif.setText("");
    }
    
    
    private void affichageUser() throws SQLException{
         ServiceRestoUser serRU = new ServiceRestoUser();
        List<RestoUser> listRU = null;
        try {
          
          //listDmnd = serDmnd.readAll();
          
          //afficher que les demandes non signalées
          //listDmnd = serDmnd.readNotSign();
          listRU = serRU.readAll();
            System.out.println(listRU);
          
          /*
          //comboBox choix trie demande : date d'ajout proche(nouvelles demandes)
          if(trieType.equals("alphabetique")){
                List<DemandeAide> listDmndSorted = null;
                listDmndSorted =serDmnd.trierCatAlph(listDmnd);
                System.out.println(listDmndSorted);
                listDmnd = listDmndSorted;
          }
          *//*
          //comboBox choix trie Categorie : date d'ajout lointaine(anciennes demandes)
          if(trieType.equals("ajoutDateProche")){
                List<Categorie> listCatSorted = null;
                listCatSorted =serCat.trierCatOrdreAjtDec();
                System.out.println(listCatSorted);
                listCat = listCatSorted;
          }*/
            /*  
          //comboBox choix trie Categorie : date lointaine  
          if(trieType.equals("ajoutDateLointaine")){
                List<Categorie> listCatSorted = null;
                listCatSorted =serCat.trierCatOrdreAjtCroi();
                System.out.println(listCatSorted);
                listCat = listCatSorted;
          }*/
          
        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        //separator de debut à gauche
//        Separator h1 = new Separator(Orientation.VERTICAL);
//            h1.setPrefWidth(40);
//            h1.setPrefHeight(44);
//            h1.setVisible(false);
//            flowPaneUser.getChildren().add(h1);

        for (int i = 0; i < listRU.size(); i++) {
            
            RestoUser resto = listRU.get(i);
            
            //details sur user proprietaire du demande
            String nomResto = listRU.get(i).getNomResto();
            String pays = listRU.get(i).getPays();
            String ville = listRU.get(i).getVille();
            String numTel = listRU.get(i).getNumTel();
            String siteWeb = listRU.get(i).getSiteWeb();
            String pageFb = listRU.get(i).getPageFb();
            String coord = listRU.get(i).getCoord();
            String image = listRU.get(i).getImage();
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            //Vbox container pour l'affichage d'une categorie
            VBox VBoxRU = new VBox();
            //VBoxCat.setSpacing(5);
            //*******************************style modified**********************************************************************
            //VBoxCat.setStyle("-fx-background-color : #dddddd;");
            //VBoxCat.setStyle("-fx-border-radius: 30px;");
            //VBoxCat.getStyleClass().clear();
            VBoxRU.getStyleClass().add("VBoxRU");
            
            VBoxRU.setAlignment(Pos.CENTER);
            //VBoxCat.setPrefHeight(262);
            VBoxRU.setPrefHeight(400);
           // VBoxCat.setPrefWidth(170);
            VBoxRU.setPrefWidth(350);
            VBoxRU.setMaxSize(350, 400);
            VBoxRU.setMinSize(350, 400);

            //Label nomResto
            Label lblNomResto = new Label(nomResto);
            lblNomResto.getStyleClass().add("nomResto");
            //Label emplacement
            Label lblEmplacement = new Label(pays+", "+ville );
            lblEmplacement.getStyleClass().add("emplacement");
            //Label numTel
           Label lblNumTel = new Label("Téléphone : ");
           lblNumTel.getStyleClass().add("numTel");
            //Label numTel
           Label lblNumTelV = new Label(numTel);
           lblNumTelV.getStyleClass().add("numTelV");
           //label pageFb
            Label lblSiteWeb= new Label("Site web :");
            lblSiteWeb.getStyleClass().add("siteWeb");
            //Link siteWeb
             Hyperlink linkSiteWeb = new Hyperlink(siteWeb);
            linkSiteWeb.getStyleClass().add("linkRestoUser");
            //label pageFb
            Label lblPageFb= new Label("Page facebook :");
            lblPageFb.getStyleClass().add("pageFb");
            //Link pageFb
            Hyperlink linkPageFb = new Hyperlink(pageFb);
            linkPageFb.getStyleClass().add("linkRestoUser");
            
            //*******************image Restaurant****************
            
            ImageView imgV = new ImageView();
            imgV.setLayoutX(2);
            imgV.setLayoutY(2);
            try {
                String x=listRU.get(i).getImage();
               imgV.setImage(new Image(new FileInputStream("src/images/"+x),150,150,false,false));
               
            } catch (FileNotFoundException ex) {

            }
            
            //separateur horizontal entre nom et bouttons
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(70);
            sh.setVisible(false);
            //separateur horizontal entre labels
            Separator shL = new Separator(Orientation.HORIZONTAL);
            shL.setPrefHeight(20);
            shL.setVisible(false);
            //separateur horizontal entre labels 1
            Separator shL1 = new Separator(Orientation.HORIZONTAL);
            shL1.setPrefHeight(50);
            shL1.setVisible(false);
            //separateur horizontal entre labels 2
            Separator shL2 = new Separator(Orientation.HORIZONTAL);
            shL2.setPrefHeight(8);
            shL2.setVisible(false);
            //separateur horizontal entre labels 3
            Separator shL3 = new Separator(Orientation.HORIZONTAL);
            shL3.setPrefHeight(8);
            shL3.setVisible(false);
            //separateur horizontal entre labels 4
            Separator shL4 = new Separator(Orientation.HORIZONTAL);
            shL4.setPrefHeight(8);
            shL4.setVisible(false);
            //separateur horizontal entre labels 5
            Separator shL5 = new Separator(Orientation.HORIZONTAL);
            shL5.setPrefHeight(8);
            shL5.setVisible(false);
            //separator dans les hbox1
            Separator vHBox1 = new Separator(Orientation.VERTICAL);
            vHBox1.setPrefWidth(50);
            vHBox1.setVisible(false);
            //separator dans les hbox2
            Separator vHBox2 = new Separator(Orientation.VERTICAL);
            vHBox2.setPrefWidth(50);
            vHBox2.setVisible(false);
            //separator dans les hbox3
            Separator vHBox3 = new Separator(Orientation.VERTICAL);
            vHBox3.setPrefWidth(50);
            vHBox3.setVisible(false);
            
           
            
            
            //boutton aime Demande = btnAime            
            
            JFXButton btnMaps = new JFXButton("Google Maps");
            btnMaps.setPrefSize(170, 25);
            btnMaps.getStyleClass().add("btnMaps");
            //btnAime.setTooltip(new Tooltip("J'aime"));
            //btnMaps.setMaxSize(100, 200);
//            btnMaps.setOnAction((event) -> { 
//               try {
//
//
//
//                Desktop.getDesktop().browse(new URI("http://maps.google.com/maps?q="+coord));
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                System.out.println("ici");
//            } catch (URISyntaxException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            };});
//            
            
           
            
            btnMaps.setOnAction((event) -> { 
                btnRetourWeb.getStyleClass().clear();
                btnRetourWeb.getStyleClass().add("btnRetourMap");
                paneBlack.setVisible(true);
                paneWeb.setVisible(true);
        WebView browser = new WebView();
        browser.setMaxSize(880.0, 486.0);
        paneWeb.getChildren().add(browser);
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://maps.google.com/maps?q="+coord);
        });
            
            linkSiteWeb.setOnAction((event) -> { 
                btnRetourWeb.getStyleClass().clear();
                btnRetourWeb.getStyleClass().add("buttonDialog");
                paneBlack.setVisible(true);
                paneWeb.setVisible(true);
        WebView browser = new WebView();
        browser.setMaxSize(880.0, 486.0);
        paneWeb.getChildren().add(browser);
        WebEngine webEngine = browser.getEngine();
        webEngine.load(linkSiteWeb.getText());
        });
            
            linkPageFb.setOnAction((event) -> { 
                btnRetourWeb.getStyleClass().clear();
                btnRetourWeb.getStyleClass().add("buttonDialog");
                paneBlack.setVisible(true);
                paneWeb.setVisible(true);
        WebView browser = new WebView();
        browser.setMaxSize(880.0, 486.0);
        paneWeb.getChildren().add(browser);
        WebEngine webEngine = browser.getEngine();
        webEngine.load(linkPageFb.getText());
        });
           
            
            
            
            //ajout des differents element representant une categorie dans un Vbox           
             HBox HBoxNumTel = new HBox();
            HBoxNumTel.getStyleClass().add("hbox");
            HBoxNumTel.getChildren().add(vHBox1);
            HBoxNumTel.getChildren().add(lblNumTel);
            HBoxNumTel.getChildren().add(lblNumTelV);   
            HBoxNumTel.setSpacing(0);
            HBoxNumTel.setAlignment(Pos.CENTER);
            
            
                HBox HBoxSiteWeb = new HBox();
            HBoxSiteWeb.getStyleClass().add("hbox");
            HBoxSiteWeb.getChildren().add(vHBox2);
            HBoxSiteWeb.getChildren().add(lblSiteWeb);
            HBoxSiteWeb.getChildren().add(linkSiteWeb);   
            HBoxSiteWeb.setSpacing(0);
            HBoxSiteWeb.setAlignment(Pos.CENTER);
            
            
                HBox HBoxPageFb = new HBox();
            HBoxPageFb.getStyleClass().add("hbox");
            HBoxPageFb.getChildren().add(vHBox3);
            HBoxPageFb.getChildren().add(lblPageFb);
            HBoxPageFb.getChildren().add(linkPageFb);
            HBoxPageFb.setSpacing(0);
            HBoxPageFb.setAlignment(Pos.CENTER);
            
            VBoxRU.getChildren().add(shL);
            VBoxRU.getChildren().add(lblNomResto);
            VBoxRU.getChildren().add(shL2);
            VBoxRU.getChildren().add(lblEmplacement);
//            VBoxRU.getChildren().add(shL1);
            VBoxRU.getChildren().add(imgV);
            VBoxRU.getChildren().add(shL5);
            VBoxRU.getChildren().add(HBoxNumTel);
            VBoxRU.getChildren().add(shL3);
            VBoxRU.getChildren().add(HBoxSiteWeb);
            VBoxRU.getChildren().add(shL4);
            VBoxRU.getChildren().add(HBoxPageFb);
          //  VBoxRU.getChildren().add(linkSiteWeb);
          //  VBoxRU.getChildren().add(linkPageFb);
          
            VBoxRU.getChildren().add(sh);
            VBoxRU.getChildren().add(btnMaps);
            
            
//            HBox btnHBox = new HBox();
//            btnHBox.getStyleClass().add("hbox");
//            btnHBox.getChildren().add(btnAime);
//            btnHBox.getChildren().add(btnPreview);
//            btnHBox.getChildren().add(btnPart);
//            btnHBox.setSpacing(10);
//            btnHBox.setAlignment(Pos.CENTER);
//            
//            VBoxCat.getChildren().add(btnHBox);
            
            /*VBoxCat.getChildren().add(btnAime);
            VBoxCat.getChildren().add(btnPreview);
            VBoxCat.getChildren().add(btnPart);*/

            //vbx array contient les different Vbox(categories)
            vbx.add(VBoxRU);
            //***********************************************
            flowPaneUser.getChildren().add(vbx.get(i));
            
            //****************************************************
            flowPaneUser.getChildren().add(as.get(i));
            
            if (i == 0) {
                System.out.println("i=0");
            } else {
                //if (((i + 1) % 6) == 0) {
                if (((i + 1) % 3) == 0) {
                    Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    //sepHoriz.setPrefWidth(1120);
                    sepHoriz.setPrefHeight(35);
                    sepHoriz.setPrefWidth(1400);
                    
                    sepHoriz.setVisible(false);
                    //******************************************
                    flowPaneUser.getChildren().add(sepHoriz);
                    
                }
            }
            
        }
    }

    @FXML
    private void retourWeb(ActionEvent event) {
        paneBlack.setVisible(false);
        paneWeb.setVisible(false);
    }
    
    
    private void verifMontant(ActionEvent event){
       
        txtMontant.setOnKeyPressed(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
            String value = txtMontant.getText();
            int l = value.length();
            if (Character.getNumericValue(ke.getCharacter().codePointAt(1)) >= '0' && Character.getNumericValue(ke.getCharacter().codePointAt(1)) <= '9') {
               txtMontant.setEditable(true);
//               label.setText("");
            } else {
               txtMontant.setEditable(false);
//               label.setText("* Enter only numeric digits(0-9)");
            }
            }
        });
    
    }

    
    @FXML
    private void montantControl(KeyEvent event) {
        txtMontant.setOnKeyTyped(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
            
            txtMontant.setEditable(true);
            String valeur = txtMontant.getText();
            int l = valeur.length();
            boolean flagEfface =  false;
            boolean flagPoint = false;
            //System.out.println(ke.getText());
            try{
                if((ke.getCode().compareTo(KeyCode.BACK_SPACE))==0) {
                System.out.println("backspace");
                flagEfface = true;
                }
                
                if((ke.getCharacter()).equalsIgnoreCase(String.valueOf('.'))){
                    System.out.println("comma");
                    flagPoint = true;
                }
            if (Integer.parseInt(ke.getCharacter()) >= 0 && Integer.parseInt(ke.getCharacter()) <= 9)  {
                    //txtMontant.setText(valeur+ke.getText());
//                  label.setText("");
            System.out.println("num");
                    }else{
                System.out.println("zah");
            }
                
                
                
                }catch(NumberFormatException ex){
//                    if(ke.getCode().compareTo(KeyCode.BACK_SPACE)==0) {
//                      System.out.println("backspace");
//                    }else{
//                    if(ke.getCode().compareTo(KeyCode.COMMA)==0){
//                    System.out.println("comma");
//                    }else{
                    if(flagEfface == false && flagPoint == false){
                    ke.consume();
                    }
//                            }
//                            
//                
//        }
//        
                
////                    txtMontant.setEditable(false);
//                    if(l<=0){
//                        txtMontant.setText("");
//                        txtMontant.positionCaret(0);
//                    }else{
//                    txtMontant.setText(valeur.substring(0, l));
//                    txtMontant.positionCaret(l);
//                    }
                    
                   
            }
            
        
        }
        });
        
                }

    @FXML
    private void controlTarif(KeyEvent event) {
        txtTarif.setOnKeyTyped(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
            
            txtTarif.setEditable(true);
            String valeur = txtTarif.getText();
            int l = valeur.length();
            boolean flagEfface =  false;
            boolean flagPoint = false;
            //System.out.println(ke.getText());
            try{
                if((ke.getCode().compareTo(KeyCode.BACK_SPACE))==0) {
                System.out.println("backspace");
                flagEfface = true;
                }
                
                if((ke.getCharacter()).equalsIgnoreCase(String.valueOf('.'))){
                    System.out.println("comma");
                    flagPoint = true;
                }
            if (Integer.parseInt(ke.getCharacter()) >= 0 && Integer.parseInt(ke.getCharacter()) <= 9)  {
                    //txtMontant.setText(valeur+ke.getText());
//                  label.setText("");
            System.out.println("num");
                    }else{
                System.out.println("zah");
            }
                
                
                
                }catch(NumberFormatException ex){
//                    if(ke.getCode().compareTo(KeyCode.BACK_SPACE)==0) {
//                      System.out.println("backspace");
//                    }else{
//                    if(ke.getCode().compareTo(KeyCode.COMMA)==0){
//                    System.out.println("comma");
//                    }else{
                    if(flagEfface == false && flagPoint == false){
                    ke.consume();
                    }
//                            }
//                            
//                
//        }
//        
                
////                    txtMontant.setEditable(false);
//                    if(l<=0){
//                        txtMontant.setText("");
//                        txtMontant.positionCaret(0);
//                    }else{
//                    txtMontant.setText(valeur.substring(0, l));
//                    txtMontant.positionCaret(l);
//                    }
                    
                   
            }
            
        
        }
        });
        
                }

    @FXML
    private void home(MouseEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/HomeFXML.fxml"));
            rootPaneM.getChildren().clear();
            rootPaneM.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
                
    
    
    


