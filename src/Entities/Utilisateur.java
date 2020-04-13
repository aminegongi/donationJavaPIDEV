/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;
import Utils.Adresse;
import java.sql.Timestamp;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Amine Gongi
 */
public class Utilisateur {

    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private byte enabled;
    private String salt;
    private String password;
    private java.sql.Date last_login;
    private String confirmation_token;
    private java.sql.Date password_requested_at;
    private String roles;
    private String nom;
    private String numTel;
    private String pays;
    private String ville;
    private String image;
    private int pointXP;
    private String prenom;
    private String genre;
    private java.sql.Date dateNaissance;
    private String pageFB;
    private String siteWeb;
    private String description;
    private double longitude;
    private double latitude;
    private int yesNews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.sql.Date getLast_login() {
        return last_login;
    }

    public void setLast_login(java.sql.Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public java.sql.Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(java.sql.Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPointXP() {
        return pointXP;
    }

    public void setPointXP(int pointXP) {
        this.pointXP = pointXP;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public java.sql.Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPageFB() {
        return pageFB;
    }

    public void setPageFB(String pageFB) {
        this.pageFB = pageFB;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getYesNews() {
        return yesNews;
    }

    public void setYesNews(int yesNews) {
        this.yesNews = yesNews;
    }
    
    
    
    public Utilisateur() {
    }

    
    public Utilisateur(int id, String username, String username_canonical, String email, String email_canonical, byte enabled, String salt, String password, java.sql.Date last_login, String confirmation_token, java.sql.Date password_requested_at, String roles, String nom, String numTel, String pays, String ville, String image, int pointXP, String prenom, String genre, java.sql.Date dateNaissance, String pageFB, String siteWeb, String description, double longitude, double latitude, int yesNews) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.numTel = numTel;
        this.pays = pays;
        this.ville = ville;
        this.image = image;
        this.pointXP = pointXP;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.pageFB = pageFB;
        this.siteWeb = siteWeb;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.yesNews = yesNews;
    }
    
    
}
