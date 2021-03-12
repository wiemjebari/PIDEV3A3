/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author yassine
 */
public class Reaction {
    private int idReaction;
    private User user;
    private Conseils conseil;
    private int type;

    public Reaction() {
    }

    public Reaction(User user, Conseils conseil, int type) {
        this.user = user;
        this.conseil = conseil;
        this.type = type;
    }

    public Reaction(int idReaction, User user, Conseils conseil, int type) {
        this.idReaction = idReaction;
        this.user = user;
        this.conseil = conseil;
        this.type = type;
    }

    public int getIdReaction() {
        return idReaction;
    }

    public void setIdReaction(int idReaction) {
        this.idReaction = idReaction;
    }

    

    public User getUser() {
        return user;
    }

    public Conseils getConseil() {
        return conseil;
    }

    public int getType() {
        return type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setConseil(Conseils conseil) {
        this.conseil = conseil;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reaction{" + "user=" + user + ", conseil=" + conseil + ", type=" + type + '}';
    }
    
    
    
    
    
}
