/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;
import Utils.Adresse;
/**
 *
 * @author Amine Gongi
 */
public class Entreprise extends Utilisateur{
    
    private String matriculeFiscale;
    private String pageFb;
    private String siteWeb;
    private String description;
    private float latitude;
    private float longitude;
    
    public Entreprise() {
        super();
    }

    public Entreprise(String matriculeFiscale, String pageFb, String siteWeb, String description, float latitude, float longitude, String mail, String mdp, String salt, String numTel, Adresse adresse, String image, int pointXP,String nom) {
        super(mail, mdp, salt, numTel, adresse, image, pointXP, nom);
        this.matriculeFiscale = matriculeFiscale;
        this.pageFb = pageFb;
        this.siteWeb = siteWeb;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getPageFb() {
        return pageFb;
    }

    public void setPageFb(String pageFb) {
        this.pageFb = pageFb;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n"+super.toString()+"Entreprise{" + "matriculeFiscale=" + matriculeFiscale + ", pageFb=" + pageFb + ", siteWeb=" + siteWeb + ", description=" + description + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.matriculeFiscale);
        hash = 31 * hash + Objects.hashCode(this.pageFb);
        hash = 31 * hash + Objects.hashCode(this.siteWeb);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Float.floatToIntBits(this.latitude);
        hash = 31 * hash + Float.floatToIntBits(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entreprise other = (Entreprise) obj;
        if (!Objects.equals(this.matriculeFiscale, other.matriculeFiscale)) {
            return false;
        }
        if (!Objects.equals(this.pageFb, other.pageFb)) {
            return false;
        }
        if (!Objects.equals(this.siteWeb, other.siteWeb)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (Float.floatToIntBits(this.latitude) != Float.floatToIntBits(other.latitude)) {
            return false;
        }
        if (Float.floatToIntBits(this.longitude) != Float.floatToIntBits(other.longitude)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
    
    
}
