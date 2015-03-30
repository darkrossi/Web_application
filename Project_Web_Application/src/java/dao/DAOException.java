/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

/**
 *
 * @author oswald
 */
public class DAOException extends Exception {

    DAOException(String problème_fermeture_de_connexion_avec_la_B, SQLException sqle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
