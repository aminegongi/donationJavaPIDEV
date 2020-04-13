package Entities;

import Entities.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author heshem
 */
public class Emplois {
    
    private int Id ;
    private String Titre ;
    private String Descreption ;
    private String Photo ;
    private double Salaire ;
    private String Emplacement  ;
    private String TypeDemploi;
    private String TypeContrat;
    private int IdCategorie;
    private Utilisateur User ;

    public Emplois(int Id, String Titre, String Descreption, String Photo, double Salaire, String Emplacement, String TypeDemploi, String TypeContrat, int IdCategorie, Utilisateur User) {
        this.Id = Id;
        this.Titre = Titre;
        this.Descreption = Descreption;
        this.Photo = Photo;
        this.Salaire = Salaire;
        this.Emplacement = Emplacement;
        this.TypeDemploi = TypeDemploi;
        this.TypeContrat = TypeContrat;
        this.IdCategorie = IdCategorie;
        this.User = User;
    }

    public Emplois(String Titre, String Descreption, String Photo, double Salaire, String Emplacement, String TypeDemploi, String TypeContrat, int IdCategorie, Utilisateur User) {
        this.Titre = Titre;
        this.Descreption = Descreption;
        this.Photo = Photo;
        this.Salaire = Salaire;
        this.Emplacement = Emplacement;
        this.TypeDemploi = TypeDemploi;
        this.TypeContrat = TypeContrat;
        this.IdCategorie = IdCategorie;
        this.User = User;
    }

    
    
    
    public Emplois(String Titre, String Descreption, String Photo, double Salaire, String Emplacement, String TypeDemploi, String TypeContrat,int id) {
        this.Titre = Titre;
        this.Descreption = Descreption;
        this.Photo = Photo;
        this.Salaire = Salaire;
        this.Emplacement = Emplacement;
        this.TypeDemploi = TypeDemploi;
        this.TypeContrat = TypeContrat;
        this.IdCategorie=id;        
    }

    public Emplois(int Id, String Titre, String Descreption, String Photo, double Salaire, String Emplacement, String TypeDemploi, String TypeContrat,int id) {
        this.Id = Id;
        this.Titre = Titre;
        this.Descreption = Descreption;
        this.Photo = Photo;
        this.Salaire = Salaire;
        this.Emplacement = Emplacement;
        this.TypeDemploi = TypeDemploi;
        this.TypeContrat = TypeContrat;
        this.IdCategorie=id;     
    }

    public int getId() {
        return Id;
    }

    public String getTitre() {
        return Titre;
    }

    public String getDescreption() {
        return Descreption;
    }

    public String getPhoto() {
        return Photo;
    }

    public double getSalaire() {
        return Salaire;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public String getTypeDemploi() {
        return TypeDemploi;
    }

    public String getTypeContrat() {
        return TypeContrat;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setDescreption(String Descreption) {
        this.Descreption = Descreption;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public void setSalaire(double Salaire) {
        this.Salaire = Salaire;
    }

    public void setEmplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    public void setTypeDemploi(String TypeDemploi) {
        this.TypeDemploi = TypeDemploi;
    }

    public void setTypeContrat(String TypeContrat) {
        this.TypeContrat = TypeContrat;
    }

    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }
  
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "Emplois{" + "Id=" + Id + ", Titre=" + Titre + ", Descreption=" + Descreption + ", Photo=" + Photo + ", Salaire=" + Salaire + ", Emplacement=" + Emplacement + ", TypeDemploi=" + TypeDemploi + ", TypeContrat=" + TypeContrat + ", IdCategorie=" + IdCategorie + '}';
    }

    
   
    
    
            
            
    
}
