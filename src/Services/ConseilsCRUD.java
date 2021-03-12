/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Conseils;
import Interfaces.IConseils;
import Tools.MyConnection;
import java.sql.Connection;
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
public class ConseilsCRUD implements IConseils<Conseils> {

    private Connection con;
    private Statement ste;

    public ConseilsCRUD() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterConseils(Conseils t) throws SQLException {

        ste = con.createStatement();

        String requeteInsert = "INSERT INTO `projet`.`conseils` (`id_article` , `Titre_article`, `description`, `image`) VALUES (NULL, '" + t.getTitre_article() + "', '" + t.getDescription() + "', '" + t.getImage() + "' );";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean supprimerConseils(Conseils t) throws SQLException {

        PreparedStatement pre = con.prepareStatement("DELETE FROM `projet`.`conseils` where id_article =? ");
        pre.setInt(1, t.getId_article());
        System.out.println("test");
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("An Article was deleted successfully!");
        }
        return true;
    }

    @Override
    public boolean updateConseils(Conseils t, int id) throws SQLException {

        String sql = "UPDATE conseils SET Titre_article=?, description=?, image=? WHERE id_article=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getTitre_article());
        statement.setString(2, t.getDescription());
        statement.setString(3, t.getImage());
        statement.setInt(4, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing Article was updated successfully!");
        }
        return true;
    }

    @Override
    public List<Conseils> displayConseils() throws SQLException {
        List<Conseils> arr = new ArrayList<>();

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from conseils");
        while (rs.next()) {
            int id = rs.getInt("id_article");

            String Titre = rs.getString("Titre_article");
            String Description = rs.getString("description");
            String image = rs.getString("image");

            Conseils c = new Conseils(id, Titre, Description, image);

            arr.add(c);

        }

        return arr;
    }

    @Override
    public Conseils FindById(int id) throws SQLException {
        String req = "select * from conseils where id_article  = ?";
        Conseils conseil = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {

                conseil = new Conseils(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                System.out.println(conseil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conseil;
    }

}
