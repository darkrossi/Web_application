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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Affiche;

/**
 *
 * @author oswald
 */
public class AfficheDAO extends AbstractDataBaseDAO {

    /**
     *
     */
    public AfficheDAO() {
    }

    /**
     *
     * @param ds
     */
    public AfficheDAO(DataSource ds) {
        super(ds);
    }

    /**
     *
     * @return
     * @throws DAOException
     */
    public List<Affiche> getListeAffiches() throws DAOException {
        List<Affiche> affiches = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT NSP, Affiche FROM Spectacle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                if (rs.getInt("NSP") != 0) {
                    String url = rs.getString("Affiche");
                    affiches.add(new Affiche(url));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficheDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(conn);
        }

        if (affiches.isEmpty()) {
            affiches.add(new Affiche("empty.png"));
        }
        return affiches;
    }
}
