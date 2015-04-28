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
import modele.Dossier;

/**
 *
 * @author oswald
 */
public class DossierDAO extends AbstractDataBaseDAO {

    public DossierDAO(DataSource ds) {
        super(ds);
    }

    public List<Dossier> getFolders(String loginU, int boolResa) throws DAOException {
        List<Dossier> result = new ArrayList<>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from Dossier d, Spectacle s, Representation r "
                    + "WHERE d.LoginU ='" + loginU + "' and d.NR = r.NR and r.NSP = s.NSP and d.boolResa = " + boolResa;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Dossier dossier = new Dossier(rs.getInt("ND"),
                        rs.getInt("NbP"),
                        rs.getString("LoginU"),
                        rs.getInt("NT"),
                        rs.getInt("NR"),
                        rs.getString("NomS"),
                        rs.getString("DateR"),
                        rs.getString("HeureR"),
                        rs.getInt("boolResa"));
                System.err.println(dossier);
                result.add(dossier);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public boolean confirmResa(int ND) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            /* On récupère l'indice max de NT */
            int indiceNT_Max = 0;
            requeteSQL = "select max(NT) from Ticket";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNT_Max = rs.getInt(1);
            }

            /* On ajoute un nouveau ticket */
            indiceNT_Max++;
            requeteSQL = "INSERT INTO Ticket (NT)"
                    + "VALUES (" + indiceNT_Max + ")";
            st.executeQuery(requeteSQL);

            requeteSQL = "Update Dossier set boolResa = 0, NT = " + indiceNT_Max + " where ND = " + ND;
            rs = st.executeQuery(requeteSQL);

            return true;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    public boolean annuleResa(int ND) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            /* On récupère les informations de la représentation */
            requeteSQL = "select r.NbP, r.NR, d.NbP from Dossier d, Representation r "
                    + "where d.NR = r.NR and d.ND = " + ND;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            int nbPlacesRepr = rs.getInt(1);
            int NR = rs.getInt(2);
            int nbPlacesResa = rs.getInt(3);

            requeteSQL = "UPDATE Representation SET NbP=" + (nbPlacesRepr + nbPlacesResa) + " WHERE NR=" + NR;
            st.executeQuery(requeteSQL);

            requeteSQL = "delete from PlacesRes where ND =" + ND;
            rs = st.executeQuery(requeteSQL);

            requeteSQL = "Delete from Dossier where ND = " + ND;
            rs = st.executeQuery(requeteSQL);

            return true;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

}
