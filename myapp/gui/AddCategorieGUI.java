/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;



import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.CategorieService;

/**
 *
 * @author bhk
 */
public class AddCategorieGUI extends Form{

    public AddCategorieGUI(Form previous) {
        /*
        Le paramÃ¨tre previous dÃ©finit l'interface(Form) prÃ©cÃ©dente.
        Quelque soit l'interface faisant appel Ã  AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter une categorie");
        setLayout(BoxLayout.y());
         
        TextField tfNomcategorie = new TextField("","nomcategorie");
               TextField tfAdresse= new TextField("","adresse");
                TextField tfColor= new TextField("","color");
 
            
        Button btnValider = new Button("Ajouter Categorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNomcategorie.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfColor.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Categorie t = new Categorie(tfNomcategorie.getText(), tfAdresse.getText(),tfColor.getText());
                        if( CategorieService.getInstance().addCategorie(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));}
                        else
                        { Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNomcategorie,tfAdresse,tfColor,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface prÃ©cÃ©dente
                
    }
    
    
}

