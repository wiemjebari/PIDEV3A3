
package com.mycompany.myapp.gui;

import com.codename1.io.Util;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ProduitService;
import com.codename1.components.ShareButton;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Display;
import com.codename1.ui.TextArea;

import java.util.List;
public class ListProduitGui extends Form {

 
   Form previous;
   Form current;
    public ListProduitGui(Form previous) {
        setTitle("Liste des Produits");
        setLayout(BoxLayout.y());
        
        List <Produit> produits = ProduitService.getInstance().getAllEvents();
    
        for (int i = 0; i < produits.size(); i++) {
            Label l = new Label("Produit N°"+i);
           
          addAll(l);
           
     
            Produit get = produits.get(i);
            add(addProduit(get));
          
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());    

     
    }   
    
     private Container addProduit(Produit e){
          Container holder = new Container(BoxLayout.x());
          Container detaills = new Container(BoxLayout.y());
          
        
     
         
          Label id=new Label ("id:"+e.getId());
            id.setTextPosition(RIGHT);
          Label nomp=new Label ("nomproduit:"+e.getNomproduit());
          
            Label prix =new Label ("prixproduit:"+e.getPrixproduit());
              
               Label stock=new Label("stockproduit:"+e.getStockproduit());
               Label description=new Label("description:"+e.getDescription());
               Label photo =new Label ("photo:"+e.getPhoto());
                  
         
       //    Picker dateDeb  = new Picker("dateDeb:"+e.getDateDeb());
   //   //  dateDeb.getDate();


    Button btnSupprimer = new Button("");
     Style supprimerStyle=new Style(btnSupprimer.getUnselectedStyle());
     supprimerStyle.setFgColor(0xf21f1f);
         FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
           btnSupprimer.setIcon(supprimerImage);
             btnSupprimer.addPointerPressedListener(l-> {
                 
         
         if(ProduitService.getInstance().deleteProduit(e.getId())){
             new ListProduitGui(previous).show();
             
         }
         
           
        
     }); 
             
      final ShareButton share = new ShareButton();
      final TextArea t = new TextArea("");
 
detaills.addComponent( share);
share.setTextToShare(t.getText());
    share.addPointerPressedListener(l-> {
                 
         
        share.setTextToShare(t.getText());
       // Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php?u=" + Util.encodeUrl(e));
             
    });  
           detaills.add(id);
            detaills.add(nomp);
            detaills.add(prix);
         detaills.add(photo);
              detaills.add(stock);
             detaills.add(description);
               //    detaills.add(dateFin);
                 //   detaills.add(id);
             
              
                     
        
      //  detaills.add(g)
         
                                  
        detaills.add(btnSupprimer);
     
       
       
          
        
          
          holder.add(detaills);
          return (holder);
          
     }
}
   