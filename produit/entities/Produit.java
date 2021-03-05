/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit.entities;

/**
 *
 * @author wiemj
 */
public class Produit {
    
    
    private int id_produit;
    private String nom_produit;
    private enum nom_categorie;
    private float prix_produit;

    public Produit() {
    }

    public Produit(int id_produit, String nom_produit, enum nom_categorie,float prix_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.nom_categorie = nom_categorie;
         this.prix_produit = prix_produit;
    }

    public int getIdProduit() {
        return id_produit;
    }

    public String getNomProduit() {
        return nom_produit;
    }

    public String getNomCategorie() {
        return nom_categorie;
    }
       public float getPrixProduit() {
        return prix_produit;
    }

    public void setId_produit(int id_produit) {
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

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + " "
                + ", nom_categorie=" + nom_categorie + ", prix_produit=" + prix_produit + '}';
    }

   
    
    
}
