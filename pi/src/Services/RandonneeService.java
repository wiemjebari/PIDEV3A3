/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Randonnees;
import IServices.ServiceInterface;
import Tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.DatePicker;

/**
 *
 * @author ASUS
 */
public class RandonneeService implements ServiceInterface<Randonnees>{
    
    private Connection con;
    private Statement ste;

    public RandonneeService() {
        con = MyConnection.getInstance().getCnx();
    }
    

    @Override
    public void add(Randonnees r) throws SQLException {
        ste = con.createStatement();
        PreparedStatement ps = con.prepareStatement("insert into `projet`.`Randonnees`(`date`,'lieu`,`heure_depart`,`heure_retour`,`nbr_place`,`photo`) Values (?,?,?,?,?,?);");
        
        
        ps.setDate(1, r.getDate());
        ps.setString(2, r.getLieu());
        ps.setInt(3, r.getHeure_depart());
        ps.setInt(4, r.getNbr_place());
        ps.setString(5, r.getPhoto());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        if(getById(id)!=null){
            ste = con.createStatement();
            String query = "delete from randonnees where id="+id;
            ste.execute(query);
            System.out.println("randonne supprimé");
        }
        else{
            System.out.println("randonne n'existe pas");
        }
    }

    @Override
    public void update(Randonnees r) throws SQLException {
        if(getById(r.getId())!=null){
            ste = con.createStatement();
            String query = "update randonnees Set date='"+r.getDate()+"',Lieu='"+r.getLieu()+"',heure_depart='"+r.getHeure_depart()+"',heure_retour='"+r.getHeure_retour()+"',photo='"+r.getPhoto()+"' where id='"+r.getId()+"'";
            ste.execute(query);
            System.out.println("randonne modifié");
        }
        else{
            System.out.println("randonne n'existe pas");
        }
    }

    @Override
    public List<Randonnees> readAll() throws SQLException {
        List<Randonnees> l=new ArrayList<Randonnees>();
        ste = con.createStatement();
        ResultSet rs=ste.executeQuery("select * from randonnees");
        while(rs.next()){
            Randonnees r= new Randonnees(rs.getInt("id"),rs.getDate("date"),rs.getString("lieu"),rs.getInt("heure_depart"),rs.getInt("heure_retour"),rs.getInt("nbr_places"),rs.getString("photo"));
            l.add(r);
        }
        return l;
    }

    @Override
    public Randonnees getById(int id) throws SQLException {
        ste = con.createStatement();
        ResultSet rs=ste.executeQuery("select * from randonnees where id="+id);
        while(rs.next()){
            Randonnees r= new Randonnees(rs.getInt("id"),rs.getDate("date"),rs.getString("lieu"),rs.getInt("heure_depart"),rs.getInt("heure_retour"),rs.getInt("nbr_places"),rs.getString("photo"));
            return r;
        }
        return null;
    }
    
    
}
