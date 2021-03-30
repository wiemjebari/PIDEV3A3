/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.entity;

/**
 *
 * @author wiemj
 */
public class User {

   
    int id;
    String username;
    String email;
    int enabled;
    String password;
    String confirmation_token;
    String nom;
    String prenom;
    String addresse;
    int telephone;
    private static int userconnected;
    

    public User(int id, String username, String email, int enabled, String password, String confirmation_token, String nom, String prenom, String addresse, int telephone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.telephone = telephone;
    }

    public User(String username, String email, int enabled, String password, String confirmation_token, String nom, String prenom, String addresse, int telephone) {
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.telephone = telephone;
    }

    public User() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", enabled=" + enabled + ", password=" + password + ", confirmation_token=" + confirmation_token + ", nom=" + nom + ", prenom=" + prenom + ", addresse=" + addresse + ", telephone=" + telephone + '}';
    }

     public static int getUserconnected() {
        return userconnected;
    }
   

    public static void setUserconnected(int userconnected) {
        User.userconnected = userconnected;
    }
    
    
    
}
