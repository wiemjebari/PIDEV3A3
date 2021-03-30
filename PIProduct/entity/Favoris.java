/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.entity;

/**
 *
 * @author bouyo
 */
public class Favoris {
    private int idFavorieProduit;
    private Produit Produit;
    private int idOwner;

    public Favoris() {
    }

  
    public Favoris(int idFavorieProduit, Produit idProduit, int idOwner) {
        this.idFavorieProduit = idFavorieProduit;
        this.Produit = idProduit;
        this.idOwner = idOwner;
    }

    public Favoris(Produit idProduit, int idOwner) {
        this.Produit = idProduit;
        this.idOwner = idOwner;
    }
 public Favoris( Produit idProduit) {
        this.Produit = idProduit;
    }
   
    

    public int getIdFavorieProduit() {
        return idFavorieProduit;
    }

    public void setIdFavorieProduit(int idFavorieProduit) {
        this.idFavorieProduit = idFavorieProduit;
    }

    public Produit getProduit() {
        return Produit;
    }

    public void setProduit(Produit Produit) {
        this.Produit = Produit;
    }


 
    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public String toString() {
        return "Favoris{" + "idFavorieProduit=" + idFavorieProduit + ", Produit=" + Produit.getIdProduit() + ", idOwner=" + idOwner + '}';
    }
    
    
}
