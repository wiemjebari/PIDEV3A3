/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.interfaces;

import gestionproduit.entity.User;
import java.util.List;

/**
 *
 * @author Achraf
 */
public interface UserInterface {
    public void AjouterUser(User u);
    public void ModiferUser(int id,User u);
    public void SupprimerUser(int id);
    public Boolean Login(String username,String password);
    public User AfficherUser(String username);
    public List<User> getAllUser();
    public User getUser(String username);
    public boolean verifAdmin(String username);
    
    
    
}
