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
public class Panier {
    
   private int id_panier; 
   private int id_produit;
    private String nom_produit;
    private int id_categorie;
    private float prix_produit;
    private int quantite=1;

    public Panier() {
    }
    
    

    public Panier(int id_panier, int id_produit, String nom_produit, int id_categorie, float prix_produit) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.id_categorie = id_categorie;
        this.prix_produit = prix_produit;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", id_categorie=" + id_categorie + ", prix_produit=" + prix_produit + ", quantite=" + quantite + '}';
    }
    
    
    
}
