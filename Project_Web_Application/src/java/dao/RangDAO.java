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

    /**
     *
     * @param ds
     */
    public RangDAO(DataSource ds) {
        super(ds);
    }

    /**
     *
     * @param NSa
     * @param NR
     * @return
     * @throws DAOException
     */
    public Hashtable<Rang, List<Place>> getRangs(int NSa, int NR) throws DAOException {
        Hashtable<Rang, List<Place>> rangs = new Hashtable<>();

        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select r.NRa, c.NomCT, c.PrixCT, p.NP, p.NumPl "
                    + "from Rang r, Place p, CatTarifs c "
                    + "where r.NSA = " + NSa + " and r.NRa = p.NRa and c.NCT = r.NCT "
                    + "and p.NP not in(Select p.NP "
                    + "from Place p, PlacesRes plr, Dossier d, Rang r "
                    + "where p.NP = plr.NP and r.NRA = p.NRA and r.NSA = " + NSa + " and plr.ND = d.ND and d.NR =" + NR + ")";
            rs = st.executeQuery(requeteSQL);

            Place place;
            while (rs.next()) {
                Rang rang = new Rang(rs.getInt("NRa"), rs.getString("NomCT"), rs.getInt("PrixCT"));
                if (!rangs.containsKey(rang)) {
                    rangs.put(rang, new ArrayList<>());
                }
                place = new Place(rs.getInt("NP"), rs.getInt("NumPl"), rs.getInt("NRa"));
                rangs.get(rang).add(place);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
        } finally {
            closeConnection(conn);
        }
        return rangs;
    }
    
    public List<Rang> getListRangs(List<Place> places) throws DAOException {
        List<Rang> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            
            for (int i = 0; i < places.size(); i++){
                requeteSQL = "select * from Rang r, CatTarifs ct "
                    + "WHERE r.NRa = " + places.get(i).getNRa() + " and r.NCT = ct.NCT";
                rs = st.executeQuery(requeteSQL);
                while (rs.next()) {
                    Rang rang = new Rang(rs.getInt("NRa"),
                            rs.getString("NomCT"),
                            rs.getInt("PrixCT"));
                    System.err.println(rang);
                    result.add(rang);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
