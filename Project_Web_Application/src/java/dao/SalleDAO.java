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
import modele.Salle;

/**
 *
 * @author oswald
 */
public class SalleDAO extends AbstractDataBaseDAO {

//    private final String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
//    private final String login = "fournimi";
//
//    public SalleDAO() {
//    }
    public SalleDAO(DataSource ds) {
        super(ds);
    }

    public List<Salle> getListeSalles() throws DAOException {
        List<Salle> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select distinct s.NSA, r.CatTarif, r.NbP, s.NbRa "
                    + "from Salle s, Rang r "
                    + "where r.NSA = s.NSA";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Salle salle = new Salle(rs.getInt("NSA"),
                        rs.getInt("CatTarif"),
                        rs.getInt("NbRa"),
                        rs.getInt("NbP") * rs.getInt("NbRa"));
                System.err.println(salle);
                result.add(salle);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

//    public List<Salle> getListeSallesBis() throws DAOException, ClassNotFoundException {
//        Class.forName("oracle.jdbc.OracleDriver");
//        List<Salle> salles = new ArrayList<>();
//        try (Connection Connexion = DriverManager.getConnection(url, login, login)) {
//            Statement State = Connexion.createStatement();
//            ResultSet resultat = State.executeQuery("SELECT NSA FROM Salle");
//            while (resultat.next()) {
//                int id = resultat.getInt("NSA");
//                salles.add(new Salle(id));
//            }
//        } catch (SQLException e) {
//        }
//        return salles;
//    }
    public boolean ajouterSalle(int nbRa, int nbP, int catTarif) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        int indiceNSa_Max = 0;
        int indiceNPl_Max = 0;
        int indiceNRa_Max = 0;
        if (nbRa * nbP < 70) {
            try {
                conn = getConnection();
                Statement st = conn.createStatement();
                requeteSQL = "select max(NSA) from Salle";
                rs = st.executeQuery(requeteSQL);
                while (rs.next()) {
                    indiceNSa_Max = rs.getInt(1);
                }

                requeteSQL = "select max(NP) from Place";
                rs = st.executeQuery(requeteSQL);
                while (rs.next()) {
                    indiceNPl_Max = rs.getInt(1);
                }

                requeteSQL = "select max(NRa) from Rang";
                rs = st.executeQuery(requeteSQL);
                while (rs.next()) {
                    indiceNRa_Max = rs.getInt(1);
                }

                indiceNSa_Max++;
                requeteSQL = "INSERT INTO Salle (NSA, NbRa) VALUES (" + indiceNSa_Max + ", " + nbRa + ")";
                st.executeQuery(requeteSQL);

                for (int i = 1; i <= nbRa; i++) {
                    indiceNRa_Max++;
                    requeteSQL = "INSERT INTO Rang (NRA, CatTarif, NSA, NbP) VALUES (" + indiceNRa_Max + ", " + catTarif + ", "
                            + indiceNSa_Max + ", " + nbP + ")";
                    st.executeQuery(requeteSQL);
                    for (int j = 1; j <= nbP; j++) {
                        indiceNPl_Max++;
                        requeteSQL = "INSERT INTO Place (NP, NRa, isTaken, ND) "
                                + "VALUES (" + indiceNPl_Max + ", " + i + ", 0, -1)";
                        st.executeQuery(requeteSQL);
                    }
                }

                return true;
            } catch (SQLException e) {
                throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
            } finally {
                closeConnection(conn);
            }
        } else {
            return false;
        }
    }
}
