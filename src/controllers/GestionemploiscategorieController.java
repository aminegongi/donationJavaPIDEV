/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Categorie_Emplois;
import Entities.*;
import Services.*;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.GatheringByteChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author heshem
 */
public class GestionemploiscategorieController implements Initializable {

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
    private FlowPane flowPaneUsers;
    @FXML
    private ImageView btnBack;
    @FXML
    private TextField tfLibelle;
    @FXML
    private TextArea tfDesc;
    @FXML
    private Button btnAjouterCat;
    @FXML
    private FlowPane flowPaneGestionCategorie;
    @FXML
    private PieChart chartemplois;
    @FXML
    private PieChart chart;
    @FXML
    private PieChart chartsalaire;
    @FXML
    private Pane paneforbox;
    @FXML
    private PieChart chartemplacement;
    @FXML
    private JFXButton idgestioncategorie;
    @FXML
    private JFXButton idstatistique;
     @FXML
    private Pane paneclear;
    @FXML
    private JFXButton idaffichercarte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {     
          flowPaneGestionCategorie.setVisible(true);
                    paneforbox.setVisible(false);
                    affichageUS();
      } catch (SQLException ex) {
          Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
      }
        idgestioncategorie.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                     
                    flowPaneGestionCategorie.setVisible(true);
                    paneforbox.setVisible(false);
                    affichageUS();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
        
        idstatistique.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                                    

                flowPaneGestionCategorie.setVisible(false);
                    paneforbox.setVisible(true);
                    affichagechart();
      affichagecharttypecontrat();
      affichagechartsalaire();
//      
  affichageemplacement();
                
            }
        });
        
        
        
   idaffichercarte.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent event) {
              
              
              try {
                  try { 
                      EmploisService es = new EmploisService();
                      Map<String,Integer> map = new HashMap<>();
                      map = es.nbCompteemplacement();
                      for(Map.Entry<String,Integer> x:map.entrySet()){
                          System.out.println(x.getKey() + "::" +x.getValue());
                      }
                          String chaine ="http://localhost/maps/aa.php?";
                          for(Map.Entry<String,Integer> x:map.entrySet()){
//                          System.out.println(x.getKey() + "::" +x.getValue());
                              chaine+=x.getKey()+"="+x.getValue()+"&";
                      }
                       chaine = chaine.substring(0, chaine.length() - 1);
                       System.out.println(chaine);
                      
                      Desktop.getDesktop().browse(new URI(chaine));
                  } catch (IOException ex) {
                      Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              } catch (URISyntaxException ex) {
                  Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
              }

          }
      }) ;

        
        
        
//        
//       affichagechart();
//      affichagecharttypecontrat();
//      affichagechartsalaire();
//      
//  affichageemplacement();
    }
        
    public  void affichagebtn() {

    }
    
    public void affichagecarte() throws IOException
    {   
        
              if(Desktop.isDesktopSupported()) //ou alors tu cast un exception
                             {
                  try {
                      Desktop.getDesktop().browse(new URI("http://localhost/maps/aa.php?Ariana=77&Tunis=77&Beja=8&BenArous=4&Bizerte=77&Gabes=54&Gafsa=44&Jendouba=44&Kairouan=77&Kasserine=44&Kebili=85&Kef=74&Mahdia=75&Manouba=44&Medenine=44&Monastir=77&Nabeul=44&Sfax=44&SidiBouzid=45&Siliana=45&Sousse=45&Tataouine=45&Tozeur=55&Zaghouan=77"));
                  } catch (URISyntaxException ex) {
                      Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
                  }

                              }       
        

    }
    
