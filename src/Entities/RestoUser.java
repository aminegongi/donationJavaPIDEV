/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author safratix
 */
public class RestoUser {
    private String nomResto;
    private String pays;
    private String ville;
    private String numTel;
    private String siteWeb;
    private String pageFb;
    private String coord;
    private String image;
    
    public RestoUser(String nomResto, String pays, String ville, String numTel, String siteWeb, String pageFb, String coord, String image) {
        this.nomResto = nomResto;
        this.pays = pays;
        this.ville = ville;
        this.numTel = numTel;
        this.siteWeb = siteWeb;
        this.pageFb = pageFb;
        this.coord = coord;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomResto() {
        return nomResto;
    }

    public void setNomResto(String nomResto) {
        this.nomResto = nomResto;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getPageFb() {
        return pageFb;
    }

    public void setPageFb(String pageFb) {
        this.pageFb = pageFb;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }
    
     
        
}
