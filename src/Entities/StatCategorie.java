/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Hedi
 */
public class StatCategorie {
    int id;
    String nom;
    int nbrDmnd;

    public StatCategorie() {
    }

    public StatCategorie(int id, String nom, int nbrDmnd) {
        this.id = id;
        this.nom = nom;
        this.nbrDmnd = nbrDmnd;
    }

    public StatCategorie(String nom, int nbrDmnd) {
        this.nom = nom;
        this.nbrDmnd = nbrDmnd;
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

    public int getNbrDmnd() {
        return nbrDmnd;
    }

    public void setNbrDmnd(int nbrDmnd) {
        this.nbrDmnd = nbrDmnd;
    }

    @Override
    public String toString() {
        //return "StatCategorie{" + "nom=" + nom + ", nbrDmnd=" + nbrDmnd + '}';
        return  nom + " : " + nbrDmnd ;
    }
    
    
    
    
}
