/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import produit.interfaces.IProduit;
import produit.entities.Produit;
import produit.tools.MyConnection;

/**
 *
 * @author wiemj
 */
public class ProduitCrud implements IProduit<Produit>{
   
    @Override
    public void ajouterProduit(Produit t) {
        try {
            String requete = "INSERT INTO produit (nom_produit,nom_categorie,prix_produit)"
                    + "VALUES ('"+t.getNomProduit()+"','"+t.getNomCategorie()+"','"+t.getPrixProduit()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimerProduit(Produit t) {
        try {
            String requete = "DELETE FROM produit where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getIdProduit());
            pst.executeUpdate();
            System.out.println("Produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
   
    @Override
    public void updateProduit(Produit t) {
        try {
            String requete = "UPDATE produit SET nom_produit=? WHERE id_produit=?  "
                   ;
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNomProduit());
            pst.setInt(2, t.getIdProduit());
            pst.executeUpdate();
            System.out.println("Produit modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public List<Produit> displayProduit() {
         List<Produit> produitList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM produit";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Produit p = new Produit();
                p.setId_produit(rs.getInt("id_produit"));
                p.setNom_produit(rs.getString("nom_produit"));
                p.setNom_categorie(rs.getString("nom_categorie"));
                p.setPrix_produit(rs.getFloat("prix_produit"));
                produitList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return produitList;
    }
    }
