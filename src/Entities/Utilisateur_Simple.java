/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;
import Utils.Adresse;
import java.sql.Timestamp;

/**
 *
 * @author Amine Gongi
 */
public class Utilisateur_Simple extends Utilisateur {

    private String prenom;
    private String genre;
    private Date dateNaissance;

    public Utilisateur_Simple() {
        super();
    }

    public Utilisateur_Simple(String prenom, String genre, Date dateNaissance, String mail, String mdp, String salt, String numTel, Adresse adresse, String image, int pointXP,String nom) {
        super(mail, mdp, salt, numTel, adresse, image, pointXP, nom);

        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + Objects.hashCode(this.prenom);
        hash = 47 * hash + Objects.hashCode(this.genre);
        hash = 47 * hash + Objects.hashCode(this.dateNaissance);
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
        final Utilisateur_Simple other = (Utilisateur_Simple) obj;
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "Utilisateur_Simple{" + "prenom=" + prenom + ", genre=" + genre + ", dateNaissance=" + dateNaissance + '}';
    }

}
