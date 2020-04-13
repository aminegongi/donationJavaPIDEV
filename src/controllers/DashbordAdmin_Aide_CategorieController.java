/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.*;
import Services.*;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.geometry.Orientation;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Hedi
 */
public class DashbordAdmin_Aide_CategorieController implements Initializable {

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
    private TextField tfNouvCatNom;
    @FXML
    private FlowPane flowPaneCategories;
    @FXML
    private ComboBox comboTrier;
    ObservableList<String> trieChoix = FXCollections.observableArrayList("ordre alphabétique","date d'ajout proche","date d'ajout lointaine");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            comboTrier.setValue("Trier par");
            comboTrier.setItems(trieChoix);
            // TODO
            this.affichageCategories("none");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void AjouterCategorie(ActionEvent event) throws FileNotFoundException {
        String name = tfNouvCatNom.getText();
        System.out.println(name);
        Categorie nouvelleCat = new Categorie(name);
        ServiceCategorie serCat = new ServiceCategorie();
        

        
        try {
            if(!(name.isEmpty())){
            serCat.ajouter(nouvelleCat);
            Alert al = new Alert(Alert.AlertType.INFORMATION, "la Categorie "+name+" a été ajoutée avec succès!", ButtonType.OK);
            al.show();
            }
            else{
                Alert al = new Alert(Alert.AlertType.ERROR, "Veuillez saisir un nom pour la categorie", ButtonType.OK);
            al.show();
            }
        } catch (SQLException ex) {
            System.out.println("deja existante");
            Alert al = new Alert(Alert.AlertType.ERROR, "Ooops! la Categorie "+name.toLowerCase()+" est deja Existante!", ButtonType.OK);
            al.show();
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        flowPaneCategories.getChildren().clear();
        this.affichageCategories("none");
        comboTrier.setValue("Trier par");
    }
    
    //trie type = alphabetique ou ajoutDateProche ou ajoutDateLointaine 
    private void affichageCategories(String trieType) throws FileNotFoundException {

        ServiceCategorie serCat = new ServiceCategorie();
        List<Categorie> listCat = null;
        try {
          listCat = serCat.readAll();
          
          //comboBox choix trie Categorie : alphabetique
          if(trieType.equals("alphabetique")){
                List<Categorie> listCatSorted = null;
                listCatSorted =serCat.trierCatAlph(listCat);
                System.out.println(listCatSorted);
                listCat = listCatSorted;
          }
          
          //comboBox choix trie Categorie : date proche
          if(trieType.equals("ajoutDateProche")){
                List<Categorie> listCatSorted = null;
                listCatSorted =serCat.trierCatOrdreAjtDec();
                System.out.println(listCatSorted);
                listCat = listCatSorted;
          }
              
          //comboBox choix trie Categorie : date lointaine  
          if(trieType.equals("ajoutDateLointaine")){
                List<Categorie> listCatSorted = null;
                listCatSorted =serCat.trierCatOrdreAjtCroi();
                System.out.println(listCatSorted);
                listCat = listCatSorted;
          }
          
        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listCat.size(); i++) {
            
            int idCat = listCat.get(i).getId();
            String NomCat = listCat.get(i).getNom();
            
            
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            //Vbox container pour l'affichage d'une categorie
            VBox VBoxCat = new VBox();
            VBoxCat.setSpacing(5);
            VBoxCat.setStyle("-fx-background-color : #dddddd;");
            VBoxCat.setAlignment(Pos.CENTER);
            VBoxCat.setPrefHeight(120);
            VBoxCat.setPrefWidth(170);

            //Label nom = new Label("Nom : " + listCat.get(i).getNom());
            Label nom = new Label(listCat.get(i).getNom());
            nom.getStyleClass().add("titrecatbox");
            //separateur horizontal entre nom et bouttons
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(30);
            sh.setVisible(false);
            
            
            //boutton supprimer Categorie = btnSupp            
            FileInputStream inputSupp = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/delete.png");
            Image imageSupp = new Image(inputSupp);
            ImageView imageViewSupp = new ImageView(imageSupp);
            imageViewSupp.setFitHeight(20);
            imageViewSupp.setFitWidth(20);
            Button btnSupp = new Button("", imageViewSupp);
            btnSupp.setTooltip(new Tooltip("Supprimer"));
            btnSupp.setMaxSize(100, 200);
            btnSupp.setOnAction((event) -> {       
                        ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(AlertType.WARNING,"êtes-vous sûr de vouloir supprimer la categorie "+NomCat+" ?",oui,non);
                        alert.setTitle("Supprimer une categorie");
                        alert.showAndWait().ifPresent(type -> {
                                    if (type == oui) {
                                        System.out.println("btnSupp Action");
                                        try {
                                            this.supprimerCategorie(idCat);
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "Categorie "+NomCat+" supprimée avec succès!", ButtonType.OK);
                                            al.show();
                                            System.out.println("supprimer ok!");
                                            
                                        }catch (SQLException | FileNotFoundException ex){
                                            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
                                        }
                                        });
                                        });
            
            //boutton modifier Categorie = btnModif
            FileInputStream inputModif = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/edit.png");
            Image imageModif = new Image(inputModif);
            ImageView imageViewModif = new ImageView(imageModif);
            imageViewModif.setFitHeight(20);
            imageViewModif.setFitWidth(20);
            Button btnModif = new Button("", imageViewModif);
            btnModif.setTooltip(new Tooltip("Modifier"));
            btnModif.setMaxSize(100, 200);
            btnModif.setOnAction((event) -> {
            System.out.println("Button Modif ");
            //fenetre apres click sur btnModif
            TextInputDialog dialog = new TextInputDialog(NomCat);
            dialog.setTitle("Modification d'une categorie");
            dialog.setHeaderText("Saisir un nouveau nom pour la categorie "+NomCat);
            dialog.setContentText("nouveau nom :");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nouvNom -> {
                                
                                try {
                                this.modifierCategorie(idCat,nouvNom);
                                Alert al = new Alert(Alert.AlertType.INFORMATION, "Categorie "+NomCat+" modifiée avec succès!", ButtonType.OK);
                                al.show();
                                System.out.println("Your name: " + nouvNom);
                                } catch (SQLException ex) {
                                Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (FileNotFoundException ex) {
                                Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                });
                                });
            
            //Hbox contient les bouttons
            HBox hboxbtn = new HBox();
            hboxbtn.getChildren().addAll(btnModif,btnSupp);
            hboxbtn.setSpacing(10);
            hboxbtn.setAlignment(Pos.CENTER);
            
            //ajout des differents element representant une categorie dans un Vbox           
            VBoxCat.getChildren().add(nom);
            VBoxCat.getChildren().add(sh);
            //VBoxCat.getChildren().add(btnSupp);
            //VBoxCat.getChildren().add(btnModif);
            VBoxCat.getChildren().add(hboxbtn);
            VBoxCat.getStyleClass().add("vboxbtncat");

            //vbx array contient les different Vbox(categories)
            vbx.add(VBoxCat);
            flowPaneCategories.getChildren().add(vbx.get(i));

            flowPaneCategories.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0");
            } else {
                if (((i + 1) % 6) == 0) {
                    Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    sepHoriz.setPrefWidth(1120);
                    sepHoriz.setPrefHeight(35);
                    sepHoriz.setVisible(false);
                    flowPaneCategories.getChildren().add(sepHoriz);
                }
            }
        }
    }
        
    public void supprimerCategorie(int id) throws SQLException, FileNotFoundException{
        ServiceCategorie serCat = new ServiceCategorie();
        serCat.delete(id);
        flowPaneCategories.getChildren().clear();
        this.affichageCategories("none");
        }
        
    public void modifierCategorie(int id,String nouvNom) throws SQLException, FileNotFoundException{
        ServiceCategorie serCat = new ServiceCategorie();
        Categorie cat = new Categorie(nouvNom);
        serCat.update(id,cat);
        flowPaneCategories.getChildren().clear();
        this.affichageCategories("none");
        }

    @FXML
    public void trierCategories() throws FileNotFoundException{
    if (comboTrier.getValue().equals("ordre alphabétique")){
            System.out.println("pressed ordre alphabétique");
            flowPaneCategories.getChildren().clear();
            this.affichageCategories("alphabetique");
    }
    
    else if(comboTrier.getValue().equals("date d'ajout proche")){
        
            //System.out.println("date d'ajout");
            flowPaneCategories.getChildren().clear();
            this.affichageCategories("ajoutDateProche");
    }
    
    
    else if(comboTrier.getValue().equals("date d'ajout lointaine")){
        
            //System.out.println("date d'ajout");
            flowPaneCategories.getChildren().clear();
            this.affichageCategories("ajoutDateLointaine");
    }
    
    }
}
