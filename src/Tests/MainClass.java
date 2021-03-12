/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Conseils;
import Services.ConseilsCRUD;
import Tools.MyConnection;

/**
 *
 * @author yassine
 */
public class MainClass {
       public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode()+"--"+mc2.hashCode());
        Conseils c = new Conseils( "Sante", "Confort");
        Conseils c2 = new Conseils( "nada", "relax");
        Conseils c3 = new Conseils( "chayma", "loulou");
        Conseils c4 = new Conseils( "oumayma", "lalala");
        ConseilsCRUD pcd = new ConseilsCRUD();
        // pcd.setDescription("lilili");
         pcd.updateConseils(c2);
      
       //System.out.println(pcd.displayConseils());
        
       // pcd.ajouterConseils(c4);
       //pcd.ajouterConseils(c2);
      // pcd.ajouterConseils(c);
   //   pcd.supprimerConseils(c4); 
         //  System.out.println(pcd.displayConseils() );
        
      
           
       
               
    }
}
