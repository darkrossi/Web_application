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
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Hashtable;
import java.util.List;
import javax.sql.DataSource;
import modele.Representation;

/**
 *
 * @author oswald
 */
public class RepresentationDAO extends AbstractDataBaseDAO {

    /**
     *
     */
    public RepresentationDAO() {
    }

    /**
     *
     * @param ds
     */
    public RepresentationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Ajoute l'ouvrage d'auteur et de titre spécifié dans la table bibli
     *
     * @param date
     * @param auteur
     * @param heure
     * @param titre
     * @param NSp
     * @param NSa
     * @param duree
     * @return 
     * @throws dao.DAOException
     */
    public boolean ajouterRepresentation(String date, String heure, String NSp, String NSa)
            throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        int indiceNR_Max = 0;
        int nbPlaceSalle = 0;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select max(NR) from Representation";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNR_Max = rs.getInt(1);
                indiceNR_Max++;
            }

            requeteSQL = "select s.NbRa, r.NbP "
                    + "from Salle s, Rang r "
                    + "where s.NSA = " + Integer.parseInt(NSa);
            rs = st.executeQuery(requeteSQL);
            rs.next();
            nbPlaceSalle = rs.getInt("NbRa") * rs.getInt("NbP");

            requeteSQL = "select * from Representation "
                    + "where NSp = " + Integer.parseInt(NSp) + " and DateR = '" + date + "'";
            rs = st.executeQuery(requeteSQL);
            if (!rs.next()) { // Si il n'y a pas déjà de représentation pour ce spectacle à cette date
                requeteSQL = "INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)"
                        + "VALUES (" + indiceNR_Max + ", '" + date + "', '" + heure + "', "
                        + Integer.parseInt(NSp) + ", " + Integer.parseInt(NSa) + ", " + nbPlaceSalle + ")";
                st.executeQuery(requeteSQL);
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
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

    /**
     *
     * @return
     * @throws DAOException
     */
    public Hashtable<String, List<Representation>> getRepresFromSp() throws DAOException {
        Hashtable<String, List<Representation>> result = new Hashtable<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * "
                    + "from Representation r, Spectacle s "
                    + "where r.NSP = s.NSP";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Representation representation = new Representation(rs.getInt("NR"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"),
                        rs.getInt("NSP"),
                        rs.getInt("NSA"),
                        rs.getInt("NbP"),
                        rs.getString("Affiche"));
                System.err.println(representation);
                if (!result.containsKey(rs.getString("NomS"))) {
                    result.put(rs.getString("NomS"), new ArrayList<>());
                }
                result.get(rs.getString("NomS")).add(representation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     *
     * @param NSp
     * @return
     * @throws DAOException
     */
    public List<Representation> getRepresList(int NSp) throws DAOException {
        List<Representation> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * "
                    + "from Representation r, Spectacle s "
                    + "where r.NSP = s.NSP and r.NSP = " + NSp;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Representation repres = new Representation(rs.getInt("NR"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"),
                        rs.getInt("NSP"),
                        rs.getInt("NSA"),
                        rs.getInt("NbP"),
                        rs.getString("Affiche"));
                result.add(repres);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     *
     * @param NR
     * @return
     * @throws DAOException
     */
    public Representation getRepres(int NR) throws DAOException {
        Representation result = new Representation();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * "
                    + "from Representation "
                    + "where NR = " + NR;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                result = new Representation(rs.getInt("NR"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"),
                        rs.getInt("NSP"),
                        rs.getInt("NSA"),
                        rs.getInt("NbP"));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     *
     * @param NSp
     * @param dateDe
     * @param dateA
     * @return
     * @throws DAOException
     * @throws ParseException
     */
    public List<Representation> getListeRepresTri(int NSp, String dateDe, String dateA) throws DAOException, ParseException {
        List<Representation> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select * "
                    + "from Representation r, Spectacle s "
                    + "where r.NSP = s.NSP and r.NSP = " + NSp;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Representation repres = new Representation(rs.getInt("NR"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"),
                        rs.getInt("NSP"),
                        rs.getInt("NSA"),
                        rs.getInt("NbP"),
                        rs.getString("Affiche"));
                result.add(repres);
            }

            if (dateDe != null && dateA != null && !"".equals(dateDe) && !"".equals(dateA) && !"null".equals(dateDe) && !"null".equals(dateA)) {
//                throw new DAOException("HERE '" + dateDe + "' et '" + dateA + "'", null);
                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!between(result.get(i).getDate(), dateDe, dateA)) {
                        result.remove(i);
                    }
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     *
     * @param date
     * @param dateDe
     * @param dateA
     * @return
     * @throws DAOException
     */
    public static boolean between(String date, String dateDe, String dateA) throws DAOException {
        int[] dateT = new int[3];
        int[] dateDeT = new int[3];
        int[] dateAT = new int[3];

        for (int i = 0; i < 2; i++) {
            dateT[i] = Integer.parseInt(date.substring(3 * i, 3 * i + 2));
            dateDeT[i] = Integer.parseInt(dateDe.substring(3 * i, 3 * i + 2));
            dateAT[i] = Integer.parseInt(dateA.substring(3 * i, 3 * i + 2));
        }
        dateT[2] = Integer.parseInt(date.substring(6, 10));
        dateDeT[2] = Integer.parseInt(dateDe.substring(6, 10));
        dateAT[2] = Integer.parseInt(dateA.substring(6, 10));

        Date dateFinale = new Date(dateT[2] - 1900, dateT[1] - 1, dateT[0]);
        Date dateFinaleDe = new Date(dateDeT[2] - 1900, dateDeT[1] - 1, dateDeT[0]);
        Date dateFinaleA = new Date(dateAT[2] - 1900, dateAT[1] - 1, dateAT[0]);

        double diffDe = dateFinale.getTime() - dateFinaleDe.getTime();
        double diffA = dateFinaleA.getTime() - dateFinale.getTime();

        return diffDe >= 0 && diffA >= 0;

    }

    /**
     *
     * @param NR
     * @param NSA
     * @return
     * @throws DAOException
     */
    public int getNbPlRestRes(int NR, int NSA) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select count(p.NP) "
                    + "from Rang r, Place p, CatTarifs c "
                    + "where r.NSA = " + NSA + "  and r.NRa = p.NRa and c.NCT = r.NCT "
                    + "and p.NP not in( "
                    + "Select p.NP "
                    + "from Place p, PlacesRes plr, Dossier d, Rang r "
                    + "where p.NP = plr.NP and r.NRA = p.NRA and r.NSA = " + NSA + " and plr.ND = d.ND and d.NR =" + NR + " )";
            rs = st.executeQuery(requeteSQL);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

}
