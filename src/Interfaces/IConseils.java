/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Conseils;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yassine
 */
public interface IConseils <T>{
     public void ajouterConseils(T t)throws SQLException;
     public boolean supprimerConseils(T t)throws SQLException;
     public boolean updateConseils(T t,int id)throws SQLException;
     public List<T> displayConseils()throws SQLException;
     public Conseils FindById(int id) throws SQLException;
    
}