//    public void afficherdernier()
//    {  
//        
//        
//        
//         EmploisService gEmp = new EmploisService();
//        ArrayList<Emplois> listEmp = null;
//        try { 
//            listEmp = (ArrayList<Emplois>) gEmp.readidmax(0);
//            
//
//        } catch (SQLException ex) {
//            Logger.getLogger(GestionEmploiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//             for (int i = 0; i < listEmp.size(); i++) {
//            HBox HboxEmp = new HBox();
//            HboxEmp.setSpacing(3);
//            HboxEmp.setStyle("-fx-background-color : #dddddd;");
//            HboxEmp.setPrefHeight(200);
//            HboxEmp.setPrefWidth(525);
//            HboxEmp.setAlignment(Pos.CENTER);
//
//            Rectangle r = new Rectangle(185, 185);
//
//            try {
//                String x=listEmp.get(i).getPhoto();
//                r.setFill(new ImagePattern(new Image(new FileInputStream("src/imagesemplois/"+x))));
//            } catch (FileNotFoundException ex) {
//
//            }
//            HboxEmp.getChildren().add(r);
//
//            VBox VboxEmp = new VBox();
//            VboxEmp.setAlignment(Pos.CENTER_LEFT);
//            VboxEmp.setPrefHeight(160);
//            VboxEmp.setPrefWidth(380);
//
//            Label titre = new Label("Titre : " + listEmp.get(i).getTitre());
//            Label emplacement = new Label("Emplacement : " + listEmp.get(i).getEmplacement());
//            Label salaire = new Label("Salaire : " + listEmp.get(i).getSalaire());
//            Label typedemploi = new Label("Type d'Emploi : " + listEmp.get(i).getTypeDemploi());
//            Label typecontrat = new Label("Type De Contrat : " + listEmp.get(i).getTypeContrat());
//            Label description = new Label("Description : " + listEmp.get(i).getDescreption());
//            VboxEmp.getChildren().add(titre);
//            VboxEmp.getChildren().add(emplacement);
//            VboxEmp.getChildren().add(salaire);
//            VboxEmp.getChildren().add(typedemploi);
//            VboxEmp.getChildren().add(typecontrat);
//            VboxEmp.getChildren().add(description);
//          
//          
//            HboxEmp.getChildren().add(VboxEmp);
//
//            paneforbox.getChildren().add(HboxEmp);  
//             }
//            }
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void affichageUS() throws SQLException {
        flowPaneUsers.getChildren().clear();
        Categorie_EmploisService gUs = new Categorie_EmploisService();

        ArrayList<Categorie_Emplois> listUs = (ArrayList<Categorie_Emplois>) gUs.readAllCategorieEmplois();
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();

        for (int i = 0; i < listUs.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxUser = new VBox();
            VBoxUser.setSpacing(5);
            VBoxUser.setStyle("-fx-background-color : #dddddd;");
            VBoxUser.setAlignment(Pos.CENTER);
            VBoxUser.setPrefHeight(262);
            VBoxUser.setPrefWidth(170);

          

            Label nom = new Label("Titre : " + listUs.get(i).getTitre());
            Label prenom = new Label("Description: " + listUs.get(i).getDescription());
           
            int id = listUs.get(i).getId_Categorie_Emplois();
            
            
            Button btnSupp = new Button("supp");
            btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        gUs.deleteCategorieEmplois(id);
                        affichageUS();
                        System.out.println(id+"aazazaza");
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            VBoxUser.getChildren().add(btnSupp);

            
            VBoxUser.getChildren().add(nom);
            VBoxUser.getChildren().add(prenom);


            vbx.add(VBoxUser);
            flowPaneUsers.getChildren().add(vbx.get(i));

            flowPaneUsers.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0");
            } else {
                if (((i + 1) % 3) == 0) {
                    Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                    sepHoriz.setPrefWidth(1120);
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


    
    
      private void affichagechart(){
        
                        chartemplois.setTitle("Statstique selon type d'emploi");

    
        EmploisService gu = new EmploisService();
        HashMap<String,Integer> CompteRole =gu.nbComptetypeemploi();

        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Offre ", CompteRole.get("Offre")),
                    new PieChart.Data("Demande", CompteRole.get("Demande")));
        
        int totUser = CompteRole.get("Offre")+CompteRole.get("Demande");
        chartemplois.setData(pieChartData);
        chartemplois.getData().forEach(data -> {
            String percentage = String.format("%.1f%%", (data.getPieValue() / totUser));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });     
        
    }     
    
    
    
    
    private void affichageemplacement(){
        
               chartemplacement.setTitle("Statstique selon region");

        EmploisService gu = new EmploisService();
        HashMap<String,Integer> CompteRole =gu.nbCompteemplacement();
         System.out.println(CompteRole);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totUser =0;
        for(Map.Entry<String,Integer> hh : CompteRole.entrySet()){
            pieChartData.add(new PieChart.Data(hh.getKey(), hh.getValue()));
             totUser += hh.getValue();
        }
        
        chartemplacement.setData(pieChartData);
        
//        int totUser = CompteRole.get("Tunis")+CompteRole.get("Ariana")+CompteRole.get("Zaghouan")+CompteRole.get("Tozeur")+CompteRole.get("Tataouine")+CompteRole.get("Sousse")+CompteRole.get("Siliana")+CompteRole.get("SidiBouzid")+CompteRole.get("Sfax")+CompteRole.get("Nabeul")+CompteRole.get("Monastir")+CompteRole.get("Medenine")+CompteRole.get("Manouba")+CompteRole.get("Mahdia")+CompteRole.get("Kef")+CompteRole.get("Kebili")+CompteRole.get("Kasserine")+CompteRole.get("Kairouan")+CompteRole.get("Gafsa")+CompteRole.get("Jendouba")+CompteRole.get("Beja")+CompteRole.get("BenArous")+CompteRole.get("Bizerte")+CompteRole.get("Gabes");
//        chartemplacement.setData(pieChartData);
//        chartemplacement.getData().forEach(data -> {
//            String percentage = String.format("%.1f%%", (data.getPieValue() / totUser));
//            Tooltip toolTip = new Tooltip(percentage);
//            Tooltip.install(data.getNode(), toolTip);
//        }); 
                
        
    }   
    
    
    
    EmploisService gu = new EmploisService();
        HashMap<String,Integer> CompteRole =gu.nbComptetypecontrat();
       
       
    
       private void affichagecharttypecontrat(){
        
    
           
           chart.setTitle("Statstique selon type de contrat");
           
           
         EmploisService gu = new EmploisService();
        HashMap<String,Integer> CompteRole =gu.nbComptetypecontrat();
         System.out.println(CompteRole);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totUser =0;
        for(Map.Entry<String,Integer> hh : CompteRole.entrySet()){
            pieChartData.add(new PieChart.Data(hh.getKey(), hh.getValue()));
             totUser += hh.getValue();
        }
        
        chart.setData(pieChartData);  
           
           
           
           
           
           
           
           
//        EmploisService gu = new EmploisService();
//        HashMap<String,Integer> CompteRole =gu.nbComptetypecontrat();
//        System.out.println("zzzzzzzzzzzzzzzzzz");
//       System.out.println(CompteRole );
//       
//        ObservableList<PieChart.Data> pieChartData = 
//                FXCollections.observableArrayList(
//                    new PieChart.Data("Contrat Duree Indeterminee", CompteRole.get("ContratDureeIndeterminee")),
//                    new PieChart.Data("Contrat durée determinee", CompteRole.get("Contratdureedeterminee")),
//                    new PieChart.Data("Contrat Travail Temporaire", CompteRole.get("ContratTravailTemporaire")) ,  
//                    new PieChart.Data("Contrat Apprentissage", CompteRole.get("ContratApprentissage")),
//                    new PieChart.Data("Contrat Professionnalisation", CompteRole.get("ContratProfessionnalisation")),
//                    new PieChart.Data("Contrat UniqueInsertion", CompteRole.get("ContratUniqueInsertion"))        
//                );
//        
//        int totUser = CompteRole.get("ContratDureeIndeterminee")+CompteRole.get("Contratdureedeterminee")+CompteRole.get("ContratTravailTemporaire")+CompteRole.get("ContratApprentissage")+CompteRole.get("ContratProfessionnalisation")+CompteRole.get("ContratUniqueInsertion");
//        chart.setData(pieChartData);
//        chart.getData().forEach(data -> {
//            String percentage = String.format("%.1f%%", (data.getPieValue() / totUser));
//            Tooltip toolTip = new Tooltip(percentage);
//            Tooltip.install(data.getNode(), toolTip);
//        });     
//        
    }
        
       
       
       private void affichagechartsalaire()
       {
         EmploisService gu = new EmploisService();
        HashMap<String,Integer> CompteRole =gu.nbComptesalaire();
        chartsalaire.setTitle("Statstique selon salaire");
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("0-500 ", CompteRole.get("0-500")),
                    new PieChart.Data("501-1000", CompteRole.get("501-1000")),
                    new PieChart.Data("1001-2000", CompteRole.get("1001-2000")),
                    new PieChart.Data("2001-50000", CompteRole.get("2001-50000")));
        int totUser = CompteRole.get("0-500")+CompteRole.get("501-1000")+CompteRole.get("1001-2000")+CompteRole.get("2001-50000");
        chartsalaire.setData(pieChartData);
        chartsalaire.getData().forEach(data -> {
            String percentage = String.format("%.1f%%", (data.getPieValue() / totUser));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });    
//       
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

    @FXML
    private void ajoutCat(ActionEvent event) {
        String l = tfLibelle.getText();
        String d = tfDesc.getText();
        if(l.length() >2 && d.length()>2){
            Categorie_Emplois ce = new Categorie_Emplois(l, d);
            Categorie_EmploisService gEs = new Categorie_EmploisService();
            try {
                gEs.ajouterCategorieEmplois(ce);
                affichageUS();
            } catch (SQLException ex) {
                Logger.getLogger(GestionemploiscategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR, "Erreur :vérifier vos champs!", ButtonType.OK);
            al.showAndWait();
        }
    }
}
