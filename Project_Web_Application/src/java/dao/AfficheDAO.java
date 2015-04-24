/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Affiche;

/**
 *
 * @author oswald
 */
public class AfficheDAO {

    private final String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    private final String login = "fournimi";

    /* fermeture d'une connexion
     * @param c la connexion à fermer
     * @throws DAOException si problème lors de la fermeture de la connexion
     */
    protected void closeConnection(Connection c) throws DAOException {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException sqle) {
                throw new DAOException("Problème fermeture de connexion avec la BD ", sqle);
            }
        }
    }

    public List<Affiche> getListeAffiches() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AfficheDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Affiche> affiches = new ArrayList<>();
        Connection Connexion = null;
        try {
            Connexion = DriverManager.getConnection(url, login, login);
            Statement State = Connexion.createStatement();
            ResultSet resultat = State.executeQuery("SELECT NSP, Affiche FROM Spectacle");
            while (resultat.next()) {
                if (resultat.getInt("NSP") != 0) {
                    String url = resultat.getString("Affiche");
                    affiches.add(new Affiche(url));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closeConnection(Connexion);
            } catch (DAOException ex) {
                Logger.getLogger(AfficheDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (affiches.isEmpty()) {
            affiches.add(new Affiche("empty.png"));
        }
        return affiches;
    }
}
