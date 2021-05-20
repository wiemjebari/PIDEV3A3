/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ProduitService {
     public ArrayList<Produit> produits;
      public ArrayList<Categorie> tasks;
    public ArrayList<Long> r;
    public ArrayList<String> medcat;
    public static ProduitService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ProduitService() {
         req = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance= new ProduitService();
        }
      return instance;
    }


    public ArrayList<Produit> parseEvents(String jsonText){
       
        try {
            
            produits=new ArrayList<>();
            
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du rÃ©sultat json

            /*
                On doit convertir notre rÃ©ponse texte en CharArray Ã  fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilitÃ© de new CharArrayReader(json.toCharArray())
            
            La mÃ©thode parse json retourne une MAP<String,Object> ou String est 
            la clÃ© principale de notre rÃ©sultat.
            Dans notre cas la clÃ© principale n'est pas dÃ©finie cela ne veux pas
            dire qu'elle est manquante mais plutÃ´t gardÃ©e Ã  la valeur par defaut
            qui est root.
            En fait c'est la clÃ© de l'objet qui englobe la totalitÃ© des objets 
                    c'est la clÃ© dÃ©finissant le tableau de tÃ¢ches.
            */
            Map<String,Object> produitsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on rÃ©cupÃ¨re l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tÃ¢che.               
            
            Le format Json impose que l'objet soit dÃ©finit sous forme
            de clÃ© valeur avec la valeur elle mÃªme peut Ãªtre un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adÃ©quate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)produitsListJson.get("root");
            
            //Parcourir la liste des tÃ¢ches Json
            for(Map<String,Object> obj : list){
                //CrÃ©ation des tÃ¢ches et rÃ©cupÃ©ration de leurs donnÃ©es
            
                Produit t = new Produit();
               float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float stock = Float.parseFloat(obj.get("stockproduit").toString());
                t.setStockproduit((int)stock);
                 float prix= Float.parseFloat(obj.get("prixproduit").toString());
                t.setPrixproduit((float)prix);
                 
          //  t.setNomEvent(obj.get("nomevent").toString());
               t.setNomproduit(obj.get("nomproduit").toString());
               t.setDescription(obj.get("description").toString());
               t.setPhoto(obj.get("photo").toString());
              //    t.setDateDeb(dateDeb);
                    
           
           

             
             

             
             
            
              
              
             
             
              
                
                //Ajouter la tÃ¢che extraite de la rÃ©ponse Json Ã  la liste
                produits.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu rÃ©cupÃ©rer une liste des tÃ¢ches Ã  partir
        de la base de donnÃ©es Ã  travers un service web
        
        */
        return produits;
    }
    
    public ArrayList<Produit> getAllEvents(){
        String url = Statics.BASE_URL+"/produit/AllProduit";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
   
    public boolean deleteProduit(int id) {
        String url = Statics.BASE_URL + "/produit/deleteProduitJSON/" +id;
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                         
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return resultOK=true;
    }
    
     public boolean addProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/addProduit/new?nomproduit=" + p.getNomproduit()+ "&prixproduit=" + p.getPrixproduit()+ "&stockproduit=" + p.getStockproduit()+ "&description=" + p.getDescription()+ "&photo=" + p.getPhoto() +"&idcategorie="+p.getIdcategorie(); //crÃ©ation de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminÃ© de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle mÃ©thode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistrÃ© et donc Ã©xÃ©cutÃ© mÃªme si 
                la rÃ©ponse reÃ§ue correspond Ã  une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public ArrayList<String> parseParCategorie(String jsonText){
      ArrayList  r=new ArrayList<>();
        JSONParser j=new JSONParser();
        Map<String,Object> meditationsListJson;
        try {
            meditationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<String> list= (ArrayList<String>)meditationsListJson.get("root");
            System.out.println(list);
            System.out.println("=>"+list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                r.add(list.get(i));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
      public ArrayList<Long> chart(){
    
        String url = Statics.BASE_URL+"/RjsonNBR/chart";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 String reponsejson=new String(req.getResponseData());
                 r = parsenbRParCategorie(new String(req.getResponseData()));
                 req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); 
        return r;
    }
 
   
  public ArrayList<Long> parsenbRParCategorie(String jsonText){
     ArrayList   medcat=new ArrayList<>();
        JSONParser j=new JSONParser();
        Map<String, Object> response;
        Map<String,Object> meditationsListJson;
        try {
            meditationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Double> list= (ArrayList<Double>)meditationsListJson.get("root");
            System.out.println(list);
            System.out.println("=>"+list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(Math.round(list.get(i)));
                medcat.add(Math.round(list.get(i)));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return medcat;
    } 
   public ArrayList<Long> getAllnbERParCategorie(){
        String url = Statics.BASE_URL+"/RjsonNBR/chart";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 r= parsenbRParCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
    }
  public ArrayList<String> getAllParCategorie(){
        String url = Statics.BASE_URL+"/RjsonCat/chart";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                medcat = parseParCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return medcat;
    }  
  
   public boolean modifierCategorie(Produit p) {
           String url = Statics.BASE_URL + "/produit/updateProduitJSON/"+p.getId()+ "?nomproduit=" + p.getNomproduit()+"&prixproduit=" + p.getPrixproduit()+"&stockproduit=" + p.getStockproduit();
            req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminÃ© de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle mÃ©thode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistrÃ© et donc Ã©xÃ©cutÃ© mÃªme si 
                la rÃ©ponse reÃ§ue correspond Ã  une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

}
