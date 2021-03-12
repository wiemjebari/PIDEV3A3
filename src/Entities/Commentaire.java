/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author yassine
 */
public class Commentaire {
    private User user;
    private Conseils conseil;
    private int id_commentaire;
    private String contenu;
    private Date DateCreation;

    public Commentaire() {
    }

    public Commentaire(User user, Conseils conseil, int id_commentaire, String contenu, Date DateCreation) {
        this.user = user;
        this.conseil = conseil;
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.DateCreation = DateCreation;
    }

    public User getUser() {
        return user;
    }

    public Conseils getConseil() {
        return conseil;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setConseil(Conseils conseil) {
        this.conseil = conseil;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "user=" + user + ", conseil=" + conseil + ", id_commentaire=" + id_commentaire + ", contenu=" + contenu + ", DateCreation=" + DateCreation + '}';
    }

    
    
    
    
}
