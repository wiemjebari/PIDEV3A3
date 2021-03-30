/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.utils.css;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wiemj
 */
public class ConnexionBd {
     Connection connexion;
    final String url = "jdbc:mysql://localhost:3306/project";
    final String user = "root";
    final String password = "";
    private static ConnexionBd instance;

    private ConnexionBd() {
        try {
            connexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConnexionBd getInstance() {
        if (instance == null) {
            instance = new ConnexionBd();
        }

        return instance;
    }

    public Connection getConnection() {
        return connexion;
    }}

    

