/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class donation {
    protected int id;
    protected int id_cagnotte;
    protected int id_utilisateur;
    protected String nom;
    protected float montant;
    protected Date date;
    protected String methode;

    public donation() {
    }
    
    public donation(int id_cagnotte, int id_utilisateur, String nom, float montant, String methode) {
        this.id_cagnotte = id_cagnotte;
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.montant = montant;
        this.methode = methode;
    }
    
    public donation(int id_cagnotte, int id_utilisateur, float montant, String methode) {
        this.id_cagnotte = id_cagnotte;
        this.id_utilisateur = id_utilisateur;
        this.montant = montant;
        this.methode = methode;
    }

    public donation(int id, int id_cagnotte, int id_utilisateur, Date date, String methode) {
        this.id = id;
        this.id_cagnotte = id_cagnotte;
        this.id_utilisateur = id_utilisateur;
        this.date = date;
        this.methode = methode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cagnotte() {
        return id_cagnotte;
    }

    public void setId_cagnotte(int id_cagnotte) {
        this.id_cagnotte = id_cagnotte;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + this.id_cagnotte;
        hash = 53 * hash + this.id_utilisateur;
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.methode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final donation other = (donation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_cagnotte != other.id_cagnotte) {
            return false;
        }
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.methode, other.methode)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donation{" + "id=" + id + ", id_cagnotte=" + id_cagnotte + ", id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", date=" + date + ", methode=" + methode + '}';
    }
    
}
