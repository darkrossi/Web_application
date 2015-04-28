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

    public AchatDAO(DataSource ds) {
        super(ds);
    }

    public boolean ajouterAchat(String loginU, int NR, int NRa, int NP, int nbP, int boolResa) throws DAOException {
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

            /* On récupère les informations de la représentation */
            String[] columns = {"NR", "NbP", "NSa"};
            requeteSQL = "select * from Representation "
                    + "where NR = " + NR;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            int[] dataRepr = {rs.getInt(columns[0]), rs.getInt(columns[1]), rs.getInt(columns[2])};

            /* On récupère le nombre de place restante pour la représentation donnée */
            int nbPlacesRestantes = dataRepr[1];
            int nbPlacesMAJ = nbPlacesRestantes - nbP;

            /* On met à jour le nb de places restantes pour cette représentation */
            requeteSQL = "UPDATE Representation SET NbP=" + nbPlacesMAJ + " WHERE NR=" + NR;
            st.executeQuery(requeteSQL);

            /* On marque les places réservées */
            requeteSQL = "UPDATE Place "
                    + "SET isTaken=1, ND=" + indiceND_Max + " "
                    + "WHERE NP=" + NP;
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
            return true;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + requeteSQL, e);
        } finally {
            closeConnection(conn);
        }
    }
}

//public boolean ajouterAchat(String loginU, String[] listRepr, String[] listNbP) throws DAOException {
//        if (listRepr == null) {
//            return true;
//        } else {
//            ResultSet rs = null;
//            String requeteSQL = "";
//            Connection conn = null;
//            try {
//                for (int i = 0; i < listRepr.length; i++) {
//                    /* On récupère l'indice max de ND */
//                    int indiceND_Max = 0;
//                    conn = getConnection();
//                    Statement st = conn.createStatement();
//                    requeteSQL = "select max(ND) from Dossier";
//                    rs = st.executeQuery(requeteSQL);
//                    while (rs.next()) {
//                        indiceND_Max = rs.getInt(1);
//                        indiceND_Max++;
//                    }
//
//                    /* On récupère l'indice max de NT */
//                    int indiceNT_Max = 0;
//                    requeteSQL = "select max(NT) from Ticket";
//                    rs = st.executeQuery(requeteSQL);
//                    while (rs.next()) {
//                        indiceNT_Max = rs.getInt(1);
//                        indiceNT_Max++;
//                    }
//
//                    /* On récupère les informations de la représentation */
//                    int NR = Integer.parseInt(listRepr[i]);
//                    String[] columns = {"NR", "NbP", "NSa"};
//                    requeteSQL = "select * from Representation "
//                            + "where NR = " + NR;
//                    rs = st.executeQuery(requeteSQL);
//                    rs.next();
//                    int[] dataRepr = {rs.getInt(columns[0]), rs.getInt(columns[1]), rs.getInt(columns[2])};
//
//                    /* On récupère le nombre de place restante pour la représentation donnée */
//                    int nbPlacesRestantes = dataRepr[1];
//                    int nbPlacesMAJ = nbPlacesRestantes - Integer.parseInt(listNbP[i]);
//
//                    /* On met à jour le nb de places restantes pour cette représentation */
//                    requeteSQL = "UPDATE Representation SET NbP=" + nbPlacesMAJ + " WHERE NR=" + NR;
//                    st.executeQuery(requeteSQL);
//
//                    /* On marque les places réservées */
//                    requeteSQL = "select r.NRa, p.NP "
//                            + "from Rang r, Place p "
//                            + "where r.NSA = " + dataRepr[2] + " and p.NRa = r.NRa and p.isTaken = 0";
//                    rs = st.executeQuery(requeteSQL);
//
//                    int NRaTemp = -1;
//                    List<Integer> indicesPl = new ArrayList<>();
//                    while (rs.next()) {
//                        if (NRaTemp == -1) {
//                            NRaTemp = rs.getInt(1);
//                        }
//                        if (rs.getInt(1) != NRaTemp) {
//                            indicesPl = new ArrayList<>();
//                            NRaTemp = rs.getInt(1);
//                        }
//                        indicesPl.add(rs.getInt(2));
//                        if (indicesPl.size() == Integer.parseInt(listNbP[i])) {
//                            break;
//                        }
//                    }
//
//                    for (Integer indicesPl1 : indicesPl) {
//                        requeteSQL = "UPDATE Place "
//                                + "SET isTaken=1, ND=" + indiceND_Max + " "
//                                + "WHERE NP=" + indicesPl1;
//                        st.executeQuery(requeteSQL);
//                    }
//
//                    /* On ajoute un nouveau ticket */
//                    requeteSQL = "INSERT INTO Ticket (NT)"
//                            + "VALUES (" + indiceNT_Max + ")";
//                    st.executeQuery(requeteSQL);
//
//                    /* On ajoute le dossier correspondant */
//                    requeteSQL = "INSERT INTO Dossier (ND, NR, LoginU, NT, NbP)"
//                            + "VALUES (" + indiceND_Max + ", " + dataRepr[0] + ", '" + loginU + "', "
//                            + indiceNT_Max + ", " + Integer.parseInt(listNbP[i]) + ")";
//                    st.executeQuery(requeteSQL);
//                }
//                return true;
//            } catch (SQLException e) {
//                throw new DAOException("Erreur BD " + e.getMessage(), e);
//            } finally {
//                closeConnection(conn);
//            }
//        }
//    }
