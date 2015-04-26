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
import modele.Spectacle;

/**
 *
 * @author oswald
 */
public class SpectacleDAO extends AbstractDataBaseDAO {

    public SpectacleDAO() {
    }

    public SpectacleDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'une liste d'instances de la classe métier Spectacle (List<Spectacle>)
     *
     * @return
     * @throws dao.DAOException
     */
    public List<Spectacle> getListeSpectacles() throws DAOException {
        List<Spectacle> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Spectacle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                if (rs.getInt("NSP") != 0) {

                    Spectacle spectacle = new Spectacle(rs.getInt("NSP"),
                            rs.getString("NomS"),
                            rs.getString("AuteurS"),
                            rs.getString("MESS"),
                            rs.getInt("DureeS"),
                            rs.getString("InfoS"),
                            rs.getString("Affiche"));
                    System.err.println(spectacle);
                    result.add(spectacle);
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
     * Ajoute l'ouvrage d'auteur et de titre spécifié dans la table bibli
     *
     * @param auteur
     * @param titre
     * @param mes
     * @param duree
     * @param infos
     * @param url
     * @param note
     * @throws dao.DAOException
     */
    public boolean ajouterSpectacle(String titre, String auteur, String mes, String duree, String infos, String url)
            throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        int indiceNSP_Max = 0;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select max(NSP) from Spectacle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNSP_Max = rs.getInt(1);
                indiceNSP_Max++;
            }

            requeteSQL = "INSERT INTO Spectacle (NSP, NomS, AuteurS, MESS, DureeS, InfoS, Affiche)"
                    + "VALUES (" + indiceNSP_Max + ", '" + titre + "', '" + auteur + "', '" + mes
                    + "', " + Integer.parseInt(duree) + ", '" + infos + ", '"+ url + "')";
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

    /**
     * Récupère l'ouvrage d'identifiant id dans la table bibliographie.
     *
     * @param id
     * @return
     * @throws dao.DAOException
     */
    public Spectacle getSpectacle(int id) throws DAOException {
        Spectacle result = new Spectacle();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Spectacle where NSp = " + id;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                if (rs.getInt("NSP") != 0) {

                    result = new Spectacle(rs.getInt("NSP"),
                            rs.getString("NomS"),
                            rs.getString("AuteurS"),
                            rs.getString("MESS"),
                            rs.getInt("DureeS"),
                            rs.getString("InfoS"),
                            rs.getString("Affiche"));
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
     * Modifie l'ouvrage d'identifiant id avec le nouvel auteur et le nouveau
     * titre spécifiés dans la table bibliographie
     *
     * @param id
     * @param auteur
     * @param titre
     * @throws dao.DAOException
     */
    public void modifierSpectacle(int id, String auteur, String titre)
            throws DAOException {
        // ...
    }

    /**
     * Supprime l'ouvrage d'identifiant id dans la table bibliographie.
     *
     * @param id
     * @throws dao.DAOException
     */
    public void supprimerSpectacle(int id) throws DAOException {
        //...
    }

//    private final String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
//    private final String login = "fournimi";
//    
//    public List<Spectacle> getListeSpectaclesMenu() throws DAOException, ClassNotFoundException {
//        Class.forName("oracle.jdbc.OracleDriver");
//        List<Spectacle> spectacles = new ArrayList<>();
//        try (Connection Connexion = DriverManager.getConnection(url, login, login)) {
//            Statement State = Connexion.createStatement();
//            ResultSet resultat = State.executeQuery("SELECT NSP, NomS FROM Spectacle");
//            while (resultat.next()) {
//                if (resultat.getInt("NSP") != 0) {
//                    spectacles.add(new Spectacle(resultat.getInt("NSP"), resultat.getString("NomS")));
//                }
//            }
//        } catch (SQLException e) {
//        }
//        
//        return spectacles;
//    }
    public List<Spectacle> getListeSpectaclesTri(String motscles,
            String date1,
            String date2,
            String prixDe,
            String prixA,
            String[] checkGenre,
            String[] checkPop) throws DAOException {

        List<Spectacle> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Spectacle "
                    + "where * like '%" + motscles + "%'";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                if (rs.getInt("NSP") != 0) {

                    Spectacle spectacle = new Spectacle(rs.getInt("NSP"),
                            rs.getString("NomS"),
                            rs.getString("AuteurS"),
                            rs.getString("MESS"),
                            rs.getInt("DureeS"),
                            rs.getString("Affiche"));
                    System.err.println(spectacle);
                    result.add(spectacle);
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
