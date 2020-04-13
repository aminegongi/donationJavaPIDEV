/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Objects;

/**
 *
 * @author Amine Gongi
 */
public class Adresse {
    private String pays;
    private String ville;

    public Adresse() {
    }

    public Adresse(String pays, String ville) {
        this.pays = pays;
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.pays);
        hash = 83 * hash + Objects.hashCode(this.ville);
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
        final Adresse other = (Adresse) obj;
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" + "pays=" + pays + ", ville=" + ville + '}';
    }
    
    
}
