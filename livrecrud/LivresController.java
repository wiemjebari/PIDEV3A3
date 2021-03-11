/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.livrecrud;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author walid
 */
public class LivresController implements Initializable {
    
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfEditeur;
    @FXML
    private TextField tfCategorie;
    @FXML
    private TextField tfDuree;
    @FXML
    private TextField tfSource;
    @FXML
    private TextField tfAudio;
     @FXML
    private TextField tfImage;
    @FXML
    private TableView<Livres> tvLivres;
    @FXML
    private TableColumn<Livres,Integer> colId;
    @FXML
    private TableColumn<Livres,String> colTitre;
    @FXML
    private TableColumn<Livres,String> colEditeur;
     @FXML
    private TableColumn<Livres,Integer> colCategorie;
    @FXML
    private TableColumn<Livres,Integer> colDuree;
    @FXML
    private TableColumn<Livres,String> colSource;
    @FXML
    private TableColumn<Livres,String> colAudio;
    @FXML
    private TableColumn<Livres,String> colImage;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
     @FXML
    private Button ajouteraudio;
    @FXML
    private TextField search;
        int index = -1;
   
   
    
    
   
        

    @FXML
    private void handleButtonAction(ActionEvent event) {
    if(event.getSource() == btnInsert){
            insertRecord();
        }
    else if (event.getSource() == btnUpdate){
            updateRecord();
        }
    else if(event.getSource() == btnDelete){
            deleteButton();
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showLivres();
    }    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
        
    }
    
   public ObservableList<Livres> getLivresList(){
        ObservableList<Livres> livreList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM livre_audio";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Livres livre_audio;
            while(rs.next()){
                livre_audio = new Livres(rs.getInt("id"), rs.getString("titre"), rs.getString("editeur"),rs.getInt("id_categorie"), rs.getInt("duree"),rs.getString("source"),rs.getString("audio"),rs.getString("image"));
                livreList.add(livre_audio);
            }
                
        }catch(SQLException ex){
        }
        return livreList;
    }
    
    
    public void showLivres(){
         ObservableList<Livres> list = getLivresList();
        
       colId.setCellValueFactory(new PropertyValueFactory<Livres,Integer>("id"));
         colTitre.setCellValueFactory(new PropertyValueFactory<Livres,String>("titre"));
         colEditeur.setCellValueFactory(new PropertyValueFactory<Livres,String>("editeur"));
         colCategorie.setCellValueFactory(new PropertyValueFactory<Livres,Integer>("id_categorie"));
         colDuree.setCellValueFactory(new PropertyValueFactory<Livres,Integer>("duree"));
         colSource.setCellValueFactory(new PropertyValueFactory<Livres,String>("source"));
         colAudio.setCellValueFactory(new PropertyValueFactory<Livres,String>("audio"));
         colImage.setCellValueFactory(new PropertyValueFactory<Livres,String>("image"));
         tvLivres.setItems(list);
        
    }
   private void insertRecord(){
        String query = "INSERT INTO livre_audio VALUES (" + tfID.getText() + ",'" + tfTitre.getText() + "','" + tfEditeur.getText() + "','"
                + tfCategorie.getText() + "'," + tfDuree.getText() + ",'" + tfSource.getText() + "','" + tfAudio.getText() + "','" + tfImage.getText() + "')";
        executeQuery(query);
        showLivres();
    }
   private void updateRecord(){
        String query = "UPDATE  livre_audio SET titre  = '" + tfTitre.getText() + "', editeur = '" + tfEditeur.getText() + "', id_categorie = " +
                tfCategorie.getText() + ", duree = " + tfDuree.getText() + ",source = '" + tfSource.getText() + "', audio = '" + tfAudio.getText() + "' , image = '" + tfImage.getText() + "' WHERE id = " + tfID.getText() + "";
        executeQuery(query);
        showLivres();
    }
    private void deleteButton() {
         String query = "DELETE FROM livre_audio WHERE id =" + tfID.getText() + "";
        executeQuery(query);
        showLivres();
        
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

  @FXML  
 void getSelected (MouseEvent event){
    index = tvLivres.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfID.setText(colId.getCellData(index).toString());
    tfTitre.setText(colTitre.getCellData(index));
    tfEditeur.setText(colEditeur.getCellData(index));
    tfCategorie.setText(colCategorie.getCellData(index).toString());
    tfDuree.setText(colDuree.getCellData(index).toString());
    tfSource.setText(colSource.getCellData(index));
    tfAudio.setText(colAudio.getCellData(index));
    tfImage.setText(colImage.getCellData(index));


    
    }

    @FXML
    private void ajouteraudio(ActionEvent event) {
        FileChooser filechooser= new FileChooser();
        filechooser.setInitialDirectory(new File("src\\audio"));
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
    private void search(KeyEvent event) {
        
    }

    @FXML
    private void gotocategorie(MouseEvent event) {
          try {
            FXMLLoader fxmll= new FXMLLoader(getClass().getResource("Categories.fxml"));
            Parent root= fxmll.load();
            search.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void gotochart(MouseEvent event) {
    }
   
    }
