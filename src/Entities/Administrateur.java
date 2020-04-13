/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Amine Gongi
 */
public class Administrateur extends Utilisateur{
    private String username;

    public Administrateur() {
        super();
    }

    public Administrateur(String username, String mail, String mdp, String salt, String nom) {
        super(mail, mdp, salt, nom);
        this.username = username;
    }
    
    public Administrateur(Administrateur a) {
        super(a.getMail(), a.getMdp(), a.getSalt(), a.getNom());
        this.username = a.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.username);
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
        final Administrateur other = (Administrateur) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Administrateur{" + "username=" + username + '}';
    }
    
    
    
}
