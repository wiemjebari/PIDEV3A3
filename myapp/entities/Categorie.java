/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Categorie {
 private int idc;
    private String nomcategorie,adresse,color;
    
    public Categorie(int idc, String nomcategorie, String adresse,String color) {
        this.idc = idc;
        this.nomcategorie = nomcategorie;
        this.adresse = adresse;
        this.color=color;
    }

    public Categorie(String nomcategorie, String adresse,String color) {
        this.nomcategorie = nomcategorie;
        this.adresse = adresse;
        this.color=color;
    }

    public Categorie() {
    }

    public Categorie(String string, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Categorie(String text, String text0, String text1, String text2, String text3, String text4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idc=" + idc + ", nomcategorie=" + nomcategorie + ", adresse=" + adresse + ", color=" + color + '}';
    }

   






}