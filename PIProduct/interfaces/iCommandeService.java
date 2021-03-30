/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.interfaces;

import gestionproduit.entity.commande;
import java.util.List;

/**
 *
 * @author wiemj
 */
public interface iCommandeService {
    public List<commande> consulterCommande();
    
}
