/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author hocin
 */
public class Categorie {
    
    
    int id  ;
    String nom ;
    String description ;

    public Categorie(int id, String nom , String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
    }

   
    
}
