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

    public boolean ajouterReservation(String loginU, String[] listRepr, String[] listNbP) throws DAOException {
        if (listRepr == null) {
            return true;
        } else {
            ResultSet rs = null;
            String requeteSQL = "";
            Connection conn = null;
            try {
                for (int i = 0; i < listRepr.length; i++) {
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
                    int currentNR = Integer.parseInt(listRepr[i]);
                    String[] columns = {"NR", "NbP", "NSa"};
                    requeteSQL = "select * from Representation "
                            + "where NR = " + currentNR;
                    rs = st.executeQuery(requeteSQL);
                    rs.next();
                    int[] dataRepr = {rs.getInt(columns[0]), rs.getInt(columns[1]), rs.getInt(columns[2])};

                    /* On récupère le nombre de place restante pour la représentation donnée */
                    int nbPlacesRestantes = dataRepr[1];
                    int nbPlacesMAJ = nbPlacesRestantes - Integer.parseInt(listNbP[i]);

                    /* On met à jour le nb de places restantes pour cette représentation */
                    requeteSQL = "UPDATE Representation SET NbP=" + nbPlacesMAJ + " WHERE NR=" + currentNR;
                    st.executeQuery(requeteSQL);

                    /* On marque les places réservées */
                    requeteSQL = "select * "
                            + "from Rang r, Place p "
                            + "where r.NSA = " + dataRepr[2] + " and p.NRa = r.NRa and p.isTaken = 0";
                    rs = st.executeQuery(requeteSQL);
                    int NRaTemp = -1;
                    List<Integer> indicesPl = new ArrayList<>();
                    while (rs.next()) {
                        if (NRaTemp == -1) {
                            NRaTemp = rs.getInt("r.NRa");
                        }
                        if (rs.getInt("r.NRa") != NRaTemp) {
                            indicesPl = new ArrayList<>();
                            NRaTemp = rs.getInt("r.NRa");
                        }
                        indicesPl.add(rs.getInt("p.NP"));
                        if (indicesPl.size() == 2) {
                            break;
                        }
                    }
                    
//                    Erreur BD Nom de colonne non valideselect * from Rang r, Place p where r.NSa = 1 and p.NRa = r.NRa and p.isTaken = 0 

                    for (int j = 0; j < 2; j++) {
                        requeteSQL = "UPDATE Place "
                                + "SET isTaken=1 "
                                + "WHERE NP=" + currentNR;
                        st.executeQuery(requeteSQL);
                    }

                    /* On ajoute un nouveau ticket */
                    requeteSQL = "INSERT INTO Ticket (NT)"
                            + "VALUES (" + indiceNT_Max + ")";
                    st.executeQuery(requeteSQL);

                    /* On ajoute le dossier correspondant */
                    requeteSQL = "INSERT INTO Dossier (ND, NR, LoginU, NT, NbP)"
                            + "VALUES (" + indiceND_Max + ", " + dataRepr[0] + ", '" + loginU + "', "
                            + indiceNT_Max + ", " + Integer.parseInt(listNbP[i]) + ")";
                    st.executeQuery(requeteSQL);
                }
                return true;
            } catch (SQLException e) {
                throw new DAOException("Erreur BD " + e.getMessage() + requeteSQL, e);
            } finally {
                closeConnection(conn);
            }
        }
    }
}
