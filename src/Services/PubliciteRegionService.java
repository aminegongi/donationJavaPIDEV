/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.GestionnaireHistoriqueConnexion.readJsonFromUrl;
import Utils.DataSource;
import Entities.PubliciteRegion;
import Entities.Publicité;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileOutputStream;
import com.itextpdf.text.Document ;
import com.itextpdf.text.* ;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import static Utils.jsonReader.readJsonFromUrl;
import java.io.File;

/**
 *
 * @author Ahmed Fourati
 */
public class PubliciteRegionService {
    Connection cn = DataSource.getInstance().getConnection();
    
    public List<PubliciteRegion> getInfo(Publicité p){
    String sql = "SELECT region.nom,publicite_region.nbClick from region INNER JOIN publicite_region on region.id=publicite_region.idRegion WHERE idPublicite='"+p.getId()+"' ;" ;
    List<PubliciteRegion> list = new ArrayList<>() ;
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                PubliciteRegion pr = new PubliciteRegion();
                pr.setIdPublicite(p.getId());
                pr.setNomRegion(rs.getString(1));
                pr.setNbClick(rs.getInt(2));
              list.add(pr);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteRegionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
    public void ecrirePdf(Publicité  p ,File SavePath){
    
        List<PubliciteRegion> list = new ArrayList<>() ;
        list= this.getInfo(p);
        
        Document doc = new Document();
  PdfWriter docWriter = null;
 
  DecimalFormat df = new DecimalFormat("0.00");
 
  try {
    
   //special font sizes
   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
 
   //file path
      System.out.println(SavePath.toString()+"\\rendu.pdf");
      System.out.println("\\");
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(SavePath.toString()+"\\rendu.pdf"));
    Image background = Image.getInstance("./publicitePdfBackground.png");
   //document header attributes
   doc.addAuthor("betterThanZero");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("MySampleCode.com");
   doc.addTitle("Report with Column Headings");
   doc.setPageSize(PageSize.LETTER);
   doc.setMargins(50,50,150 ,50) ; 
   
   
   
   //open document
   doc.open();
 float width = doc.getPageSize().getWidth();
        float height = doc.getPageSize().getHeight();
        docWriter.getDirectContentUnder()
                .addImage(background, width, 0, 0, height, 0, 0);
        
   //create a paragraph
   Paragraph paragraph = new Paragraph("Notre rapport de publicité "+"contient les informations sur le nombre des cliques "
           +"sur votre publicité mis sur notre plateforme officielle de DoNation "+ "Ces informations sont privées et appartienent seulement "+ "à votre entreprise . \n"+ 
           " DonNation vous remercie pour votre confiance . " );
   
    
   //specify column widths
   float[] columnWidths = {4f, 1.5f};
   //create PDF table with the given widths
   PdfPTable table = new PdfPTable(columnWidths);
   // set table width a percentage of the page width
   table.setWidthPercentage(90f);
 
   //insert column headings
   insertCell(table, "Region-Tunisie", Element.ALIGN_LEFT, 1, bfBold12);
   
   insertCell(table, "Nombre de click", Element.ALIGN_RIGHT, 1, bfBold12);
   table.setHeaderRows(1);
   
 
  
    
   //just some random data to fill 
   for(int x=0; x<list.size(); x++){
     
    insertCell(table, list.get(x).getNomRegion(), Element.ALIGN_LEFT, 1, bf12);
    
    String nbreClick = String.valueOf(list.get(x).getNbClick());
    insertCell(table, nbreClick, Element.ALIGN_RIGHT, 1, bf12);
     
   }
    
    table.setSpacingAfter(30.2f);
    paragraph.setSpacingAfter(30.2f);
    
   //add the PDF table to the paragraph
    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
      doc.add(table);
      doc.add(paragraph);
      doc.add(new Phrase("Rapport crée avec les derniers mis à jour:" +sdf.format(date).toString()));
         


 
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (doc != null){
    //close the document
    doc.close();
   }
   if (docWriter != null){
    //close the writer
    docWriter.close();
   }
  }
    }
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
   
 }
    
    public String getIpAdressCity(){
        JSONObject json = null;
        String ipAdresse = null;
                     try
        { 
            URL url_name = new URL("https://api.ipify.org"); // ou http://checkip.amazonaws.com/ //ou https://api.ipify.org
            BufferedReader bf = new BufferedReader(new InputStreamReader(url_name.openStream())); 
             
                    ipAdresse = bf.readLine().trim();
                    
                   // System.out.println("adresse ip" + ipAdresse );
                    GestionnaireHistoriqueConnexion ghc  = new GestionnaireHistoriqueConnexion();
                    json = ghc.readJsonFromUrl("https://ipapi.co/"+ipAdresse+"/json/");
        }
                     catch (Exception e) 
        { 
            return null;
          
        } 
        return json.get("city").toString() ; 
    }
    
    public String getIpAdressCountry(){
        JSONObject json = null;
        String ipAdresse = null;
                     try
        { 
            URL url_name = new URL("https://api.ipify.org"); // ou http://checkip.amazonaws.com/ //ou https://api.ipify.org
            BufferedReader bf = new BufferedReader(new InputStreamReader(url_name.openStream())); 
             
                    ipAdresse = bf.readLine().trim();
                    
                   // System.out.println("adresse ip" + ipAdresse );
                    GestionnaireHistoriqueConnexion ghc  = new GestionnaireHistoriqueConnexion();
                    json = ghc.readJsonFromUrl("https://ipapi.co/"+ipAdresse+"/json/");
        }
                     catch (Exception e) 
        { 
            return null;
          
        } 
        return json.get("country_code").toString() ; 
    }
    
    public void updateNbrClick(String city , int idPublicité) {
        System.out.println("city:"+city);
        System.out.println("id pub:" +idPublicité);
        String qSql = "UPDATE `publicite_region` SET `nbClick`=`nbClick`+1 WHERE ((idPublicite='"+idPublicité+"') AND (idRegion=(SELECT id FROM region WHERE nom='"+city+"') ))";
       
        try {
          Statement st = cn.createStatement();
            st.executeUpdate(qSql);
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteRegionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    public void updateNbrClickCountry(String country , int idPublicité) {
        String qSql = "UPDATE `publicite_country` SET `nbrClick`=`nbrClick`+1 WHERE ((idPublicite='"+idPublicité+"') AND (idCountry=(SELECT id FROM apps_countries WHERE country_code='"+country+"') ))";

        try {
           Statement st = cn.createStatement();
            st.executeUpdate(qSql);
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteRegionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
}
