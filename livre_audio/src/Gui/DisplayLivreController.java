/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Livre;
import Tools.DbConnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class DisplayLivreController implements Initializable {
    

    @FXML
    private TableColumn<Livre, String> colTitre;
    @FXML
    private TableColumn<Livre, String> colEditeur;
    @FXML
    private TableColumn<Livre, Integer> colCategorie;
    @FXML
    private TableColumn<Livre, Integer> colDuree;
    @FXML
    private TableColumn<Livre, String> colSource;
    @FXML
    private TableColumn<Livre, String> colAudio;
    @FXML
    private TableColumn<Livre, String> colImage;
    @FXML
    private TableColumn<Livre, String> editCol;
    @FXML
    private TextField search;
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Livre> tvLivre;
 
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Livre livre = null ;
    
    ObservableList<Livre>  LivreList = FXCollections.observableArrayList();
   



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/addLivre.fxml"));
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
            LivreList.clear();
            
            query = "SELECT * FROM `livre_audio`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                LivreList.add(new  Livre(
                resultSet.getInt("id"),
                resultSet.getString("titre"),                        
                resultSet.getString("editeur"),
                resultSet.getInt("id_categorie"),
                resultSet.getInt("duree"),
                resultSet.getString("source"),
                resultSet.getString("audio"),
                resultSet.getString("image")));
                tvLivre.setItems(LivreList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        search();
        
    }


    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colEditeur.setCellValueFactory(new PropertyValueFactory<>("Editeur"));
         colCategorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        colDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
         colSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        colAudio.setCellValueFactory(new PropertyValueFactory<>("audio"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));

       
        
        //add cell of button edit 
         Callback<TableColumn<Livre, String>, TableCell<Livre, String>> cellFoctory = (TableColumn<Livre, String> param) -> {
            // make cell containing buttons
            final TableCell<Livre, String> cell = new TableCell<Livre, String>() {
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
                                livre = tvLivre.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `livre_audio` WHERE id  ="+livre.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           livre = tvLivre.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/Gui/addLivre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(DisplayCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddLivreController addLivreController = loader.getController();
                            addLivreController.setUpdate(true);
                            addLivreController.setTextField(livre.getId(), livre.getTitre(), 
                                    livre.getEditeur(),livre.getId_categorie(), livre.getDuree(), livre.getSource(), livre.getAudio(), livre.getImage());                     
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
         tvLivre.setItems(LivreList);
         search();
         
    }

    @FXML
    private void search() {
        
      colTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        colEditeur.setCellValueFactory(new PropertyValueFactory<Livre, String>("Editeur"));
         colCategorie.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id_categorie"));
        colDuree.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("duree"));
         colSource.setCellValueFactory(new PropertyValueFactory<Livre, String>("source"));
        colAudio.setCellValueFactory(new PropertyValueFactory<Livre, String>("audio"));
        colImage.setCellValueFactory(new PropertyValueFactory<Livre, String>("image"));
        
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Livre> filteredData = new FilteredList<>(LivreList, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Livre -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Livre.getTitre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livre> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvLivre.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvLivre.setItems(sortedData);
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

    
}
