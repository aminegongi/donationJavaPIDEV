/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.PublicationDon;

/**
 *
 * @author Ahmed Fourati
 */
public class AppelAuDon extends PublicationDon {
    private int  nbrePlat ;
    private int etat  ; 
    
    public AppelAuDon( String titre, String description, String datePublicaton, int nbreUp,int ajoutePar,int nbrePlat, int etat) {
        super(titre, description, datePublicaton, nbreUp, ajoutePar);
        this.nbrePlat = nbrePlat;
        this.etat = etat;
    }

    public AppelAuDon() {
    }

    public int getNbrePlat() {
        return nbrePlat;
    }

    public void setNbrePlat(int nbrePlat) {
        this.nbrePlat = nbrePlat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "AppelAuDon{"+ super.toString() + "nbrePlat=" + nbrePlat + ", etat=" + etat + '}';
    }
    
    
    
}
