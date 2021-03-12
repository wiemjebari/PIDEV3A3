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
public class PanierController implements Initializable {

    @FXML
    private AnchorPane bp;
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
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField search;
    @FXML
    private TableView<Panier> tbPanier;
    @FXML
    private TableColumn<Panier, Integer> ColIdPanier;
    @FXML
    private TableColumn<Panier, Integer> ColIdP;
    @FXML
    private TableColumn<Panier, String> ColNomP;
    @FXML
    private TableColumn<Panier, String> ColNomC;
    @FXML
    private TableColumn<Panier, Float> ColPrix;
    @FXML
    private TableColumn<Panier, Integer> ColQuantite;
    @FXML
    private TextField tfidPanier;
    int index=-1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btnInsert){
            validate();
        } else if (event.getSource() == btnUpdate){
            updateProduct();
    }else if(event.getSource() == btnDelete){
            deleteProduct();
        }
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
    
      public ObservableList <Panier> getPanierList(){
  
    ObservableList <Panier> PanierList=FXCollections.observableArrayList();
    Connection conn=getConnection();
    String query="SELECT * FROM panier";
    Statement st;
    ResultSet rs;
    
    try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
        Panier panier;
        while(rs.next()){
            panier=new Panier(rs.getInt("id_panier"),rs.getInt("id_produit"),rs.getString("nom_produit"),rs.getString("nom_categorie"),rs.getFloat("prix_produit"),rs.getInt("quantite"));
            PanierList.add(panier);
        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
    return PanierList;
}

    public void showPanier(){
        ObservableList<Panier> list = getPanierList();
    ColIdPanier.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("id_panier"));
    ColIdP.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("id_produit"));
    ColNomP.setCellValueFactory(new PropertyValueFactory<Panier,String>("nom_produit"));
    ColNomC.setCellValueFactory(new PropertyValueFactory<Panier,String>("nom_categorie"));
    ColPrix.setCellValueFactory(new PropertyValueFactory<Panier,Float>("prix_produit"));
    ColQuantite.setCellValueFactory(new PropertyValueFactory<Panier,Integer>("quantite"));
    
    tbPanier.setItems(list);}
    
     void getSelected (MouseEvent event){
    index = tbPanier.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    } 
    
    tfidPanier.setText(ColIdPanier.getCellData(index).toString());
    tfidP.setText(ColIdP.getCellData(index).toString());
    tfNomP.setText(ColNomP.getCellData(index));
    tfNomC.setText(ColNomC.getCellData(index));
    tfPrixP.setText(ColPrix.getCellData(index).toString());
    tfQuantite.setText(ColQuantite.getCellData(index).toString());}

    private void validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateProduct() {
         String query = "UPDATE  produit SET nom_produit  = '" + tfNomP.getText() + "', nom_categorie = '" + tfNomC.getText() + "', prix_produit = " +
                tfPrixP.getText() + ", quantite = " + tfQuantite.getText() + " WHERE id_produit = " + tfidP.getText() + "";
        executeQuery(query);
         showPanier();
        JOptionPane.showMessageDialog(null, "Le Produit avec l'id :  "+tfidP.getText()+" sera modifié ?");
    }

    private void deleteProduct() {
         String query = "DELETE FROM produit WHERE id_panier =" + tfidPanier.getText() + "";
        executeQuery(query);
        JOptionPane.showMessageDialog(null, " Le Produit avec l'id :  "+tfidP.getText()+" sera supprimé ?");
        showPanier();
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

