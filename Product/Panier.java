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
public class Panier {
    private Integer id_panier;
    private Integer id_produit;
    private String nom_produit;
    private String nom_categorie;
    private Float prix_produit;
    private Integer quantite;

    public Panier(Integer id_panier, Integer id_produit, String nom_produit, String nom_categorie, Float prix_produit, Integer quantite) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.nom_categorie = nom_categorie;
        this.prix_produit = prix_produit;
        this.quantite = quantite;
    }



    public Integer getId_panier() {
        return id_panier;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public Float getPrix_produit() {
        return prix_produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Integer getId_produit() {
        return id_produit;
    }
    

    public void setId_panier(Integer id_panier) {
        this.id_panier = id_panier;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setPrix_produit(Float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setId_produit(Integer id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", nom_categorie=" + nom_categorie + ", prix_produit=" + prix_produit + ", quantite=" + quantite + '}';
    }
    

    
    
    
}
