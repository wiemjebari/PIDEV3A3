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
public class Commentaire {
    private int id;
    private String text;

    public Commentaire(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", text=" + text + '}';
    }
    
    
}
