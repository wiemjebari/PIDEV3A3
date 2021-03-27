/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Tools.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import Entities.Categorie;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class DisplayCategorieController implements Initializable {

    
    @FXML
    private TableColumn<Categorie, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Categorie categorie = null ;
    
    ObservableList<Categorie>  CategorieList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Categorie , String> colNom;
    @FXML
    private TableColumn<Categorie , String> colDescription;
    @FXML
    private TextField search;
    @FXML
    private TableView<Categorie> tvCategorie;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
            search();

    }    
    
    
    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/addCategorie.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void refreshTable() {
        try {
            CategorieList.clear();
            
            query = "SELECT * FROM `categorie_audio`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                CategorieList.add(new  Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),                        
                        resultSet.getString("description")));
                tvCategorie.setItems(CategorieList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                 search();

    }


    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        
        //add cell of button edit 
         Callback<TableColumn<Categorie, String>, TableCell<Categorie, String>> cellFoctory = (TableColumn<Categorie, String> param) -> {
            // make cell containing buttons
            final TableCell<Categorie, String> cell = new TableCell<Categorie, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                categorie = tvCategorie.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `categorie_audio` WHERE id  ="+categorie.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            categorie = tvCategorie.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/Gui/addCategorie.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddCategorieController addCategorieController = loader.getController();
                            addCategorieController.setUpdate(true);
                            addCategorieController.setTextField(categorie.getId(), categorie.getNom(), 
                                     categorie.getDescription());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         tvCategorie.setItems(CategorieList);
         
                  search();

    }

    @FXML
    private void search() {
        colNom.setCellValueFactory(new PropertyValueFactory<Categorie , String>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Categorie , String>("description"));
     
        
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Categorie> filteredData = new FilteredList<>(CategorieList, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Categorie -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Categorie.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvCategorie.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvCategorie.setItems(sortedData);
    }

    @FXML
    private void gotolivre(MouseEvent event) {
         try {
            FXMLLoader fxmll= new FXMLLoader(getClass().getResource("DisplayLivre.fxml"));
            Parent root= fxmll.load();
            search.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void gotocategorie(MouseEvent event) {
         try {
            FXMLLoader fxmll= new FXMLLoader(getClass().getResource("DisplayCategorie.fxml"));
            Parent root= fxmll.load();
            search.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void gotochart(MouseEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }
    
}
