/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.livrecrud;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author timou
 */
public class EspaceClientController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox page;
    @FXML
    private TilePane tilepane;
    @FXML
    private TextField moood;
    @FXML
    private Button btnPublier;
    @FXML
    private TextField tfcommenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Livres> list = getLivresList();
          for (Livres r : getLivresList()) {
            
            
             VBox h =new VBox();
            
             Text text=new Text(r.getTitre() );
              MediaView m= new MediaView();
            
             h.setMinWidth(100);
             h.setMinHeight(100);
             String path="file:/C:/Users/walid/3D Objects/LivreCRUD/src/audio/adele.mp3";
             HBox mp3 =new HBox();
             ImageView play=new ImageView();
             ImageView pause=new ImageView();
             ImageView img=new ImageView();
             play.setFitWidth(20);
             play.setFitHeight(20);
             pause.setFitWidth(20);
             img.setFitHeight(100);
             img.setFitWidth(200);
             pause.setFitHeight(20);
             Image play1=new Image("file:/C:/Users/walid/3D Objects/LivreCRUD/build/classes/image/play.png");
             Image pause1=new Image("file:/C:/Users/walid/3D Objects/LivreCRUD/build/classes/image/pause.png");
             Image img1=new Image(r.getImage());
             play.setImage(play1);
             pause.setImage(pause1);
             img.setImage(img1);
             h.getChildren().add(img);
             mp3.getChildren().add(play);
             mp3.getChildren().add(pause);
                        //Media media = new Media(r.getAudio());
                        //MediaPlayer vidd=new MediaPlayer(media);
                        //m.setMediaPlayer(vidd);
                        //mp3.setMinHeight(20);
                        //mp3.getChildren().add(m);
       
             h.setStyle("-fx-background-color:#B7CEEC;-fx-border-color:white");
             h.getChildren().add(text);
             h.getChildren().add(mp3); 
          tilepane.getChildren().add(h);
          play.setOnMouseClicked( (MouseEvent event1) -> {
           //vidd.play();   
          });
          pause.setOnMouseClicked( (MouseEvent event1) -> {
           //vidd.stop();
          });
          
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

   

    @FXML
    private void Publier(ActionEvent event) {
        
        String query = "INSERT INTO commentaire VALUES (" + tfcommenter.getText() + "')";
        executeQuery(query);
        
    }

    @FXML
    private void search(KeyEvent event) {
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
        }
    }
        
    }
    

