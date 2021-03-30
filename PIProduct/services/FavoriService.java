/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.services;

import gestionproduit.utils.css.ConnexionBd;
import gestionproduit.entity.Favoris;
import gestionproduit.entity.Produit;
import gestionproduit.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestionproduit.interfaces.iFavorisService;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouyo
 */
public class FavoriService implements iFavorisService{
     Connection cnx;
    PreparedStatement pst;
Favoris favori = new Favoris();
    ResultSet rs;
    Statement stmt;

    public FavoriService() {
        this.cnx = ConnexionBd.getInstance().getConnection();

   }
    
 
   @Override
    public void ajouterFavoris(Favoris f)  {
         
                      
        String req1 = "insert into produitfavories (id_produit,id_owner) values(?,?) ";
         try {
             PreparedStatement pst = cnx.prepareStatement(req1);
             pst.setInt(1, f.getProduit().getIdProduit());
             pst.setInt(2, User.getUserconnected()
);
        
                pst.executeUpdate();
       
            } catch (SQLException ex) {
             Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
         }
    
     
         
     
     }
   public List<Favoris> consulterProduitByIdOwner(int id_owner) {
        ArrayList<Favoris> listN = new ArrayList<Favoris>();
      try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produitfavories where id_owner='"+id_owner+"'");
            while (rs.next()) {
                //System.out.println("nom Produit " + rs.getString(3) + "description de Produit " + rs.getString(4) + "photo de produit " + rs.getString(5) + "prix de produit " + rs.getFloat(6) + "sttock de produit " + rs.getInt(7) + "catégorie de produit " + rs.getString(8) + "video de produit " + rs.getString(9));
                ProduitService ps=new ProduitService();
                Produit p =ps.rechercherroduitById(rs.getInt(2));
                listN.add(new Favoris(p));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

    @Override
    public void supprimerFavoris(int id_favori) {
    
    } 

    @Override
    public List<Favoris> consulterProduit(int idowner) {
 ArrayList<Favoris> listN = new ArrayList<Favoris>();
 ProduitService ps = new ProduitService();
       try {
            Statement st2 = cnx.createStatement();
            ResultSet rs = st2.executeQuery("Select * from produitfavories where id_owner = "+idowner);
            while (rs.next()) {
               listN.add(new Favoris(rs.getInt("id_favori"),
                       ps.findbyid(rs.getInt("id_produit")),
                       rs.getInt("id_owner")));
            }
            st2.close();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

    public void ajouterFavoris(Produit p, int owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    }