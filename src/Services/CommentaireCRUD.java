/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Conseils;
import Entities.User;
import Interfaces.ICommentaire;
import Tools.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yassine
 */
public class CommentaireCRUD implements ICommentaire<Commentaire> {

    private Connection con;
    private Statement ste;

    public CommentaireCRUD() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterCommentaire(Commentaire t) throws SQLException {
        ste = con.createStatement();

        String requeteInsert = "INSERT INTO `projet`.`commentaire` (`idComment` , `idUser`, `idArticle`, `content`, `dateCreation`) VALUES (NULL, '" + t.getUser().getIdUser() + "', '" + t.getConseil().getId_article() + "', '" + t.getContenu() + "', '" + t.getDateCreation() + "' );";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean supprimerCommentaire(Commentaire t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `projet`.`commentaire` where idComment =? ");
        pre.setInt(1, t.getId_commentaire());
        System.out.println("test");
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Comment was deleted successfully!");
        }
        return true;
    }

    @Override
    public boolean updateCommentaire(Commentaire t, int id) throws SQLException {
        String sql = "UPDATE commentaire SET content=?, dateCreation= NOW() WHERE idComment=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getContenu());
        
        statement.setInt(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing Comment was updated successfully!");
        }
        return true;
    }

    @Override
    public List<Commentaire> displayCommentaire(Conseils c) throws SQLException {
        List<Commentaire> arr = new ArrayList<>();

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commentaire WHERE idArticle= " + c.getId_article());

        while (rs.next()) {

            int id = rs.getInt("idComment");
            int idUser = rs.getInt("idUser");
            int idArticle = rs.getInt("idArticle");
            String content = rs.getString("content");
            Date date = rs.getDate("dateCreation");

            ConseilsCRUD sc = new ConseilsCRUD();
            Conseils art = new Conseils();
            art = sc.FindById(idArticle);
            User userConnected = new User(1, "chayma", "ouni", "chayma.ouni@esprit.tn", "chayma123");

            Commentaire comm = new Commentaire(userConnected, art, id, content, date);

            arr.add(comm);

        }

        return arr;
    }

    @Override
    public int NumberOfComments(Conseils c) throws SQLException {
        List<Commentaire> arr = new ArrayList<>();
        int number=0;
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from commentaire WHERE idArticle= " + c.getId_article());
         while (rs.next()) {
             number++;
         }
         return number;
    }

}
