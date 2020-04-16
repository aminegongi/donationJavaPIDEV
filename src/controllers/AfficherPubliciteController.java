/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.PubliciteRegionService;
import Services.PubliciteService;
import Utils.Browser;
import Entities.Publicité;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Ahmed Fourati
 */
public class AfficherPubliciteController implements Initializable {
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
    private TableView<Publicité> tableViewPublicite;
    @FXML
    private TableColumn<Publicité, String> titre;
    @FXML
    private TableColumn<Publicité, String> marque;
    @FXML
    private TableColumn<Publicité, String> description;
    @FXML
    private TableColumn<?, ?> dateAjout;
    @FXML
    private Button supprimerPubliciteButton;
    @FXML
    private TextField TitrePublicite;
    @FXML
    private TextField descriptionPublicite;
    @FXML
    private TextField marquePublicite;
    @FXML
    private ImageView imagePublicitePreview;
    @FXML
    private Button importerImage;
    @FXML
    private Label publiciteImageCommentaire;
    private String imagePublicite  ; 
    List<String> listFichier;
    @FXML
    private Button exporterEnPdf;
    @FXML
    private Button allCountriesStats;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         PubliciteService ps = new PubliciteService();
        ArrayList<Publicité> publiciteList =  (ArrayList<Publicité>) ps.afficher() ; 
    ObservableList<Publicité> data = FXCollections.observableArrayList(publiciteList);
    tableViewPublicite.setItems(data);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAjout.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
       
        tableViewPublicite.setEditable(true);
        titre.setCellFactory(TextFieldTableCell.forTableColumn());
        marque.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        
         imagePublicite=null ;
        listFichier= new ArrayList<>();
       listFichier.add("*.png");
       listFichier.add("*.jpg");
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
    private void modifierTitre(TableColumn.CellEditEvent<Publicité, String > event) {
     Publicité p = tableViewPublicite.getSelectionModel().getSelectedItem();
        p.setTitre(event.getNewValue());
        PubliciteService ps = new PubliciteService();
        ps.modifier(p, p.getId());
    }
    @FXML
    private void modifierMarque(TableColumn.CellEditEvent<Publicité, String> event) {
     Publicité p = tableViewPublicite.getSelectionModel().getSelectedItem();
        p.setMarque(event.getNewValue());
        PubliciteService ps = new PubliciteService();
        ps.modifier(p, p.getId());
    }
    @FXML
    private void modifierDescription(TableColumn.CellEditEvent<Publicité, String> event) {
     Publicité p = tableViewPublicite.getSelectionModel().getSelectedItem();
        p.setDescription(event.getNewValue());
        PubliciteService ps = new PubliciteService();
        ps.modifier(p, p.getId());
    }

    @FXML
    private void supprimerPublicite(ActionEvent event) {
        
        Publicité selectedItem = tableViewPublicite.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        PubliciteService ps = new PubliciteService() ; 
        tableViewPublicite.getItems().remove(selectedItem);
        ps.supprimer(selectedItem);
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
    private void ajouterPublicite(ActionEvent event) throws IOException {
        if(TitrePublicite.getText().isEmpty() || marquePublicite.getText().isEmpty() || descriptionPublicite.getText().isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant d'ajouter une publicité");

            alert.showAndWait();
        }
        else{
         DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = new Date();
        
        Publicité p = new Publicité(TitrePublicite.getText(), descriptionPublicite.getText(),marquePublicite.getText() ,sdf.format(date) , imagePublicite);
        PubliciteService ps = new PubliciteService();
        ps.ajouter(p);
        ArrayList<Publicité> publiciteList =  (ArrayList<Publicité>) ps.afficher() ; 
    ObservableList<Publicité> data = FXCollections.observableArrayList(publiciteList);
    
    tableViewPublicite.setItems(data);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAjout.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        TitrePublicite.setText("");
        descriptionPublicite.setText("");
        marquePublicite.setText("");
        publiciteImageCommentaire.setText("");
        imagePublicitePreview.setImage(null);
        }
    }

   @FXML
    private void importerPubliciteImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser() ; 
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if(f !=null){
        
            publiciteImageCommentaire.setText("Image selectionnée"+f.getAbsolutePath());
             InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(f.getAbsolutePath()));
//             System.out.println("Working Directory = " +
//              System.getProperty("user.dir"));
//            System.out.println("nomfichier"+f.getName());
            os = new FileOutputStream(new File(UiLoginController.pathToSymfonyProject+"web/uploads/imagesPubs/"+f.getName()));
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
           
            File file = new File(UiLoginController.pathToSymfonyProject+"web/uploads/imagesPubs/"+f.getName());
//            System.out.println(file.toURI().toString());
        imagePublicitePreview.setImage(new Image(file.toURI().toString()));
        imagePublicite=f.getName() ; 
        }
        else if (f ==null){
            publiciteImageCommentaire.setText("Erreur chargement de l'image");
        }
    }

    

    @FXML
    private void exporterPDF(ActionEvent event) {
        Publicité selectedItem = tableViewPublicite.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        
            PubliciteRegionService prs = new PubliciteRegionService() ; 
            
            JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("choosertitle");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);

    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
       prs.ecrirePdf(selectedItem, chooser.getSelectedFile());
    } else {
      System.out.println("No Selection ");
    }
  
        
       
        
        
        }
        else {
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à exporter.");

        alert.showAndWait();
        }
    }

   

    @FXML
    private void afficherCountries(ActionEvent event) {
        Publicité selectedItem = tableViewPublicite.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
         Scene scene;
         Stage stage = new Stage();  
        stage.setTitle("Web View");
        scene = new Scene(new Browser(Integer.toString(selectedItem.getId())),750,500, Color.web("#666970"));
        stage.setScene(scene);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
        stage.show();
        
        
        
        }
        else {
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une publicité au moins.");

        alert.showAndWait();
        }
        
    }

   

    
   
    
}
