/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TableView<Categorie> tbCategorie;
    @FXML
    private TableColumn<Categorie, Integer> colId;
    @FXML
    private TableColumn<Categorie, String> colNom;
    @FXML
    private TableColumn<Categorie, String> colAdresse;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField search;
    
    
    
         int index=-1;

    @FXML
    private void handleButtonAction(ActionEvent event) {
    if(event.getSource() == btnInsert){
            insertCategorie();
        } else if (event.getSource() == btnUpdate){
            updateCategorie();
    }else if(event.getSource() == btnDelete){
            deleteCategorie();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCategorie();
        search();
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
       public ObservableList <Categorie> getCategorieList(){
  
    ObservableList <Categorie> CategorieList=FXCollections.observableArrayList();
    Connection conn=getConnection();
    String query="SELECT * FROM categorie";
    Statement st;
    ResultSet rs;
    
    try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
        Categorie categorie;
        while(rs.next()){
            categorie=new Categorie(rs.getInt("id_categorie"),rs.getString("nom_categorie"),rs.getString("adresse"));
            CategorieList.add(categorie);
        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
    return CategorieList;
}


public void showCategorie(){
        ObservableList<Categorie> Categorielist = getCategorieList();
    colId.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id_categorie"));
    colNom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom_categorie"));
    colAdresse.setCellValueFactory(new PropertyValueFactory<Categorie,String>("adresse"));
    
    
    tbCategorie.setItems(Categorielist);
    
}


     @FXML
    private void getSelected(MouseEvent event) {
           index = tbCategorie.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }   
    tfId.setText(colId.getCellData(index).toString());
    tfNom.setText(colNom.getCellData(index));
    tfAdresse.setText(colAdresse.getCellData(index));}
    

 

    private void deleteCategorie() {
     String query = "DELETE FROM categorie WHERE id_categorie =" + tfId.getText() + "";
        executeQuery(query);
        JOptionPane.showMessageDialog(null, " La catégorie avec l'id :  "+tfId.getText()+" est supprimé ?");
        showCategorie();
    }
    
        void search() {
        ObservableList<Categorie> list = getCategorieList();
        Connection conn=getConnection();
         
    colId.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id_categorie"));
    colNom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom_categorie"));
    colAdresse.setCellValueFactory(new PropertyValueFactory<Categorie,String>("adresse"));
   
        
    tbCategorie.setItems(list);
    FilteredList<Categorie> filteredData = new FilteredList<>(list, b -> true);  
    search.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(categorie -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (String.valueOf(categorie.getId_categorie()).indexOf(lowerCaseFilter) != -1 ) {
     return true; 
    } else if (categorie.getNom_categorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; 
    }else if (categorie.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; 
    
    }
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Categorie> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tbCategorie.comparatorProperty());  
  tbCategorie.setItems(sortedData);      
    }
     

    private void executeQuery(String query) {
          Connection conn=getConnection();
       Statement st;
       try{
           st=conn.createStatement();
           st.executeUpdate(query);
       }catch(Exception ex){
           ex.printStackTrace();
       }
       }

    private void insertCategorie() {
           String query = "INSERT INTO categorie VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfAdresse.getText() +"')";
        executeQuery(query);
         JOptionPane.showMessageDialog(null, " Catégorie ajouté");
        showCategorie();
        
           
           
    }

    private void updateCategorie() {
        String query = "UPDATE  categorie SET nom_categorie  = '" + tfNom.getText() + "' WHERE id_categorie = " + tfId.getText() + "";
        executeQuery(query);
         JOptionPane.showMessageDialog(null, " Catégorie modifié");
        showCategorie();
    }
    }
    

