/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.GestionnaireUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class UIActivationCompteController implements Initializable {

    @FXML
    private TextField txtMail;
    @FXML
    private Button btnActivation;
    @FXML
    private TextField txtCodeAct;
    @FXML
    private Label labelMail;
    @FXML
    private StackPane stackActivation;
    @FXML
    private ImageView imgback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String mail;
        if(UiInscriptionController.mailToVal == null){
            mail=UiLoginController.mailToVal;
        }
        else{
            mail=UiInscriptionController.mailToVal;
        }
        txtMail.setText(mail);
    }

    @FXML
    private void ActivationCompte(ActionEvent event) {
        GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
        boolean ok = gu.activerCompteKeyMail(txtMail.getText(), txtCodeAct.getText());
        Alert al1 = new Alert(Alert.AlertType.CONFIRMATION, "Compte Activer Bravo", ButtonType.OK);
        Alert al2 = new Alert(Alert.AlertType.WARNING, "Invalide Mail ou Code d'activation", ButtonType.OK);

        if (ok) {
            al1.showAndWait();
            Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UiLogin.fxml"));
                        stackActivation.getChildren().clear();
                        stackActivation.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } else {
            al2.showAndWait();
        }
        txtMail.clear();
        txtCodeAct.clear();
    }

    @FXML
    private void BackToLogin(MouseEvent event) {
        Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UiLogin.fxml"));
                        stackActivation.getChildren().clear();
                        stackActivation.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }


}
