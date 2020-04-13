/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Utilisateur;
import Services.GestionnaireHistoriqueConnexion;
import Services.GestionnaireUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import com.github.sarxos.webcam.Webcam;
import Services.WebCamService;
import Services.WebCamView;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Amine Gongi
 */
public class ProfilUserController implements Initializable {
    
    private WebCamService service ;
    
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
    private boolean flag ;
    @FXML
    private Circle circleImage;
    @FXML
    private JFXButton btnChoisir;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private Hyperlink HyHistoCnx;
    
    List<String> listFichier;
    
    String role = HomeFXMLController.isUserRole;
    @FXML
    private JFXButton webcamTake;
    @FXML
    private Pane webcamPane;
    @FXML
    private ImageView imgPrevWeb;
    @FXML
    private Pane paneNoir;
    @FXML
    private Circle sideCircle;
    @FXML
    private Label sideName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneNoir.setVisible(false);
        webcamPane.setVisible(false);
        listFichier= new ArrayList<>();
        listFichier.add("*.png");
        listFichier.add("*.jpg");
        System.out.println(role);
        flag = false;
        /*
        if(role.equals("us"))
        {
            System.out.println(HomeFXMLController.u);
            nom.setText(HomeFXMLController.u.getNom());
            prenom.setText(HomeFXMLController.u.getPrenom());
            File file = new File("./src/images/"+HomeFXMLController.u.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
            sideName.setText(HomeFXMLController.u.getNom()+" "+HomeFXMLController.u.getPrenom());
            sideCircle.setFill(new ImagePattern(i));
        }
        else if(role.equals("org"))
        {
            System.out.println(HomeFXMLController.o);
            nom.setText(HomeFXMLController.o.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.o.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
            sideName.setText(HomeFXMLController.o.getNom());
            sideCircle.setFill(new ImagePattern(i));
        }
        else if(role.equals("ent"))
        {
            System.out.println(HomeFXMLController.e);
            nom.setText(HomeFXMLController.e.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.e.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
            sideName.setText(HomeFXMLController.e.getNom());
            sideCircle.setFill(new ImagePattern(i));
        }
        else if(role.equals("resto"))
        {
            System.out.println(HomeFXMLController.r);
            nom.setText(HomeFXMLController.r.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.r.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
            sideName.setText(HomeFXMLController.r.getNom());
            sideCircle.setFill(new ImagePattern(i));
        }*/

    }    

     @FXML
    private void OpenSidebar(MouseEvent event) {
        if(!flag){
            flag = true;
            stackSide.setPrefWidth(200);
            System.out.println("bye");
            paneSide.setVisible(true);
        }
    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
        if(flag){
            flag = false;
            paneSide.setVisible(false);
            System.out.println("Hye");
            stackSide.setPrefWidth(20);
        }
    }

    @FXML
    private void uploadPhoto(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser() ; 
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if(f !=null){
            System.out.println("Image selectionnée"+f.getAbsolutePath());
            InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(f.getAbsolutePath()));
//             System.out.println("Working Directory = " +
//              System.getProperty("user.dir"));
            System.out.println("nomfichier"+f.getName());
            os = new FileOutputStream(new File("./src/images/"+f.getName()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            System.out.println("copyyyyyy");
            
             
        } finally {
            is.close();
            os.close();
        }
           
        File file = new File("./src/images/"+f.getName());
//            System.out.println(file.toURI().toString());
        //imagePublicitePreview.setImage(new Image(file.toURI().toString()));
        //imagePublicite=f.getName() ;
        Image i= new Image(file.toURI().toString());
        circleImage.setFill(new ImagePattern(i));
        GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
        gu.setImage(f.getName(), UiLoginController.uh.getId());
        }
        else if (f ==null){
            System.out.println("Erreur chargement de l'image");
        }
    }

    @FXML
    private void PrintHistoCnx(ActionEvent event) {
        GestionnaireHistoriqueConnexion ghc = new GestionnaireHistoriqueConnexion();
        ghc.RapportHCbyUser(UiLoginController.uh);
    }

    @FXML
    private void openWebcam(ActionEvent event) {
        /*Webcam cam = Webcam.getWebcams().get(0);
        service = new WebCamService(cam);
        WebCamView view = new WebCamView(service);
        webcamPane.setVisible(true);
        webcamPane.getChildren().add(view.getView());*/
        Webcam wb =Webcam.getWebcams().get(1);
        paneNoir.setVisible(true);
        webcamPane.setVisible(true);
        wb.open();
        String name = UUID.randomUUID().toString().substring(1, 8)+".jpg";
        File f= new File("src/images/"+name);

        try {
            ImageIO.write(wb.getImage(),"JPG" ,f);
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Ok");

        Image i =new Image(f.toURI().toString());

        imgPrevWeb.setImage(i);

        String chemin=f.getAbsolutePath();
        System.out.println(chemin);
        
        circleImage.setFill(new ImagePattern(i));
        GestionnaireUtilisateur gu = new GestionnaireUtilisateur();
        gu.setImage(f.getName(), UiLoginController.uh.getId());
        sideCircle.setFill(new ImagePattern(i));
        paneNoir.setVisible(false);
        webcamPane.setVisible(false);
        
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

    @FXML
    private void saveModif(MouseEvent event) {
        /*
        if(role.equals("us"))
        {
            GestionnaireUtilisateur_Simple gus = new GestionnaireUtilisateur_Simple();
            System.out.println(HomeFXMLController.u);
            nom.setText(HomeFXMLController.u.getNom());
            prenom.setText(HomeFXMLController.u.getPrenom());
            File file = new File("./src/images/"+HomeFXMLController.u.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
        }
        else if(role.equals("org"))
        {
            System.out.println(HomeFXMLController.o);
            nom.setText(HomeFXMLController.o.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.o.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
        }
        else if(role.equals("ent"))
        {
            System.out.println(HomeFXMLController.e);
            nom.setText(HomeFXMLController.e.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.e.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
        }
        else if(role.equals("resto"))
        {
            System.out.println(HomeFXMLController.r);
            nom.setText(HomeFXMLController.r.getNom());
            prenom.setVisible(false);
            File file = new File("./src/images/"+HomeFXMLController.r.getImage());
            Image i= new Image(file.toURI().toString());
            circleImage.setFill(new ImagePattern(i));
        }*/
    }
    
}
