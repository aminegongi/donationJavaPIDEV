/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.cagnotte;
import Entities.categorieCagnotte;
import Services.GestionnaireCagnotte;
import Services.GestionnaireCategorie;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class GestionCagnotteController implements Initializable {
    @FXML
    private BorderPane border_pane;
    @FXML
    private StackPane stackSide;
    @FXML
    private Pane paneSide;
    boolean flag = false;
    @FXML
    private Pane stackSide2;
    @FXML
    private StackPane rootPaneM;
    private Pane paneHomePage;
    @FXML
    private Pane paneDetailCagnotte;
    @FXML
    private Pane paneFaireDon;
    @FXML
    private FlowPane paneListerCagnottes;
    @FXML
    private Button backToList;
    @FXML
    private ScrollPane paneListerCagnottesParent;
    @FXML
    private Label nom;
    @FXML
    private Label organisation;
    @FXML
    private Label proprietaire;
    @FXML
    private Label date_fin;
    @FXML
    private Label date_debut;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private Label montant_actuel;
    @FXML
    private Label montant_demande;
    private cagnotte selected_cagnotte = null;
    private boolean faire_don_initialized = false;
    private boolean ajouter_don_initialized = false;
    private JFXTextField montant;
    @FXML
    private Button valider;
    @FXML
    private CheckBox anon;
    @FXML
    private JFXTextField nom_field;
    @FXML
    private JFXTextField montant_field;
    @FXML
    private ChoiceBox<String> methode_field;
    @FXML
    private Button backToDetail;
    @FXML
    private Button ajouterCagnotteButton;
    @FXML
    private Pane paneAjouterCagnotte;
    @FXML
    private JFXTextField nom_field_ajout;
    @FXML
    private JFXTextField montant_demande_field_ajout;
    @FXML
    private ChoiceBox<String> cat;
    @FXML
    private Button ajouter;
    @FXML
    private DatePicker date_debut_picker;
    @FXML
    private DatePicker date_fin_picker;
    private final int ConnectedUserRole = 2; //1 For simple user, 2 for organisation
    @FXML
    private Button activate_don;
    @FXML
    private Button faire_un_don;
    @FXML
    private Button pause_don;
    @FXML
    private Pane paneListerCagnotteChild;
    @FXML
    private Pane paneBlackBackground;
    @FXML
    private Pane paneWebBrowser;
    @FXML
    private WebView webView;
    private float montant_a_ajouter = 0;
    @FXML
    private Button backToFaireDon;
    @FXML
    private JFXTextField searchBar;
    @FXML
    private ImageView searchBarImg;
    private boolean don_montant_valide = false;
    private boolean don_methode_valide = false;
    @FXML
    private Label don_error;
    private boolean ajout_montant_demande_valide = false;
    @FXML
    private Label ajout_error;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stackSide2.setStyle("-fx-background-color: #F83F29;");
        paneListerCagnottesParent.setVisible(true);
        paneListerCagnottes.setVisible(true);
        searchBar.setVisible(true);
        searchBarImg.setVisible(true);
        paneDetailCagnotte.setVisible(false);
        paneAjouterCagnotte.setVisible(false);
        paneFaireDon.setVisible(false);
        paneBlackBackground.setVisible(false);
        paneWebBrowser.setVisible(false);
        webView.setVisible(false);
        don_error.setVisible(false);
        GestionnaireCagnotte gc = new GestionnaireCagnotte();
        gc.checkIfCagnotteExpiredAndClose();
        affichageCagnottes("display_all_cagnottes");
        
        
    }    

     @FXML
    private void OpenSidebar(MouseEvent event) {
        if(!flag){
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
            paneSide.setStyle("-fx-background-color: #F83F29;");
            
            
        }

    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
        if(flag){
             flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
            stackSide2.setStyle("-fx-background-color: # F83F29;");
          
        
        }
    }

    private void goToListerCagnottes() {
        paneListerCagnottes.getChildren().clear();
        paneListerCagnottes.getChildren().add(paneListerCagnotteChild);             
        paneDetailCagnotte.setVisible(false);
        paneAjouterCagnotte.setVisible(false);
        paneFaireDon.setVisible(false);
        paneListerCagnottes.setVisible(true);
        paneListerCagnottesParent.setVisible(true);
        searchBar.setVisible(true);
        searchBarImg.setVisible(true);
        paneBlackBackground.setVisible(false);
        paneWebBrowser.setVisible(false);
        webView.setVisible(false);
        affichageCagnottes("display_all_cagnottes");       
    }
    
    private void affichageCagnottes(String search_key){
        GestionnaireCagnotte gc = new GestionnaireCagnotte();
        List<cagnotte> list = new ArrayList<>();
        //list = null;
        if (ConnectedUserRole == 1){
            if (search_key.equals("display_all_cagnottes")){
                list = gc.getCagnottesEtatTrue();
            }else{
                list = gc.getCagnottesEtatTrueSearch(search_key);
            }
        }else if (ConnectedUserRole == 2){
            if (search_key.equals("display_all_cagnottes"))
                list = gc.getCagnottes();
            else{
                list = gc.getCagnottesSearch(search_key);
            }
        }
  
        ArrayList<Separator> as = new ArrayList();
        ArrayList<VBox> vbx = new ArrayList(); 
        
        for (int i = 0; i < list.size(); i++) {
            
            cagnotte c = list.get(i);
            
            //details sur user proprietaire du demande
            int id = c.getId();
            String nom = c.getNom();
            int id_categorie = c.getId_categorie();
            float montant_demande = c.getMontant_demande();
            float montant_actuel = c.getMontant_actuel();
            int id_proprietaire = c.getId_proprietaire();
            int id_organisation = c.getId_organisation();
            int etat = c.getEtat();
            GestionnaireCategorie gct = new GestionnaireCategorie();
            String categorie = gct.getCategorieNomFromId(id_categorie);
            
            //Separator vertical entre les categories
            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(20);
            h.setPrefHeight(44);
            h.setVisible(false);
            as.add(h);
            
            VBox VBoxCat = new VBox();
            VBoxCat.setSpacing(5);
            VBoxCat.getStyleClass().add("vboxcagnotte");
            VBoxCat.setAlignment(Pos.CENTER);
            VBoxCat.setPrefHeight(300);
            VBoxCat.setPrefWidth(390);

            Label nom_lab = new Label("Titre: " + nom);
            Label categorie_lab = new Label("Categorie: " + categorie);
            Label montant_demande_lab = new Label("Montant demandé: " + montant_demande + " DT");
            Label montant_actuel_lab= new Label("Montant Achevé: " + montant_actuel + " DT");
            Label proprietaire_lab= new Label("Publié par: " + id_proprietaire);
            Label organisation = new Label("Organisation: " + id_organisation);
            Label etat_lab = new Label("Etat: " + etat);
            
            Separator sh = new Separator(Orientation.HORIZONTAL);
            sh.setPrefHeight(80);
            sh.setVisible(false);
       
            //boutton preview Demande = btnpreview
            /*FileInputStream inputPreview = new FileInputStream(CUsersHediDocumentsNetBeansProjectsPlateformeAideFXsrcRessourcespreview.png);
            Image imagePreview = new Image(inputPreview);
            ImageView imageViewPreview = new ImageView(imagePreview);
            imageViewPreview.setFitHeight(20);
            imageViewPreview.setFitWidth(20);*/
            Button button_afficher = new Button("Afficher", null);
            button_afficher.setMaxSize(100, 200);
            button_afficher.setOnAction((event) -> {
            System.out.println("Button afficher");
            selected_cagnotte = c;
            goToDetailCagnotte();
            initializeDetailCagnotte();
            });
            
            
            VBoxCat.getChildren().add(nom_lab);
            VBoxCat.getChildren().add(categorie_lab);
            VBoxCat.getChildren().add(montant_demande_lab);
            VBoxCat.getChildren().add(montant_actuel_lab);
            VBoxCat.getChildren().add(proprietaire_lab);
            VBoxCat.getChildren().add(organisation);
            VBoxCat.getChildren().add(etat_lab);
            VBoxCat.getChildren().add(button_afficher);
            vbx.add(VBoxCat);
            
            paneListerCagnottes.getChildren().add(vbx.get(i));
            paneListerCagnottes.getChildren().add(as.get(i));
            
            if (i == 0) {
                System.out.println(i=0);
            } else {
                if (((i + 1) %3) == 0) {
                    Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    sepHoriz.setPrefHeight(35);
                    sepHoriz.setPrefWidth(1400);
                    sepHoriz.setVisible(false);
                    paneListerCagnottes.getChildren().add(sepHoriz);
                    
                }
            }
        }
    }
    
    @FXML
    private void backToListButton(ActionEvent event) {
        paneListerCagnottes.getChildren().clear();
        paneListerCagnottes.getChildren().add(paneListerCagnotteChild);             
        paneDetailCagnotte.setVisible(false);
        paneAjouterCagnotte.setVisible(false);
        paneListerCagnottes.setVisible(true);
        paneListerCagnottesParent.setVisible(true);
        searchBar.setVisible(true);
        searchBarImg.setVisible(true);
        paneBlackBackground.setVisible(false);
        affichageCagnottes("display_all_cagnottes");
        
    }

    @FXML
    private void backToDetailButton(ActionEvent event) {
        paneFaireDon.setVisible(false);
        paneDetailCagnotte.setVisible(true);
    }
    
    @FXML
    private void backToFaireDon(ActionEvent event) {
        paneFaireDon.setVisible(true);
        paneWebBrowser.setVisible(false);
        webView.setVisible(false);
        don_error.setVisible(false);
    }
    
    private void goToAjouterCagnotte(){
        //paneListerCagnottesParent.setVisible(false);
        //paneListerCagnottes.setVisible(false);
        paneAjouterCagnotte.setVisible(true);
        paneBlackBackground.setVisible(true);
        searchBar.setVisible(false);
        searchBarImg.setVisible(false);
    }
    
    private void goToDetailCagnotte(){
        //paneListerCagnottesParent.setVisible(false);
        //paneListerCagnottes.setVisible(false);
        paneDetailCagnotte.setVisible(true);
        paneBlackBackground.setVisible(true);
        searchBar.setVisible(false);
        searchBarImg.setVisible(false);
    }
    
    private void goToFaireDon(){
        paneDetailCagnotte.setVisible(false);
        paneFaireDon.setVisible(true);
        don_error.setVisible(false);
    }
    
    private void goToWebView(){
        paneAjouterCagnotte.setVisible(false);
        paneWebBrowser.setVisible(true);
        webView.setVisible(true);
        WebEngine webEngine = webView.getEngine();
        //System.out.println("Montant a ajouer: " + montant_a_ajouter);
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                if(webEngine.getLocation().equals("http://proto.tn/Donation/save.php")){
                System.out.println("bbbb");
                if (anon.isSelected()){
                    cagnotte c = selected_cagnotte;
                    GestionnaireCagnotte gc = new GestionnaireCagnotte();
                    System.out.println("Montant: "+ montant_field.getText());
                    gc.ajouterDon(c, "Anonyme", Float.parseFloat(montant_field.getText()), methode_field.getValue());

                }else{
                    cagnotte c = selected_cagnotte;
                    GestionnaireCagnotte gc = new GestionnaireCagnotte();
                    System.out.println("Montant: "+ montant_field.getText());
                    gc.ajouterDon(c, nom_field.getText(), Float.parseFloat(montant_field.getText()), methode_field.getValue());
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("DoNation!");
                alert.setHeaderText("Don ajouté!");
                alert.setContentText("Merci pour votre générosité.");
                alert.showAndWait();
                
                goToListerCagnottes();
                }
            }
        });
        webEngine.load("http://proto.tn/Donation/index.php?montant=" + montant_a_ajouter);
        System.out.println("aaa");
        /**/
    }
    
    private void initializeDetailCagnotte(){
        GestionnaireCagnotte gc = new GestionnaireCagnotte();
        cagnotte c = selected_cagnotte;
        nom.setText(c.getNom());
        organisation.setText(String.valueOf((c.getId_organisation())));
        proprietaire.setText(String.valueOf((c.getId_proprietaire())));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date_debut.setText(df.format(c.getDate_de_debut()));
        date_fin.setText(df.format(c.getDate_de_fin()));
        montant_demande.setText(String.valueOf((c.getMontant_demande()) + " DT"));
        montant_actuel.setText(String.valueOf((c.getMontant_actuel()) + " DT"));
        progress_bar.setProgress((c.getMontant_actuel()/c.getMontant_demande()));
        if (ConnectedUserRole == 2){
            if (c.getEtat() == 0){
                if ((c.getMontant_actuel() >= c.getMontant_demande()) || ((c.getDate_de_debut().compareTo(c.getDate_de_fin()) > 0))){
                    activate_don.setVisible(false);
                }
                else{
                    activate_don.setVisible(true);
                }
                pause_don.setVisible(false);
                faire_un_don.setVisible(false);
            }
            else if (c.getEtat() == 1){
                activate_don.setVisible(false);
                pause_don.setVisible(true);
                faire_un_don.setVisible(true);
            }
        }
        else if ((ConnectedUserRole == 1) && (c.getEtat() == 1)){
            activate_don.setVisible(false);
            pause_don.setVisible(false);
            faire_un_don.setVisible(true);
        }
    }

    @FXML
    private void faire_un_don(ActionEvent event) {
        goToFaireDon();
        initializeFaireDon();
    }
    
    private void initializeFaireDon(){
        cagnotte c = selected_cagnotte;
        don_error.setVisible(false);
        if(faire_don_initialized == false){
            methode_field.getItems().add("PayPal");
            faire_don_initialized = true;
        } 
    }

    private void initializeAjouterCagnotte(){
        if(ajouter_don_initialized == false){
            List<categorieCagnotte> l = new ArrayList<>();
            GestionnaireCategorie gcat = new GestionnaireCategorie();
            l = gcat.getCategories();
            for(int i=0; i<l.size(); i++){
                cat.getItems().add(l.get(i).getNom());
            }
            ajouter_don_initialized = true;
        }
        ajout_error.setVisible(false);
    }
    
    @FXML
    private void validerPaiement(ActionEvent event) {
        if (((!(nom_field.getText().trim().isEmpty())) || (anon.isSelected())) && (isThisFloat(montant_field.getText())) && (!(montant_field.getText().trim().isEmpty())) && (!(methode_field.getSelectionModel().isEmpty()))){
            montant_a_ajouter = Float.parseFloat(montant_field.getText());
            goToWebView();
            don_error.setVisible(false);
        }
        else{
            don_error.setText("Veuillez verifier les informations entrées");
            don_error.setVisible(true);
        }
        
    }

    @FXML
    private void setAnon(ActionEvent event) {
        if (anon.isSelected()){
            nom_field.setDisable(true);
        }else{
            nom_field.setDisable(false);
        }
    }

    @FXML
    private void ajouterCagnotte(ActionEvent event) {
        goToAjouterCagnotte();
        initializeAjouterCagnotte();
    }
    
    private boolean ajout_check_fields(){
        if ((isThisFloat(montant_demande_field_ajout.getText())) && (!(cat.getSelectionModel().isEmpty())) && (date_fin_picker.getValue().toString() != null) && (date_debut_picker.getValue().toString() != null) && (nom_field_ajout.getText() != null)){
            if (java.sql.Date.valueOf(date_fin_picker.getValue()).after(java.sql.Date.valueOf(date_debut_picker.getValue()))){
                return true;
            }
        }
        return false;
    }

    @FXML
    private void valider_ajout_cagnotte(ActionEvent event) {
        if (ajout_check_fields()){
            GestionnaireCagnotte gc = new GestionnaireCagnotte();
            GestionnaireCategorie gct = new GestionnaireCategorie();
            //Date date_debut = new Date(Integer.parseInt(dy.getText()) - 1900, Integer.parseInt(dm.getText()), Integer.parseInt(dd.getText()));
            //Date date_fin = new Date(Integer.parseInt(fy.getText()) - 1900, Integer.parseInt(fm.getText()), Integer.parseInt(fd.getText()));
            LocalDate date_debut = date_debut_picker.getValue();
            LocalDate date_fin = date_fin_picker.getValue();
            int id_categorie = gct.getCategorieIdFromName(cat.getValue());
            System.out.println("Cat: " + id_categorie);
            cagnotte c = new cagnotte(nom_field_ajout.getText(), id_categorie, java.sql.Date.valueOf(date_debut), java.sql.Date.valueOf(date_fin), Float.parseFloat(montant_demande_field_ajout.getText()), 0, 1);
            gc.ajouterDemande(c);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("DoNation!");
            alert.setHeaderText("Demande d'ajout de cagnotte ajouté!");
            alert.setContentText("Veuillez patienter pour q'une organisation valide ta cagnotte.");
            alert.showAndWait();
            goToListerCagnottes();
            ajout_error.setVisible(false);
        }else{
            ajout_error.setVisible(true);
            ajout_error.setText("Veuillez verifier les informations entrées");
        }
    }

    @FXML
    private void activate_don(ActionEvent event) {
        GestionnaireCagnotte gc = new GestionnaireCagnotte();
        cagnotte c = selected_cagnotte;
        gc.validerDemande(c);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("DoNation!");
        alert.setHeaderText("Cagnotte prise en charge!");
        alert.setContentText("Cette cagnotte peut maintenant recevoir des dons.");
        alert.showAndWait();
        goToListerCagnottes();
    }

    @FXML
    private void pause_don(ActionEvent event) {
        GestionnaireCagnotte gc = new GestionnaireCagnotte();
        cagnotte c = selected_cagnotte;
        gc.pauseCagnotte(c);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("DoNation!");
        alert.setHeaderText("Cagnotte pausé!");
        alert.setContentText("Cette cagnotte peut réactivée.");
        alert.showAndWait();
        goToListerCagnottes();
    }

    @FXML
    private void updateListeCagnotte(KeyEvent event) {
        String search_key = searchBar.getText();
        paneListerCagnottes.getChildren().clear();
        paneListerCagnottes.getChildren().add(paneListerCagnotteChild);             
        affichageCagnottes(search_key);       
    }

    private boolean isThisFloat(String st){
        try{
            Float.parseFloat(st);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    @FXML
    private void don_check_montant(KeyEvent event) {
        if (isThisFloat(montant_field.getText())){
            don_montant_valide = true;
            System.out.println("montant validé");
            don_error.setVisible(false);
            
        }else{
            don_montant_valide = false;
            don_error.setText("Le montant doit être un nombre!");
            don_error.setVisible(true);
        }
    }

    @FXML
    private void ajout_check_montant(KeyEvent event) {
        if (isThisFloat(montant_demande_field_ajout.getText())){
            ajout_montant_demande_valide = true;
            System.out.println("montant validé");
            ajout_error.setVisible(false);
            
        }else{
            ajout_montant_demande_valide = false;
            ajout_error.setText("Le montant doit être un nombre!");
            ajout_error.setVisible(true);
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
