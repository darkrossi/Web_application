/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author oswald
 */
public class UtilisateurDAO extends AbstractDataBaseDAO {

    /**
     *
     * @param ds
     */
    public UtilisateurDAO(DataSource ds) {
        super(ds);
    }

    /**
     *
     * @param login
     * @param mdp
     * @return
     * @throws DAOException
     */
    public String verifUser(String login, String mdp) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        String sortie = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT * "
                    + "FROM Users "
                    + "WHERE LoginU = '" + login + "' AND MdpU = '" + mdp + "'";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                sortie = rs.getString("LoginU");
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return sortie;
    }

    /**
     *
     * @param login
     * @param mdp
     * @param nom
     * @param prenom
     * @param mail
     * @return
     * @throws DAOException
     */
    public boolean ajouterUser(String login, String mdp, String nom, String prenom, String mail) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
//        int indiceNSP_Max = 0;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
//            requeteSQL = "select max(NSP) from Spectacle";
//            rs = st.executeQuery(requeteSQL);
//            while (rs.next()) {
//                indiceNSP_Max = rs.getInt(1);
//                indiceNSP_Max++;
//            }

            requeteSQL = "INSERT INTO Users (LoginU, NomU, PrenomU, MailU, MdpU)"
                    + "VALUES ('" + login + "', '" + nom + "', '" + prenom + "', '" + mail
                    + "', '" + mdp + "')";
            st.executeQuery(requeteSQL);

            return true;

        } catch (SQLException e) {
            // si l'exception concerne l'unicité de chaque login dans la table
            if (e.getErrorCode() == 1) {
                // alors on redirige vers une page vers une page qui indique que ce login est déjà enregistré dans la bdd
                return false;
            } else {
                // sinon lancer l'exception
                throw new DAOException("Erreur BD " + e.getMessage(), e);
            }
        } finally {
            closeConnection(conn);
        }
    }
}