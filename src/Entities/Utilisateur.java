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
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String nom;
    private String numTel;
    
    //private String pays;
    //private String ville;
    protected Adresse adresse;
    
    private String image;
    private int pointXP;
    private String prenom;
    private String genre;
    private Date dateNaissance;
    private String pageFB;
    private String siteWeb;
    private String description;
    private float longitude;
    private float latitude;
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
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

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getYesNews() {
        return yesNews;
    }

    public void setYesNews(int yesNews) {
        this.yesNews = yesNews;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    
    public Utilisateur() {
    }

    public Utilisateur(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles, String nom, String numTel, Adresse adresse, String image, int pointXP, String prenom, String genre, Date dateNaissance, String pageFB, String siteWeb, String description, float longitude, float latitude, int yesNews) {
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
        this.adresse = adresse;
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

    public Utilisateur(String username, String email, int enabled, String password, String confirmation_token, String roles, String nom, String numTel, Adresse adresse, String image, String prenom, String genre, Date dateNaissance, int yesNews) {
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.roles = roles;
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.image = image;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.yesNews = yesNews;
    }

    public Utilisateur(String username, String email, int enabled, String password, String confirmation_token, String roles, String numTel, Adresse adresse, String image, String pageFB, String siteWeb, String description, float longitude, float latitude, int yesNews) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.roles = roles;
        this.numTel = numTel;
        this.adresse = adresse;
        this.image = image;
        this.pageFB = pageFB;
        this.siteWeb = siteWeb;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.yesNews = yesNews;
    }
    
    
    
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", nom=" + nom + ", numTel=" + numTel + ", adresse=" + adresse + ", image=" + image + ", pointXP=" + pointXP + ", prenom=" + prenom + ", genre=" + genre + ", dateNaissance=" + dateNaissance + ", pageFB=" + pageFB + ", siteWeb=" + siteWeb + ", description=" + description + ", longitude=" + longitude + ", latitude=" + latitude + ", yesNews=" + yesNews + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.username);
        hash = 71 * hash + Objects.hashCode(this.username_canonical);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.email_canonical);
        hash = 71 * hash + this.enabled;
        hash = 71 * hash + Objects.hashCode(this.salt);
        hash = 71 * hash + Objects.hashCode(this.password);
        hash = 71 * hash + Objects.hashCode(this.last_login);
        hash = 71 * hash + Objects.hashCode(this.confirmation_token);
        hash = 71 * hash + Objects.hashCode(this.password_requested_at);
        hash = 71 * hash + Objects.hashCode(this.roles);
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.numTel);
        hash = 71 * hash + Objects.hashCode(this.adresse);
        hash = 71 * hash + Objects.hashCode(this.image);
        hash = 71 * hash + this.pointXP;
        hash = 71 * hash + Objects.hashCode(this.prenom);
        hash = 71 * hash + Objects.hashCode(this.genre);
        hash = 71 * hash + Objects.hashCode(this.dateNaissance);
        hash = 71 * hash + Objects.hashCode(this.pageFB);
        hash = 71 * hash + Objects.hashCode(this.siteWeb);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Float.floatToIntBits(this.longitude);
        hash = 71 * hash + Float.floatToIntBits(this.latitude);
        hash = 71 * hash + this.yesNews;
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.username_canonical, other.username_canonical)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.email_canonical, other.email_canonical)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.last_login, other.last_login)) {
            return false;
        }
        if (!Objects.equals(this.confirmation_token, other.confirmation_token)) {
            return false;
        }
        if (!Objects.equals(this.password_requested_at, other.password_requested_at)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.numTel, other.numTel)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (this.pointXP != other.pointXP) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        if (!Objects.equals(this.pageFB, other.pageFB)) {
            return false;
        }
        if (!Objects.equals(this.siteWeb, other.siteWeb)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (Float.floatToIntBits(this.longitude) != Float.floatToIntBits(other.longitude)) {
            return false;
        }
        if (Float.floatToIntBits(this.latitude) != Float.floatToIntBits(other.latitude)) {
            return false;
        }
        if (this.yesNews != other.yesNews) {
            return false;
        }
        return true;
    }

    
    

}
