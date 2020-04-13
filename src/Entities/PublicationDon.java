/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ahmed Fourati
 */
public class PublicationDon {
    protected int id ; 
    protected  String titre ; 
    protected  String description ; 
    protected  String datePublicaton ; 
    protected  int nbreUp ;
    protected  int ajoutePar ;
    

    public PublicationDon(String titre, String description, String datePublicaton, int nbreUp,int ajoutePar) {
        this.titre = titre;
        this.description = description;
        this.datePublicaton = datePublicaton;
        this.nbreUp = nbreUp;
        this.ajoutePar = ajoutePar;
    }

    public PublicationDon() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublicaton() {
        return datePublicaton;
    }

    public void setDatePublicaton(String datePublicaton) {
        this.datePublicaton = datePublicaton;
    }

    public int getNbreUp() {
        return nbreUp;
    }

    public void setNbreUp(int nbreUp) {
        this.nbreUp = nbreUp;
    }

    public int getAjoutePar() {
        return ajoutePar;
    }

    public void setAjoutePar(int ajoutePar) {
        this.ajoutePar = ajoutePar;
    }
    

    @Override
    public String toString() {
//        return "PublicationDon{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", datePublicaton=" + datePublicaton + ", nbreUp=" + nbreUp + '}';
        return "date : "+ datePublicaton +"\n"; 
    }
    
    
    
    
    
}
