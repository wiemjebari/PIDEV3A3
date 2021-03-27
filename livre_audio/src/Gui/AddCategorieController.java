/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Tools.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import Entities.Categorie;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddCategorieController implements Initializable {

    

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Categorie categorie = null;
    private boolean update;
    int categorieId;
    @FXML
    private JFXTextField tfDescription;
    @FXML
    private JFXTextField tfNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {

        connection = DbConnect.getConnect();
        String nom = tfNom.getText();
        
        String description = tfDescription.getText();

        if (nom.isEmpty() || description.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        tfNom.setText(null);
        tfDescription.setText(null);
       
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `categorie_audio`( `nom`, `description`) VALUES (?,?)";

        }else{
            query = "UPDATE `categorie_audio` SET "
                    + "`nom`=?,"
                    
                    + "`description`= ? WHERE id = '"+categorieId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfNom.getText());
            preparedStatement.setString(2, tfDescription.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String nom, String description) {

        categorieId = id;
        tfNom.setText(nom);
        
        tfDescription.setText(description);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
