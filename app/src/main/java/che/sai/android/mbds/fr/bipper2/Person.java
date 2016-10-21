package che.sai.android.mbds.fr.bipper2;

/**
 * Created by Domitille on 21/10/2016.
 */

import java.io.Serializable;


public class Person implements Serializable {
    String nom;
    String prenom;
    String sexe;
    Integer telephone;
    String email;
    String createdBy;
    String password;
    String createdAt;
    String updateAt;
    String id;
    boolean connected=true;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Person() {

    }

    public Person(String nom, String prenom, String sexe, Integer telephone, String email, String createdBy, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
        this.email = email;
        this.createdBy = createdBy;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}