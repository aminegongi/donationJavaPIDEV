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
public class Organisation extends Utilisateur {

    private String numVisa;
    private String pageFb;
    private String siteWeb;
    private String description;
    private float latitude;
    private float longitude;

    public Organisation() {
        super();
    }

    public Organisation(String numVisa, String pageFb, String siteWeb, String description, float latitude, float longitude, String mail, String mdp, String salt, String numTel, Adresse adresse, String image, int pointXP, String nom) {
        super(mail, mdp, salt, numTel, adresse, image, pointXP, nom);

        this.numVisa = numVisa;
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

    public String getNumVisa() {
        return numVisa;
    }

    public void setNumVisa(String numVisa) {
        this.numVisa = numVisa;
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
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.numVisa);
        hash = 53 * hash + Objects.hashCode(this.pageFb);
        hash = 53 * hash + Objects.hashCode(this.siteWeb);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Float.floatToIntBits(this.latitude);
        hash = 53 * hash + Float.floatToIntBits(this.longitude);
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
        final Organisation other = (Organisation) obj;
        if (!Objects.equals(this.numVisa, other.numVisa)) {
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

    @Override
    public String toString() {
        return "\n" + super.toString() + "Organisation{" + "numVisa=" + numVisa + ", pageFb=" + pageFb + ", siteWeb=" + siteWeb + ", description=" + description + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
