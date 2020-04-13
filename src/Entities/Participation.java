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
public class Participation {
    private int idDemande;
    private int idUser;

    public Participation(int idDemande, int idUser) {
        this.idDemande = idDemande;
        //this.idUser = idUser;
        //pour tester
        this.idUser = UserTest.id;
    }

    public Participation() {
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
       // this.idUser = idUser;
        this.idUser = UserTest.id;
        
    }

    @Override
    public String toString() {
        return "Participation{" + "idDemande=" + idDemande + ", idUser=" + idUser + '}';
    }
    
    
}
