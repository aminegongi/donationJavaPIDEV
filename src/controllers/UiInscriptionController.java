/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.*;
import Services.*;
import Utils.Adresse;
import com.jfoenix.controls.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.json.*;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class UiInscriptionController implements Initializable {

    @FXML
    private JFXComboBox<String> CBType;
    private String role;
    @FXML
    private VBox Flowpane;
    @FXML
    private VBox VBoxInscri;
    @FXML
    private JFXButton btInscri;

    //User 
    JFXTextField tfNom = new JFXTextField();
    Label ErrNom = new Label("Nom doit étre supérieur à 2 caractéres");
    JFXTextField tfMail = new JFXTextField();
    Label ErrMail = new Label("Mail Existant ou format erroné");
    JFXTextField tfTel = new JFXTextField();
    Label ErrTel = new Label("Numéro ne contient que 7 ou plus chiffres ! ");
    JFXPasswordField tfPassword = new JFXPasswordField();
    Label Errpass = new Label("  ");
    JFXPasswordField tfConfirmPassword = new JFXPasswordField();
    Label ErrConpass = new Label("Mot de passe non identique ");
    //JFXComboBox<String> cbPays = new JFXComboBox<>();
    JFXTextField tfPays = new JFXTextField();    
    Label ErrPays = new Label("Champ Obligatoire ! ");
    JFXTextField tfVille = new JFXTextField();
    Label ErrVille = new Label("Champ Obligatoire ! ");

    //US
    JFXTextField tfPrenom = new JFXTextField();
    Label ErrPnom = new Label("Prénom doit étre supérieur à 2 caractéres");
    JFXComboBox<String> cbGenre = new JFXComboBox<>();
    Label ErrGenre = new Label("Champ Obligatoire ! ");
    JFXDatePicker dateP = new JFXDatePicker();
    Label ErrDateN = new Label("Champ Obligatoire ! ");
    
    //Organisation
    JFXTextArea desc = new JFXTextArea();
    JFXTextField numVisa = new JFXTextField();
    JFXTextField siteWeb = new JFXTextField();
    JFXTextField pageFb = new JFXTextField();
    
    
    @FXML
    private StackPane stackInscri;
    @FXML
    private ImageView imgback;

    GestionnaireUtilisateur gUser = new GestionnaireUtilisateur();
    @FXML
    private RadioButton rbtVerifMail;
    @FXML
    private ToggleGroup VerifCompte;
    @FXML
    private RadioButton rbtVerifSMS;
    @FXML
    private RadioButton rbtVerifPhone;
    
    String AIP=null;
    String pays;
    String region; 
    String idn;
    String lon;
    String lat;
    
    public static String mailToVal; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Load Country
        //cbPays.getItems
        try
        { 
            URL url_name = new URL("http://checkip.amazonaws.com/"); // wala  http://bot.whatismyipaddress.com
            BufferedReader bf = new BufferedReader(new InputStreamReader(url_name.openStream())); 
            AIP = bf.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            AIP = "IP Prob" ;
        } 
        
         JSONObject json = null;
         try {
         json = readJsonFromUrl("https://api.ipgeolocation.io/ipgeo?apiKey=f2d99b10637d4a90b8084db4c8a9dc15&ip="+AIP);
         } catch (IOException ex) {
         System.out.println("ezfzezfzfzz");
         } catch (JSONException ex) {
         System.out.println("ezfzezfzfzz");
         }
         
         //ystem.out.println(json.toString());
         //System.out.println(json.get("country_name"));
         pays =json.get("country_name").toString();
         region=json.get("city").toString();
         idn = json.get("calling_code").toString();
         lat= json.get("latitude").toString();
         lon =json.get("longitude").toString();
         
         
        //System.out.println(json.get("name"));
        CBType.getItems().addAll("Utilisateur Simple", "Organisation", "Restaurant", "Entreprise");
        
        tfVille.setText(region);
        
        //CBType.getItems().add("Entreprise");
    }

    @FXML
    private void choixType(ActionEvent event) {
        VBoxInscri.getChildren().clear();
        
        VBoxInscri.setSpacing(8);

        tfNom.setPromptText("Votre Nom : ");
        tfNom.setLabelFloat(true);
        ErrNom.setTextFill(Color.web("#FF0000"));
        ErrNom.setVisible(false);
        tfNom.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (tfNom.getText().length() <= 2) {
                    ErrNom.setVisible(true);
                    btInscri.setDisable(true);
                } else {
                    ErrNom.setVisible(false);
                    btInscri.setDisable(false);
                }
            }
        });

        tfMail.setPromptText("Votre EMail : ");
        tfMail.setLabelFloat(true);
        ErrMail.setTextFill(Color.web("#FF0000"));
        ErrMail.setVisible(false);
        tfMail.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gUser.checkMail(tfMail.getText()) ) {
                    btInscri.setDisable(true);
                    ErrMail.setVisible(true);
                } else {
                    btInscri.setDisable(false);
                    ErrMail.setVisible(false);
                }
            }
        });

        tfPassword.setPromptText("Votre Mot de Passe : ");
        tfPassword.setLabelFloat(true);
        Errpass.setTextFill(Color.web("#FF0000"));
        Errpass.setVisible(false);
        tfPassword.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (tfPassword.getText().equals(tfConfirmPassword.getText())) {
                    btInscri.setDisable(false);
                    ErrConpass.setVisible(false);
                } else {
                    btInscri.setDisable(true);
                    ErrConpass.setVisible(true);
                }
            }
        });

        tfConfirmPassword.setPromptText("Retapez Votre mot de passe : ");
        tfConfirmPassword.setLabelFloat(true);

        ErrConpass.setTextFill(Color.web("#FF0000"));
        ErrConpass.setVisible(false);

        tfConfirmPassword.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (tfPassword.getText().equals(tfConfirmPassword.getText())) {
                    btInscri.setDisable(false);
                    ErrConpass.setVisible(false);
                } else {
                    btInscri.setDisable(true);
                    ErrConpass.setVisible(true);
                }
            }
        });

        tfTel.setPromptText("Votre Téléphone (Sans IDN pays): "+idn);
        tfTel.setLabelFloat(true);

        ErrTel.setTextFill(Color.web("#FF0000"));
        ErrTel.setVisible(false);

        tfTel.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (tfTel.getText().matches("^[0-9]*$") && tfTel.getText().length() > 7) {
                    btInscri.setDisable(false);
                    ErrTel.setVisible(false);
                } else {
                    btInscri.setDisable(true);
                    ErrTel.setVisible(true);
                }
            }
        });

        //cbPays.getItems().addAll("Tunisie", "Maroc", "Italie", "France", "Malte");
        tfPays.setText(pays);
        tfPays.setDisable(true);
        tfPays.setPromptText("Votre Pays :");
        tfPays.setLabelFloat(true);
        ErrPays.setTextFill(Color.web("#FF0000"));
        ErrPays.setVisible(false);

        tfVille.setPromptText("Votre Ville : ");
        tfVille.setText(region);
        tfVille.setDisable(true);
        tfVille.setLabelFloat(true);

        ErrVille.setTextFill(Color.web("#FF0000"));
        ErrVille.setVisible(false);

        if (CBType.getValue().equals("Utilisateur Simple")) {

            tfPrenom.setPromptText("Votre Prénom : ");
            tfPrenom.setLabelFloat(true);

            ErrPnom.setTextFill(Color.web("#FF0000"));
            ErrPnom.setVisible(false);

            tfPrenom.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    if (tfPrenom.getText().length() <= 2) {
                        ErrPnom.setVisible(true);
                        btInscri.setDisable(true);
                    } else {
                        ErrPnom.setVisible(false);
                        btInscri.setDisable(false);
                    }
                }
            });

            cbGenre.setPromptText("Genre");
            cbGenre.getItems().addAll("Homme", "Femme");

            ErrGenre.setTextFill(Color.web("#FF0000"));
            ErrGenre.setVisible(false);

            dateP.setPromptText("Vote Date de Naissance : ");


            ErrDateN.setTextFill(Color.web("#FF0000"));
            ErrDateN.setVisible(false);
            VBoxInscri.getChildren().addAll(tfNom, ErrNom, tfPrenom, ErrPnom, tfMail, ErrMail, tfPassword, Errpass, tfConfirmPassword, ErrConpass, tfTel , ErrTel, tfPays, ErrPays, tfVille, ErrVille, cbGenre, ErrGenre, dateP, ErrDateN);
        } 
        //if (CBType.getValue().equals("Organisation")) {
        else {
            
            pageFb.setPromptText("URL de votre Page Fb :");
            pageFb.setLabelFloat(true);
            Label ErrFB = new Label(" ");
            
            siteWeb.setPromptText("URL de votre Site Web :");
            siteWeb.setLabelFloat(true);
            Label ErrWeb = new Label(" ");
            
            numVisa.setPromptText("Numéro de Visa : ");
            numVisa.setLabelFloat(true);
            Label ErrVisa = new Label(" ");

            desc.setPromptText("Déscription : ");
            desc.setLabelFloat(true);
            Label ErrDesc = new Label(" ");

            VBoxInscri.getChildren().addAll(tfNom, ErrNom, tfMail, ErrMail, tfPassword, Errpass, tfConfirmPassword, ErrConpass, tfTel, ErrTel, tfPays, ErrPays, tfVille, ErrVille,pageFb,ErrFB ,siteWeb,ErrWeb ,numVisa,ErrVisa,desc,ErrDesc);
        }
    }

    private boolean CheckBeforeInscriUS() {
        int x = 0;
        if (tfNom.getText().equals("")) {
            x++;
            ErrNom.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfPrenom.getText().equals("")) {
            x++;
            ErrPnom.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfMail.getText().equals("")) {
            x++;
            ErrMail.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfTel.getText().equals("")) {
            x++;
            ErrTel.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfPassword.getText().equals("")) {
            x++;
            ErrConpass.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfConfirmPassword.getText().equals("")) {
            x++;
            ErrConpass.setVisible(true);
            btInscri.setDisable(true);
        }
        if (tfPays.getText() == null) {
            x++;
            ErrConpass.setVisible(true);
            btInscri.setDisable(true);
        }
        System.out.println(tfPays.getText());
        if (tfVille.getText().equals("")) {
            x++;
            ErrVille.setVisible(true);
            btInscri.setDisable(true);
        }
        if (dateP.getValue().toString() == null) {
            x++;
            ErrDateN.setVisible(true);
            btInscri.setDisable(true);
        }
        System.out.println(dateP.getValue().toString());

        if (x == 0) {
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void Inscri(ActionEvent event) {
        mailToVal=tfMail.getText();
        /*
        if (CBType.getValue().equals("Utilisateur Simple")) {
            GestionnaireUtilisateur_Simple gus = new GestionnaireUtilisateur_Simple();
            if (!CheckBeforeInscriUS()) {
                Utilisateur_Simple us = new Utilisateur_Simple(tfPrenom.getText(), cbGenre.getValue(), Date.valueOf(dateP.getValue()), tfMail.getText(), tfPassword.getText(), "Bsalt", idn+tfTel.getText(), new Adresse(tfPays.getText(), tfVille.getText()), "image", 0, tfNom.getText());

                int mv = -1;
                if (rbtVerifMail.isSelected()) {
                    mv = 1;
                } else if (rbtVerifSMS.isSelected()) {
                    mv = 2;
                } else if (rbtVerifPhone.isSelected()) {
                    mv = 3;
                }
                int X = gus.InscrireUS(us, mv);
                Alert alV = new Alert(Alert.AlertType.CONFIRMATION, "Inscription Réussi , confirmer votre compte", ButtonType.OK);
                Alert alE = new Alert(Alert.AlertType.WARNING, "Echec Inscription", ButtonType.OK);
                if (X == 1) {
                    alV.showAndWait();

                    Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UIActivationCompte.fxml"));
                        stackInscri.getChildren().clear();
                        stackInscri.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    alE.showAndWait();
                }
            }
        } else if (CBType.getValue().equals("Organisation")) {
            GestionnaireOrganisation gOrg = new GestionnaireOrganisation();
            Organisation org = new Organisation(numVisa.getText(), pageFb.getText(), siteWeb.getText(), desc.getText(), new Float(lat), new Float(lon) ,tfMail.getText(), tfPassword.getText(), "Bsalt", idn+tfTel.getText(), new Adresse(tfPays.getText(), tfVille.getText()), "image", 0, tfNom.getText());
            int mv = -1;
                if (rbtVerifMail.isSelected()) {
                    mv = 1;
                } else if (rbtVerifSMS.isSelected()) {
                    mv = 2;
                } else if (rbtVerifPhone.isSelected()) {
                    mv = 3;
                }
                int X = gOrg.InscrireORG(org,mv);
                Alert alV = new Alert(Alert.AlertType.CONFIRMATION, "Inscription Réussi , confirmer votre compte", ButtonType.OK);
                Alert alE = new Alert(Alert.AlertType.WARNING, "Echec Inscription", ButtonType.OK);
                if (X == 1) {
                    alV.showAndWait();

                    Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UIActivationCompte.fxml"));
                        stackInscri.getChildren().clear();
                        stackInscri.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    alE.showAndWait();
                }
        }*/
    }

    @FXML
    private void BackToLogin(MouseEvent event) {
        Pane newLoadedPane;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UiLogin.fxml"));
            stackInscri.getChildren().clear();
            stackInscri.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
