/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.PubliciteRegionService;
import Services.PubliciteService;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import Entities.AppelAuDon;
import Entities.OffreDon;
//import Entities.Organisation;
import Entities.PublicationDon;
import Entities.PubliciteRegion;
import Entities.Publicité;
import Entities.Utilisateur;
//import Entities.Restaurant;
import Services.AppelAuDonService;
import Services.GestionnaireUtilisateur;
//import Services.GestionnaireOrganisation;
//import Services.GestionnaireRestaurant;
import Services.OffreDonService;
import Utils.SmsRestauOrg;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import static java.awt.SystemColor.text;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Ahmed Fourati
 */
public class GestionRestauOrgController implements Initializable {
    private int idUserConnecte;//  à changer avec intergration
    private String roleUser ; //  à changer avec intergration
     DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
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
    private ImageView pubImage1;
    private Label titirePub1;
    @FXML
    private Label idPub1;
    @FXML
    private StackPane ajouterStackPane;
    @FXML
    private Label ajouterOfrreDemandeLabel;
    @FXML
    private TextField nbrePlatInput;
    @FXML
    private TextArea descpritionInput;
    @FXML
    private TextField titreInput;
    @FXML
    private Button ajouterPublicationDon;
    @FXML
    private StackPane ajouterStackPane1;
    @FXML
    private FlowPane FlowPanePublications;
    private String descriptionPub1=null ; 
    @FXML
    private Pane PublicitePane;
    private TranslateTransition trans ;
    @FXML
    private Label ajouterOfrreDemandeLabel1;
    @FXML
    private JFXTextField titreInput1;
    @FXML
    private JFXTextField nbrePlatInput1;
    @FXML
    private JFXTextArea descpritionInput1;
    @FXML
    private JFXButton ajouterPublicationDon1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = false ;
        
        
                   stackSide2.setStyle("-fx-background-color: #056F8B;");
                  
        
                   idPub1.setVisible(false);
                   
                   
                    //roleUser = HomeFXMLController.isUserRole; 
                    roleUser=UiLoginController.uh.getRoles();
                    System.out.println("roleeeeeee"+roleUser);
         idUserConnecte = UiLoginController.uh.getId(); // met 11 (user= oragnisation)
       
       ajouterStackPane1.setVisible(false);
        
        affichage();
        if(roleUser.equals("ROLE_ORG")){
            ajouterOfrreDemandeLabel.setText("Appel au don");
            titreInput.setVisible(true);
            descpritionInput.setVisible(true);
            nbrePlatInput.setVisible(true);
            ajouterPublicationDon.setVisible(true);
        }
        else if(roleUser.equals("ROLE_RES")){
        ajouterOfrreDemandeLabel.setText("Offre Don");
            titreInput.setVisible(true);
            descpritionInput.setVisible(true);
            nbrePlatInput.setVisible(false);
            ajouterPublicationDon.setVisible(true);
        }
        else{
          ajouterOfrreDemandeLabel.setVisible(false);
            titreInput.setVisible(false);
            descpritionInput.setVisible(false);
            nbrePlatInput.setVisible(false);
            ajouterPublicationDon.setVisible(false);  
        }
         afficherPublicite();
         trans = new TranslateTransition(javafx.util.Duration.seconds(10), pubImage1);
         trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.setFromY(-1000);
        trans.setToY(1000);
        
        
        trans.setAutoReverse(false);
      
