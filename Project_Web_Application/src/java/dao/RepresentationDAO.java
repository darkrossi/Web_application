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
import modele.Representation;

/**
 *
 * @author oswald
 */
public class RepresentationDAO extends AbstractDataBaseDAO {

    public RepresentationDAO() {
    }

    public RepresentationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Ajoute l'ouvrage d'auteur et de titre spécifié dans la table bibli
     *
     * @param auteur
     * @param titre
     * @param mes
     * @param duree
     * @throws dao.DAOException
     */
    public boolean ajouterRepresentation(String date, String heure, String NSp, String NSa, String NbP)
            throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        int indiceNR_Max = 0;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = "select max(NR) from Representation";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNR_Max = rs.getInt(1);
                indiceNR_Max++;
            }

            requeteSQL = "INSERT INTO Representation (NR, DateR, HeureR, NSP, NSA, NbP)"
                    + "VALUES (" + indiceNR_Max + ", '" + date + "', '" + heure + "', " + Integer.parseInt(NSp) + ", " + Integer.parseInt(NSa) + ", " + Integer.parseInt(NbP) + ")";
            st.executeQuery(requeteSQL);
            return true;

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
                        rs.getInt("NbP"));
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

//    private final String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
//    private final String login = "fournimi";
//
//    public List<Representation> getListeRepres(int NSp) throws DAOException, ClassNotFoundException {
//        Class.forName("oracle.jdbc.OracleDriver");
//        List<Representation> representations = new ArrayList<>();
//        try (Connection Connexion = DriverManager.getConnection(url, login, login)) {
//            Statement State = Connexion.createStatement();
//            ResultSet result = State.executeQuery("SELECT * FROM Representation WHEN NSP = " + NSp);
//            while (result.next()) {
//                representations.add(new Representation(result.getInt("NR"), 
//                        result.getString("DateR"),
//                        result.getString("HeureR"),
//                        result.getInt("NSP"),
//                        result.getInt("NSA"),
//                        result.getInt("NbP")));
//            }
//        } catch (SQLException e) {
//        }
//
//        return representations;
//    }
}