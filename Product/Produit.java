/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

/**
 *
 * @author wiemj
 */
public class Produit {
    private Integer id_produit;
    private String nom_produit;
    private String nom_categorie;
    private float prix_produit;
    private Integer quantite;

    public Produit(Integer id_produit, String nom_produit, String nom_categorie, float prix_produit, Integer quantite) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.nom_categorie = nom_categorie;
        this.prix_produit = prix_produit;
        this.quantite = quantite;
    }

    public Integer getId_produit() {
        return id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setId_produit(Integer id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", nom_categorie=" + nom_categorie + ", prix_produit=" + prix_produit + ", quantite=" + quantite + '}';
    }
    
    
}
