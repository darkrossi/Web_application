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
import java.util.Hashtable;
import java.util.List;
import javax.sql.DataSource;
import modele.Place;

/**
 *
 * @author oswald
 */
public class RangDAO extends AbstractDataBaseDAO {

    public RangDAO(DataSource ds) {
        super(ds);
    }

    public Hashtable<Integer, List<Place>> getRangs(int NSa, boolean dispo) throws DAOException {
        Hashtable<Integer, List<Place>> rangs = new Hashtable<>();

        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            if (dispo) {
                requeteSQL = "select r.NRa, p.NP "
                        + "from Rang r, Place p "
                        + "where r.NSA = " + NSa + " and r.NRa = p.NRa and p.isTaken = 0";
            } else {
                requeteSQL = "select r.NRa, p.NP "
                        + "from Rang r, Place p "
                        + "where r.NSA = " + NSa + " and r.NRa = p.NRa";
            }
            rs = st.executeQuery(requeteSQL);

            Place place;
            while (rs.next()) {
                if (!rangs.containsKey(rs.getInt(1))) {
                    rangs.put(rs.getInt(1), new ArrayList<>());
                }
                place = new Place(rs.getInt(2));
                System.err.println(place);
                rangs.get(rs.getInt(1)).add(place);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
        } finally {
            closeConnection(conn);
        }
        return rangs;
    }
}
