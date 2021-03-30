/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.entity;

/**
 *
 * @author Achraf
 */
public class Recommendation {
    public int id;
    public int id_owner;
    public String titre;
    public String categorie;
    public String description;
    public String nom;
    public String adresse;
    public String num_tel;
    public String email;
    public float note;
    public String photo;
    public static int id_recModifier;
    static int id_owner_rec;
    public Recommendation(){
        
    };

    public Recommendation( int id_owner, String titre, String categorie, String description, String nom, String adresse, String num_tel, String email, float note, String photo) {
        
        this.id_owner = id_owner;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.note = note;
        this.photo = photo;
    }
    public Recommendation(int id, int id_owner, String titre, String categorie, String description, String nom, String adresse, String num_tel, String email, float note, String photo) {
        
        this.id=id;
        this.id_owner = id_owner;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.note = note;
        this.photo = photo;
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static int getId_recModifier() {
        return id_recModifier;
    }

    public static void setId_recModifier(int id_recModifier) {
        Recommendation.id_recModifier = id_recModifier;
    }
    

    @Override
    public String toString() {
        return "Recommendation{" + "id=" + id + ", id_owner=" + id_owner + ", titre=" + titre + ", categorie=" + categorie + ", description=" + description + ", nom=" + nom + ", adresse=" + adresse + ", num_tel=" + num_tel + ", email=" + email + ", note=" + note + ", photo=" + photo + '}';
    }

    public static int getId_owner_rec() {
        return id_owner_rec;
    }

    public static void setId_owner_rec(int id_owner_rec) {
        Recommendation.id_owner_rec = id_owner_rec;
    }
    
    
    
}
