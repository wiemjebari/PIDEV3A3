/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.entity;

import java.io.File;

/**
 *
 * @author wiemj
 */
public class Produit {
 
    private int idProduit;
    private int idOwer;
    private String categorieProduit;
    private String nomProduit;
    private String descriptionProduit;
    private String photoProduit;
    private String videoProduit;
    private float prixProduit;
    private int stockProduit;
    private File Image;
    public static int id_pModifier;

    public Produit() {
    }

    

 

    public File getImage() {
        return Image;
    }

    public void setImage(File Image) {
        this.Image = Image;
    }

        public Produit( String categorieProduit, int idOwner, String descriptionProduit,  float prixProduit, int stockProduit,  String nomProduit, String photoProduit) {        this.nomProduit = nomProduit;

        this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit=photoProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
        this.idOwer=idOwner;
    }
     public Produit(int idProduit ,String nomProduit , String descriptionProduit, String photoProduit,  float prixProduit, int stockProduit,  String categorieProduit
) {        this.nomProduit = nomProduit;

        this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
    }
     public Produit(String categorieProduit, int idOwer, String descriptionProduit,  float prixProduit, int stockProduit,  String nomProduit
) {        
        this.idOwer =idOwer;
        this.nomProduit = nomProduit;
         this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
    }
    public Produit( String categorieProduit, String descriptionProduit, String photoProduit, String videoProduit, float prixProduit, int stockProduit,  String nomProduit
) {        this.nomProduit = nomProduit;

        this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.videoProduit = videoProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
    }
    public Produit( String categorieProduit, String descriptionProduit, String photoProduit,  float prixProduit, int stockProduit,  String nomProduit
) {        this.nomProduit = nomProduit;

        this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.prixProduit = prixProduit;
        this.stockProduit = stockProduit;
    }
    public Produit(String categorieProduit, String descriptionProduit, String photoProduit, String videoProduit) {
        this.categorieProduit = categorieProduit;
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.videoProduit = videoProduit;
    }
 public Produit(String descriptionProduit, String photoProduit, String videoProduit) {
        this.descriptionProduit = descriptionProduit;
        this.photoProduit = photoProduit;
        this.videoProduit = videoProduit;
    }
   

    public Produit(String nom_produit, String description, String photo, float prix_produit, int stock_produit, String categorie_produit, String video) {
 this.nomProduit = nom_produit;
        this.descriptionProduit = description;
        this.photoProduit = photo;
        this.categorieProduit = categorie_produit;
        this.prixProduit = prix_produit;
        this.stockProduit = stock_produit;
        this.videoProduit= video;
    }

    public Produit(int id_produit, String nom_produit, String description, String photo, float prix_produit, int stock_produit, String categorie_produit, String video) {
        this.idProduit= id_produit;
        this.nomProduit = nom_produit;
        this.descriptionProduit = description;
        this.photoProduit = photo;
        this.categorieProduit = categorie_produit;
        this.prixProduit = prix_produit;
        this.stockProduit = stock_produit;
        this.videoProduit= video;    }

   


  

   

  

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(String categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    public String getVideoProduit() {
        return videoProduit;
    }

    public void setVideoProduit(String videoProduit) {
        this.videoProduit = videoProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getStockProduit() {
        return stockProduit;
    }

    public void setStockProduit(int stockProduit) {
        this.stockProduit = stockProduit;
    }

    public int getIdOwer() {
        return idOwer;
    }

    public void setIdOwer(int idOwer) {
        this.idOwer = idOwer;
    }

    public static int getId_pModifier() {
        return id_pModifier;
    }

    public static void setId_pModifier(int id_pModifier) {
        Produit.id_pModifier = id_pModifier;
    }

    
    
    
}
