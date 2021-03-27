/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Livre;
import Tools.DbConnect;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class AddLivreController implements Initializable {
String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Livre livre = null;
    private boolean update;
    int livreId;
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXTextField tfEditeur;
    @FXML
    private JFXTextField tfCategorie;
    @FXML
    private JFXTextField tfDuree;
    @FXML
    private JFXTextField tfSource;
    @FXML
    private JFXTextField tfAudio;
    @FXML
    private JFXTextField tfImage;

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
        String titre = tfTitre.getText();
        
        String editeur = tfEditeur.getText();
         String id_categorie = String.valueOf(tfCategorie.getText());
        String duree = String.valueOf(tfDuree.getText());
        String source = tfSource.getText();
        String audio = tfAudio.getText();
        String image = tfImage.getText();
if (titre.isEmpty() || editeur.isEmpty() || id_categorie.isEmpty() || duree.isEmpty() || source.isEmpty() || audio.isEmpty() || image.isEmpty())
{  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les données");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }
    }
@FXML
    private void clean() {
        tfTitre.setText(null);
        tfEditeur.setText(null);
        tfCategorie.setText(null);
        tfDuree.setText(null);
        tfSource.setText(null);
tfAudio.setText(null);
 tfImage.setText(null);      
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `livre_audio`( `titre`, `editeur`, `id_categorie`, `duree`, `source`, `audio`, `image` ) VALUES (?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `livre_audio` SET "
                    + "`titre`=?,"
                     + "`editeur`=?,"
                     + "`id_categorie`=?,"
                     + "`duree`=?," 
                    + "`source`=?,"
                     + "`audio`=?,"
                    
                    + "`image`= ? WHERE id = '"+livreId+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfTitre.getText());
            preparedStatement.setString(2, tfEditeur.getText());
            preparedStatement.setString(3, String.valueOf(tfCategorie.getText()));
            preparedStatement.setString(4, String.valueOf(tfDuree.getText()));
            preparedStatement.setString(5, tfSource.getText());
            preparedStatement.setString(6, tfAudio.getText());
            preparedStatement.setString(7, tfImage.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, String titre, String editeur, int id_categorie, int duree, String source, String audio, String image) {

        livreId = id;
        tfTitre.setText(titre);
        tfEditeur.setText(editeur);
tfCategorie.setText(String.valueOf(id_categorie));
tfDuree.setText(String.valueOf(duree));
        tfSource.setText(source);
        tfAudio.setText(audio);
        tfImage.setText(image); 

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
@FXML
    private void ajouterimage(ActionEvent event) {
         FileChooser filechooser= new FileChooser();
        filechooser.setInitialDirectory(new File("src\\image"));
        //filechooser.getExtensionFilters().add(
        //new FileChooser.ExtensionFilter("*.mp3"));
        File file=filechooser.showOpenDialog(null);
        String fn=  file.getName();
        String ext = fn.substring(fn.lastIndexOf("."));
        if(file!=null){
            if(ext.equals(".jpg")){
            this.tfImage.setText(fn);
            
  
            }else{
            System.out.println("file is not valid");
                JOptionPane.showMessageDialog(null," You have to select an image file", "Invalid File", 0);
        }
        }
    }
     @FXML
    private void ajouteraudio(ActionEvent event) {
          FileChooser filechooser= new FileChooser();
        filechooser.setInitialDirectory(new File("\\audio"));
        //filechooser.getExtensionFilters().add(
        //new FileChooser.ExtensionFilter("*.mp3"));
        File file=filechooser.showOpenDialog(null);
        String fn=  file.getName();
        String ext = fn.substring(fn.lastIndexOf("."));
        if(file!=null){
            if(ext.equals(".mp3")){
            this.tfAudio.setText(fn);
            
  
            }else{
            System.out.println("file is not valid");
                JOptionPane.showMessageDialog(null," You have to select an audio file", "Invalid File", 0);
        }
        }
    
   
    }
    
}
