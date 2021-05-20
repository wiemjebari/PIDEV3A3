
package com.mycompany.myapp.entities;

import com.codename1.ui.TextField;
import java.util.Date;


public class Produit {
   
    private int id;

    private String nomproduit;
    private Float prixproduit;
    private int idcategorie=49;
    private String description ;
    private int stockproduit ;
    private String photo;

    public Produit() {
        
    }

  

    public Produit(String nomproduit, Float prixproduit, Integer stockproduit, String description) {
         this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
      
        this.description = description;
        this.stockproduit = stockproduit;
    }

 

    public int getId() {
        return id;
    }

    public Produit(String nomproduit, Float prixproduit, int idcategorie, String description, int stockproduit, String photo) {
        this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
        this.idcategorie = idcategorie;
        this.description = description;
        this.stockproduit = stockproduit;
        this.photo = photo;
    }

    public Produit(int id, String nomproduit, Float prixproduit, int idcategorie, String description, int stockproduit, String photo) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
        this.idcategorie = idcategorie;
        this.description = description;
        this.stockproduit = stockproduit;
        this.photo = photo;
    }
    

    public Produit(String nomproduit, Float prixproduit, String description, int stockproduit, String photo) {
        this.nomproduit = nomproduit;
        this.prixproduit = prixproduit;
        this.description = description;
        this.stockproduit = stockproduit;
        this.photo = photo;
    }
    
    
    

    public void setId(int id) {
        this.id = id;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public Float getPrixproduit() {
        return prixproduit;
    }

    public void setPrixproduit(Float prixproduit) {
        this.prixproduit = prixproduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockproduit() {
        return stockproduit;
    }

    public void setStockproduit(int stockproduit) {
        this.stockproduit = stockproduit;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomproduit=" + nomproduit + ", prixproduit=" + prixproduit + ", idcategorie=" + idcategorie + ", description=" + description + ", stockproduit=" + stockproduit + ", photo=" + photo + '}';
    }
    

   

    



    
}
