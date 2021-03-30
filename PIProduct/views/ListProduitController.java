/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import gestionproduit.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import gestionproduit.entity.Produit;
import gestionproduit.services.ProduitService;
import java.io.File;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class ListProduitController implements Initializable {
      @FXML
    private AnchorPane box1;
    @FXML
    private ImageView photo1;
    @FXML
    private Label produit1;
    @FXML
    private JFXButton modif1;
    @FXML
    private JFXButton supp1;
    @FXML
    private AnchorPane box2;
    @FXML
    private Label produit2;
    @FXML
    private JFXButton modif2;
    @FXML
    private JFXButton supp2;
    @FXML
    private ImageView photo2;
    @FXML
    private Label prix2;
    @FXML
    private Label stock2;
    @FXML
    private Label nom2;
    @FXML
    private Label description2;
    @FXML
    private Pagination paginator;
    @FXML
    private Label prix1;
    @FXML
    private Label stock1;
    @FXML
    private Label nom1;
    @FXML
    private Label description1;
    @FXML
    private AnchorPane box3;
    @FXML
    private Label produit3;
    @FXML
    private JFXButton modif3;
    @FXML
    private JFXButton supp3;
    @FXML
    private ImageView photo3;
    @FXML
    private Label prix3;
    @FXML
    private Label stock3;
    @FXML
    private Label nom3;
    @FXML
    private Label description3;
    @FXML
    private AnchorPane box4;
    @FXML
    private Label produit4;
    @FXML
    private JFXButton modif4;
    @FXML
    private JFXButton supp4;
    @FXML
    private ImageView photo4;
    @FXML
    private Label prix4;
    @FXML
    private Label stock4;
    @FXML
    private Label nom4;
    @FXML
    private Label description4;
    private ChoiceBox<String> choixInsertion1;
    private StackPane vide;
    ArrayList<Produit> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ProduitService service = new ProduitService();

        list = (ArrayList<Produit>) service.consulterProduit();

        if (list.isEmpty()) {
            box1.setVisible(false);
            box2.setVisible(false);
            box3.setVisible(false);
            box4.setVisible(false);
            paginator.setVisible(false);
           vide.setVisible(true);
        }
      else {
         //   setNbPages();
           initAnnonceAccouplementPage(0);
//                       vide.setVisible(false);

        }
        }
//     choixInsertion1.getItems().setAll("Informatique et multimédia", "Vehicule", "Immobilier","Habillement et bien etre","Informatique et multimédia");


    
   

    

    public List<Produit> getListProduit(int index) {

        int start = 4 * index;
        int fin = start + 4;
        if (list.size() > start) {
            if (list.size() > fin) {
                return list.subList(start, fin);
            } else {
                return list.subList(start, list.size());
            }
        }
        return list.subList(0, 3);
    }

    private void initAnnonceAccouplementPage(int index) {

        // utilisateurservice = new UtilisateurServices();
        paginator.setCurrentPageIndex(index);
        List<Produit> produit = getListProduit(index);
        if (produit.size() >= 1) {
            box1.setVisible(true);

          //  Utilisateur u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(0).getId_utilisateur());
            produit1.setText(produit.get(0).getCategorieProduit());
            nom1.setText(produit.get(0).getNomProduit());
            prix1.setText(String.valueOf(produit.get(0).getPrixProduit()));
            stock1.setText(String.valueOf(produit.get(0).getStockProduit()));
            description1.setText(String.valueOf(produit.get(0).getDescriptionProduit()).substring(0, 10));
            System.out.println(" hhhhhh"+produit.get(0).getPhotoProduit());

             File file = new  File("http://localhost/uploadsimg/" + produit.get(0).getPhotoProduit());
          //  photo1;     
          Image image = new Image(file.toURI().toString());

photo1.setImage(image);
         photo1.setFitHeight(225);
          photo1.setFitWidth(250);


        } else {
            box1.setVisible(false);
        }

        ///////////////////////////////////////////////////////
        if (produit.size() >= 2) {
            box2.setVisible(true);

          //  User u = utilisateurservice.rechercher(QuatreAnnonceAccouplements.get(1).getId_utilisateur());
            produit2.setText(produit.get(0).getCategorieProduit());
            nom2.setText(produit.get(0).getNomProduit());
            prix2.setText(String.valueOf(produit.get(0).getPrixProduit()));
            stock2.setText(String.valueOf(produit.get(0).getStockProduit()));
            description2.setText(String.valueOf(produit.get(0).getDescriptionProduit()).substring(0, 10));

               ImageView im = new  ImageView("http://localhost/uploadsimg/" + produit.get(0).getPhotoProduit());
//                    photo2.getChildren().add(im);

/*
            modif2.setOnAction((ActionEvent e) -> {
                modifierannonce(produit.get(1), u);
                valider.setOnAction((ActionEvent e1) -> {
                    modifier(QuatreAnnonceAccouplements.get(1).getId());
                });
            });
            supp2.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(1).getId());
            });
*/
        } else {
            box2.setVisible(false);
        }
        ///////////////////////////////////////////////////////////

        if (produit.size() >= 3) {
            box3.setVisible(true);

          produit3.setText(produit.get(0).getCategorieProduit());
            nom3.setText(produit.get(0).getNomProduit());
            prix3.setText(String.valueOf(produit.get(0).getPrixProduit()));
            stock3.setText(String.valueOf(produit.get(0).getStockProduit()));
            description3.setText(String.valueOf(produit.get(0).getDescriptionProduit()).substring(0, 10));

                ImageView im = new  ImageView("http://localhost/uploadsimg/" + produit.get(0).getPhotoProduit());
   //    photo3.getChildren().add(im);

           
           /* modif3.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(2), u);
                valider.setOnAction((ActionEvent el) -> {
                    modifier(QuatreAnnonceAccouplements.get(2).getId());
                });
            });
            supp3.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(2).getId());
            });
*/
        } else {
            box3.setVisible(false);
        }

        ///////////////////////////////////////////////////////////
        if (produit.size() >= 4) {
            box4.setVisible(true);

             produit4.setText(produit.get(0).getCategorieProduit());
            nom4.setText(produit.get(0).getNomProduit());
            prix4.setText(String.valueOf(produit.get(0).getPrixProduit()));
            stock4.setText(String.valueOf(produit.get(0).getStockProduit()));
            description4.setText(String.valueOf(produit.get(0).getDescriptionProduit()).substring(0, 10));

           ImageView im = new  ImageView("http://localhost/uploadsimg/" + produit.get(0).getPhotoProduit());
       box4.getChildren().add(im);

/*
            modif4.setOnAction((ActionEvent e) -> {
                modifierannonce(QuatreAnnonceAccouplements.get(3), u);
                valider.setOnAction((ActionEvent el) -> {
                    modifier(QuatreAnnonceAccouplements.get(3).getId());
                });
            });
            supp4.setOnAction((ActionEvent e) -> {
                supprimerannonce(QuatreAnnonceAccouplements.get(3).getId());
            });
*/
        } else {
            photo4.setVisible(false);
        }

    }

     private void modifierProduit(Produit a, User u) {


     

    }
}
