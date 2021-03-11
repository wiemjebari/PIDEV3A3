/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.livrecrud;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class CategoriesController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TableView<Categories> tvCategories;
    @FXML
    private TableColumn<Categories, Integer> colId;
    @FXML
    private TableColumn<Categories, String> colNom;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

   int index = -1;
        
     ObservableList<Livres> DataList;
    @FXML
    private TextField search;

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
        showCategories();
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
    
   public ObservableList<Categories> getCategoriesList(){
        ObservableList<Categories> categorieList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM categorie_audio";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Categories categorie_audio;
            while(rs.next()){
                categorie_audio = new Categories(rs.getInt("id"), rs.getString("nom"));
                categorieList.add(categorie_audio);
            }
                
        }catch(SQLException ex){
        }
        return categorieList;
    }
    
    
    public void showCategories(){
         ObservableList<Categories> list = getCategoriesList();
        
       colId.setCellValueFactory(new PropertyValueFactory<Categories,Integer>("id"));
         colNom.setCellValueFactory(new PropertyValueFactory<Categories,String>("nom"));
         
         tvCategories.setItems(list);
        
    }
   private void insertRecord(){
        String query = "INSERT INTO categorie_audio VALUES (" + tfID.getText() + ",'" + tfNom.getText() + "')";
        executeQuery(query);
        showCategories();
    }
   private void updateRecord(){
        String query = "UPDATE  categorie_audio SET nom  = '" + tfNom.getText() + "' WHERE id = " + tfID.getText() + "";
        executeQuery(query);
        showCategories();
    }
    private void deleteButton() {
         String query = "DELETE FROM categorie_audio WHERE id =" + tfID.getText() + "";
        executeQuery(query);
        showCategories();
        
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
    index = tvCategories.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfID.setText(colId.getCellData(index).toString());
    tfNom.setText(colNom.getCellData(index));
    

    
    }

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void gotolivre(MouseEvent event) {
        try {
            FXMLLoader fxmll= new FXMLLoader(getClass().getResource("Livres.fxml"));
            Parent root= fxmll.load();
            search.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void gotocategorie(MouseEvent event) {
    }

    @FXML
    private void gotochart(MouseEvent event) {
    }
   
   
}


