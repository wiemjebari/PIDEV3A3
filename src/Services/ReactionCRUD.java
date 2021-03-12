/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Conseils;
import Entities.Reaction;
import Entities.User;
import Interfaces.IReaction;
import Tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yassine
 */
public class ReactionCRUD implements IReaction<Reaction> {

    private Connection con;
    private Statement ste;

    public ReactionCRUD() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterReaction(Reaction t) throws SQLException {
        ste = con.createStatement();

        String requeteInsert = "INSERT INTO `projet`.`reaction` (`idReaction` , `idUser`, `idArticle`, `type`) VALUES (NULL, '" + t.getUser().getIdUser() + "', '" + t.getConseil().getId_article() + "', '" + t.getType() + "' );";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public void supprimerReaction(Reaction t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `projet`.`reaction` where idReaction = " + t.getIdReaction());

        System.out.println("this is " + t);
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("An reaction was deleted successfully!");
        }

    }

    public int FindReaction(int idArticle, int idUser) throws SQLException {
        String req = "select * from reaction where idArticle  = ? AND idUser = ?";
        int type = 0;
        Conseils conseil = null;

        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, idArticle);
        ps.setInt(2, idUser);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getInt("type") == 1) {
                type = 1;
            }
            if (resultSet.getInt("type") == 0) {
                type = 2;
            }

        } else {
            type = 0;
        }

        return type;
    }

    public Reaction FindById(int idArticlee, int idUser) throws SQLException {
        String req = "select * from reaction where idArticle  = ? AND idUser = ?";
        Reaction reaction = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idArticlee);
            ps.setInt(2, idUser);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("Im in ///");
                System.out.println(resultSet.getInt(3));
                ConseilsCRUD ccc = new ConseilsCRUD();
                Conseils cons = new Conseils();
                        cons = ccc.FindById(resultSet.getInt(3));
                System.out.println(cons);
                User userConnected = new User(1, "chayma", "ouni", "chayma.ouni@esprit.tn", "chayma123");
                reaction = new Reaction(resultSet.getInt(1), userConnected,cons, resultSet.getInt(4));
                System.out.println(reaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reaction;
    }
    
     public int NumberReacts(int idArticlee) throws SQLException {
        String req = "select * from reaction where idArticle  = ?";
       int nb = 0;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idArticlee);
           
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                nb++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
    }
}


