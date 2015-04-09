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

    public UtilisateurDAO(DataSource ds) {
        super(ds);
    }

    public String verifUser(String login, String mdp) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        String sortie = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * "
                    + "from Spectacle "
                    + "where loginU = '" + login + "' and mdpU = '" + mdp + "'";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                sortie = rs.getString("loginU");
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return sortie;
    }
}
