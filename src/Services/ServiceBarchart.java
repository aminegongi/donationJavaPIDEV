/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.StatCategorie;
import java.sql.SQLException;
import java.util.List;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 *
 * @author Hedi
 */
public class ServiceBarchart {
    
        public static VBox createChart(){

        //Defining string to label XAxis   
    String Euro = "Euro";  
    String Pound = "British Pound";  
    String A_Dollar = "Austrelian Dollar";  
    String frenc= "Swis Franc";  
    
    //Configuring category and NumberAxis   
    CategoryAxis xaxis= new CategoryAxis();  
    NumberAxis yaxis = new NumberAxis(0.1,2,0.1);  
    xaxis.setLabel("Currency");  
    yaxis.setLabel("Dollar price");  
    
    
    //Configuring BarChart   
    BarChart<String,Float> bar = new BarChart(xaxis,yaxis);  
    bar.setTitle("Dollar Conversion chart");  
      
    //Configuring Series for XY chart   
    XYChart.Series<String,Float> series = new XYChart.Series<>();  
    series.getData().add(new XYChart.Data(Euro,0.83));  
    series.getData().add(new XYChart.Data(Pound,0.73));  
    series.getData().add(new XYChart.Data(frenc,1.00));  
    series.getData().add(new XYChart.Data(A_Dollar,1.32));  
      
    //Adding series to the barchart   
    bar.getData().add(series);  
    
    VBox vboxBar = new VBox();
    vboxBar.getChildren().add(bar);
    
    return vboxBar;
    
    
    }
        
    public static VBox makingChart() throws SQLException{
        ServiceStatCategorie serStat = new ServiceStatCategorie();
        ServiceCategorie serCat = new ServiceCategorie();
        List<StatCategorie> listStat = serStat.nbrDmndAllCat(serCat.readAll());
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        series.setName("Categorie");
        
            for ( StatCategorie statCat : listStat ){
        
            
            String nom = statCat.getNom();
            int nbrDmnd = statCat.getNbrDmnd();   
            series.getData().add(new XYChart.Data(nom,nbrDmnd));

        }
           //Configuring category and NumberAxis   
    CategoryAxis xaxis= new CategoryAxis();  
    NumberAxis yaxis = new NumberAxis(0,31,1);  
    xaxis.setLabel("Categorie");  
    yaxis.setLabel("Nombre demandes");  
    
    
    //Configuring BarChart   
    BarChart<String,Float> bar = new BarChart(xaxis,yaxis);  
     bar.setLegendSide(Side.RIGHT);
     bar.setLegendVisible(false);
        //Adding series to the barchart   
    bar.getData().add(series);  
    
    VBox vboxBar = new VBox();
    vboxBar.getChildren().add(bar);
    
    return vboxBar;
    }
}
