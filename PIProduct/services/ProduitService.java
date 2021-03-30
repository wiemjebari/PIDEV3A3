/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.services;

import gestionproduit.entity.Produit;
import gestionproduit.entity.Recommendation;
import gestionproduit.entity.User;
import gestionproduit.interfaces.IProduitService;
import gestionproduit.utils.css.ConnexionBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wiemj
 */
public class ProduitService  implements IProduitService {

    Connection cnx;
    Statement stmt;

    public ProduitService() {
        
        this.cnx = ConnexionBd.getInstance().getConnection();
    }

    @Override
    public void ajouterProduit(Produit p) {
         try {
           String req = "INSERT INTO produit (categorie_produit,id_owner,description,prix_produit,stock_produit,nom_produit,photo) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getNomProduit());
            st.setInt(2, User.getUserconnected());
            st.setString(3, p.getCategorieProduit());
            st.setFloat(4, p.getPrixProduit());
            st.setInt(5, p.getStockProduit());
            st.setString(6, p.getDescriptionProduit());
            st.setString(7,p.getPhotoProduit());

     
            st.executeUpdate();
            System.out.println("Produit ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }

    @Override
    public void supprimerProduit(int id_produit) {
      try {
            String req = "DELETE FROM produit WHERE produit.`id_produit` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_produit);
            st.executeUpdate();
            System.out.println("Produit supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierProduit(Produit p) {
        try {

            String req = "UPDATE `produit` SET `nom_produit` = ?, `description`= ? , `photo`= ? , `prix_produit`= ?, `stock_produit`= ?, `categorie_produit`= ?, `video`= ? WHERE `id_produit` = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getNomProduit());
            st.setString(2, p.getDescriptionProduit());
            st.setString(3, p.getPhotoProduit());
            st.setFloat(4, p.getPrixProduit());
            st.setInt(5, p.getStockProduit());
            st.setString(6, p.getCategorieProduit());
            st.setString(7, p.getVideoProduit());
            st.setInt(8, p.getIdProduit());

            st.executeUpdate();
            System.out.println("Produit modifié !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> consulterProduit() {
       ArrayList<Produit> listN = new ArrayList<Produit>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit");
            while (rs.next()) {
  //System.out.println("nom Produit " + rs.getString(3) + "Categorie de Produit " + rs.getString(4) + "prix de produit " + rs.getFloat(5)+"sttock de produit " + rs.getInt(6) + "photo de produit " + rs.getString(7)   + "video de produit " + rs.getString(8)+ "description " + rs.getString(9));
                listN.add(new Produit(rs.getInt(1),
                        rs.getString(9),
                        rs.getString(4),
                        rs.getString(7),
                        rs.getFloat(5),
                        rs.getInt(6),
                    
                        rs.getString(3)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

      public List<Produit> consulterProduitByIdOwner(int id_owner) {
        ArrayList<Produit> listN = new ArrayList<Produit>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where id_owner='"+id_owner+"'");
            while (rs.next()) {
                //System.out.println("nom Produit " + rs.getString(3) + "description de Produit " + rs.getString(4) + "photo de produit " + rs.getString(5) + "prix de produit " + rs.getFloat(6) + "sttock de produit " + rs.getInt(7) + "catégorie de produit " + rs.getString(8) + "video de produit " + rs.getString(9));
                listN.add(new Produit(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }
      
      public Produit rechercherroduitById(int id_produit) {
        String requete = "select * from produit where id_produit='" + id_produit + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom_produit = rs.getString("nom_produit");
                String description = rs.getString("description");
                String photo = rs.getString("photo");
                float prix_produit = rs.getFloat("prix_produit");
                int stock_produit = rs.getInt("stock_produit");
                String categorie_produit = rs.getString("categorie_produit");
                return (new Produit(id_produit, nom_produit, description, photo, prix_produit, stock_produit, categorie_produit));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return null;
    }
      
      public int getnbrInfo() {
        String req = "SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'cafe , restaurant et supermarche'";

        int x = 0;
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                x = rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    }

     public int getnbrCafe() {
        String req = "SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'cafe'";

        int x = 0;
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                x = rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    } 
     
     public int getnbrRestau() {
        String req = "SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'restaurant'";

        int x = 0;
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                x = rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    }
     public int getnbrSupermarche() {
        String req = "SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'supermarche'";

        int x = 0;
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                x = rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;
    }

     public Produit findbyid(int id) {
        Produit p = new Produit();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit WHERE produit.`id_produit` = '" + id + "'");
            while (rs.next()) {
                p = new Produit(
                        rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

     public Produit AfficherDetailProduit(int id_prod) {
        ArrayList<Produit> listN = new ArrayList<Produit>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit WHERE produit.`id_produit` = '" + id_prod + "'");
            while (rs.next()) {
                listN.add(new Produit(
                        rs.getInt(1),
                        rs.getString(9),
                        rs.getString(4),                             
                        rs.getString(7),
                        rs.getFloat(5),
                        rs.getInt(6),
                        
                        rs.getString(3)
                        
                       /* rs.getInt(1),
                        rs.getString(9),
                        rs.getString(4),
                        rs.getString(7),
                        rs.getFloat(5),
                        rs.getInt(6),
                    
                        rs.getString(3))*/
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN.get(0);
    }     
  
     public void quantiteApresCommande(Produit p, int stockn) {

        try {
            // System.out.println("produit"+p.getIdProduit());
            System.out.println("Stock aprés Commande ****** ");
            String req = "UPDATE produit SET stock_produit='" + stockn + "'WHERE id_produit='" + p.getIdProduit() + "'";
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Produit> findAllFiltrer(String filtre) {
        List<Produit> produits = new ArrayList<>();
        Produit produit = new Produit();
        try {
            String sql = "SELECT * FROM `produit` where categorie_produit like '%" + filtre + "%'";
            PreparedStatement statement = this.cnx.prepareStatement(sql);
            //statement.setString(1,filtre);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                produit = new Produit(results.getInt("id_produit"), results.getString("nom_produit"),
                        results.getString("description"), results.getString("photo"),
                        results.getFloat("prix_produit"), results.getInt("stock_produit"),
                        results.getString("categorie_produit"));
                produits.add(produit);
                //  System.out.println(produit.toString());
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

    public List<Produit> RechercherPro(String filtre) {
        List<Produit> produits = new ArrayList<>();
        Produit produit = new Produit();
        try {
            String sql = "SELECT * FROM `produit` where categorie_produit like '%" + filtre + "%' or nom_produit like '%" + filtre + "%' or description like '%" + filtre + "%'";
            PreparedStatement statement = this.cnx.prepareStatement(sql);
            //statement.setString(1,filtre);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                produit = new Produit(results.getInt("id_produit"), results.getString("nom_produit"), results.getString("description"), results.getString("photo"), results.getFloat("prix_produit"), results.getInt("stock_produit"), results.getString("categorie_produit"));
                produits.add(produit);
                //  System.out.println(produit.toString());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }
    
    
    
    
     public List<Produit> findAllFiltrerup() {
        List<Produit> produits = new ArrayList<>();
        Produit produit = new Produit();
        try {
            String sql = "SELECT * FROM `produit` order by prix_produit";
            PreparedStatement statement = this.cnx.prepareStatement(sql);
            //statement.setString(1,filtre);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                produit = new Produit(results.getInt("id_produit"), results.getString("nom_produit"),
                        results.getString("description"), results.getString("photo"),
                        results.getFloat("prix_produit"), results.getInt("stock_produit"),
                        results.getString("categorie_produit"));
                produits.add(produit);
                //  System.out.println(produit.toString());
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

   
    public List<Produit> findAllFiltrerdown() {
        List<Produit> produits = new ArrayList<>();
        Produit produit = new Produit();
        try {
            String sql = "SELECT * FROM `produit` order by prix_produit desc";
            PreparedStatement statement = this.cnx.prepareStatement(sql);
            //statement.setString(1,filtre);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                produit = new Produit(results.getInt("id_produit"), results.getString("nom_produit"),
                        results.getString("description"), results.getString("photo"),
                        results.getFloat("prix_produit"), results.getInt("stock_produit"),
                        results.getString("categorie_produit"));
                produits.add(produit);
                //  System.out.println(produit.toString());
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }


  
   

   

    public boolean verifFav(int userconnected, int idProduit) {
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produitfavories WHERE produitfavories.`id_produit` = '" + idProduit + "' and produitfavories.`id_owner` = '" + userconnected + "' ");
            while (rs.next()) {
              return true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 

}
