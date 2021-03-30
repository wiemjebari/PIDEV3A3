package gestionproduit.services;

/**
 *
 * @author bouyo
 */
import gestionproduit.entity.ProduitLike;
import gestionproduit.utils.css.ConnexionBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProduitLikeService {
  Connection cnx;

    public ProduitLikeService() {
        this.cnx = ConnexionBd.getInstance().getConnection();
    }  
    
    public boolean getUserLike(ProduitLike pl) {
        
      try {      
          String requete = "select id_owner  from like_produit where 	id_owner ='"+pl.getIdUser()+"' and id_produit ='"+pl.getIdProduit()+"'";
             PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              return true;
            }
            else{
                return false;
            }               
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
      return true;
    }
     public void ajouterLike(ProduitLike pl) {
        
      try {      
             String requete1="INSERT INTO like_produit (id_produit,id_owner) VALUES ('"+pl.getIdProduit()+"','"+pl.getIdUser()+"')";
            Statement st1=cnx.createStatement();
            st1.executeUpdate(requete1);
            
            System.out.println("like ajouté !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }
     
      public int getNumberLike(int p) {
        String requete = "select count(id) nb from like_produit where id_produit ='"+p+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int nbLike=rs.getInt("nb");
                
              return nbLike;
               // System.out.println(" publication succ");
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }   
    return 0;    }
}
