/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Categorie_Emplois;
import Entities.Emplois;
//mport Entities.Organisation;
import Services.EmploisService;
import Entities.Utilisateur;
//import Entities.Utilisateur_Simple;
import Services.Categorie_EmploisService;
//import Services.GestionnaireEntreprise;
//import Services.GestionnaireOrganisation;
//import Services.GestionnaireRestaurant;
import Services.GestionnaireUtilisateur;
//import Services.GestionnaireUtilisateur_Simple;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ahmed Fourati
 */
public class GestionEmploiController implements Initializable {

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
    private boolean flag;
    @FXML
    private TilePane tilePaneEmploi;
    @FXML
    private JFXButton allEmp;
    @FXML
    private JFXButton mesemploi;
    @FXML
    private JFXButton ajouteremploi;
     private VBox VBoxajout;
    String imagePublicite;
    String trdate;
    
    Utilisateur UserConnect ;
    @FXML
    private VBox vboxtrier;
    
    
    ArrayList<Emplois> listemmp = new ArrayList<>();
    @FXML
    private JFXTextField rechEmp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmploisService gEmpI = new EmploisService();
        JFXComboBox<String> trierdate = new JFXComboBox<>();
         trierdate.getItems().addAll("Le plus recent", "Le moins recent");
         trierdate.setPrefWidth(148);
         trierdate.setPromptText("Date");
         vboxtrier.getChildren().add(trierdate);
         String trdate =trierdate.getValue();
         
