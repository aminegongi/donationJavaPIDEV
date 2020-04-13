/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.GestionnaireAdministrateur;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controllers.UiLoginController.mailToVal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class AdminLoginController implements Initializable {
    @FXML
    private StackPane pane;
    @FXML
    private JFXTextField txtMail;
    @FXML
    private Label labelMail;
    @FXML
    private JFXPasswordField txtMdp;
    @FXML
    private Button FacebookSign;
    
    public static int idAdmin ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VerifMail(KeyEvent event) {
    }

    @FXML
    private void cnx(ActionEvent event) {
        GestionnaireAdministrateur ga= new GestionnaireAdministrateur();
        Boolean b=ga.checkLogin(txtMail.getText(), txtMdp.getText());
        idAdmin=ga.getidd(txtMail.getText());
        System.out.println(idAdmin);
        if(b){
            /*Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DashbordAdmin.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
             try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DashbordAdmin_Users.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Alert a= new Alert(Alert.AlertType.WARNING, "username ou mot de passe incorrect", null);
            a.showAndWait();
        }
    }

    @FXML
    private void goLoginClient(MouseEvent event) {
        Pane newLoadedPane;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/views/UiLogin.fxml"));
                pane.getChildren().clear();
                pane.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(UiLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
