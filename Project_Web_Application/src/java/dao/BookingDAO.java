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
public class BookingDAO extends AbstractDataBaseDAO {

    public BookingDAO(DataSource ds) {
        super(ds);
    }

    public boolean ajouterReservation(String loginU, String[] listRepr) throws DAOException {
        if (listRepr.length == 0) {
            return true;
        } else {
            ResultSet rs = null;
            String requeteSQL = "";
            Connection conn = null;
            int indiceND_Max = 0;
            try {
                conn = getConnection();
                Statement st = conn.createStatement();
                requeteSQL = "select max(ND) from Dossier";
                rs = st.executeQuery(requeteSQL);
                while (rs.next()) {
                    indiceND_Max = rs.getInt(1);
                    indiceND_Max++;
                }

//                requeteSQL = "INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS, Spectacle)"
//                        + "VALUES (" + indiceND_Max + ", '" + titre + "', '" + auteur + "', '" + mes
//                        + "', " + Integer.parseInt(duree) + ", '" + url + "')";
                st.executeQuery(requeteSQL);

            } catch (SQLException e) {
                throw new DAOException("Erreur BD " + e.getMessage(), e);
            } finally {
                closeConnection(conn);
            }
            return false;
        }
    }
}