         trierdate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if(trierdate.getValue().equals("Le plus recent")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.trierid(1);
                        System.out.println(listemmp);
                       
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(trierdate.getValue().equals("Le moins recent")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.trierid(0);
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
         
         JFXComboBox<String> triertype = new JFXComboBox<>();
         
         
         
         triertype.getItems().addAll("Demande", "Offre");
         triertype.setPrefWidth(148);
         triertype.setPromptText("Type");
         vboxtrier.getChildren().add(triertype); 
         
         
           triertype.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if(triertype.getValue().equals("Offre")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytype("Offre");
                        System.out.println(listemmp);
                       
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(triertype.getValue().equals("Demande")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytype("Demande");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
         
         
         
         
         
         
         
         JFXComboBox<String> triersalaire = new JFXComboBox<>();
         triersalaire.getItems().addAll("ascendant","descendant");
         triersalaire.setPrefWidth(148);
         triersalaire.setPromptText("Salaire");
         vboxtrier.getChildren().add(triersalaire);
         
         
         triersalaire.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if(triersalaire.getValue().equals("ascendant")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.triersalaire(0);
                        System.out.println(listemmp);
                       
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(triersalaire.getValue().equals("descendant")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.triersalaire(1);
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
         
         JFXComboBox<String> trieremplacement = new JFXComboBox<>();
         trieremplacement.getItems().addAll("Ariana","Beja","BenArous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","SidiBouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");
         trieremplacement.setPrefWidth(148);
         trieremplacement.setPromptText("Emplacement");
         vboxtrier.getChildren().add(trieremplacement); 
         
         
         trieremplacement.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if(trieremplacement.getValue().equals("Tunis")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Tunis");
                        System.out.println(listemmp);
                       
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(trieremplacement.getValue().equals("Ariana")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Ariana");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } 
                else if(trieremplacement.getValue().equals("Beja")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Beja");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(trieremplacement.getValue().equals("BenArous")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("BenArous");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Bizerte")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Bizerte");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Gabes")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Gabes");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Gafsa")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Gafsa");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Jendouba")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Jendouba");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Kairouan")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Kairouan");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Kasserine")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Kasserine");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Kebili")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Kebili");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Kef")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Kef");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Mahdia")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Mahdia");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Manouba")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Manouba");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Medenine")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Medenine");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Monastir")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Monastir");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Nabeul")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Nabeul");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Sfax")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Sfax");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("SidiBouzid")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("SidiBouzid");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Siliana")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Siliana");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Sousse")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Sousse");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Tataouine")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Tataouine");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Tozeur")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Tozeur");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else if(trieremplacement.getValue().equals("Zaghouan")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbyemplacement("Zaghouan");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
         
         JFXComboBox<String> triertypecontrat = new JFXComboBox<>();
         triertypecontrat.getItems().addAll("ContratDureeIndeterminee","Contratdureedeterminee","ContratTravailTemporaire","ContratApprentissage","ContratProfessionnalisation","ContratUniqueInsertion");
         triertypecontrat.setPrefWidth(148);
         triertypecontrat.setPromptText("Type De Contrat");
         vboxtrier.getChildren().add(triertypecontrat); 
        triertypecontrat.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if(triertypecontrat.getValue().equals("ContratDureeIndeterminee")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("ContratDureeIndeterminee");
                        System.out.println(listemmp);
                       
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(triertypecontrat.getValue().equals("Contratdureedeterminee")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("Contratdureedeterminee");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } 
                  else if(triertypecontrat.getValue().equals("ContratTravailTemporaire")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("ContratTravailTemporaire");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                  else if(triertypecontrat.getValue().equals("ContratApprentissage")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("ContratApprentissage");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                  else if(triertypecontrat.getValue().equals("ContratUniqueInsertion")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("ContratUniqueInsertion");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                 else if(triertypecontrat.getValue().equals("ContratProfessionnalisation")){

                    try {
                        listemmp=(ArrayList<Emplois>)gEmpI.readAllbytypecontrat("ContratProfessionnalisation");
                        System.out.println(listemmp);
                      
                        affichageEmpbyFiltre(listemmp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        
        
        System.out.println(HomeFXMLController.isUserRole); 
        UserConnect= UiLoginController.uh;
        
        
        
        cemplacement.getItems().addAll("Ariana","Tunis","Beja","BenArous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","SidiBouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghouan");
        ctypeemploi.getItems().addAll("Offre","Demande");
        ctypecontrat.getItems().addAll("ContratDureeIndeterminee","Contratdureedeterminee","ContratTravailTemporaire","ContratApprentissage","ContratProfessionnalisation","ContratUniqueInsertion");
        
        flag = false;
        stackSide2.setStyle("-fx-background-color: #F6E859;");
        VBoxajout=new VBox();
        affichageTtEmp();
    }

    private void affichageTtEmp() {
       
        EmploisService gEmp = new EmploisService();
        ArrayList<Emplois> listEmp = null;
        try { 
            listEmp = (ArrayList<Emplois>) gEmp.readAll1();
            

        } catch (SQLException ex) {
            Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listEmp.size(); i++) {
            HBox HboxEmp = new HBox();
            HboxEmp.getStyleClass().add("vbEmp");
            HboxEmp.setSpacing(3);
            HboxEmp.setStyle("-fx-background-color : #dddddd;");
            HboxEmp.setPrefHeight(200);
            HboxEmp.setPrefWidth(525);
            HboxEmp.setAlignment(Pos.CENTER);

            Rectangle r = new Rectangle(185, 185);

            try {
                String x=listEmp.get(i).getPhoto();
                r.setFill(new ImagePattern(new Image(new FileInputStream("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+x))));
            } catch (FileNotFoundException ex) {

            }
            HboxEmp.getChildren().add(r);

            VBox VboxEmp = new VBox();
            VboxEmp.setAlignment(Pos.CENTER_LEFT);
            VboxEmp.setPrefHeight(160);
            VboxEmp.setPrefWidth(380);
            VBox VboxInterm = new VBox();
            VboxInterm.getStyleClass().add("vbInterm");
            Label titre = new Label("Titre : " + listEmp.get(i).getTitre());
            Label emplacement = new Label("Emplacement : " + listEmp.get(i).getEmplacement());
            Label salaire = new Label("Salaire : " + listEmp.get(i).getSalaire());
            Label typedemploi = new Label("Type d'Emploi : " + listEmp.get(i).getTypeDemploi());
            Label typecontrat = new Label("Type De Contrat : " + listEmp.get(i).getTypeContrat());
            Label description = new Label("Description : " + listEmp.get(i).getDescreption());
            VboxInterm.getChildren().add(titre);
            VboxInterm.getChildren().add(emplacement);
            VboxInterm.getChildren().add(salaire);
            VboxInterm.getChildren().add(typedemploi);
            VboxInterm.getChildren().add(typecontrat);
            VboxInterm.getChildren().add(description);
            VboxEmp.getChildren().add(VboxInterm);
            
           String maill =listEmp.get(i).getUser().getEmail();
             
            /////////////////////////////////
             
            
            
              Button contacter = new Button("contacter");
              contacter.getStyleClass().add("btnHechem");
              contacter.getStyleClass().add("btnHechem");
            
       
            contacter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
//                         System.out.print(maill);
                    Desktop desktop;
                   if (Desktop.isDesktopSupported() 
                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
                           desktop.mail(mailto);
                        
                      
                            
                        
                       }
                   else {
//   TODO fallback to some Runtime.exec(..) voodoo?
                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                         }
                    
 
                    
                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                        
                    
                }
            }); 
               
            
            
                 Button btnemplacement = new Button("emplacement");
                 btnemplacement.getStyleClass().add("btnHechem");
                 
                 btnemplacement.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
////                         System.out.print(maill);
//                    Desktop desktop;
//                   if (Desktop.isDesktopSupported() 
//                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
//                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
//                           desktop.mail(mailto);
//                       } else {
////   TODO fallback to some Runtime.exec(..) voodoo?
//                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
//                         }
//                    
 
//                                     String l = listemmp.get(i).getUser().getAdresse().g;
//                                     String a = "11.0170375";
                                     
                                     String l = "34.7615155";
                                     String a = "10.6630578";
                    if(Desktop.isDesktopSupported()) //ou alors tu cast un exception
                             {
                             Desktop.getDesktop().browse(new URI("www.google.com/maps/@"+l+","+a+",14z"));
                              }

                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
//              Separator sHBtn = new Separator();
//              sHBtn.setPrefWidth(40);
              HBox hbBtn = new HBox();
              hbBtn.getStyleClass().add("hbBtn");
//              hbBtn.getChildren().add(sHBtn);
              hbBtn.getChildren().add(contacter);
              hbBtn.getChildren().add(btnemplacement);
              VboxEmp.getChildren().add(hbBtn);
//              VboxEmp.getChildren().add(contacter);
            
            ////////////////////////////////
          
            HboxEmp.getChildren().add(VboxEmp);

            tilePaneEmploi.getChildren().add(HboxEmp);
        }
    }

    private void affichageTtEmpbyid() {
       
        tilePaneEmploi.getChildren().clear();
        EmploisService gEmp = new EmploisService();
        ArrayList<Emplois> listEmp = null;
        try {
            listEmp = (ArrayList<Emplois>) gEmp.readAllbyid(UserConnect.getId());
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listEmp.size(); i++) {
            HBox HboxEmp = new HBox();
            HboxEmp.setSpacing(3);
            HboxEmp.setStyle("-fx-background-color : #dddddd;");
            HboxEmp.setPrefHeight(200);
            HboxEmp.setPrefWidth(525);
            HboxEmp.setAlignment(Pos.CENTER);

            Rectangle r = new Rectangle(185, 185);

            try {
                r.setFill(new ImagePattern(new Image(new FileInputStream("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+listEmp.get(i).getPhoto()))));
            } catch (FileNotFoundException ex) {

            }
            HboxEmp.getChildren().add(r);

            VBox VboxEmp = new VBox();
            VboxEmp.setAlignment(Pos.CENTER_LEFT);
            VboxEmp.setPrefHeight(160);
            VboxEmp.setPrefWidth(380);

            TextField titre = new TextField(listEmp.get(i).getTitre());
//            TextField emplacement = new TextField(listEmp.get(i).getEmplacement());
            
             ComboBox<String> emplacement = new ComboBox<>();
            emplacement.getItems().addAll("Ariana","Beja","BenArous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","SidiBouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghouan");
              emplacement.getSelectionModel().select(listEmp.get(i).getEmplacement());
            TextField salaire = new TextField(listEmp.get(i).getSalaire()+"");
            
            
            ComboBox<String> typedemploi = new ComboBox<>();
            typedemploi.getItems().addAll("Offre","Demande");
            
            typedemploi.getSelectionModel().select(listEmp.get(i).getTypeDemploi());
            
            

            
            ComboBox<String> typecontrat = new ComboBox<>();
            typecontrat.getItems().addAll("ContratDureeIndeterminee","Contratdureedeterminee","ContratTravailTemporaire","ContratApprentissage","ContratProfessionnalisation","ContratUniqueInsertion");
            typecontrat.getSelectionModel().select(listEmp.get(i).getTypeContrat());
            
            TextField description = new TextField(listEmp.get(i).getDescreption());
            int IdCategorie= listEmp.get(i).getIdCategorie();
            String photo=listEmp.get(i).getPhoto();
            Utilisateur user = new Utilisateur();
            int id=listEmp.get(i).getId();
            
            HBox hbBtn = new HBox();
            hbBtn.setAlignment(Pos.CENTER_RIGHT);
            Button btSupp = new Button("Supprimer");
            btSupp.getStyleClass().add("btnHechem");
            
            btSupp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(AlertType.WARNING,"êtes-vous sûr de vouloir supprimer la publication  ?",oui,non);
                        alert.setTitle("Supprimer une demande");
                        alert.showAndWait().ifPresent(type -> {
                                    if (type == oui) {
                    try {
                        gEmp.delete(id);
                        affichageTtEmpbyid();
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }}});
                }
            });
            
            Button btModif = new Button("Modifier");
            btModif.getStyleClass().add("btnHechem");
            
            
              salaire.setOnKeyTyped(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
            
            salaire.setEditable(true);
            String valeur = salaire.getText();
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
            
            
            
            
            
            
            btModif.setOnAction(new EventHandler<ActionEvent>() {
                            

                @Override
                public void handle(ActionEvent event) {
                     String chainetitre =titre.getText();
                                       String chainedescription =description.getText();
                    if((chainetitre.length()>2)&&(chainedescription.length()>2))
                    {      
                    try {
                       
                        Emplois emModif = new Emplois(titre.getText(),description.getText(),photo,Double.valueOf(salaire.getText()),emplacement.getValue(),typedemploi.getValue(),typecontrat.getValue(),IdCategorie);
                        gEmp.update(emModif, id);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                    }
                    
                    else
                    {
                        
                         Alert a2 = new Alert(Alert.AlertType.ERROR, "Erreur :vérifier vos champs!", ButtonType.OK);
                        a2.showAndWait();
                    }
                
            }
            });
            
            hbBtn.getChildren().addAll(btSupp,btModif);

            VboxEmp.getChildren().add(titre);
            VboxEmp.getChildren().add(emplacement);
            VboxEmp.getChildren().add(salaire);
            VboxEmp.getChildren().add(typedemploi);
            VboxEmp.getChildren().add(typecontrat);
            VboxEmp.getChildren().add(description);
            
            VboxEmp.getChildren().add(hbBtn);
            
            HboxEmp.getChildren().add(VboxEmp);

            tilePaneEmploi.getChildren().add(HboxEmp);
        } 
              
    }
    
    private void affichageEmpbyFiltre(ArrayList<Emplois> listEmp) {
        //GestionnaireUtilisateur_Simple gUs = new GestionnaireUtilisateur_Simple();
        tilePaneEmploi.getChildren().clear();
        EmploisService gEmp = new EmploisService();
         
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listemmp.size(); i++) {
            HBox HboxEmp = new HBox();
            HboxEmp.setSpacing(3);
            HboxEmp.setStyle("-fx-background-color : #dddddd;");
            HboxEmp.setPrefHeight(200);
            HboxEmp.setPrefWidth(525);
            HboxEmp.setAlignment(Pos.CENTER);

            Rectangle r = new Rectangle(185, 185);

            try {
                String x=listEmp.get(i).getPhoto();
                r.setFill(new ImagePattern(new Image(new FileInputStream("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+x))));
            } catch (FileNotFoundException ex) {

            }
            HboxEmp.getChildren().add(r);

            VBox VboxEmp = new VBox();
            VboxEmp.setAlignment(Pos.CENTER_LEFT);
            VboxEmp.setPrefHeight(160);
            VboxEmp.setPrefWidth(380);

            Label titre = new Label("Titre : " + listemmp.get(i).getTitre());
            Label emplacement = new Label("Emplacement : " + listemmp.get(i).getEmplacement());
            Label salaire = new Label("Salaire : " + listemmp.get(i).getSalaire());
            Label typedemploi = new Label("Type d'Emploi : " + listemmp.get(i).getTypeDemploi());
            Label typecontrat = new Label("Type De Contrat : " + listemmp.get(i).getTypeContrat());
            Label description = new Label("Description : " + listemmp.get(i).getDescreption());
            VboxEmp.getChildren().add(titre);
            VboxEmp.getChildren().add(emplacement);
            VboxEmp.getChildren().add(salaire);
            VboxEmp.getChildren().add(typedemploi);
            VboxEmp.getChildren().add(typecontrat);
            VboxEmp.getChildren().add(description);
            
            String maill =listemmp.get(i).getUser().getEmail();
            //String maill ="aazaza";
  
            /////////////////////////////////
             
            
            
              Button contacter = new Button("contacter");
            
       
            contacter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
//                         System.out.print(maill);
                    Desktop desktop;
                   if (Desktop.isDesktopSupported() 
                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
                           desktop.mail(mailto);
                       } else {
  // TODO fallback to some Runtime.exec(..) voodoo?
                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                         }
                    
                    
                    
                    
                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }); 
              
            
                 Button btnemplacement = new Button("emplacement");
                 btnemplacement.getStyleClass().add("btnHechem");
                 contacter.getStyleClass().add("btnHechem");
                 btnemplacement.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
////                         System.out.print(maill);
//                    Desktop desktop;
//                   if (Desktop.isDesktopSupported() 
//                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
//                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
//                           desktop.mail(mailto);
//                       } else {
////   TODO fallback to some Runtime.exec(..) voodoo?
//                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
//                         }
//                    
 
//                                     String l = listemmp.get(i).getUser().getAdresse().g;
//                                     String a = "11.0170375";
                                     
                                     String l = "34.7615155";
                                     String a = "10.6630578";
                    if(Desktop.isDesktopSupported()) //ou alors tu cast un exception
                             {
                             Desktop.getDesktop().browse(new URI("www.google.com/maps/@"+l+","+a+",14z"));
                              }

                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });





             HBox hbBtn = new HBox();
              hbBtn.getStyleClass().add("hbBtn");
//              hbBtn.getChildren().add(sHBtn);
              hbBtn.getChildren().add(contacter);
              hbBtn.getChildren().add(btnemplacement);
              VboxEmp.getChildren().add(hbBtn);



//             VboxEmp.getChildren().add(btnemplacement);
            
            
            
            
//              VboxEmp.getChildren().add(contacter);
            
            ////////////////////////////////
          
            HboxEmp.getChildren().add(VboxEmp);

            tilePaneEmploi.getChildren().add(HboxEmp);
        }
    }

    
    @FXML
    private void OpenSidebar(MouseEvent event) {
        if (!flag) {
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
            paneSide.setStyle("-fx-background-color: #F6E859;");
        }
    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
        if (flag) {
            flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
            stackSide2.setStyle("-fx-background-color: #F6E859;");

        }
    }

    @FXML
    private void showAllEmp(ActionEvent event) {
        tilePaneEmploi.getChildren().clear();
        affichageTtEmp();
    }

    @FXML
    private void showembyid(ActionEvent event) {

        tilePaneEmploi.getChildren().clear();
        affichageTtEmpbyid();
    }
    
        
    
    
    
   
   
    
    //ajout 
    JFXTextField ttitre = new JFXTextField();
    JFXTextField tdescription = new JFXTextField();
//    JFXTextField tphoto = new JFXTextField();
//    JFXPasswordField tfPassword = new JFXPasswordField();
//    JFXPasswordField tfConfirmPassword = new JFXPasswordField();
     JFXTextField tsalaire = new JFXTextField();
    JFXComboBox<String> cemplacement = new JFXComboBox<>();
    JFXComboBox<String> ctypeemploi = new JFXComboBox<>();
    JFXComboBox<String> ctypecontrat = new JFXComboBox<>();
    JFXComboBox<String> cidcategorie = new JFXComboBox<>();
   
    
    
   
    
    
   
   

    /**
     * Initializes the controller class.
     */
 
   private void importerPubliciteImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser() ; 
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if(f !=null){
        
      //      publiciteImageCommentaire.setText("Image selectionnée"+f.getAbsolutePath());
             InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(f.getAbsolutePath()));
//             System.out.println("Working Directory = " +
//              System.getProperty("user.dir"));
//            System.out.println("nomfichier"+f.getName());
            os = new FileOutputStream(new File("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+f.getName()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            System.out.println("szzszas");
            
             
        } finally {
            is.close();
            os.close();
        }
           
              File file = new File("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+f.getName());
//            System.out.println(file.toURI().toString());
            ImageView imagePublicitePreview = new ImageView();
            imagePublicitePreview.setImage(new Image(file.toURI().toString()));
            imagePublicite=f.getName() ; 
            System.out.println( imagePublicite);
        }
        //else if (f ==null){
          //  publiciteImageCommentaire.setText("Erreur chargement de l'image");
        //}
    }
   
    private void ajouter() throws SQLException {
          
        VBoxajout.getChildren().clear();
        VBoxajout.setSpacing(17);

        ttitre.setPromptText("Titre : ");
        ttitre.setLabelFloat(true);
        tdescription.setPromptText("desc : ");
        tdescription.setLabelFloat(true);
        
         
         
        Button btupload = new Button("upload");
        btupload.getStyleClass().add("btnHechem");
        
        btupload.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent event) {
                   
                    try {
                        importerPubliciteImage(event);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
    } );
//        tphoto.setPromptText("tphoto : ");
//        tphoto.setLabelFloat(true);
//        
        tsalaire.setPromptText("salaire : ");
        tsalaire.setLabelFloat(true);
        
           String longsalaire = tsalaire.getText(); 


        
        
        
        Categorie_EmploisService serCat = new Categorie_EmploisService();
        List<Categorie_Emplois> listCat = serCat.readAllCategorieEmplois();  
        List<String> listCatNom = new ArrayList<>();
       for (Categorie_Emplois cat : listCat) {
            listCatNom.add(cat.getTitre());
         }
         
         
         ComboBox cidcategorie = new ComboBox();
         ObservableList<String> catChoix = FXCollections.observableArrayList();
         catChoix.addAll(listCatNom);
         cidcategorie.setItems(catChoix);
         cidcategorie.setValue("choisir categorie");
        
//          cidcategorie.getItems().addAll(listt.get(i).getId_Categorie_Emplois(),+"");
        
    //            aa= aa.concat((listt.get(i).getTitre()));    
        
        VBoxajout.getStyleClass().add("vbxAdd");
         VBoxajout.setAlignment(Pos.CENTER);
        VBoxajout.getChildren().addAll(ttitre,tdescription,tsalaire,cemplacement,ctypeemploi,ctypecontrat,cidcategorie);
         VBoxajout.getChildren().add(btupload);  
        Button btajouter = new Button("ajouter");
        btajouter.getStyleClass().add("btnHechem");
         VBoxajout.getChildren().add(btajouter);
         
        tilePaneEmploi.getChildren().add(VBoxajout); 
        
        
        
     
        tsalaire.setOnKeyTyped(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent ke) {
            
            tsalaire.setEditable(true);
            String valeur = tsalaire.getText();
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
        
                
 
        
        
        
        
        
        
        
        
        
            btajouter.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                public void handle(ActionEvent event) {
                   EmploisService serv = new EmploisService();
                   Categorie_EmploisService catserv = new Categorie_EmploisService();
                   Utilisateur user = new Utilisateur();
//                   String cat =cidcategorie;
                   Categorie_Emplois categ =new Categorie_Emplois("b","a");
                   
                   
                   
                   
                   
                   
                    String longdescription = tdescription.getText(); 
                    String longtitre = ttitre.getText(); 

                   
                   if( (longdescription.length()>2)&&(longtitre.length()>2))
                    {
                    try {
                        categ = catserv.readByName((String) cidcategorie.getValue());
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     int iddd=categ.getId_Categorie_Emplois();
                     System.out.println(iddd);
                     System.out.println("aaaaaaaaa");
                     System.out.println(iddd);
                   Emplois emp = new Emplois(ttitre.getText(),tdescription.getText(),imagePublicite,Double.valueOf(tsalaire.getText()),cemplacement.getValue(),ctypeemploi.getValue(),ctypecontrat.getValue(),iddd,UserConnect);
                   
                    //System.out.println(ttitre.getText()+tdescription.getText()+tphoto.getText()+Double.valueOf(tsalaire.getText())+cemplacement.getValue()+ctypeemploi.getValue()+ctypecontrat.getValue()+Integer.valueOf(cat)+"1111111111111111111111aa");
                  
                    try {
                        serv.ajouter(emp);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                   else 
                    {
                    
                     Alert al = new Alert(Alert.AlertType.ERROR, "Erreur :vérifier vos champs!", ButtonType.OK);
                      al.showAndWait();
                    }
                
                }
                
    } );
                    

   
    }

    @FXML
    private void ajouteremploi(ActionEvent event) throws SQLException {
        
        tilePaneEmploi.getChildren().clear();
        ajouter();
    }

    @FXML
    private void rechercheEmploi(KeyEvent event) {
        tilePaneEmploi.getChildren().clear();
        EmploisService gEmp = new EmploisService();
        ArrayList<Emplois> listemmp = new ArrayList<>();
        try {
            listemmp = (ArrayList<Emplois>) gEmp.rechercheByAll(rechEmp.getText());
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(listemmp);
        
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listemmp.size(); i++) {
            HBox HboxEmp = new HBox();
            HboxEmp.setSpacing(3);
            HboxEmp.setStyle("-fx-background-color : #dddddd;");
            HboxEmp.setPrefHeight(200);
            HboxEmp.setPrefWidth(525);
            HboxEmp.setAlignment(Pos.CENTER);

            Rectangle r = new Rectangle(185, 185);

            try {
                String x=listemmp.get(i).getPhoto();
                r.setFill(new ImagePattern(new Image(new FileInputStream("./../donationWebPIDEV/web/uploads/imagesEmploi/emploi/"+x))));
            } catch (FileNotFoundException ex) {

            }
            HboxEmp.getChildren().add(r);

            VBox VboxEmp = new VBox();
            VboxEmp.setAlignment(Pos.CENTER_LEFT);
            VboxEmp.setPrefHeight(160);
            VboxEmp.setPrefWidth(380);

            Label titre = new Label("Titre : " + listemmp.get(i).getTitre());
            Label emplacement = new Label("Emplacement : " + listemmp.get(i).getEmplacement());
            Label salaire = new Label("Salaire : " + listemmp.get(i).getSalaire());
            Label typedemploi = new Label("Type d'Emploi : " + listemmp.get(i).getTypeDemploi());
            Label typecontrat = new Label("Type De Contrat : " + listemmp.get(i).getTypeContrat());
            Label description = new Label("Description : " + listemmp.get(i).getDescreption());
            VboxEmp.getChildren().add(titre);
            VboxEmp.getChildren().add(emplacement);
            VboxEmp.getChildren().add(salaire);
            VboxEmp.getChildren().add(typedemploi);
            VboxEmp.getChildren().add(typecontrat);
            VboxEmp.getChildren().add(description);
            
            String maill =listemmp.get(i).getUser().getEmail();
            //String maill ="aazaza";
  
            /////////////////////////////////
             
            
            
              Button contacter = new Button("contacter");
            
       
            contacter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
//                         System.out.print(maill);
                    Desktop desktop;
                   if (Desktop.isDesktopSupported() 
                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
                           desktop.mail(mailto);
                       } else {
  // TODO fallback to some Runtime.exec(..) voodoo?
                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                         }
                    
                    
                    
                    
                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }); 
            
            
           
                 Button btnemplacement = new Button("emplacement");
                 btnemplacement.getStyleClass().add("btnHechem");
                  contacter.getStyleClass().add("btnHechem");
                 btnemplacement.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try { 
////                         System.out.print(maill);
//                    Desktop desktop;
//                   if (Desktop.isDesktopSupported() 
//                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
//                                 URI mailto = new URI("mailto:"+maill+"?subject=Offre%20d'%20emploi%20sur%20la%20platforme%20DoNation");
//                           desktop.mail(mailto);
//                       } else {
////   TODO fallback to some Runtime.exec(..) voodoo?
//                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
//                         }
//                    
 
//                                     String l = listemmp.get(i).getUser().getAdresse().g;
//                                     String a = "11.0170375";
                                     
                                     String l = "34.7615155";
                                     String a = "10.6630578";
                    if(Desktop.isDesktopSupported()) //ou alors tu cast un exception
                             {
                             Desktop.getDesktop().browse(new URI("www.google.com/maps/@"+l+","+a+",14z"));
                              }

                    
                    
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });



            HBox hbBtn = new HBox();
              hbBtn.getStyleClass().add("hbBtn");
//              hbBtn.getChildren().add(sHBtn);
              hbBtn.getChildren().add(contacter);
              hbBtn.getChildren().add(btnemplacement);
              VboxEmp.getChildren().add(hbBtn);
//              VboxEmp.getChildren().add(contacter);
            
            ////////////////////////////////
          
            HboxEmp.getChildren().add(VboxEmp);

            tilePaneEmploi.getChildren().add(HboxEmp);
    }

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
