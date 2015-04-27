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
import javax.sql.DataSource;
import modele.Dossier;

/**
 *
 * @author oswald
 */
public class DossierDAO extends AbstractDataBaseDAO {

    public DossierDAO(DataSource ds) {
        super(ds);
    }

    public List<Dossier> getFolders(String loginU, int boolResa) throws DAOException {
        List<Dossier> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Dossier d, Spectacle s, Representation r "
                    + "WHERE d.LoginU ='" + loginU + "' and d.NR = r.NR and r.NSP = s.NSP and d.boolResa = " + boolResa;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Dossier dossier = new Dossier(rs.getInt("ND"),
                        rs.getInt("NbP"),
                        rs.getString("LoginU"),
                        rs.getInt("NT"),
                        rs.getInt("NR"),
                        rs.getString("NomS"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"));
                System.err.println(dossier);
                result.add(dossier);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

}
