/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.Etat;



/**
 *
 * @author Hedi
 */
 /*enum Etat{
    VISIBLE,    //publiée sur la plateforme
    VALIDE,    //un utilisateur a participé a cette demande
    SIGNALEE  //un utilisateur a signalé  cette demande
}*/
public class DemandeAide {
    private int id;
    private int idCategorie;
    private int idUser;
    private String titre;
    private String description;
    private Etat etat;
    //private int nbParticipants;
    private int nbReactions;
    //private int nbCommentaires;

    //tout les attributs
    public DemandeAide(int id, int idCategorie,int idUser, String titre, String description, Etat etat, int nbReactions) {
        this.id = id;
        this.idCategorie = idCategorie;
        //this.idUser = idUser;
        //pour tester 
        this.idUser = UserTest.id;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        //this.nbParticipants = nbParticipants;
        this.nbReactions = nbReactions;
        //this.nbCommentaires = nbCommentaires;
    }
 
    
    //Constructeur pour test idUser fromTableSql not UserTest
    //titre,idCategorie,idUser,id,description,etat,nbReaction
    //RQ : titre en premier lieu
        public DemandeAide(String titre, int idCategorie,int idUser, int id, String description, Etat etat, int nbReactions) {
        this.id = id;
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        //this.nbParticipants = nbParticipants;
        this.nbReactions = nbReactions;
        //this.nbCommentaires = nbCommentaires;
    }
    
    
    
    //tout les attributs sauf id
    public DemandeAide(int idCategorie, int idUser, String titre, String description, Etat etat, int nbReactions) {
        this.idCategorie = idCategorie;
        //this.idUser = idUser;
        this.idUser = UserTest.id;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        //this.nbParticipants = nbParticipants;
        this.nbReactions = nbReactions;
        //this.nbCommentaires = nbCommentaires;
    }
    
    
    //pour test iduser from table not userTest
    public DemandeAide(String titre, int idUser, int idCategorie, String description, Etat etat, int nbReactions) {
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        //this.nbParticipants = nbParticipants;
        this.nbReactions = nbReactions;
        //this.nbCommentaires = nbCommentaires;
    }
    
    
    
    //idCategorie,titre, description
    public DemandeAide(int idCategorie, String titre, String description) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.description = description;
        this.etat = Etat.VISIBLE;
        //this.nbParticipants = 0;
        this.nbReactions = 0;
        //this.nbCommentaires = 0;
    }

    public DemandeAide() {
    }


    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        //this.idUser = idUser;
        this.idUser = UserTest.id;
    }
    
    
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etat getEtat() {
        return this.etat;
    }
    
    public String getEtatAsString() {
        return this.etat.name();
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /*public int getNbParticipants() {
        return nbParticipants;
    }*/

    /*public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }*/

    public int getNbReactions() {
        return nbReactions;
    }

    public void setNbReactions(int nbReactions) {
        this.nbReactions = nbReactions;
    }

   /* public int getNbCommentaires() {
        return nbCommentaires;
    }

    public void setNbCommentaires(int nbCommentaires) {
        this.nbCommentaires = nbCommentaires;
    }*/

    @Override
    public String toString() {
        return "DemandeAide{" + "id=" + id + ", idCategorie=" +  
                idCategorie + ", idUser=" + idUser +
                ", titre=" + titre + ", description=" +
                description + ", etat=" + etat + 
                ", nbReactions=" + nbReactions +  "}\n";
    }


         
}
