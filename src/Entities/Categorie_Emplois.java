package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author heshem
 */
public class Categorie_Emplois {
    private int Id_Categorie_Emplois;
    private String Titre ;
    private String Description ;

    public Categorie_Emplois(int Id_Categorie_Emplois, String Titre, String Description) {
        this.Id_Categorie_Emplois = Id_Categorie_Emplois;
        this.Titre = Titre;
        this.Description = Description;
    }

    public Categorie_Emplois(String Titre, String Description) {
        this.Titre = Titre;
        this.Description = Description;
    }

    public int getId_Categorie_Emplois() {
        return Id_Categorie_Emplois;
    }

    public void setId_Categorie_Emplois(int Id_Categorie_Emplois) {
        this.Id_Categorie_Emplois = Id_Categorie_Emplois;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Categorie_Emplois{" + "Id_Categorie_Emplois=" + Id_Categorie_Emplois + ", Titre=" + Titre + ", Description=" + Description + '}';
    }
    
}
