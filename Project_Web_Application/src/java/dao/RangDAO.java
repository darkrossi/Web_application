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
import modele.Rang;

/**
 *
 * @author oswald
 */
public class RangDAO extends AbstractDataBaseDAO {

    public RangDAO(DataSource ds) {
        super(ds);
    }

    public Hashtable<Rang, List<Place>> getRangs(int NSa, boolean dispo) throws DAOException {
        Hashtable<Rang, List<Place>> rangs = new Hashtable<>();

        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select r.NRa, c.NomCT, c.PrixCT, p.NP, p.isTaken "
                    + "from Rang r, Place p, CatTarifs c "
                    + "where r.NSA = " + NSa + " and r.NRa = p.NRa and c.NCT = r.NCT";
            rs = st.executeQuery(requeteSQL);

            Place place;
            while (rs.next()) {
                Rang rang = new Rang(rs.getInt("NRa"), rs.getString("NomCT"), rs.getInt("PrixCT"));
                if (!rangs.containsKey(rang)) {
                    rangs.put(rang, new ArrayList<>());
                }
                place = new Place(rs.getInt("NP"), rs.getInt("isTaken"));
                rangs.get(rang).add(place);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
        } finally {
            closeConnection(conn);
        }
        return rangs;
    }
}
