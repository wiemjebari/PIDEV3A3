/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.livrecrud;

/**
 *
 * @author walid
 */
public class Livres {
    private int id;
    private String titre;
    private String editeur;
    private int id_categorie;
    private int duree;
    private String source;
    private String audio; 
    private String image;
    

 
     public Livres(int id, String titre, String editeur,int id_categorie, int duree, String source, String audio,String image) {
        this.id = id;
        this.titre = titre;
        this.editeur = editeur;
        this.id_categorie = id_categorie;
        this.duree = duree;
        this.source = source;
        this.audio = audio;
        this.image=image;
    }

    

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public int getDuree() {
        return duree;
    }

    public String getSource() {
        return source;
    }

    public String getAudio() {
        return audio;
    }
    public String getImage() {
        return image;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
