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

    public SpectacleDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'une liste d'instances de la classe métier Spectacle (List<Spectacle>)
     * @return 
     * @throws dao.DAOException
     */
    public List<Spectacle> getListeSpectacles() throws DAOException {
        List<Spectacle> result = new ArrayList<Spectacle>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Spectacle";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Spectacle spectacle = new Spectacle(rs.getInt("NSP"),
                        rs.getString("NomS"),
                        rs.getString("AuteurS"),
                        rs.getString("MESS"),
                        rs.getInt("DureeS"));
                System.err.println(spectacle);
                result.add(spectacle);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Ajoute l'ouvrage d'auteur et de titre spécifié dans la table
     * bibli
     * @param auteur
     * @param titre
     * @throws dao.DAOException
     */
    public void ajouterSpectacle(String auteur, String titre)
            throws DAOException {
        // ...
    }

    /**
     * Récupère l'ouvrage d'identifiant id dans la table bibliographie.
     * @param id
     * @return 
     * @throws dao.DAOException
     */
    public Spectacle getSpectacle(int id) throws DAOException {
        // ...
        return null;
    }

    /**
     * Modifie l'ouvrage d'identifiant id avec le nouvel auteur et le nouveau
     * titre spécifiés dans la table bibliographie
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
     * @param id
     * @throws dao.DAOException
     */
    public void supprimerSpectacle(int id) throws DAOException {
        //...
    }
}
