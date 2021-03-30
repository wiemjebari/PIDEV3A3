/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author walid
 */
public class Commentaire {
    private String text;

    public Commentaire( String text) {
        this.text = text;
    }

    

    public String getText() {
        return text;
    }

   

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "text=" + text + '}';
    }

    
   
    
    
}
