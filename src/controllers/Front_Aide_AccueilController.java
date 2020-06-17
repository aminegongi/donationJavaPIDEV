/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Categorie;
import Entities.DemandeAide;
import Entities.Participation;
import Entities.Signalement;
import Entities.User;
import Entities.UserTest;
import Entities.Utilisateur;
import Services.ServiceCategorie;
import Services.ServiceDemandeAide;
import Services.ServiceParticipation;
import Services.ServiceSignalement;
import Services.ServiceUser;
import Utils.Etat;
import Utils.Mail;
import Utils.RaisonSignale;
import Utils.Sms;
import static java.awt.SystemColor.text;
import java.awt.Window;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Hedi
 */
public class Front_Aide_AccueilController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private Button btnMesDmnd;
    @FXML
    private Button btnAjoutDmnd;
    @FXML
    private FlowPane flowPaneListeDmnd;
    @FXML
    private Label listeDmndAide;
    
    
    @FXML
    private StackPane stackSide;
    @FXML
    private Pane stackSide2;
    @FXML
    private Pane paneSide;
    boolean flag = false;
    
    @FXML
    private Pane opacityPane;
    
    @FXML
    private Pane descPane;
    @FXML
    private ScrollPane descScrollPane;
    @FXML
    private StackPane rootPaneM;
    @FXML
    private BorderPane border_pane;
    
    @FXML
    private Button btnListeDmnd;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            opacityPane.setVisible(false);
            descPane.setVisible(false);
            btnListeDmnd.setVisible(false);
            //btnMesDmnd.setVisible(false);
            this.affichageDemandes("none");
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stackSide2.setStyle("-fx-background-color: #F89843;");
    }    

    @FXML
    private void goToMesDmnd(ActionEvent event) throws SQLException, FileNotFoundException {
        System.out.println("btn mes dmnd ok");
        //this.clearPane();
        btnListeDmnd.setVisible(true);
       flowPaneListeDmnd.getChildren().clear();
           
        //flowPaneListeDmnd.getChildren().add(new Label("hghghg"));
        
       // btnListeDmnd.setVisible(true);
       // listeDmndAide.setText("Mes demandes d'aide");
        
        
        ServiceDemandeAide serDmnd = new ServiceDemandeAide();
        List<DemandeAide> listDmnd = null;
        try {
  
          listDmnd = serDmnd.readByIdUser();
          
          System.out.print("my demandes : ");
          System.out.print(listDmnd);
          
          
        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listDmnd.size(); i++) {
            
            DemandeAide dmnd = listDmnd.get(i);
            //integration modif ***************************************************************************************
            //details sur user proprietaire du demande
           /* int idUser = listDmnd.get(i).getIdUser();
            ServiceUser serUser = new ServiceUser();
            User  user = serUser.readById(idUser);
            String nomUser = user.getNom();
            String prenomUser = user.getPrenom();
            String mailUser = user.getMail();
            */
            //**********************************************************************************************************
            /*
            String nomUser = "hedi";
            String prenomUser = "hedi";
            String mailUser = "hedi";
            */
            int idUser = listDmnd.get(i).getIdUser();
            ServiceUser serUser = new ServiceUser();
            Utilisateur  user = serUser.getById(idUser);
            String nomUser = user.getUsername();
            System.out.println("connected" + nomUser);
            //String prenomUser = user.getPrenom();
            String mailUser = user.getEmail();
            
            
            int idDmnd = listDmnd.get(i).getId();
            String TitreDmnd = listDmnd.get(i).getTitre();
            
            
            
            
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            //Vbox container pour l'affichage d'une categorie
            VBox VBoxCat = new VBox();
            //VBoxCat.setSpacing(5);
            //*******************************style modified**********************************************************************
            //VBoxCat.setStyle("-fx-background-color : #dddddd;");
            //VBoxCat.setStyle("-fx-border-radius: 30px;");
            //VBoxCat.getStyleClass().clear();
            VBoxCat.getStyleClass().add("vbox");
            
            VBoxCat.setAlignment(Pos.CENTER);
            //VBoxCat.setPrefHeight(262);
            VBoxCat.setPrefHeight(300);
           // VBoxCat.setPrefWidth(170);
           VBoxCat.setPrefWidth(350);

            //Label titre = new Label("Titre : " + listDmnd.get(i).getTitre());
            Label titreLbl = new Label(listDmnd.get(i).getTitre());
            titreLbl.getStyleClass().add("titre");
            //Label description
            Label descriptionLbl = new Label(listDmnd.get(i).getDescription());
            descriptionLbl.getStyleClass().add("desc");
            //Label publier par
            Label publierParLbl = new Label("Publiée par :");
            publierParLbl.getStyleClass().add("pbParLbl");
            //Label nomUser et prenomUser
            Label nomPrenomUserLbl = new Label(nomUser);
            //Label mailUser
            Label mailUserLbl = new Label(mailUser);
            mailUserLbl.getStyleClass().add("mailLbl");
            //Label nombre reaction jaime
            Label nbrReactLbl = new Label("J'aime : "+listDmnd.get(i).getNbReactions());
            nbrReactLbl.getStyleClass().add("aimeLbl");
            
            
            
            //separateur horizontal entre nom et bouttons
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(30);
            sh.setVisible(false);
            
            

            
            
            //boutton supprimer demande = btnSupp            
//integration modif ***************************************************************
            //FileInputStream inputSupp = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/delete.png");
            FileInputStream inputSupp = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/delete.png");

            Image imageSupp = new Image(inputSupp);
            ImageView imageViewSupp = new ImageView(imageSupp);
            imageViewSupp.setFitHeight(20);
            imageViewSupp.setFitWidth(20);
            Button btnSupp = new Button("", imageViewSupp);
          
       //Button btnSupp = new Button("supp");
//************************************************************************************************
            btnSupp.setTooltip(new Tooltip("Supprimer"));
            btnSupp.setMaxSize(100, 200);
            btnSupp.setOnAction((e) -> {       
                        ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(AlertType.WARNING,"êtes-vous sûr de vouloir supprimer la categorie "+dmnd.getTitre()+" ?",oui,non);
                        alert.setTitle("Supprimer une demande");
                        alert.showAndWait().ifPresent(type -> {
                                    if (type == oui) {
                                        System.out.println("btnSupp Action");
                                        try {System.out.println("id demaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaande"+idDmnd);
                                            this.supprimerDmnd(idDmnd);
                                            this.suppToMesDmnd();
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "demande "+dmnd.getTitre()+" supprimée avec succès!", ButtonType.OK);
                                            al.show();
                                            System.out.println("supprimer ok!");
                                            
                                        }catch (SQLException | FileNotFoundException ex){
                                            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
                                        }
                                        });
                                        });
            
            
 //************************************************************************************************************************************           
            
            //boutton preview Demande = btnpreview
            
//integration modif*************************************************************************************************************************
           
            //FileInputStream inputPreview = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/preview.png");
            FileInputStream inputPreview = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/preview.png");

            Image imagePreview = new Image(inputPreview);
            ImageView imageViewPreview = new ImageView(imagePreview);
            imageViewPreview.setFitHeight(30);
            imageViewPreview.setFitWidth(30);
            Button btnPreview = new Button("", imageViewPreview);
            
           //Button btnPreview = new Button("prev");
//***************************************************************************************************************************************
            btnPreview.setTooltip(new Tooltip("Description"));
            btnPreview.setMaxSize(100, 200);
            btnPreview.setOnAction((e) -> {
            System.out.println("Button preview ");
                try {
                    this.afficherDescriptionMaDmnd(dmnd);
                } catch (SQLException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                });
            
            
            

            
   
            //ajout des differents element representant une categorie dans un Vbox           
            VBoxCat.getChildren().add(titreLbl);
            VBoxCat.getChildren().add(descriptionLbl);
            
            VBoxCat.getChildren().add(publierParLbl);
            VBoxCat.getChildren().add(nomPrenomUserLbl);
            VBoxCat.getChildren().add(mailUserLbl);
            
            //VBoxCat.getChildren().add(nbrReactLbl);
            VBoxCat.getChildren().add(sh);
            
            HBox btnHBox = new HBox();
            btnHBox.getStyleClass().add("hbox");
            btnHBox.getChildren().add(btnSupp);
            btnHBox.getChildren().add(btnPreview);
            //btnHBox.getChildren().add(btnPart);
            btnHBox.setSpacing(10);
            btnHBox.setAlignment(Pos.CENTER);
            
            VBoxCat.getChildren().add(btnHBox);
            
            /*VBoxCat.getChildren().add(btnAime);
            VBoxCat.getChildren().add(btnPreview);
            VBoxCat.getChildren().add(btnSup);*/

            //vbx array contient les different Vbox(categories)
            vbx.add(VBoxCat);
            //***********************************************
            flowPaneListeDmnd.getChildren().add(vbx.get(i));
            
            //****************************************************
            flowPaneListeDmnd.getChildren().add(as.get(i));
            
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
                    flowPaneListeDmnd.getChildren().add(sepHoriz);
                    
                }
            }
        }
        
        
        
        
    }

    @FXML
    private void goToAjoutDmnd(ActionEvent event) throws SQLException, FileNotFoundException {
        System.out.println("btn ajouter dmnd ok");
       // this.clearPane();
       this.afficheFormAjoutDmnd();
    }
    
    public void clearPane(){
    //listeDmndAide.setVisible(false);
    flowPaneListeDmnd.setVisible(false);
    flowPaneListeDmnd.getChildren().clear();
    
    }
    
    
    public void AimeDmnd(int id) throws SQLException, FileNotFoundException{
    ServiceDemandeAide serDmnd = new ServiceDemandeAide();
    serDmnd.reagir(id);
    flowPaneListeDmnd.getChildren().clear();
    this.affichageDemandes("none");
    }
    
    //signale une demande, prend en param un objet signalement retourne par this.afficheFormSignale()
    public void signaleDmnd(Signalement sig) throws SQLException, FileNotFoundException{
    ServiceSignalement serSig = new ServiceSignalement();
    //Signalement sig = new Signalement(idDemande, idUser, raison, description);
    serSig.ajouter(sig);


    }
    
        private void affichageDemandes(String trieType) throws FileNotFoundException, SQLException {

        ServiceDemandeAide serDmnd = new ServiceDemandeAide();
        List<DemandeAide> listDmnd = null;
        try {
          
          //listDmnd = serDmnd.readAll();
          
          //afficher que les demandes non signalées
          //listDmnd = serDmnd.readNotSign();
          listDmnd = serDmnd.readNotSignSql();
          
          System.out.println("liste demandes : ");
          
          System.out.println(listDmnd);
          
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

        for (int i = 0; i < listDmnd.size(); i++) {
            
            DemandeAide dmnd = listDmnd.get(i);
            
            
            //integration modif******************************************************************************************************************
            
            //details sur user proprietaire du demande
            /*
            int idUser = listDmnd.get(i).getIdUser();
            ServiceUser serUser = new ServiceUser();
            User  user = serUser.readById(idUser);
            String nomUser = user.getNom();
            String prenomUser = user.getPrenom();
            String mailUser = user.getMail();
            */
            //**********************************************************************************************************************************
            /*
            String nomUser = "khalifa";
            String prenomUser = "hedi";
            String mailUser = "hedi.khalifa@gmail.com";
            */
            
            
            int idUser = listDmnd.get(i).getIdUser();
            ServiceUser serUser = new ServiceUser();
            Utilisateur  user = serUser.getById(idUser);
            String nomUser = user.getUsername();
            //String prenomUser = user.getPrenom();
            String mailUser = user.getEmail();
            
            
            
            
            
            
            int idDmnd = listDmnd.get(i).getId();
            String TitreDmnd = listDmnd.get(i).getTitre();
            
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            //Vbox container pour l'affichage d'une categorie
            VBox VBoxCat = new VBox();
            //VBoxCat.setSpacing(5);
            //*******************************style modified**********************************************************************
            //VBoxCat.setStyle("-fx-background-color : #dddddd;");
            //VBoxCat.setStyle("-fx-border-radius: 30px;");
            //VBoxCat.getStyleClass().clear();
            VBoxCat.getStyleClass().add("vbox");
            
            VBoxCat.setAlignment(Pos.CENTER);
            //VBoxCat.setPrefHeight(262);
            VBoxCat.setPrefHeight(300);
           // VBoxCat.setPrefWidth(170);
           VBoxCat.setPrefWidth(350);

            //Label titre = new Label("Titre : " + listDmnd.get(i).getTitre());
            Label titreLbl = new Label(listDmnd.get(i).getTitre());
            titreLbl.getStyleClass().add("titre");
            //Label description
            Label descriptionLbl = new Label(listDmnd.get(i).getDescription());
            descriptionLbl.getStyleClass().add("desc");
            //Label publier par
            Label publierParLbl = new Label("Publiée par :");
            publierParLbl.getStyleClass().add("pbParLbl");
            //Label nomUser et prenomUser
            //Label nomPrenomUserLbl = new Label(nomUser+" "+prenomUser);
            Label nomPrenomUserLbl = new Label(nomUser);
            //Label mailUser
            Label mailUserLbl = new Label(mailUser);
            mailUserLbl.getStyleClass().add("mailLbl");
            //Label nombre reaction jaime
            Label nbrReactLbl = new Label("J'aime : "+listDmnd.get(i).getNbReactions());
            nbrReactLbl.getStyleClass().add("aimeLbl");
            
            
            
            //separateur horizontal entre nom et bouttons
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(30);
            sh.setVisible(false);
            
            
            //boutton aime Demande = btnAime            
//modif integration****************************************************************************************


            //FileInputStream inputAime = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/like.png");
            FileInputStream inputAime = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/like.png");
            
            Image imageAime = new Image(inputAime);
            ImageView imageViewAime = new ImageView(imageAime);
            imageViewAime.setFitHeight(30);
            imageViewAime.setFitWidth(30);
            Button btnAime = new Button("", imageViewAime);
           
            //Button btnAime = new Button("j'aime");
//**********************************************************************************************************

            
            btnAime.setTooltip(new Tooltip("J'aime"));
            btnAime.setMaxSize(100, 200);
            btnAime.setOnAction((event) -> { 
                try {
                    this.AimeDmnd(idDmnd);
                    System.out.println("Aime effectué");
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                         System.out.println("Aime prb");
                                         
                                        });
            
            //boutton preview Demande = btnpreview
            //modif integration****************************************************************************************
            //FileInputStream inputPreview = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/preview.png");
            FileInputStream inputPreview = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/preview.png");
            Image imagePreview = new Image(inputPreview);
            ImageView imageViewPreview = new ImageView(imagePreview);
            imageViewPreview.setFitHeight(30);
            imageViewPreview.setFitWidth(30);
          
            Button btnPreview = new Button("", imageViewPreview);
            //Button btnPreview = new Button("preview");
            //*********************************************************************************************************
            
            btnPreview.setTooltip(new Tooltip("Description"));
            btnPreview.setMaxSize(100, 200);
            btnPreview.setOnAction((event) -> {
            System.out.println("Button preview ");
                try {
                    this.afficherDescriptionDmnd(dmnd);
                } catch (SQLException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                });
            
          
            //boutton participate demande = btnPart
            //modif integration*********************************************************************************************************
            //FileInputStream inputPart = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/hands.png");
            FileInputStream inputPart = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/hands.png");
            Image imagePart = new Image(inputPart);
            ImageView imageViewPart = new ImageView(imagePart);
            imageViewPart.setFitHeight(30);
            imageViewPart.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnPart = new Button("", imageViewPart);
            
           //Button btnPart = new Button("particip");
            //*****************************************************************************************************************************
            btnPart.setTooltip(new Tooltip("Participer"));
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
           String titre = listDmnd.get(i).getTitre();
            btnPart.setOnAction((event) -> {
            System.out.println("Button participate ");
            Participation part = new Participation(idDmnd, UiLoginController.uh.getId());
            ServiceParticipation serPart  = new ServiceParticipation();
                try {
                    serPart.ajouter(part);
                } catch (SQLException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Mail.sendMail(titre);
            System.out.println("mail send ok");
                            Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Un email a été envoyé au proprietaire de la demande, merci pour votre collaboration");
                a.show();
                                });
            
            
            
            //ajout des differents element representant une categorie dans un Vbox           
            VBoxCat.getChildren().add(titreLbl);
            VBoxCat.getChildren().add(descriptionLbl);
            
            VBoxCat.getChildren().add(publierParLbl);
            VBoxCat.getChildren().add(nomPrenomUserLbl);
            VBoxCat.getChildren().add(mailUserLbl);
            
            //VBoxCat.getChildren().add(nbrReactLbl);
            VBoxCat.getChildren().add(sh);
            
            HBox btnHBox = new HBox();
            btnHBox.getStyleClass().add("hbox");
           // btnHBox.getChildren().add(btnAime);
            btnHBox.getChildren().add(btnPreview);
            btnHBox.getChildren().add(btnPart);
            btnHBox.setSpacing(10);
            btnHBox.setAlignment(Pos.CENTER);
            
            VBoxCat.getChildren().add(btnHBox);
            
            /*VBoxCat.getChildren().add(btnAime);
            VBoxCat.getChildren().add(btnPreview);
            VBoxCat.getChildren().add(btnPart);*/

            //vbx array contient les different Vbox(categories)
            vbx.add(VBoxCat);
            //***********************************************
            flowPaneListeDmnd.getChildren().add(vbx.get(i));
            
            //****************************************************
            flowPaneListeDmnd.getChildren().add(as.get(i));
            
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
                    flowPaneListeDmnd.getChildren().add(sepHoriz);
                    
                }
            }
        }
    }
    
    
    //affiche description d'une demande et lien signaler apres click sur btn afficher 
    private void afficherDescriptionDmnd(DemandeAide dmnd) throws SQLException, FileNotFoundException{
       
        int idDmnd = dmnd.getId();

      //données de user
        int idUser = dmnd.getIdUser();
            ServiceUser serUser = new ServiceUser();
            Utilisateur  user = serUser.getById(idUser);
            String nomUser = user.getUsername();
            //String prenomUser = user.getPrenom();
            String mailUser = user.getEmail_canonical();
            //String userphrase = nomUser+", "+mailUser;
            String userphrase = nomUser;
    
    String titre = "\n"+dmnd.getTitre()+"\n"+"\n";
    Text titreTxt = new Text(titre);
    titreTxt.getStyleClass().add("titredmndcplt");
    
    String desc = dmnd.getDescription()+"\n";
    Text descTxt = new Text(desc);
    descTxt.getStyleClass().add("descdmndcplt");
    
    String userQui = "Publiée par : ";
    Text userQuiTxt = new Text(userQui);
    userQuiTxt.getStyleClass().add("publieeparcplt");
    
    Text userTxt = new Text(userphrase);
    userTxt.getStyleClass().add("userdmndcplt");
    
    Hyperlink linksig = new Hyperlink("Signaler");
    linksig.setOnAction( (evt) -> {
            try {
                this.afficheFormSignale(dmnd);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("signaler link");
                
    } );
    
    TextFlow txtFlowDesc = new TextFlow();
    txtFlowDesc.setMaxWidth(700);
    txtFlowDesc.getChildren().add(titreTxt);
    txtFlowDesc.getChildren().add(descTxt);
    
    txtFlowDesc.getChildren().add(userQuiTxt);
    txtFlowDesc.getChildren().add(userTxt);
    txtFlowDesc.getChildren().add(linksig);
    //txtFlowDesc.getChildren().add(userPrenomTxt);
    //txtFlowDesc.getChildren().add(userMailTxt);
    
    HBox btnBox = new HBox();
    
            //boutton aime Demande = btnAime      
            /*
            FileInputStream inputAime = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/like.png");
            Image imageAime = new Image(inputAime);
            ImageView imageViewAime = new ImageView(imageAime);
            imageViewAime.setFitHeight(30);
            imageViewAime.setFitWidth(30);
            Button btnAime = new Button("", imageViewAime);
            */
            
            Button btnAime = new Button("");
            //btnAime.setTooltip(new Tooltip("J'aime"));
            btnAime.setText("J'aime");
            btnAime.setMaxSize(150, 250);
            btnAime.setOnAction((event) -> { 
                try {
                    this.AimeDmnd(idDmnd);
                    System.out.println("Aime effectué");
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                         System.out.println("Aime prb");
                                         
                                        });
            
            
            //boutton participate demande = btnPart
            
            //FileInputStream inputPart = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/hands.png");
            FileInputStream inputPart = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/hands.png");
           
            Image imagePart = new Image(inputPart);
            ImageView imageViewPart = new ImageView(imagePart);
            imageViewPart.setFitHeight(30);
            imageViewPart.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnPart = new Button("", imageViewPart);
            
            // Button btnPart = new Button("");
                    
                    
            //btnPart.setTooltip(new Tooltip("Participer"));
            btnPart.setText("Participer");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnPart.setOnAction((event) -> {
            System.out.println("Button participate ");
            
             Participation part = new Participation(idDmnd, UiLoginController.uh.getId());
            ServiceParticipation serPart  = new ServiceParticipation();
                try {
                    serPart.ajouter(part);
                } catch (SQLException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            Mail.sendMail(titre);
            System.out.println("mail send ok");
                            Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Un email a été envoyé au proprietaire de la demande, merci pour votre collaboration");
                a.show();
                                });
            
            
            //boutton retour demande = btnBack
            
            //FileInputStream inputBack = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/back.png");
            FileInputStream inputBack = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/back.png");

            Image imageBack = new Image(inputBack);
            ImageView imageViewBack = new ImageView(imageBack);
            imageViewBack.setFitHeight(30);
            imageViewBack.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnBack = new Button("", imageViewBack);
            
            
             //Button btnBack = new Button("");
            
            //btnBack.setTooltip(new Tooltip("retour"));
            btnBack.setText("Retour");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnBack.setOnAction((event) -> {
            System.out.println("Button retour ");
            opacityPane.setVisible(false);
            descPane.setVisible(false);
                                });
            
            
            
    
    //btnBox.getChildren().addAll(btnAime,btnPart,btnBack);
    //btnBox.getChildren().addAll(btnAime,btnPart,btnBack);
    btnBox.getChildren().addAll(btnPart,btnBack);
    btnBox.setSpacing(20);
    btnBox.getStyleClass().add("hboxcplt");
    
    VBox descVbx = new VBox();
    descVbx.getStyleClass().add("vboxdesc");
    descVbx.getChildren().add(txtFlowDesc);
    descVbx.getChildren().add(btnBox);
    
    
    
    /*Alert alert = new Alert(Alert.AlertType.INFORMATION,desc,ButtonType.OK);
    alert.setHeaderText(dmnd.getTitre());      
    Hyperlink link = new Hyperlink("Signaler");
    TextFlow flow = new TextFlow(new Text(desc), link);
    alert.getDialogPane().contentProperty().set( flow );
    link.setOnAction( (evt) -> {
        this.afficheFormSignale(dmnd);
                alert.close();
                
    } );
    alert.showAndWait();*/
            opacityPane.setVisible(true);
            descPane.setVisible(true);
            //descScrollPane.setVisible(true);
            descScrollPane.setContent(descVbx);
            
    }
        
    //creer objet sig signalement apartir de formulaire et invoker la methode this.signaleDmnd(sig)
    //prend en param objet DemandeAide a signaler
     public void afficheFormSignale(DemandeAide dmnd) throws FileNotFoundException{
         /*
         Dialog dialog = new Dialog();
         ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
         dialog.getDialogPane().getButtonTypes().add(cancelButton);
         dialog.setTitle("Signaler une demande");

         Pane paneForm = new Pane();
         //comboBox pour choisir la raison
         ComboBox raisonBox = new ComboBox();
         ObservableList<String> raisonChoix = FXCollections.observableArrayList("arnaque","haine","violence","autre");
         raisonBox.setItems(raisonChoix);
         raisonBox.setValue("raison de signalement");
         
         //texte area pour la description
         TextArea textDesc = new TextArea();
         
         //boutton signaler
         Button btnSignaler = new Button("Signaler");
         btnSignaler.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        System.out.println("btnSignaler pressed");
        String raisonString = (String) raisonBox.getValue();
        //test sur raison
        RaisonSignale raison = toRaisonSignale( raisonString ) ;
        
        String description = textDesc.getText();
        System.out.println("raison signale: "+raison);
        System.out.println("description: "+description);
            //dialog.close();   
        Signalement sig = new Signalement (dmnd.getId(), UserTest.id, raison, description);
            try {
                signaleDmnd(sig);
                System.out.println("obj signalé avec succès: "+sig);
                Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Demande signalée, merci pour votre collaboration");
                a.show();
                dialog.close();
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                Alert a = new Alert(AlertType.ERROR); 
                a.setHeaderText("Vous avez deja signlé cette demande");
                a.show();
                dialog.close();
            }
        
            
        }
    });
        
        //VBox contient la formulaire
         VBox vb = new VBox();
         vb.getChildren().add(raisonBox);
         vb.getChildren().add(textDesc);
         vb.getChildren().add(btnSignaler);
         
         paneForm.getChildren().add(vb);
       
        dialog.getDialogPane().setContent(paneForm);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        System.out.println("result present");
    }
                              
       */    
         
            opacityPane.setVisible(true);
            descPane.setVisible(true);
            descPane.setPrefHeight(400);
            descScrollPane.setPrefHeight(400);
            
            Label titreLb = new Label("Signaler une demande");
            titreLb.getStyleClass().add("titreAjoutDmnd");
            Label raisonLbl = new Label("Raison de signalement: ");
            //comboBox pour choisir la raison
            ComboBox raisonBox = new ComboBox();
            ObservableList<String> raisonChoix = FXCollections.observableArrayList("arnaque","haine","violence","autre");
            raisonBox.setItems(raisonChoix);
            raisonBox.setValue("raison de signalement");
            HBox hboxRaisonSig = new HBox();
            hboxRaisonSig.setSpacing(10);
            hboxRaisonSig.getChildren().addAll(raisonLbl,raisonBox);
            
            Label descSigLbl = new Label("Description");
         
            //texte area pour la description
            TextArea textDesc = new TextArea();
            
            //boutton retour demande = btnBack
            FileInputStream inputBack = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/back.png");
            Image imageBack = new Image(inputBack);
            ImageView imageViewBack = new ImageView(imageBack);
            imageViewBack.setFitHeight(30);
            imageViewBack.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnBack = new Button("", imageViewBack);
            //btnBack.setTooltip(new Tooltip("retour"));
            btnBack.setText("Retour");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnBack.setOnAction((event) -> {
            System.out.println("Button retour ");
            opacityPane.setVisible(false);
            descPane.setVisible(false);
            descPane.setPrefHeight(250);
            descScrollPane.setPrefHeight(250);
                                });
            
            
            //boutton signaler demande = btnSig
            FileInputStream inputSig = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/report.png");
            Image imageSig = new Image(inputSig);
            ImageView imageViewSig = new ImageView(imageSig);
            imageViewSig.setFitHeight(30);
            imageViewSig.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnSig = new Button("", imageViewSig);
            //btnBack.setTooltip(new Tooltip("retour"));
            btnSig.setText("Signaler");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnSig.setOnAction((event) -> {
            
                
                
                
        System.out.println("btnSignaler pressed");
        if(raisonBox.getSelectionModel().isEmpty()){
Alert a = new Alert(AlertType.ERROR); 
                a.setHeaderText("veuillez entrer une raison de signalement");
                a.show();
                }
        String raisonString = (String) raisonBox.getValue();
        //test sur raison
        RaisonSignale raison = toRaisonSignale( raisonString ) ;
        
        String description = textDesc.getText();
        System.out.println("raison signale: "+raison);
        System.out.println("description: "+description);
            //dialog.close();   
        Signalement sig = new Signalement (dmnd.getId(), UserTest.id, raison, description);
        
            try {

               
                System.out.println("avant signal");                
                this.signaleDmnd(sig);                
                System.out.println("apres signal");
                
                ServiceSignalement serSig =new ServiceSignalement();
                
                System.out.println(serSig.nbrSignalementDmnd(dmnd.getId()));
                
                ServiceDemandeAide serDmnd = new ServiceDemandeAide();
                DemandeAide dmndNew = dmnd;
                System.out.println("OLD dddddddddddddddddddddddddd");
                System.out.println(dmndNew);
                dmndNew.setEtat(Etat.SIGNALEE);
                System.out.println("NEW dmndddddddddddddddddddddddddddddddddddddd");
                System.out.println(dmndNew);
                System.out.println("updateeeeeeeeeeeeeeeeeeee");
                serDmnd.update(dmnd.getId(), dmndNew);
                System.out.println("old UPDATEDDDDDDDDDDDDDDDDDDDDDDD");
                System.out.println(dmnd);
                
                
                System.out.println("obj signalé avec succès: "+sig);
                Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Demande signalée, merci pour votre collaboration");
                a.show();
                 flowPaneListeDmnd.getChildren().clear();
                this.affichageDemandes("none");
                //dialog.close();
                
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                
                
                Alert a = new Alert(AlertType.ERROR); 
                a.setHeaderText("Vous avez deja signlé cette demande");
                a.show();
                
                                
                //dialog.close();
            }
            
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
            System.out.println("Button signaler ");
            opacityPane.setVisible(false);
            descPane.setVisible(false);
            descPane.setPrefHeight(250);
            descScrollPane.setPrefHeight(250);
                                });
            
         //HBox pour les bouttons
         HBox hboxBtn = new HBox();
         hboxBtn.setSpacing(20);
         hboxBtn.getChildren().addAll(btnSig,btnBack);
         
          VBox vboxAjoutDmnd = new VBox();
         vboxAjoutDmnd.setSpacing(10);
         vboxAjoutDmnd.getChildren().addAll(titreLb,hboxRaisonSig,descSigLbl,textDesc,hboxBtn);
         
         vboxAjoutDmnd.getStyleClass().add("vboxAjoutDmnd");
         
         
         descScrollPane.setContent(vboxAjoutDmnd);
            
            
            
     }   
        
    
  public RaisonSignale toRaisonSignale(String str){
  RaisonSignale r = RaisonSignale.valueOf(str.toUpperCase());
  return r;
 }   
  
  
  public void ajouterDmnd(DemandeAide dmnd) throws SQLException{
  ServiceDemandeAide serDmnd = new ServiceDemandeAide();
  serDmnd.ajouter(dmnd);
  }
  
  
  
       public void afficheFormAjoutDmnd() throws SQLException, FileNotFoundException{
            opacityPane.setVisible(true);
            descPane.setVisible(true);
            descPane.setPrefHeight(400);
            descScrollPane.setPrefHeight(400);
            
            
            Label titreLb = new Label("Publier une nouvelle demande");
            titreLb.getStyleClass().add("titreAjoutDmnd");
            
            HBox hboxCatChoix = new HBox();
            Label catLbl = new Label("Categorie: ");
            

         //comboBox pour choisir la categorie
         ServiceCategorie serCat = new ServiceCategorie();
         List<Categorie> listCat = serCat.readAll();
         List<String> listCatNom = new ArrayList<>();
         
         for (Categorie cat : listCat) {
            listCatNom.add(cat.getNom());
         }
         
         
         ComboBox catBox = new ComboBox();
         ObservableList<String> catChoix = FXCollections.observableArrayList();
         catChoix.addAll(listCatNom);
         catBox.setItems(catChoix);
         catBox.setValue("choisir categorie");
         
         HBox hboxTitre = new HBox();
         Label titreLbl = new Label("Titre:     ");
         TextField titreTf = new TextField();
         hboxTitre.getChildren().add(titreLbl);
         hboxTitre.getChildren().add(titreTf);
         hboxTitre.setSpacing(10);
         
         hboxCatChoix.getChildren().add(catLbl);
         hboxCatChoix.getChildren().add(catBox);
         hboxCatChoix.setSpacing(10);
         
         Label descLbl = new Label("Description: ");
         
         
         TextArea descTa = new TextArea();
         
         
         
            //boutton retour demande = btnBack
            
//integration modif**********************************************************************************************************************
            //FileInputStream inputBack = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/back.png");
            FileInputStream inputBack = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/back.png");

            Image imageBack = new Image(inputBack);
            ImageView imageViewBack = new ImageView(imageBack);
            imageViewBack.setFitHeight(30);
            imageViewBack.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnBack = new Button("", imageViewBack);
           
         // Button btnBack = new Button("back");
//*********************************************************************************************************************************************
            //btnBack.setTooltip(new Tooltip("retour"));
            btnBack.setText("Retour");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnBack.setOnAction((event) -> {
            System.out.println("Button retour ");
            opacityPane.setVisible(false);
            descPane.setVisible(false);
            descPane.setPrefHeight(250);
            descScrollPane.setPrefHeight(250);
                                });
         
         
         
           //boutton ajouter demande = btnAdd

//integration modif*********************************************************************************************************************************    
           //FileInputStream inputAdd = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/add.png");
           FileInputStream inputAdd = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/add.png");
 
           Image imageAdd = new Image(inputAdd);
            ImageView imageViewAdd = new ImageView(imageAdd);
            imageViewAdd.setFitHeight(30);
            imageViewAdd.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnAdd = new Button("", imageViewAdd);

          //Button btnAdd = new Button("");
//*************************************************************************************************************************************
            //btnBack.setTooltip(new Tooltip("retour"));
            btnAdd.setText("Publier");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnAdd.setOnAction((event) -> {
            System.out.println("Button ajouter ");
            
            
            
                    String dmndCatNom = (String) catBox.getValue();
        Categorie catPourId = new Categorie();
            try {
                //pour recuperer l'id
                catPourId =  serCat.readByName(dmndCatNom);
               
            } catch (SQLException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        int idCat = catPourId.getId();
        String description = descTa.getText();
        String titre = titreTf.getText();
        /*
        System.out.println("categorie: "+fieldTitre);
        System.out.println("categorie: "+dmndCatNom);
        System.out.println("description: "+description);*/
            //dialog.close();   
        DemandeAide dmnd = new DemandeAide (idCat, UiLoginController.uh.getId(), titre, description, 0);
            try {

                if (!(titre.isEmpty())){
                ajouterDmnd(dmnd);
                System.out.println("dmnd ajoutée avec succès: "+dmnd);
                Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Demande ajoutée avec succès!");
                //**************************SMS******************SMS**********************************SMS************************SMS
                
//integration modif*************************************************************************************************
           /*     Sms sms = new Sms();
                sms.sendSms("votre demande a ete ajoutée avec succès");
*/
                a.show();
                //dialog.close();
                flowPaneListeDmnd.getChildren().clear();
                affichageDemandes("none");
                
                opacityPane.setVisible(false);
                descPane.setVisible(false);
                descPane.setPrefHeight(250);
                descScrollPane.setPrefHeight(250);
                
                
                }
                    else{
                   Alert a = new Alert(AlertType.ERROR); 
                a.setHeaderText("veuillez saisir un titre pour votre demande");
                a.show(); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                Alert a = new Alert(AlertType.ERROR); 
                a.setHeaderText("veuillez choisir une categorie");
                a.show();
                //dialog.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
            
            
            
            
            
            
            
            
            
            
            


                                });        
         
         
            
            
            
            
            
         //HBox pour les bouttons
         HBox hboxBtn = new HBox();
         hboxBtn.setSpacing(20);
         hboxBtn.getChildren().addAll(btnAdd,btnBack);
         
         
         
         VBox vboxAjoutDmnd = new VBox();
         vboxAjoutDmnd.setSpacing(10);
         vboxAjoutDmnd.getChildren().addAll(titreLb,hboxTitre,hboxCatChoix,descLbl,descTa,hboxBtn);
         
         vboxAjoutDmnd.getStyleClass().add("vboxAjoutDmnd");
         
         
         descScrollPane.setContent(vboxAjoutDmnd);
         
     }
       
       @FXML
       public void retourDmnd() throws FileNotFoundException, SQLException{
//           listeDmndAide.setVisible(true);
//           flowPaneListeDmnd.setVisible(true);
//           //flowPaneListeDmnd.
//           this.affichageDemandes("none");
//           btnListeDmnd.setVisible(false);
//           listeDmndAide.setText("Liste des demandes d'aide :");
            flowPaneListeDmnd.getChildren().clear();
            opacityPane.setVisible(false);
            descPane.setVisible(false);
            btnListeDmnd.setVisible(false);
            
            //btnMesDmnd.setVisible(false);
            this.affichageDemandes("none");
       }
       
       
       
       
       
       
           //affiche description d'une demande et lien signaler apres click sur btn afficher 
    private void afficherDescriptionMaDmnd(DemandeAide dmnd) throws SQLException, FileNotFoundException{
       
        int idDmnd = dmnd.getId();

      //données de user
        int idUser = dmnd.getIdUser();
        /*
            ServiceUser serUser = new ServiceUser();
            User  user = serUser.readById(idUser);
            String nomUser = user.getNom();
            String prenomUser = user.getPrenom();
            String mailUser = user.getMail();
            String userphrase = nomUser+" "+prenomUser+", "+mailUser;

    */
        ServiceUser serUser = new ServiceUser();
            Utilisateur  user = serUser.getById(idUser);
            String nomUser = user.getUsername();
            //String prenomUser = user.getPrenom();
            String mailUser = user.getEmail_canonical();
            //String userphrase = nomUser+", "+mailUser;
            String userphrase = nomUser;
            
    String titre = "\n"+dmnd.getTitre()+"\n"+"\n";
    Text titreTxt = new Text(titre);
    titreTxt.getStyleClass().add("titredmndcplt");
    
    String desc = dmnd.getDescription()+"\n";
    Text descTxt = new Text(desc);
    descTxt.getStyleClass().add("descdmndcplt");
    
    String userQui = "Publiée par : ";
    Text userQuiTxt = new Text(userQui);
    userQuiTxt.getStyleClass().add("publieeparcplt");
    
    Text userTxt = new Text(userphrase);
    userTxt.getStyleClass().add("userdmndcplt");
    
    Hyperlink linksig = new Hyperlink("Signaler");
    linksig.setOnAction( (evt) -> {
            try {
                this.afficheFormSignale(dmnd);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("signaler link");
                
    } );
    
    TextFlow txtFlowDesc = new TextFlow();
    txtFlowDesc.setMaxWidth(700);
    txtFlowDesc.getChildren().add(titreTxt);
    txtFlowDesc.getChildren().add(descTxt);
    
    txtFlowDesc.getChildren().add(userQuiTxt);
    txtFlowDesc.getChildren().add(userTxt);
    //txtFlowDesc.getChildren().add(linksig);
    //txtFlowDesc.getChildren().add(userPrenomTxt);
    //txtFlowDesc.getChildren().add(userMailTxt);
    
    HBox btnBox = new HBox();
    
            //boutton supprimer demande = btnSupp     
            //*********************************************************************************************************************************
            
            //FileInputStream inputSupp = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/delete.png");
            FileInputStream inputSupp = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/delete.png");

            Image imageSupp = new Image(inputSupp);
            ImageView imageViewSupp = new ImageView(imageSupp);
            imageViewSupp.setFitHeight(20);
            imageViewSupp.setFitWidth(20);
            Button btnSupp = new Button("supprimer", imageViewSupp);
            
            //Button btnSupp = new Button("supprimer");
            //**************************************************************************
            btnSupp.setTooltip(new Tooltip("Supprimer"));
            btnSupp.setMaxSize(150, 230);
            btnSupp.setOnAction((event) -> {       
                        ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(AlertType.WARNING,"êtes-vous sûr de vouloir supprimer la categorie "+dmnd.getTitre()+" ?",oui,non);
                        alert.setTitle("Supprimer une demande");
                        alert.showAndWait().ifPresent(type -> {
                                    if (type == oui) {
                                        System.out.println("btnSupp Action");
                                        try {
                                            this.supprimerDmnd(idDmnd);
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "demande "+dmnd.getTitre()+" supprimée avec succès!", ButtonType.OK);
                                            al.show();
                                            System.out.println("supprimer ok!");
                                            
                                        }catch (SQLException | FileNotFoundException ex){
                                            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
                                        }
                                        });
                                        });
            
            
            //boutton participate demande = btnPart
            //**********************************************************************************************
           
            //FileInputStream inputPart = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/hands.png");
            FileInputStream inputPart = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/hands.png");

            Image imagePart = new Image(inputPart);
            ImageView imageViewPart = new ImageView(imagePart);
            imageViewPart.setFitHeight(30);
            imageViewPart.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnPart = new Button("", imageViewPart);
            
           //Button btnPart = new Button("");
            //***********************************************************************************************
            //btnPart.setTooltip(new Tooltip("Participer"));
            btnPart.setText("Participer");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnPart.setOnAction((event) -> {
            System.out.println("Button participate ");
            Mail.sendMail(titre);
            System.out.println("mail send ok");
                            Alert a = new Alert(AlertType.INFORMATION); 
                a.setHeaderText("Un email a été envoyé au proprietaire de la demande, merci pour votre collaboration");
                a.show();
                                });
            
            
            //boutton retour demande = btnBack
            //*****************************************
            
            //FileInputStream inputBack = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/back.png");
            FileInputStream inputBack = new FileInputStream("C:/Users/Hedi/Downloads/pidevJavaImg/back.png");

            Image imageBack = new Image(inputBack);
            ImageView imageViewBack = new ImageView(imageBack);
            imageViewBack.setFitHeight(30);
            imageViewBack.setFitWidth(30);
            //Button btnPart = new Button("Participer", imageViewPart);
            Button btnBack = new Button("", imageViewBack);
            
            //Button btnBack = new Button("");
            //btnBack.setTooltip(new Tooltip("retour"));
            btnBack.setText("Retour");
            //btnPart.setMaxSize(100, 200);
           // btnPart.setMaxSize(300, 200);
            btnBack.setOnAction((event) -> {
            System.out.println("Button retour ");
            opacityPane.setVisible(false);
            descPane.setVisible(false);
                                });
            
            
            
    
    //btnBox.getChildren().addAll(btnAime,btnPart,btnBack);
    btnBox.getChildren().addAll(btnBack,btnSupp);
    btnBox.setSpacing(20);
    btnBox.getStyleClass().add("hboxcplt");
    
    VBox descVbx = new VBox();
    descVbx.getStyleClass().add("vboxdesc");
    descVbx.getChildren().add(txtFlowDesc);
    descVbx.getChildren().add(btnBox);
    
    
    
    /*Alert alert = new Alert(Alert.AlertType.INFORMATION,desc,ButtonType.OK);
    alert.setHeaderText(dmnd.getTitre());      
    Hyperlink link = new Hyperlink("Signaler");
    TextFlow flow = new TextFlow(new Text(desc), link);
    alert.getDialogPane().contentProperty().set( flow );
    link.setOnAction( (evt) -> {
        this.afficheFormSignale(dmnd);
                alert.close();
                
    } );
    alert.showAndWait();*/
            opacityPane.setVisible(true);
            descPane.setVisible(true);
            //descScrollPane.setVisible(true);
            descScrollPane.setContent(descVbx);
            
    }
       
       
       
       public void supprimerDmnd(int id) throws SQLException, FileNotFoundException{
        ServiceDemandeAide serDmnd = new ServiceDemandeAide();
        serDmnd.delete(id);
        this.suppToMesDmnd();
        
        
        }    
       
       
       
       
       
       
       
    @FXML
    private void OpenSidebar(MouseEvent event) {
        if(!flag){
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
            paneSide.setStyle("-fx-background-color: #F89843;");
            
            
        }

    }
    
    
    
    @FXML
    private void CloseSideBar(MouseEvent event) {
        if(flag){
            flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
            stackSide2.setStyle("-fx-background-color: # F89843;");
          
        
        }
    }
    
    
    
    // apres suppression d'une dmnd
        private void suppToMesDmnd() throws SQLException, FileNotFoundException {
        System.out.println("btn mes dmnd ok");
        //this.clearPane();
        btnListeDmnd.setVisible(true);
       flowPaneListeDmnd.getChildren().clear();
           
        //flowPaneListeDmnd.getChildren().add(new Label("hghghg"));
        
       // btnListeDmnd.setVisible(true);
       // listeDmndAide.setText("Mes demandes d'aide");
        
        
        ServiceDemandeAide serDmnd = new ServiceDemandeAide();
        List<DemandeAide> listDmnd = null;
        try {
  
          listDmnd = serDmnd.readByIdUser();
          
          
        } catch (SQLException ex) {
            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listDmnd.size(); i++) {
            
            DemandeAide dmnd = listDmnd.get(i);
            
            //details sur user proprietaire du demande
            int idUser = listDmnd.get(i).getIdUser();
            ServiceUser serUser = new ServiceUser();
            User  user = serUser.readById(idUser);
            String nomUser = user.getNom();
            String prenomUser = user.getPrenom();
            String mailUser = user.getMail();
            
            int idDmnd = listDmnd.get(i).getId();
            String TitreDmnd = listDmnd.get(i).getTitre();
            
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            //Vbox container pour l'affichage d'une categorie
            VBox VBoxCat = new VBox();
            //VBoxCat.setSpacing(5);
            //*******************************style modified**********************************************************************
            //VBoxCat.setStyle("-fx-background-color : #dddddd;");
            //VBoxCat.setStyle("-fx-border-radius: 30px;");
            //VBoxCat.getStyleClass().clear();
            VBoxCat.getStyleClass().add("vbox");
            
            VBoxCat.setAlignment(Pos.CENTER);
            //VBoxCat.setPrefHeight(262);
            VBoxCat.setPrefHeight(300);
           // VBoxCat.setPrefWidth(170);
           VBoxCat.setPrefWidth(350);

            //Label titre = new Label("Titre : " + listDmnd.get(i).getTitre());
            Label titreLbl = new Label(listDmnd.get(i).getTitre());
            titreLbl.getStyleClass().add("titre");
            //Label description
            Label descriptionLbl = new Label(listDmnd.get(i).getDescription());
            descriptionLbl.getStyleClass().add("desc");
            //Label publier par
            Label publierParLbl = new Label("Publiée par :");
            publierParLbl.getStyleClass().add("pbParLbl");
            //Label nomUser et prenomUser
            Label nomPrenomUserLbl = new Label(nomUser+" "+prenomUser);
            //Label mailUser
            Label mailUserLbl = new Label(mailUser);
            mailUserLbl.getStyleClass().add("mailLbl");
            //Label nombre reaction jaime
            Label nbrReactLbl = new Label("J'aime : "+listDmnd.get(i).getNbReactions());
            nbrReactLbl.getStyleClass().add("aimeLbl");
            
            
            
            //separateur horizontal entre nom et bouttons
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(30);
            sh.setVisible(false);
            
            

            
            
            //boutton supprimer demande = btnSupp            
            FileInputStream inputSupp = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/delete.png");
            Image imageSupp = new Image(inputSupp);
            ImageView imageViewSupp = new ImageView(imageSupp);
            imageViewSupp.setFitHeight(20);
            imageViewSupp.setFitWidth(20);
            Button btnSupp = new Button("", imageViewSupp);
            btnSupp.setTooltip(new Tooltip("Supprimer"));
            btnSupp.setMaxSize(100, 200);
            btnSupp.setOnAction((e) -> {       
                        ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(AlertType.WARNING,"êtes-vous sûr de vouloir supprimer la categorie "+dmnd.getTitre()+" ?",oui,non);
                        alert.setTitle("Supprimer une demande");
                        alert.showAndWait().ifPresent(type -> {
                                    if (type == oui) {
                                        System.out.println("btnSupp Action");
                                        try {
                                            this.supprimerDmnd(idDmnd);
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "demande "+dmnd.getTitre()+" supprimée avec succès!", ButtonType.OK);
                                            al.show();
                                            System.out.println("supprimer ok!");
                                            
                                        }catch (SQLException | FileNotFoundException ex){
                                            Logger.getLogger(DashbordAdmin_Aide_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
                                        }
                                        });
                                        });
            
            
 //************************************************************************************************************************************           
            
            //boutton preview Demande = btnpreview
            FileInputStream inputPreview = new FileInputStream("C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/hedi/preview.png");
            Image imagePreview = new Image(inputPreview);
            ImageView imageViewPreview = new ImageView(imagePreview);
            imageViewPreview.setFitHeight(30);
            imageViewPreview.setFitWidth(30);
            Button btnPreview = new Button("", imageViewPreview);
            btnPreview.setTooltip(new Tooltip("Description"));
            btnPreview.setMaxSize(100, 200);
            btnPreview.setOnAction((e) -> {
            System.out.println("Button preview ");
                try {
                    this.afficherDescriptionMaDmnd(dmnd);
                } catch (SQLException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Front_Aide_AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                                });
            
            
            

            
   
            //ajout des differents element representant une categorie dans un Vbox           
            VBoxCat.getChildren().add(titreLbl);
            VBoxCat.getChildren().add(descriptionLbl);
            
            VBoxCat.getChildren().add(publierParLbl);
            VBoxCat.getChildren().add(nomPrenomUserLbl);
            VBoxCat.getChildren().add(mailUserLbl);
            
            VBoxCat.getChildren().add(nbrReactLbl);
            VBoxCat.getChildren().add(sh);
            
            HBox btnHBox = new HBox();
            btnHBox.getStyleClass().add("hbox");
            btnHBox.getChildren().add(btnSupp);
            btnHBox.getChildren().add(btnPreview);
            //btnHBox.getChildren().add(btnPart);
            btnHBox.setSpacing(10);
            btnHBox.setAlignment(Pos.CENTER);
            
            VBoxCat.getChildren().add(btnHBox);
            
            /*VBoxCat.getChildren().add(btnAime);
            VBoxCat.getChildren().add(btnPreview);
            VBoxCat.getChildren().add(btnSup);*/

            //vbx array contient les different Vbox(categories)
            vbx.add(VBoxCat);
            //***********************************************
            flowPaneListeDmnd.getChildren().add(vbx.get(i));
            
            //****************************************************
            flowPaneListeDmnd.getChildren().add(as.get(i));
            
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
                    flowPaneListeDmnd.getChildren().add(sepHoriz);
                    
                }
            }
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
