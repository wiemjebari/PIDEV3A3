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
public interface ICommentaire <T> {
     public void ajouterCommentaire(T t) throws SQLException;
     public boolean supprimerCommentaire(T t) throws SQLException;
     public boolean updateCommentaire(T t,int id) throws SQLException;
     public List<T> displayCommentaire(Conseils c) throws SQLException;
     public int NumberOfComments(Conseils c) throws SQLException;
    
}

    

