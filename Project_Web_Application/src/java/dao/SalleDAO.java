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
                        rs.getInt("NbP"));
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
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select max(NSA) from Salle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNSa_Max = rs.getInt(1);
                indiceNSa_Max++;
            }

            requeteSQL = "select max(NP) from Place";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNPl_Max = rs.getInt(1);
            }

            requeteSQL = "INSERT INTO Salle (NSA, NbRa) VALUES (" + indiceNSa_Max + ", " + nbRa + ")";
            st.executeQuery(requeteSQL);

            for (int i = 1; i <= nbRa; i++) {
                requeteSQL = "INSERT INTO Rang (NRA, CatTarif, NSA, NbP) VALUES (" + i + ", " + catTarif + ", "
                        + indiceNSa_Max + ", " + nbP + ")";
                st.executeQuery(requeteSQL);
                for (int j = 1; j <= nbP; j++) {
                    requeteSQL = "INSERT INTO Place (NP, NRa, isTaken) "
                            + "VALUES (" + indiceNPl_Max++ + ", " + i + ", 0)";
                    st.executeQuery(requeteSQL);
                }
            }

            return true;
        } catch (SQLException e) {
            // si l'exception concerne l'unicité de chaque login dans la table
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
}
