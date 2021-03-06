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

/**
 *
 * @author oswald
 */
public class AchatDAO extends AbstractDataBaseDAO {

    /**
     *
     * @param ds
     */
    public AchatDAO(DataSource ds) {
        super(ds);
    }

    /**
     *
     * @param loginU
     * @param NR
     * @param NP
     * @param nbP
     * @param boolResa
     * @return
     * @throws DAOException
     */
    public boolean ajouterAchat(String loginU, int NR, int[] NP, int nbP, int boolResa) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            /* On récupère l'indice max de ND */
            int indiceND_Max = 0;
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select max(ND) from Dossier";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceND_Max = rs.getInt(1);
                indiceND_Max++;
            }

            /* On récupère l'indice max de NT */
            int indiceNT_Max = 0;
            requeteSQL = "select max(NT) from Ticket";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNT_Max = rs.getInt(1);
                indiceNT_Max++;
            }

            /* On récupère l'indice max de NPR */
            int indiceNPR_Max = 0;
            requeteSQL = "select max(NPR) from PlacesRes";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                indiceNPR_Max = rs.getInt(1);
                indiceNPR_Max++;
            }

            /* On récupère les informations de la représentation */
            requeteSQL = "select * from Representation "
                    + "where NR = " + NR;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            int[] dataRepr = {rs.getInt("NR"), rs.getInt("NbP"), rs.getInt("NSa")};

            /* On récupère le nombre de place restante pour la représentation donnée */
            int nbPlacesRestantes = dataRepr[1];
            int nbPlacesMAJ = nbPlacesRestantes - nbP;

            /* On met à jour le nb de places restantes pour cette représentation */
            requeteSQL = "UPDATE Representation SET NbP=" + nbPlacesMAJ + " WHERE NR=" + NR;
            st.executeQuery(requeteSQL);

            if (boolResa == 0) {
                /* On ajoute un nouveau ticket */
                requeteSQL = "INSERT INTO Ticket (NT)"
                        + "VALUES (" + indiceNT_Max + ")";
                st.executeQuery(requeteSQL);

                /* On ajoute le dossier correspondant */
                requeteSQL = "INSERT INTO Dossier (ND, NR, LoginU, NT, NbP, boolResa)"
                        + "VALUES (" + indiceND_Max + ", " + dataRepr[0] + ", '" + loginU + "', "
                        + indiceNT_Max + ", " + nbP + ", " + boolResa + ")";
                st.executeQuery(requeteSQL);
            } else {
                /* On ajoute le dossier correspondant */
                requeteSQL = "INSERT INTO Dossier (ND, NR, LoginU, NT, NbP, boolResa)"
                        + "VALUES (" + indiceND_Max + ", " + dataRepr[0] + ", '" + loginU
                        + "', -1, " + nbP + ", " + boolResa + ")";
                st.executeQuery(requeteSQL);
            }

            /* On marque les places réservées */
            for (int i = 0; i < nbP; i++) {
                requeteSQL = "INSERT INTO PlacesRes (NPR, NP, ND) "
                        + "VALUES (" + indiceNPR_Max + ", " + NP[i] + ", " + indiceND_Max + ")";
                st.executeQuery(requeteSQL);
                indiceNPR_Max++;
            }

            return true;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
        } finally {
            closeConnection(conn);
        }
    }
}
