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
public class Categorie {
    private int id_categorie;
    private String nom_categorie;
    private String adresse;

    public Categorie(int id_categorie, String nom_categorie, String adresse) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.adresse = adresse;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", adresse=" + adresse + '}';
    }
    
    
}