        trans.play(); 
    }    

     @FXML
    private void OpenSidebar(MouseEvent event) {
        if(!flag){
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
            paneSide.setStyle("-fx-background-color: #056F8B;");
            
            
        }

    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
        if(flag){
            flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
            stackSide2.setStyle("-fx-background-color: #056F8B;");
          
        
        }
    }
    public void afficherPublicite(){
        PubliciteService ps = new PubliciteService(); 
        List<Publicité> list = new ArrayList<>();
        list =ps.getAlea();
//        titrePub1.setText(list.get(0).getTitre());
        idPub1.setText(Integer.toString(list.get(0).getId()));
        File file = new File("./src/images/"+list.get(0).getImage());
        pubImage1.setImage(new Image(file.toURI().toString()));
        descriptionPub1 = list.get(0).getDescription();
       
        
        }

    @FXML
    private void traitementPublicite1(MouseEvent event) throws IOException, URISyntaxException {
     
    }


    @FXML
    private void ajouterPublication(ActionEvent event) {
        if(titreInput.getText().isEmpty() || descpritionInput.getText().isEmpty()   ){
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseinger les champs avant d'ajouter une publication");

            alert.showAndWait();
            
        }else{
        if(roleUser.equals("ROLE_ORG") ){
            if(nbrePlatInput.getText().matches("[0-9]*") && nbrePlatInput.getText().length()<3 && nbrePlatInput.getText().length()>=1){
            AppelAuDon ap = new AppelAuDon(titreInput.getText(), descpritionInput.getText(), sdf.format(date),0,idUserConnecte,Integer.parseInt(nbrePlatInput.getText()),1 );
            AppelAuDonService aps = new AppelAuDonService();
            aps.ajouter(ap);}
            else{
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("le nombre des plats doit etre inférieur à 99");
            alert.showAndWait();
            }
        }
        else if(roleUser.equals("ROLE_RES"))
        {   
            OffreDon of = new OffreDon(titreInput.getText(), descpritionInput.getText(), sdf.format(date), 0,idUserConnecte, 1);
            
            OffreDonService os = new OffreDonService();
            os.ajouter(of);
         
        }

            FlowPanePublications.getChildren().remove(0, FlowPanePublications.getChildren().size());
        
        
        affichage();
        }
    }

     private void affichage() {
          FlowPanePublications.getChildren().remove(0, FlowPanePublications.getChildren().size());
           List<OffreDon> list;
       List<AppelAuDon> list2;
       
        ArrayList<PublicationDon> listX = new ArrayList<>();
       OffreDonService ods = new OffreDonService(); 
        AppelAuDonService x = new AppelAuDonService(); 
        list= ods.afficher();
        list2= x.afficher();
        listX.addAll(list);
        listX.addAll(list2);
        listX.sort((PublicationDon p1 , PublicationDon p2)->p2.getDatePublicaton().compareToIgnoreCase(p1.getDatePublicaton()));
        System.out.println("taille listX"+ listX.size());
         for(int i=0 ; i<listX.size();i++){
             System.out.println(listX.get(i));
             int etat; 
             if(listX.get(i).getClass()==OffreDon.class){
             OffreDon of = (OffreDon) listX.get(i); 
             etat = of.getEtat() ;
             }else{
             AppelAuDon of = (AppelAuDon) listX.get(i); 
             etat = of.getEtat() ;
             
             }
             
             if(etat==1){
        VBox vbox1 = new VBox() ; 
      
        vbox1.setSpacing(5);
          
            
//            vbox1.setAlignment(Pos.CENTER);
            vbox1.setPrefHeight(250);
            vbox1.setPrefWidth(700);
        HBox hbox1 = new HBox() ; 
            Circle c = new Circle(25);
            if (listX.get(i).getClass()==AppelAuDon.class){
            try {
                GestionnaireUtilisateur go2 = new GestionnaireUtilisateur();
                Utilisateur org2= go2.fetchOneUS(listX.get(i).getAjoutePar());
                
                File file3 = new File("./src/images/"+org2.getImage());
                
                c.setFill(new ImagePattern(new Image(new FileInputStream(file3))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GestionRestauOrgController.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
            else if (listX.get(i).getClass()==OffreDon.class){
            try {
                GestionnaireUtilisateur gr2 = new GestionnaireUtilisateur();
                Utilisateur res2= gr2.fetchOneUS(listX.get(i).getAjoutePar());
                
                File file3 = new File("./src/images/"+res2.getImage());
                
                c.setFill(new ImagePattern(new Image(new FileInputStream(file3))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GestionRestauOrgController.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
            Label userName  = new Label() ;
            Label roleName  = new Label() ;
            

            
            File file = new File("./src/images/Icon/sms.png");
                 ImageView smsLogo = new ImageView(new Image(file.toURI().toString()));
                 smsLogo.setFitHeight(30);
            smsLogo.setFitWidth(30);
                 
            Label phone  = new Label("",smsLogo );
            phone.setOnMouseClicked(new EventHandler<Event>() {

                
            @Override
            public void handle(Event event) {
                 Alert al = new  Alert(Alert.AlertType.INFORMATION);
                 al.setContentText("Un SMS va ètre envoyée à cette personne pour que vous puissiez entrer en contact avec lui . Etres vous sure d'envoyer le SMS ?");
                 ButtonType Oui = new ButtonType("Oui");
                ButtonType Non = new ButtonType("Non");
                al.getButtonTypes().addAll(Oui,Non);
                Optional<ButtonType> OuiNon = al.showAndWait();
         if(OuiNon.get() == Oui){
         SmsRestauOrg sms= new SmsRestauOrg();
//                System.out.println("+" + phone.getText());
                sms.sendSms("Bonjour j'ai lu votre publication sur la plateforme Donation et je souhaite entrer en contact avec vous ^^ "  , phone.getText());
                
         }
                
            }
        });
            if (listX.get(i).getClass()==AppelAuDon.class){
                System.out.println("appelAuDon");
                GestionnaireUtilisateur go = new GestionnaireUtilisateur();
                Utilisateur org= go.fetchOneUS(listX.get(i).getAjoutePar());
                phone.setText(org.getNumTel()) ;
                userName.setText("@"+org.getNom());
                roleName.setText("*"+"Organisation");
                // ici mettre image utilisateur dans la cercle 
                
            }
                     
            else if(listX.get(i).getClass()==OffreDon.class){
                                     System.out.println("oofreDon");
                GestionnaireUtilisateur gr  = new GestionnaireUtilisateur();
                Utilisateur resto =  gr.fetchOneUS(listX.get(i).getAjoutePar());
                phone.setText(resto.getNumTel()) ;
                userName.setText("@"+resto.getNom());
                roleName.setText("*"+"Restaurant");
            }

             // aporter module user 
            
                        hbox1.setAlignment(Pos.TOP_LEFT);

        Label titre  = new Label() ; 
        
        Label description  = new Label() ; 
                description.setWrapText(true);
                HBox hbox2 = new HBox() ;
                                       hbox2.setAlignment(Pos.BOTTOM_LEFT);

                    //circle set image from data base
                
                c.setTranslateY(7);
                hbox1.getChildren().add(c);
                
                
                phone.setTranslateY(7);
                VBox vbox2 = new VBox();
                vbox2.getChildren().add(userName);
                vbox2.getChildren().add(roleName);
                hbox1.getChildren().add(vbox2);
                vbox2.setTranslateY(7);
                
                
                hbox1.getChildren().add(phone);
                
                phone.setTranslateX(400);
                vbox1.getChildren().add(hbox1);
                titre.setText(listX.get(i).getTitre());
                vbox1.getStyleClass().add("vbox1");
                titre.getStyleClass().add("titrePublication");
                phone.getStyleClass().add("phoneNumber");
                userName.getStyleClass().add("userName");
                roleName.getStyleClass().add("roleName");
                description.setText(listX.get(i).getDescription());
               
                vbox1.getChildren().add(titre);
                vbox1.getChildren().add(description);
                Label dateAj = new Label("Publié le: "+listX.get(i).getDatePublicaton());
                hbox2.getChildren().add(dateAj);
                HBox hbox3 = new HBox();
                if(listX.get(i).getClass()==AppelAuDon.class){
                    AppelAuDon appp = (AppelAuDon) listX.get(i);
                    Label besoin = new Label("On a besoin de "+appp.getNbrePlat()  );
                    File fileN = new File("./src/images/Icon/mekla.png");
                 ImageView nour = new ImageView(new Image(fileN.toURI().toString()));
                 besoin.getStyleClass().add("textLong");
                 
                 nour.setFitHeight(40);
                 nour.setFitWidth(40);
                 hbox3.getChildren().add(besoin);
                 hbox3.getChildren().add(nour);
                 Label nourriture = new Label(" pour nourrir "+appp.getNbrePlat()+" personnes...");
                 nourriture.getStyleClass().add("textLong");
                 hbox3.getChildren().add(nourriture);
                }
                
                
                
                
                
                if(listX.get(i).getAjoutePar()==idUserConnecte){
//                Button supprimerPublicationButton = new Button("Supprimer la publication") ; 
//                supprimerPublicationButton.setTranslateX(300);
//                hbox2.getChildren().add(supprimerPublicationButton);
                JFXComboBox<String> actions = new JFXComboBox<>();
                    
                    actions.setPromptText("-");
                //ajouter une action 
                    actions.setTranslateX(400);
                hbox2.getChildren().add(actions);
                actions.getItems().addAll("Supprimer");
                int id = listX.get(i).getId() ;
                Alert al = new  Alert(Alert.AlertType.INFORMATION);
         ButtonType Oui = new ButtonType("Oui");
         ButtonType Non = new ButtonType("Non");
         al.getButtonTypes().addAll(Oui,Non);
                actions.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if(actions.getValue().equals("Supprimer")){
         System.out.println("id " + id  );
          al.setContentText("Vous Voulez vraiment supprimer la publication");
         Optional<ButtonType> OuiNon = al.showAndWait();
         if(OuiNon.get() == Oui){
         AppelAuDon o = new AppelAuDon();
         o.setId(id);
         x.supprimer(o);
         affichage();
         }
         else
         System.out.println("good");
         
         }
          
                    }
                });
                
                
                
                }
                
                vbox1.getChildren().add(hbox2);
                vbox1.getChildren().add(hbox3);
                
                vbox1.setTranslateX(80);
                vbox1.setTranslateY(20);
                FlowPanePublications.getChildren().add(vbox1);
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    sepHoriz.setPrefWidth(500);
                    sepHoriz.setPrefHeight(35);
                    sepHoriz.setVisible(false);
                    FlowPanePublications.getChildren().add(sepHoriz);
         }
                }

        
        
    }

    @FXML
    private void transtitionPublicite(MouseEvent event) { 
  trans.play(); 
    }

    @FXML
    private void pubClickedOn(MouseEvent event) throws IOException, URISyntaxException {
        trans.stop();
        System.out.println("hi");
           PubliciteRegionService prs = new PubliciteRegionService();
        String city = prs.getIpAdressCity();
        String country = prs.getIpAdressCountry();
        
        System.out.println(city);
        System.out.println(country);
        prs.updateNbrClick(city, Integer.valueOf(idPub1.getText()));
        prs.updateNbrClickCountry(country, Integer.valueOf(idPub1.getText()));
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(new URI(descriptionPub1));
}
        
    }

    @FXML
    private void modifierPublication(ActionEvent event) {
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
