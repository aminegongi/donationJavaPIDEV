/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.RaisonSignale;

/**
 *
 * @author Hedi
 */
public class Signalement {
    int idDemande;
    int idUser;
    RaisonSignale raison;
    String description;

    public Signalement(int idDemande, int idUser, RaisonSignale raison, String description) {
        this.idDemande = idDemande;
        //this.idUser = idUser;
        this.idUser = UserTest.id;
        this.raison = raison;
        this.description = description;
    }

    public Signalement(int idUser, RaisonSignale raison, String description) {
        //this.idUser = idUser;
        this.idUser = UserTest.id;
        this.raison = raison;
        this.description = description;
    }

    public Signalement() {
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        //this.idUser = idUser;
        this.idUser = UserTest.id;
    }

    public RaisonSignale getRaison() {
        return raison;
    }

    public void setRaison(RaisonSignale raison) {
        this.raison = raison;
    }
    
    public String getRaisonAsString() {
        return this.raison.name();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Signalement{" + "idDemande=" + idDemande + ", idUser=" + idUser + ", raison=" + raison + ", description=" + description + '}';
    }
    
    
}
