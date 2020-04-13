/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Ahmed
 */
public class cagnotte {
    protected int id;
    protected String nom;
    protected int id_categorie;
    protected Date date_de_creation;
    protected Date date_de_debut;
    protected Date date_de_fin;
    protected float montant_demande;
    protected float montant_actuel;
    protected int id_proprietaire;
    protected int id_organisation;
    protected int etat;

    public cagnotte() {
    }
    
    public cagnotte(String nom, int id_categorie, Date date_de_debut, Date date_de_fin, float montant_demande, float montant_actuel, int id_proprietaire) {
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.id_proprietaire = id_proprietaire;
    }
    
    public cagnotte(String nom, int id_categorie, Date date_de_debut, Date date_de_fin, float montant_demande, float montant_actuel, int id_proprietaire, int id_organisation) {
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.id_proprietaire = id_proprietaire;
        this.id_organisation = id_organisation;
    }
    
    public cagnotte(int id, String nom, int id_categorie, Date date_de_creation, Date date_de_debut, Date date_de_fin, float montant_demande, float montant_actuel, int id_proprietaire, int id_organisation) {
        this.id = id;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.date_de_creation = date_de_creation;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.id_proprietaire = id_proprietaire;
        this.id_organisation = id_organisation;
    }
    
    public cagnotte(String nom, int id_categorie, Date date_de_creation, Date date_de_debut, Date date_de_fin, float montant_demande, float montant_actuel, int id_proprietaire, int id_organisation, int etat) {
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.date_de_creation = date_de_creation;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.id_proprietaire = id_proprietaire;
        this.id_organisation = id_organisation;
        this.etat = etat;
    }

    public cagnotte(int id, String nom, int id_categorie, Date date_de_creation, Date date_de_debut, Date date_de_fin, float montant_demande, float montant_actuel, int id_proprietaire, int id_organisation, int etat) {
        this.id = id;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.date_de_creation = date_de_creation;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.id_proprietaire = id_proprietaire;
        this.id_organisation = id_organisation;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    
    

    public Date getDate_de_creation() {
        return date_de_creation;
    }

    public void setDate_de_creation(Date date_de_creation) {
        this.date_de_creation = date_de_creation;
    }

    public Date getDate_de_debut() {
        return date_de_debut;
    }

    public void setDate_de_debut(Date date_de_debut) {
        this.date_de_debut = date_de_debut;
    }

    public Date getDate_de_fin() {
        return date_de_fin;
    }

    public void setDate_de_fin(Date date_de_fin) {
        this.date_de_fin = date_de_fin;
    }

    public float getMontant_demande() {
        return montant_demande;
    }

    public void setMontant_demande(float montant_demande) {
        this.montant_demande = montant_demande;
    }

    public float getMontant_actuel() {
        return montant_actuel;
    }

    public void setMontant_actuel(float montant_actuel) {
        this.montant_actuel = montant_actuel;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public int getId_organisation() {
        return id_organisation;
    }

    public void setId_organisation(int id_organisation) {
        this.id_organisation = id_organisation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.date_de_creation);
        hash = 29 * hash + Objects.hashCode(this.date_de_debut);
        hash = 29 * hash + Objects.hashCode(this.date_de_fin);
        hash = 29 * hash + Float.floatToIntBits(this.montant_demande);
        hash = 29 * hash + Float.floatToIntBits(this.montant_actuel);
        hash = 29 * hash + this.id_proprietaire;
        hash = 29 * hash + this.id_organisation;
        hash = 29 * hash + this.etat;
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
        final cagnotte other = (cagnotte) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.montant_demande) != Float.floatToIntBits(other.montant_demande)) {
            return false;
        }
        if (Float.floatToIntBits(this.montant_actuel) != Float.floatToIntBits(other.montant_actuel)) {
            return false;
        }
        if (this.id_proprietaire != other.id_proprietaire) {
            return false;
        }
        if (this.id_organisation != other.id_organisation) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.date_de_creation, other.date_de_creation)) {
            return false;
        }
        if (!Objects.equals(this.date_de_debut, other.date_de_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_de_fin, other.date_de_fin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cagnotte{" + "id=" + id + ", nom=" + nom + "id_categorie=" + id_categorie + ", date_de_creation=" + date_de_creation + ", date_de_debut=" + date_de_debut + ", date_de_fin=" + date_de_fin + ", montant_demande=" + montant_demande + ", montant_actuel=" + montant_actuel + ", id_proprietaire=" + id_proprietaire + ", id_organisation=" + id_organisation + ", etat=" + etat + '}';
    }
    

}
