/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import gestionproduit.entity.Produit;
import gestionproduit.entity.User;
import gestionproduit.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class ProprietaireProduitController extends AcceuilProduitController {

    @FXML
    private AnchorPane winodow;
    @FXML
    private Pagination paginator;
    @FXML
    private Pane filter_type;
    @FXML
    private Label filt;
    @FXML
    private ToggleGroup type;
    @FXML
    private Label filt1;
    @FXML
    private JFXRadioButton prixup;
    @FXML
    private ToggleGroup type3;
    @FXML
    private JFXRadioButton prixdown;
    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label categorie1;
    @FXML
    private Label nom1;
    @FXML
    private Text prix1;
    @FXML
    private Text stock1;
    @FXML
    private Label description1;
    @FXML
    private JFXTextField recherche;
    @FXML
    private AnchorPane details;
    @FXML
    private ImageView photo;
    @FXML
    private Text descriptionD;
    @FXML
    private Label categorieD;
    @FXML
    private Label nomD;
    @FXML
    private Label stockD;
    @FXML
    private Label prixD;
    @FXML
    private Label idp1;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label categorie;
    @FXML
    private Label nom;
    @FXML
    private Text prix;
    @FXML
    private Label stock;
    @FXML
    private Label description;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label categorie2;
    @FXML
    private Label nom2;
    @FXML
    private Text prix2;
    @FXML
    private Label stock2;
    @FXML
    private Label description2;
    @FXML
    private JFXRadioButton Cafe;
    @FXML
    private JFXRadioButton supermarche;
    @FXML
    private JFXRadioButton restaurant;
    List<Produit> liste = new ArrayList<>();
    Produit p = new Produit();
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    private Button commander;
    private List<Produit> all_articles;
    ProduitService produitservice = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void restaurant(ActionEvent event) {
        details.setVisible(false);

        ProduitService es = new ProduitService();

        liste = es.consulterProduitByIdOwner(User.getUserconnected());
        if (liste.isEmpty()) {
            box.setVisible(false);
            box2.setVisible(false);
            //ide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            //vide.setVisible(false);
            setNbPages();
            initAnnoncePage(0);
        }
    }
private void setNbPages() {

        if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
        ProduitService es = new ProduitService();

        paginator.setCurrentPageIndex(i);
        List<Produit> TroisAnnonces = getAnnoncesPage(i);

        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            // System.out.println("    858585858"+TroisAnnonces.get(0).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(0).getPhotoProduit());

            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorieProduit());
            idp1.setText(Integer.toString((int) TroisAnnonces.get(0).getIdProduit()));
            System.out.println("  mmmmmmm" + idp1.getText().toString());
            nom.setText(TroisAnnonces.get(0).getNomProduit());
            prix.setText(Integer.toString((int) TroisAnnonces.get(0).getPrixProduit()) + "DT");
            stock.setText(Integer.toString(TroisAnnonces.get(0).getStockProduit()));

            description.setText(TroisAnnonces.get(0).getDescriptionProduit());
            box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(0));
                details.setVisible(true);
                box2.setVisible(false);
                box.setVisible(false);
                box1.setVisible(false);
            });

        } else {
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
            //System.out.println("    858585858"+TroisAnnonces.get(1).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(1).getPhotoProduit());

            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorieProduit());

            nom1.setText(TroisAnnonces.get(1).getNomProduit());
            prix1.setText(Integer.toString((int) TroisAnnonces.get(1).getPrixProduit()) + "DT");
            stock1.setText(Integer.toString(TroisAnnonces.get(1).getStockProduit()));

            description1.setText(TroisAnnonces.get(1).getDescriptionProduit());
            box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
                box2.setVisible(false);
                box1.setVisible(false);
            });

        } else {
            box1.setVisible(false);
        }

        if (TroisAnnonces.size() >= 3) {
            System.out.println("    hhhhh" + TroisAnnonces.get(2).getPrixProduit());
            box2.setVisible(true);
            Image img = new Image("http://localhost/uploadsimg/" + TroisAnnonces.get(2).getPhotoProduit());
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getCategorieProduit());

            nom2.setText(TroisAnnonces.get(2).getNomProduit());
            prix2.setText(Integer.toString((int) TroisAnnonces.get(2).getPrixProduit()) + "DT");
            stock2.setText(Integer.toString(TroisAnnonces.get(2).getStockProduit()));
            description2.setText(TroisAnnonces.get(2).getDescriptionProduit());
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p = TroisAnnonces.get(2));

                details.setVisible(true);
                box.setVisible(false);
                box2.setVisible(false);
                box1.setVisible(false);
            });
            // System.out.println("deeetaail ");

        } else {
            box2.setVisible(false);
        }

    }

    private List<Produit> getAnnoncesPage(int i) {
        int start = 3 * i;
        int fin = start + 3;
        if (liste.size() > start) {
            if (liste.size() > fin) {
                return liste.subList(start, fin);
            } else {
                return liste.subList(start, liste.size());
            }
        }
        return liste.subList(0, 2);
    }

    @FXML
    public void supprimer(ActionEvent event) throws IOException {
        ProduitService ps = new ProduitService();
        System.out.println("*********************\n" + p.getIdProduit() + "\n**************");
        ps.supprimerProduit(p.getIdProduit());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAllProduit.fxml"));

        loader.load();
        AnchorPane parentContent = loader.getRoot();
        window = (AnchorPane) paginator.getParent().getParent();
        AfficherAllProduitController cont = loader.getController();

        window.getChildren().setAll(parentContent);

    }

    private void sendidproduit() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateProduit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            UpdateProduitController controller = fxmlLoader.<UpdateProduitController>getController();
            controller.initData(p.getIdProduit());
            //  System.out.println(p.getIdProduit()+" **********************afficher");

        } catch (Exception e) {
            Logger.getLogger(UpdateProduitController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    public void Modifier(ActionEvent event) throws IOException {
        Produit.setId_pModifier(p.getIdProduit());

        System.out.println("wsel lahné");
        sendidproduit();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProduit.fxml"));

        loader.load();
        AnchorPane parentContent = loader.getRoot();
        window = (AnchorPane) paginator.getParent().getParent();
        UpdateProduitController cont = loader.getController();

        window.getChildren().setAll(parentContent);

    }

    private void initialiserDetails(Produit p) {
        /*if(User.getUserconnected()==p.getIdOwer())
                commander.setVisible(false);*/
        
        Image img = new Image("http://localhost/uploadsimg/" + p.getPhotoProduit());
        photo.setImage(img);
        nomD.setText(p.getNomProduit());
        categorieD.setText(p.getCategorieProduit());
        descriptionD.setText(p.getDescriptionProduit());
        prixD.setText(Float.toString(p.getPrixProduit()));
        stockD.setText(Integer.toString(p.getStockProduit()));
        Produit.id_pModifier = p.getIdProduit();
        System.out.println(idp1.getText().toString());

    }

  

    @FXML
    private void cafe(ActionEvent event) {

        liste = new ArrayList<>(produitservice.findAllFiltrer("cafe"));
        liste.forEach(a -> System.out.println(a.toString()));

        setNbPages();
        initAnnoncePage(0);
    }

    @FXML
    private void supermarche(ActionEvent event) {

        liste = new ArrayList<>(produitservice.findAllFiltrer("supermarche"));
        liste.forEach(a -> System.out.println("*********\n" + a.toString()));

        setNbPages();
        initAnnoncePage(0);
    }

    

   

   
    @FXML
    private void prixup(ActionEvent event) {
        liste = new ArrayList<>(produitservice.findAllFiltrerup());

        setNbPages();
        initAnnoncePage(0);
    }

    @FXML
    private void prixdown(ActionEvent event) {
        liste = new ArrayList<>(produitservice.findAllFiltrerdown());

        setNbPages();
        initAnnoncePage(0);
    }

    @FXML
    private void recherche(ActionEvent event) {
        liste = new ArrayList<>(produitservice.RechercherPro(recherche.getText()));

        setNbPages();
        initAnnoncePage(0);
    }

    
}
