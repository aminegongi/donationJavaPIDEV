/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Amine Gongi
 */
public class Newsletter {

    private int id;
    private String libelle;
    private String objetMail;
    private String coprsMail;
    private Date dateEnvoi;
    private Date dateAjout;

    public Newsletter() {
    }

    public Newsletter(int id, String libelle, String objetMail, String coprsMail, Date dateEnvoi, Date dateAjout) {
        this.id = id;
        this.libelle = libelle;
        this.objetMail = objetMail;
        this.coprsMail = coprsMail;
        this.dateEnvoi = dateEnvoi;
        this.dateAjout = dateAjout;
    }

    public Newsletter(String libelle, String objetMail, String coprsMail, Date dateEnvoi, Date dateAjout) {
        this.libelle = libelle;
        this.objetMail = objetMail;
        this.coprsMail = coprsMail;
        this.dateEnvoi = dateEnvoi;
        this.dateAjout = dateAjout;
    }

    public Newsletter(String libelle, String objetMail, String coprsMail) {
        this.libelle = libelle;
        this.objetMail = objetMail;
        this.coprsMail = coprsMail;
    }
    
    
    
    public Newsletter(String libelle, String objetMail, String coprsMail, Date dateAjout) {
        this.libelle = libelle;
        this.objetMail = objetMail;
        this.coprsMail = coprsMail;
        this.dateAjout = dateAjout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getObjetMail() {
        return objetMail;
    }

    public void setObjetMail(String objetMail) {
        this.objetMail = objetMail;
    }

    public String getCoprsMail() {
        return coprsMail;
    }

    public void setCoprsMail(String coprsMail) {
        this.coprsMail = coprsMail;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.libelle);
        hash = 59 * hash + Objects.hashCode(this.objetMail);
        hash = 59 * hash + Objects.hashCode(this.coprsMail);
        hash = 59 * hash + Objects.hashCode(this.dateEnvoi);
        hash = 59 * hash + Objects.hashCode(this.dateAjout);
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
        final Newsletter other = (Newsletter) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.objetMail, other.objetMail)) {
            return false;
        }
        if (!Objects.equals(this.coprsMail, other.coprsMail)) {
            return false;
        }
        if (!Objects.equals(this.dateEnvoi, other.dateEnvoi)) {
            return false;
        }
        if (!Objects.equals(this.dateAjout, other.dateAjout)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Newsletter{" + "id=" + id + ", libelle=" + libelle + ", objetMail=" + objetMail + ", coprsMail=" + coprsMail + ", dateEnvoi=" + dateEnvoi + ", dateAjout=" + dateAjout + '}';
    }
    
    
    

}
