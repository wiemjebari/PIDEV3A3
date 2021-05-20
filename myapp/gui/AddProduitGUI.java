/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Categorie;

import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.CategorieService;

import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;




public class AddProduitGUI extends Form{
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
  Button btnValider = new Button("Ajouter produit");
    public AddProduitGUI(Form previous) {
        
        /*
        Le paramÃ¨tre previous dÃ©finit l'interface(Form) prÃ©cÃ©dente.
        Quelque soit l'interface faisant appel Ã  AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un produit");
        setLayout(BoxLayout.y());
         
        TextField tfNomproduit = new TextField("","nomproduit");
         
               TextField tfprix= new TextField("","prixproduit");
                TextField tfstock= new TextField("","stockproduit");
                TextField tfdescription = new TextField("","description");
             
             
             ComboBox cc=new ComboBox();
        ArrayList <Categorie> list=CategorieService.getInstance().getAllCategories();
        for (Categorie rec:list){
            
            cc.addItem(rec.getIdc());
            
        }       cc.addItem(list);
        add(cc);
      
        CheckBox multiSelect= new CheckBox("Multi-select");
         Button img1= new Button("choisir une image");
        img1.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        String hh="C:/Users/wiemj/Downloads/PhaseMobile/PhaseMobile/src/com/mycompany/myapp/pictures";
                        Image photo;

                        try {
                            photo = Image.createImage(file).scaledHeight(500);;
                            add(photo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(photo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                System.out.println(namePic); 
                                 
                                 
                          
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNomproduit.getText().length()==0)||(tfprix.getText().length()==0)||(tfstock.getText().length()==0)||(tfdescription.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else
                {
                    try {
                                               
                   Produit P = new  Produit(tfNomproduit.getText(),Float.valueOf (tfprix.getText()),namePic,Integer.valueOf (tfstock.getText()),tfdescription.getText());
                        
                              
                      
                        if( ProduitService.getInstance().addProduit(P)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));}
                        else
                        { Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        } catch (IOException ex) {
                            }

                         

                        
                    }
                    }
                        });
            }
                });
        
        addAll(tfNomproduit,tfprix,tfstock,tfdescription,img1,btnValider);
    
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                
                , e-> previous.showBack()); // Revenir vers l'interface prÃ©cÃ©dente
                
    }
    
    
                         
                    }
                    
                
                        
                
            
            

