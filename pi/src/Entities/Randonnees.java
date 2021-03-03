/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Randonnees {
    private int id;
    private java.sql.Date date;
    private String lieu;
    private int heure_depart;
    private int heure_retour;
    private int nbr_place;
    private String photo;

    public Randonnees() {
    }

    public Randonnees(java.sql.Date date, String lieu, int heure_depart, int heure_retour, int nbr_place, String photo) {
        this.date = date;
        this.lieu = lieu;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.nbr_place = nbr_place;
        this.photo = photo;
    }
    
   
    

    public Randonnees(int id,java.sql.Date date, String lieu, int heure_depart, int heure_retour, int nbr_place, String photo) {
        this.id = id;
        this.date = date;
        this.lieu = lieu;
        this.heure_depart = heure_depart;
        this.heure_retour = heure_retour;
        this.nbr_place = nbr_place;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(int heure_depart) {
        this.heure_depart = heure_depart;
    }

    public int getHeure_retour() {
        return heure_retour;
    }

    public void setHeure_retour(int heure_retour) {
        this.heure_retour = heure_retour;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}
