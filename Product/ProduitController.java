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
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField tfidP;
    @FXML
    private TextField tfNomP;
    @FXML
    private TextField tfNomC;
    @FXML
    private TextField tfPrixP;
    @FXML
    private TextField tfQuantite;
    
     @FXML
    private TextField search;
    @FXML
    private TableView<Produit> tbProduit;
    @FXML
    private TableColumn<Produit, Integer> colidP;
    @FXML
    private TableColumn<Produit, String> colNomP;
    @FXML
    private TableColumn<Produit, String> colNomC;
    @FXML
    private TableColumn<Produit, Float> colPrixP;
    @FXML
    private TableColumn<Produit, Integer> colQuantite;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
      int index=-1;
    @FXML
    private AnchorPane bp;

    @FXML
    private void handleButtonAction(ActionEvent event) {
    if(event.getSource() == btnInsert){
            insertProduct();
        } else if (event.getSource() == btnUpdate){
            updateProduct();
    }else if(event.getSource() == btnDelete){
            deleteProduct();
        }

    }
    
   
 

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showProduct();
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
    
public ObservableList <Produit> getProductList(){
  
    ObservableList <Produit> ProductList=FXCollections.observableArrayList();
    Connection conn=getConnection();
    String query="SELECT * FROM produit";
    Statement st;
    ResultSet rs;
    
    try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
        Produit produit;
        while(rs.next()){
            produit=new Produit(rs.getInt("id_produit"),rs.getString("nom_produit"),rs.getString("nom_categorie"),rs.getFloat("prix_produit"),rs.getInt("quantite"));
            ProductList.add(produit);
        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
    return ProductList;
}


public void showProduct(){
        ObservableList<Produit> list = getProductList();
    colidP.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id_produit"));
    colNomP.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_produit"));
    colNomC.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_categorie"));
    colPrixP.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit"));
    colQuantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
    
    tbProduit.setItems(list);
    
}

private void insertProduct(){
    String query = " INSERT INTO produit VALUES (" + tfidP.getText() + ",'" + tfNomP.getText() + "','" + tfNomC.getText() + "'," 
            + tfPrixP.getText() + "," + tfQuantite.getText()+")";
            executeQuery(query);
            JOptionPane.showMessageDialog(null, " Produit ajouté");
            showProduct();
}


 @FXML
    void getSelected (MouseEvent event){
    index = tbProduit.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
    tfidP.setText(colidP.getCellData(index).toString());
    tfNomP.setText(colNomP.getCellData(index));
    tfNomC.setText(colNomC.getCellData(index));
    tfPrixP.setText(colPrixP.getCellData(index).toString());
    tfQuantite.setText(colQuantite.getCellData(index).toString());}

 private void updateProduct(){
        String query = "UPDATE  produit SET nom_produit  = '" + tfNomP.getText() + "', nom_categorie = '" + tfNomC.getText() + "', prix_produit = " +
                tfPrixP.getText() + ", quantite = " + tfQuantite.getText() + " WHERE id_produit = " + tfidP.getText() + "";
        executeQuery(query);
         showProduct();
        JOptionPane.showMessageDialog(null, "voulez-vous que Le Produit avec l'id :  "+tfidP.getText()+" sera modifié ?");
       
    }
 
       private void deleteProduct(){
        String query = "DELETE FROM produit WHERE id_produit =" + tfidP.getText() + "";
        executeQuery(query);
        JOptionPane.showMessageDialog(null, "voulez-vous que Le Produit avec l'id :  "+tfidP.getText()+" sera supprimé ?");
        showProduct();
    }
       
        void search() {
        ObservableList<Produit> list = getProductList();
        Connection conn=getConnection();
         
    colidP.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id_produit"));
    colNomP.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_produit"));
    colNomC.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_categorie"));
    colPrixP.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit"));
    colQuantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
        
    tbProduit.setItems(list);
    FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);  
    search.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (String.valueOf(person.getId_produit()).indexOf(lowerCaseFilter) != -1 ) {
     return true; 
    } else if (person.getNom_produit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; 
    }else if (person.getNom_categorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; 
    }else if (String.valueOf(person.getPrix_produit()).indexOf(lowerCaseFilter) != -1) {
     return true; 
    }
    else if (String.valueOf(person.getQuantite()).indexOf(lowerCaseFilter)!=-1)
         return true;
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Produit> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tbProduit.comparatorProperty());  
  tbProduit.setItems(sortedData);      
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

    }
           
            
         



        
    

    
