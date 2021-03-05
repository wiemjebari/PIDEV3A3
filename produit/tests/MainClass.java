/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit.tests;

import produit.entities.Produit;
import produit.services.ProduitCrud;
import produit.tools.MyConnection;

/**
 *
 * @author wiemj
 */
public class MainClass {
    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode()+"--"+mc2.hashCode());
        Produit p = new Produit(3, "plat", "restaurant",5f);
        ProduitCrud pcd = new ProduitCrud();
        pcd
       // System.out.println(pcd.displayProduit());

    /**
     * @param args the command line arguments
     */
    }
    
}
