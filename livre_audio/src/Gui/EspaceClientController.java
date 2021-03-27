/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Livre;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private TextField tfcommentaire;
    @FXML
    private TableView<?> coltext;
    @FXML
    private TableColumn<?, ?> colt;
  
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Livre> list = (ObservableList<Livre>) getLivresList();

          for (Livre r : getLivresList()) {
            
            
             VBox h =new VBox();
            
             Text text=new Text(r.getTitre() );
              MediaView m= new MediaView();
            
             h.setMinWidth(100);
             h.setMinHeight(100);
             String path="\\audio\\231.mp3";
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
             Image play1=new Image("file:/C:/Users/walid/Desktop/GestionAudio/src/image/play.png");
             Image pause1=new Image("file:/C:/Users/walid/Desktop/GestionAudio/src/image/pause.png");
             Image img1=new Image(r.getImage());
             play.setImage(play1);
             pause.setImage(pause1);
             img.setImage(img1);
             h.getChildren().add(img);
             mp3.getChildren().add(play);
             mp3.getChildren().add(pause);
                        Media media = new Media(r.getAudio());
                        MediaPlayer mediaPlayer=new MediaPlayer(media);

                        mp3.setMinHeight(20);
                        mp3.getChildren().add(m);
       
             h.setStyle("-fx-background-color:#B7CEEC;-fx-border-color:white");
             h.getChildren().add(text);
             h.getChildren().add(mp3); 
          tilepane.getChildren().add(h);
          play.setOnMouseClicked( (MouseEvent event1) -> {
           mediaPlayer.play();   
          });
          pause.setOnMouseClicked( (MouseEvent event1) -> {
           mediaPlayer.stop();
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
 
  public ObservableList<Livre> getLivresList(){
        ObservableList<Livre> livreList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM livre_audio";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Livre livre_audio;
            while(rs.next()){
                livre_audio = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("editeur"),rs.getInt("id_categorie"), rs.getInt("duree"),rs.getString("source"),rs.getString("audio"),rs.getString("image"));
                livreList.add(livre_audio);
            }
                
        }catch(SQLException ex){
        }
        return livreList;
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

    private void gotoqr(ActionEvent event) throws WriterException {
       QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://java-buddy.blogspot.com/";
        int width = 50;
        int height = 50;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        System.out.println("Success...");
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
    }

   

     
    }
    

