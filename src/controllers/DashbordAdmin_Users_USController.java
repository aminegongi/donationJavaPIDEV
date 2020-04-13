/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Utilisateur_Simple;
import Services.GestionnaireUtilisateur_Simple;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class DashbordAdmin_Users_USController implements Initializable {

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
    private TextField tfRecherche;
    @FXML
    private FlowPane flowPaneUsers;
    @FXML
    private ImageView btnBack;
    @FXML
    private VBox vboxDProfil;
    @FXML
    private Circle imgDProfil;
    @FXML
    private Pane paneDprofil;
    @FXML
    private Label labelDProfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vboxDProfil.getChildren().clear();
        vboxDProfil.setVisible(false);
        paneDprofil.setVisible(false);
        imgDProfil.setVisible(false);
        labelDProfil.setVisible(true);
        affichageUS();
    }

    private void affichageUS() {
        GestionnaireUtilisateur_Simple gUs = new GestionnaireUtilisateur_Simple();

        ArrayList<Utilisateur_Simple> listUs = (ArrayList<Utilisateur_Simple>) gUs.fetchUS();
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listUs.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);

            VBox VBoxUser = new VBox();
            VBoxUser.setSpacing(5);
            VBoxUser.setStyle("-fx-background-color : #dddddd;");
            VBoxUser.setAlignment(Pos.CENTER);
            VBoxUser.setPrefHeight(262);
            VBoxUser.setPrefWidth(170);

            Circle c = new Circle(35);
            String path = "C:/Users/Amine Gongi/Desktop/Esprit 3A/PIDEV/DoNationJava/JavaFXApplicationWUI/src/images/";
            String imgUS = listUs.get(i).getImage();
            try {
                c.setFill(new ImagePattern(new Image(new FileInputStream(path+imgUS))));
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(DashbordAdmin_Users_USController.class.getName()).log(Level.SEVERE, null, ex);
            }

            VBoxUser.getChildren().add(c);

            Label nom = new Label("Nom : " + listUs.get(i).getNom());
            Label prenom = new Label("Prénom: " + listUs.get(i).getPrenom());
            Label mail = new Label("Mail: " + listUs.get(i).getMail());
            Label etat = new Label();
            Label adresse = new Label("Adresse: " + listUs.get(i).getAdresse().getVille() + "," + listUs.get(i).getAdresse().getPays());

            int id = listUs.get(i).getId();

            

            ComboBox<String> actions = new ComboBox<>();
            actions.setPromptText("Les Actions");

            if (listUs.get(i).getEnabled() == 1) {
                etat.setText("Etat: Activer");
                actions.getItems().addAll("Voir Profil", "Désactiver");
            } else if (listUs.get(i).getEnabled() == 0) {
                etat.setText("Etat: Désactiver");
                actions.getItems().addAll("Voir Profil", "Activer");
            }
            else if (listUs.get(i).getEnabled() == -1) {
                etat.setText("Etat: Non Encore Activer");
                actions.getItems().addAll("Voir Profil", "Activer");
            }
            
            Alert al = new Alert(Alert.AlertType.NONE);
            ButtonType Oui = new ButtonType("Oui");
            ButtonType Non = new ButtonType("Non");

            al.getButtonTypes().addAll(Oui, Non);

            actions.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (actions.getValue().equals("Voir Profil")) {
                        vboxDProfil.getChildren().clear();
                        
                        labelDProfil.setVisible(false);
                        vboxDProfil.setVisible(true);
                        paneDprofil.setVisible(true);
                        imgDProfil.setVisible(true);
                        
                        System.out.println("Voir Profil " + id);
                        Utilisateur_Simple uProfil = null;
                        uProfil = gUs.fetchOneUS(id);
                        String imgDP = uProfil.getImage(); 
                        try {
                            imgDProfil.setFill(new ImagePattern(new Image(new FileInputStream(path+imgDP))));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DashbordAdmin_Users_USController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Label np = new Label(uProfil.getNom()+" "+uProfil.getPrenom());
                        Label Mail = new Label(uProfil.getMail());
                        Label tel = new Label(uProfil.getNumTel());
                        Label adresse = new Label(uProfil.getAdresse().getPays()+", "+uProfil.getAdresse().getVille());
                        Label genre = new Label(uProfil.getGenre());
                        //Label pointxp = new Label(uProfil.getPointXP()+"");
                        Label dateNaissance = new Label(uProfil.getDateNaissance().toString());
                        Label dateInscri = new Label(uProfil.getDateInscription().toString());
                        Font f = new Font(16);
                        np.setTextFill(Color.web("white"));
                        np.setFont(f); 
                        Mail.setTextFill(Color.web("white"));
                        Mail.setFont(f); 
                        tel.setTextFill(Color.web("white"));
                        tel.setFont(f);
                        adresse.setTextFill(Color.web("white"));
                        adresse.setFont(f);
                        genre.setTextFill(Color.web("white"));
                        genre.setFont(f);
                        dateNaissance.setTextFill(Color.web("white"));
                        dateNaissance.setFont(f);
                        dateInscri.setTextFill(Color.web("white"));
                        dateInscri.setFont(f);
                        
                        Image MailImg = new Image(getClass().getResourceAsStream("/images/Icon/icons8_mailing_24px.png"));
                        Image phoneImg = new Image(getClass().getResourceAsStream("/images/Icon/icons8_phone_26px_1.png"));
                        Image adresseImg = new Image(getClass().getResourceAsStream("/images/Icon/icons8_location_24px.png"));
                        Image genreImg = new Image(getClass().getResourceAsStream("/images/Icon/icons8_gender_24px.png"));
                        Image inscriIng = new Image(getClass().getResourceAsStream("/images/Icon/icons8_sign_up_24px.png"));
                        Image NaisImg = new Image(getClass().getResourceAsStream("/images/Icon/icons8_baby_24px.png"));
                        
                        Mail.setGraphic(new ImageView(MailImg));
                        tel.setGraphic(new ImageView(phoneImg));
                        adresse.setGraphic(new ImageView(adresseImg));
                        genre.setGraphic(new ImageView(genreImg));
                        dateNaissance.setGraphic(new ImageView(NaisImg));
                        dateInscri.setGraphic(new ImageView(inscriIng));
                                
                        
                        vboxDProfil.getChildren().addAll(np,Mail,tel,adresse,genre,dateNaissance,dateInscri);
                        System.out.println(uProfil);
                    } 
                    else if (actions.getValue().equals("Désactiver")) {
                        al.setContentText("Vous Voulez vraiment désactiver le compte !");
                        Optional<ButtonType> OuiNon = al.showAndWait();
                        if (OuiNon.get() == Oui) {
                            gUs.desactiverCompte(id);
                            flowPaneUsers.getChildren().clear();
                            affichageUS();
                        }
                    } 
                    else if (actions.getValue().equals("Activer")) {
                        al.setContentText("Vous Voulez vraiment activer le compte !");
                        Optional<ButtonType> OuiNon = al.showAndWait();
                        if (OuiNon.get() == Oui) {
                            gUs.activerCompte(id);
                            flowPaneUsers.getChildren().clear();
                            affichageUS();
                        }
                    }
                }
            });
            
            VBoxUser.getChildren().add(nom);
            VBoxUser.getChildren().add(prenom);
            VBoxUser.getChildren().add(mail);
            VBoxUser.getChildren().add(etat);
            VBoxUser.getChildren().add(adresse);
            VBoxUser.getChildren().add(actions);

            vbx.add(VBoxUser);
            flowPaneUsers.getChildren().add(vbx.get(i));

            flowPaneUsers.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0");
            } else {
                if (((i + 1) % 4) == 0) {
                    Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    sepHoriz.setPrefWidth(700);
                    sepHoriz.setPrefHeight(35);
                    sepHoriz.setVisible(false);
                    flowPaneUsers.getChildren().add(sepHoriz);
                }
            }
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
    private void BackToUser(MouseEvent event) {

        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin_Users.fxml"));
            rootPane.getChildren().clear();
            rootPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
