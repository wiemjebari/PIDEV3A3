/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.services;

  
import gestionproduit.entity.Recommendation;
import gestionproduit.entity.User;
import gestionproduit.interfaces.UserInterface;
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


/**
 *
 * @author Achraf
 */
public class UserServices implements UserInterface {

    Connection cnx;
    Statement stmt;
    
    private static UserServices userservices;

    public UserServices() {
        this.cnx = ConnexionBd.getInstance().getConnection();

    }

    @Override
    public void AjouterUser(User u) {
        try {
            String req = "INSERT INTO fos_user (username,email,enabled,password,confirmation_token,nom,prenom,addresse,telephone) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setInt(3, 1);
            st.setString(4, u.getPassword());
            st.setString(5, u.getConfirmation_token());
            st.setString(6, u.getNom());
            st.setString(7, u.getPrenom());
            st.setString(8, u.getAddresse());
            st.setInt(9, u.getTelephone());
            st.executeUpdate();
            System.out.println("user ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModiferUser(int id, User u) {
        try {
            System.out.println("-------"+u.getNom());
            String req = "UPDATE fos_user SET `username` = ?, `email`= ? , `password`=?, `nom`= ?, `prenom`= ?, `addresse`= ?, `telephone`= ?"
                    + " WHERE id = '" + id + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword()+"{"+u.getUsername()+"}");
            st.setString(4, u.getNom());
            st.setString(5, u.getPrenom());
            st.setString(6, u.getAddresse());
            st.setInt(7, u.getTelephone());

            st.executeUpdate();
            System.out.println("user modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void UpdateEnabledUser(int id, int enabled) {
        try {
            //System.out.println("-------"+u.getNom());
            String req = "UPDATE fos_user SET `enabled` = ?"
                    + " WHERE id = '" + id + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, enabled);
            

            st.executeUpdate();
            System.out.println("user modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void SupprimerUser(int id) {
        try {
            String req = "DELETE FROM fos_user WHERE fos_user.`id` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("user supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Boolean Login(String username, String password) {

        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user WHERE fos_user.`username` = '" + username + "'and  fos_user.`password` like '"+password+"%'");
            
            if (rs.next()) {
                System.out.println("login success");
                return true;
            }
            

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public User AfficherUser(String username) {
 ArrayList<User> listN = new ArrayList<User>();
 User u = new User();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where fos_user.`username`='"+username+"'");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                u.setId(rs.getInt(1));
                        u.setUsername(rs.getString(2));
                        u.setEmail(rs.getString(4));
                        u.setEnabled(rs.getInt(6));
                       //u.setConfirmation_token(rs.getString(10));
                        u.setPassword(rs.getString(8));
                        u.setConfirmation_token(rs.getString(10));
                        u.setNom(rs.getString(13));
                        u.setPrenom(rs.getString(14));
                        u.setAddresse(rs.getString(15));
                        u.setTelephone(rs.getInt(16));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public User AfficherUserId(int id) {
 ArrayList<User> listN = new ArrayList<User>();
 User u = new User();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where fos_user.`id`='"+id+"'");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                u.setId(rs.getInt(1));
                        u.setUsername(rs.getString(2));
                        u.setEmail(rs.getString(4));
                        u.setEnabled(rs.getInt(6));
                       
                        u.setPassword(rs.getString(8));
                        u.setConfirmation_token(rs.getString(10));
                        u.setNom(rs.getString(13));
                        u.setPrenom(rs.getString(14));
                        u.setAddresse(rs.getString(15));
                        u.setTelephone(rs.getInt(16));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    @Override
    public List<User> getAllUser() {
        ArrayList<User> listN = new ArrayList<User>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(10),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getInt(16)
               
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }

    @Override
    public User getUser(String username) {
        User u =new User();
        
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where username='"+username+"'");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                
                        u.setId(rs.getInt(1));
                        u.setUsername(rs.getString(2));
                        u.setEmail(rs.getString(3));
                        u.setEnabled(rs.getInt(4));
                      
                        u.setPassword(rs.getString(5));
                        u.setConfirmation_token(rs.getString(6));
                        u.setNom(rs.getString(7));
                        u.setPrenom(rs.getString(8));
                        u.setAddresse(rs.getString(9));
                        u.setTelephone(rs.getInt(10));
                        
                       
                
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public static UserServices getInstance() {
        if (userservices == null) {
            return userservices = new UserServices();
        }
        return userservices;
    }

    @Override
    public boolean verifAdmin(String username) {
        ArrayList<User> listN = new ArrayList<User>();
 User u = new User();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where fos_user.`username`='"+username+"' and fos_user.`roles` like '%ROLE_AGENT%' ");
            if(rs.next())
                return true;
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

}
