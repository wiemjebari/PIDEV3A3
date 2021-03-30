/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author wiemj
 */
public class commande {
  private int idCommandeProduit;
    private int idproduit;
    private int idAcheteur;
    private int quantiteCommandeProduit;

   
     public commande(int idAcheteur,int idproduit, int quantiteCommandeProduit) {
        this.idproduit = idproduit;
        this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }
      public commande(int idproduit, int quantiteCommandeProduit) {
        this.idproduit = idproduit;
       // this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

    public int getIdCommandeProduit() {
        return idCommandeProduit;
    }

    public void setIdCommandeProduit(int idCommandeProduit) {
        this.idCommandeProduit = idCommandeProduit;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }
     public int getId_user() {
        return idAcheteur;
    }

    public void setId_user(int id_user) {
        this.idAcheteur = id_user;
    }
    public int getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public int getQuantiteCommandeProduit() {
        return quantiteCommandeProduit;
    }

    public void setQuantiteCommandeProduit(int quantiteCommandeProduit) {
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommandeProduit=" + idCommandeProduit + ", idproduit=" + idproduit + ", idAcheteur=" + idAcheteur + ", quantiteCommandeProduit=" + quantiteCommandeProduit + '}';
    }

    
    
}
