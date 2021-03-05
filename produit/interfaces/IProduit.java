/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit.interfaces;

import java.util.List;

/**
 *
 * @author wiemj
 */
public interface IProduit<T> {
    
    public void ajouterProduit(T t);
     public void supprimerProduit(T t);
     public void updateProduit(T t);
     public List<T> displayProduit();
    
}
