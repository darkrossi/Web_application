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
import modele.Place;

/**
 *
 * @author hoel
 */
public class PlaceDAO extends AbstractDataBaseDAO {

    /**
     *
     * @param ds
     */
    public PlaceDAO(DataSource ds) {
        super(ds);
    }

    public List<Place> getPlaces(int ND) throws DAOException {
        List<Place> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from PlacesRes pr, Place p, Rang r "
                    + "WHERE pr.ND = " + ND + " and pr.NP = p.NP and r.NRa = r.NRa";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Place place = new Place(rs.getInt("NP"),
                        rs.getInt("NumPl"),
                        rs.getInt("NRa"));
                System.err.println(place);
                result.add(place);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
