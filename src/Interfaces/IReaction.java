/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yassine
 */
public interface IReaction <T>{
     public void ajouterReaction(T t)throws SQLException;
     public void supprimerReaction(T t)throws SQLException;

}

