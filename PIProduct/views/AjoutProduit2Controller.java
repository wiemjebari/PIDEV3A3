/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import gestionproduit.entity.FTTS;
import gestionproduit.entity.Produit;
import gestionproduit.entity.ProduitLike;
import gestionproduit.entity.Upload;
import gestionproduit.services.ProduitLikeService;
import gestionproduit.services.ProduitService;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class AjoutProduit2Controller implements Initializable {
    JFXComboBox<String> categories = new JFXComboBox<>();

    @FXML
    private AnchorPane pass;
    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPanePublication;
     private Label lbpub;
    private Label lbvideo;
    private JFXTextField tfnom;
  private ObservableList<Produit> data = FXCollections.observableArrayList();

        FilteredList<Produit> filteredData = new FilteredList<>(data);
        Label l = new Label();
     private final Image likebutton = new Image("/images/like.png");
      ImageView imageviewlike = new ImageView(likebutton);
      static int recupid;
      int idprod;

    private JFXTextField tfstock;
    private JFXTextField tfprix;
    private JFXTextArea taDescription;
 private File file;
      private File file1 = new File("");
       String vid;
            private Upload up;
            String pic;
            private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
     private Image image;
             ProduitService ps = new ProduitService();
                  int iduser;


public static Produit pp;
    public AjoutProduit2Controller() {
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficherlist();
          categories.getItems().add(("Café"));
          categories.getItems().add(("restaurant"));
          categories.getItems().add(("Supermarché"));
          
         categories.setPromptText("Café");
        // TODO
    }  
     private void upload(ActionEvent event) throws IOException {
        
  FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //pic=(file.toURI().toString());
            pic=new Upload().upload(file,"img");
            System.out.println(pic);
            image= new Image("http://localhost/uploads/"+pic);
            lbpub.setText(pic);
    }
     
      private void uploadvideo(ActionEvent event) {
        
FileInputStream input = null;
            FileChooser fileChooser = new FileChooser();
            //Set extension filter
            extFilterJPG = new FileChooser.ExtensionFilter("mp4 files (*.MP4)", "*.mp4");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("mkv files (*.MKV)", "*.mkv");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg);
            up = new Upload();
            file1 = fileChooser.showOpenDialog(null);

            lbvideo.setText(file1.getPath());
            
            try {
                input = new FileInputStream(file1.getPath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      
      
    private void AjouterProduit(ActionEvent event) {
         boolean ajoute = true;
        Produit p = new Produit();
        if (file1.isFile()) {
                    try {
                          
             vid=up.upload(file1, "video");                                

                    } catch (IOException ex) {
                        Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
        
        
        ProduitService ps= new ProduitService();
        String nom = tfnom.getText();
        String description= taDescription.getText();
        float prix = Float.parseFloat(tfprix.getText().toString());
        int stock = Integer.valueOf(tfstock.getText());
        String categorie = categories.getValue().toString();
        
        Produit p1 = new Produit(categorie,description,pic,vid,prix,stock,nom);
        ps.ajouterProduit(p1);
        ObservableList<Produit> ProduitData = FXCollections.observableArrayList();
        List<Produit> listep= new ArrayList<Produit>();
       listep=ps.consulterProduit();
       ProduitData.addAll(listep);
           String desc = taDescription.getText();
        FTTS FTTSProduit = new FTTS(desc);
                FTTSProduit.speak();

          TrayNotification tray = new TrayNotification("Notification !", "Produit ajoutée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
        afficherlist();
    }

    private void afficherlist() {
        ProduitService evt = new ProduitService();

          Image[] images;
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(ps.consulterProduit());

            int k = 0;
            grid.setHgap(1);
            grid.setVgap((data.stream().count()) + 1);
          
            for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
                for (int j = 0; j < 1; j++) {
                    if (k < data.stream().count()) {
                        String urli = data.get(k).getPhotoProduit();
                          Group root = new Group();
                            ImageView im= new ImageView(new Image("http://localhost/uploadsimg/"+urli));
                                im.setFitWidth(250);
                                im.setFitHeight(200);
                          
                
  Label visit = new Label("");

visit.setOnMouseEntered(event -> {
                    visit.setUnderline(true);
                });
                visit.setOnMouseExited(event -> {
                    visit.setUnderline(false);
                });
                visit.setOnMouseClicked(event -> {
                });
                
                        root.getChildren().add(im);
                         root.setAccessibleText(Integer.valueOf(data.get(k).getIdProduit()).toString());

                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf(data.get(k).getIdProduit()));
                        id.setVisible(false);
                        id.setAccessibleText("id");
                        Pane p1 = new Pane();
                        p1.setStyle("-fx-background-color: white;"
                                + "-fx-background-radius: 10px;"
                                + "-fx-border-color: black;"
                                + "-fx-border-radius:10px;"
                                + "-fx-opacity: 0.6;");
                        Label l2 = new Label("clik me !!!");
                        l2.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        p1.setVisible(false);
                        l2.setVisible(true);
                        l.setOnMouseEntered((MouseEvent event) -> {
                        p1.setVisible(true);

                        });
                        l.setOnMouseExited((MouseEvent event) -> {
                            p1.setVisible(false);
                        });
                        p1.getChildren().add(l2);
                        l.setAlignment(Pos.CENTER);
                
VBox vv = new VBox();
          vv.setSpacing(5);

          
          
         VBox v1 = new VBox();
          v1.setSpacing(5);
          
                  Label l = new Label("");
                  
                        ProduitLikeService prodlikeservice=new ProduitLikeService();
                        int nblike=prodlikeservice.getNumberLike(data.get(k).getIdProduit());
           Label l1 = new Label("");
          // l.setText(Integer.toString(nblike));
            Label l3 = new Label("");
            Label contenue = new Label("Produit: ");
          contenue.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contenuprod = new Label(data.get(k).getDescriptionProduit());
                 contenuprod.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
                 
             
                       Label nom = new Label("Nom: ");
                     nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label nomproduit = new Label(data.get(k).getNomProduit());
                      nomproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                       
                         
               
                 JFXButton supprimer = new JFXButton();
                          
               
                              supprimer.setText("Supprimer ");
                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                supprimer.prefWidth(80);
                supprimer.setAccessibleHelp("Bouton");
                supprimer.setAccessibleText(Integer.toString(data.get(0).getIdProduit()));
                        
                 JFXButton likeBtn = new JFXButton();
                
                likeBtn.setStyle("-fx-text-fill: hite;-fx-font: 15 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-text-color:red;");
                 likeBtn.setText(Integer.toString(nblike));
                likeBtn.setGraphic(imageviewlike);
                likeBtn.setAccessibleText(Integer.toString(data.get(0).getIdProduit()));
                        
                
                      supprimer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    System.out.println("aaa"+data.get(0).getIdProduit());
                                                ps.supprimerProduit(data.get(1).getIdProduit());
                                                   
                                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer votre produit ?");
        alert.show();
        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/views/AjoutProduit2.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                
                      
                      
                      
                      //add button
                      JFXButton affvideo = new JFXButton();
                            
                              affvideo.setText("Favoris ");
                affvideo.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                affvideo.prefWidth(120);
                
                   affvideo.setAccessibleText(Integer.valueOf(data.get(k).getIdProduit()).toString());

                        
                        
                
                      affvideo.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                   recupid=Integer.valueOf(affvideo.getAccessibleText());
                                                                        System.out.println(recupid+"jjjj");     
                                          pp=new Produit();
                                      //  pp=ps.rechercherroduitById(recupid);
                                                    System.out.println("aa"+pp.getVideoProduit());
                                        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/views/VideoProduit.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
                                    }  
                                                }
                                            });
                      
                 likeBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                           idprod=Integer.valueOf(likeBtn.getAccessibleText());
                                         
                                            ProduitLikeService  pls=new ProduitLikeService();
                                            ProduitLike pl=new ProduitLike(iduser,idprod);  
                                            if(!pls.getUserLike(pl))
                                            {
                                            pls.ajouterLike(pl);
                                            
                                           afficherlist();
                                                }
                                            
                                                }
                                                
                                            });
                
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,l1,nom,contenue);
                     v1.getChildren().addAll(likeBtn,contenue,nomproduit,contenuprod,supprimer ,affvideo);
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPanePublication.setContent(grid);
                      
                    }

                }

                for (Node node : grid.getChildren()) {
                    if (node instanceof VBox) {
                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof ImageView) {
                                node1.setOnMouseClicked((MouseEvent E) -> {
                                });
                            }

                        }
                    }
                }

                grid.setOnMouseClicked((MouseEvent E) -> {
                    for (Node node : grid.getChildren()) {

                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof Group) {
                                node1.setOnMouseClicked((MouseEvent E1) -> {

                                    try {
                                        // recupid = Integer.valueOf(node1.getAccessibleText());
                                        
                                        
                                     //   recupid = Integer.valueOf(node1.getAccessibleText());
                                      System.out.println(recupid+"-------------------");
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/views/AjoutProduit2.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        //      System.out.println(recupid);
                                        //        System.out.println(annonce);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AjoutProduit2Controller.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


            }
    
    }
    
}
