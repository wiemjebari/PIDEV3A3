/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.services;

import gestionproduit.utils.css.ConnexionBd;
import gestionproduit.entity.commande;
import gestionproduit.entity.Favoris;
import gestionproduit.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import gestionproduit.interfaces.iCommandeService;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wiemj
 */
public class CommandeService  implements iCommandeService{
    Connection cnx;
    Statement stmt;

   public CommandeService() {
        this.cnx = ConnexionBd.getInstance().getConnection();
    }
    
 public void CreateProduitsCommand(commande c) throws SQLException {
     
 try {
            String req = "insert into commandeprod (id_acheteur,quantite,id_produit) values(1,?,?)";

     System.out.println("-----"+c.getQuantiteCommandeProduit());
    System.out.println("iiiiii"+c.getIdproduit());
     
            PreparedStatement st = cnx.prepareStatement(req);
           st.setInt(1, User.getUserconnected()
);
            st.setInt(2, c.getQuantiteCommandeProduit());
            st.setInt(3,c.getIdproduit());
            
            

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public List<commande> consulterCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
