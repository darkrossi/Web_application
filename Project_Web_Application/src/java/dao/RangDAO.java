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

    public Hashtable<Integer, List<Place>> getRangs(int NSa) throws DAOException {
        Hashtable<Integer, List<Place>> rangs = new Hashtable<>();

        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select NbRa "
                    + "from Salle "
                    + "where NSA = " + NSa;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            int nbRg = rs.getInt("NbRa");

            requeteSQL = "select NRa, NP "
                    + "from Rang r, Place p "
                    + "where r.NSA = " + NSa + " and r.NRa = p.NRa";
            rs = st.executeQuery(requeteSQL);

            Place place;
            while (rs.next()) {
                if (rangs.get(rs.getInt("NRA")) == null) {
                    //rangs.put(rs.getInt("NRa"), new ArrayList<>());
                }
                place = new Place(rs.getInt("NP"));
                System.err.println(place);
                rangs.get(rs.getInt("NRA")).add(place);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return rangs;
    }
}
