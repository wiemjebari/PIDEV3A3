/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author yassine
 */
public class Conseils {
     private int id_article;
    private String Titre_article;
    private String description;
    private String image;
    

    public Conseils() {
    }

    public Conseils(String Titre_article, String description, String image) {
        this.Titre_article = Titre_article;
        this.description = description;
        this.image = image;
    }

    public Conseils(int id_article, String Titre_article, String description, String image) {
        this.id_article = id_article;
        this.Titre_article = Titre_article;
        this.description = description;
        this.image = image;
    }

    public int getId_article() {
        return id_article;
    }

    public String getTitre_article() {
        return Titre_article;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setTitre_article(String Titre_article) {
        this.Titre_article = Titre_article;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Conseils{" + "id_article=" + id_article + ", Titre_article=" + Titre_article + ", description=" + description + ", image=" + image + '}';
    }

    

  
    }

    
    
    
    
    
    
