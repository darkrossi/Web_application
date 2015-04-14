/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
        List<Salle> result = new ArrayList<Salle>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select NSa from Salle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Salle salle = new Salle(rs.getInt("Nsa"));
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

    public boolean ajouterSalle() throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        int indiceNSa_Max = 0;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select max(NSA) from Salle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNSa_Max = rs.getInt(1);
                indiceNSa_Max++;
            }

            requeteSQL = "INSERT INTO Salle (NSA) VALUES (" + indiceNSa_Max + ")";
            st.executeQuery(requeteSQL);
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
