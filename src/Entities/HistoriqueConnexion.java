/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Objects;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;

/**
 *
 * @author Amine Gongi
 */
public class HistoriqueConnexion {
    private int id;
    //private int idUtilisateur;
    private Utilisateur user;
    private String ipAdresse;
    private Timestamp dateCnx;

    public HistoriqueConnexion() {
    }
    
    
    public HistoriqueConnexion(Utilisateur us) {
        this.user = us;
        this.dateCnx=null;
        
        /* Adresse IP machine
        try {
            this.ipAdresse =InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            this.ipAdresse = "IP Prob" ;
        }
        */
        
        // Adresse IP Publique
        try
        { 
            URL url_name = new URL("http://checkip.amazonaws.com/"); // wala  http://bot.whatismyipaddress.com
            BufferedReader bf = new BufferedReader(new InputStreamReader(url_name.openStream())); 
            this.ipAdresse = bf.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            this.ipAdresse = "IP Prob" ;
        } 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getIpAdresse() {
        return ipAdresse;
    }

    public void setIpAdresse(String ipAdresse) {
        this.ipAdresse = ipAdresse;
    }

    public Timestamp getDateCnx() {
        return dateCnx;
    }

    public void setDateCnx(Timestamp dateCnx) {
        this.dateCnx = dateCnx;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.user);
        hash = 73 * hash + Objects.hashCode(this.ipAdresse);
        hash = 73 * hash + Objects.hashCode(this.dateCnx);
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
        final HistoriqueConnexion other = (HistoriqueConnexion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.ipAdresse, other.ipAdresse)) {
            return false;
        }
        if (!Objects.equals(this.dateCnx, other.dateCnx)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoriqueConnexion{" + "idHc=" + id + ", user= nom: " + user.getNom() +" , mail:"+user.getEmail() + ", ipAdresse=" + ipAdresse + ", dateCnx=" + dateCnx + '}';
    }

    

    


    
    
    
}
