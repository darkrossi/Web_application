/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.sql.DataSource;
import modele.Spectacle;

/**
 *
 * @author oswald
 */
public class SpectacleDAO extends AbstractDataBaseDAO {

    /**
     *
     */
    public SpectacleDAO() {
    }

    /**
     *
     * @param ds
     */
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
                            rs.getString("Affiche"),
                            rs.getString("InfoS"));
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
     * @return
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
                    + "', " + Integer.parseInt(duree) + ", '" + infos + "', '" + url + "')";
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
                            rs.getString("Affiche"),
                            rs.getString("InfoS"));
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

    /**
     *
     * @param motscles
     * @param date1
     * @param date2
     * @param prixDe
     * @param prixA
     * @param checkGenre
     * @param checkPop
     * @return
     * @throws DAOException
     */
    public List<Spectacle> getListeSpectaclesTri(String motscles,
            String date1,
            String date2,
            String prixDe,
            String prixA) throws DAOException {

        Hashtable<Integer, Spectacle> hashSpect = new Hashtable<>();
        Hashtable<Integer, List<String>> hashDate = new Hashtable<>();

        List<Spectacle> result = new ArrayList<>();
        List<Spectacle> resultFinal = new ArrayList<>();

        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        String s = "debut ";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            String whereMotsCles = " and (LOWER(s.NomS) like '%" + motscles + "%'"
                    + " or LOWER(s.AuteurS) like '%" + motscles + "%'"
                    + " or LOWER(s.MESS) like '%" + motscles + "%'"
                    + " or LOWER(s.InfoS) like '%" + motscles + "%')";

            String wherePrix = "";
            if (!"".equals(prixDe) && !"".equals(prixA)) {
                wherePrix = " and rg.CatTarif  between " + prixDe + " and " + prixA;
            }

            requeteSQL = "select distinct s.NSp, s.NomS, s.AuteurS, s.MESS, s.DureeS, s.Affiche, s.infos, rep.dateR "
                    + "from Spectacle s, Representation rep, Salle sa, Rang rg "
                    + "where rep.NSp = s.NSp and rep.NSa = sa.NSa and sa.NSa = rg.NSa"
                    + whereMotsCles + wherePrix;
            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                if (rs.getInt("NSP") != 0) {
                    Spectacle spectacle = new Spectacle(rs.getInt("NSP"),
                            rs.getString("NomS"),
                            rs.getString("AuteurS"),
                            rs.getString("MESS"),
                            rs.getInt("DureeS"),
                            rs.getString("Affiche"),
                            rs.getString("InfoS"));
                    if (!hashSpect.containsKey(rs.getInt("NSP"))) {
                        hashSpect.put(rs.getInt("NSP"), spectacle);
                    }
                    if (!hashDate.containsKey(rs.getInt("NSP"))) {
                        hashDate.put(rs.getInt("NSP"), new ArrayList<>());
                    }
                    hashDate.get(rs.getInt("NSP")).add(rs.getString("dateR"));
                }
            }

            Enumeration keys2 = hashDate.keys();
            while (keys2.hasMoreElements()) { // On affiche le menu déroulant avec les rangs
                Integer NS = (Integer) keys2.nextElement();
                if (!"".equals(date1) && !"".equals(date2)) { // Si il y a un tri par date
                    List<String> dates = hashDate.get(NS);
                    for (int i = 0; i < dates.size(); i++) {
                        if (between(dates.get(i), date1, date2)) {
                            result.add(hashSpect.get(NS));
                            break;
                        }
                    }
                } else { // Si il n'y pas de tri par date
                    result.add(hashSpect.get(NS));
                }
            }
            
            for(int i = result.size()-1; i>=0; i--){
                resultFinal.add(result.get(i));
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage() + " " + s, e);
        } finally {
            closeConnection(conn);
        }
        return resultFinal;
    }

    /**
     *
     * @param date
     * @param dateDe
     * @param dateA
     * @return
     * @throws DAOException
     */
    public static boolean between(String date, String dateDe, String dateA) throws DAOException {
        int[] dateT = new int[3];
        int[] dateDeT = new int[3];
        int[] dateAT = new int[3];

        for (int i = 0; i < 2; i++) {
            dateT[i] = Integer.parseInt(date.substring(3 * i, 3 * i + 2));
            dateDeT[i] = Integer.parseInt(dateDe.substring(3 * i, 3 * i + 2));
            dateAT[i] = Integer.parseInt(dateA.substring(3 * i, 3 * i + 2));
        }
        dateT[2] = Integer.parseInt(date.substring(6, 10));
        dateDeT[2] = Integer.parseInt(dateDe.substring(6, 10));
        dateAT[2] = Integer.parseInt(dateA.substring(6, 10));

        Date dateFinale = new Date(dateT[2] - 1900, dateT[1] - 1, dateT[0]);
        Date dateFinaleDe = new Date(dateDeT[2] - 1900, dateDeT[1] - 1, dateDeT[0]);
        Date dateFinaleA = new Date(dateAT[2] - 1900, dateAT[1] - 1, dateAT[0]);

        double diffDe = dateFinale.getTime() - dateFinaleDe.getTime();
        double diffA = dateFinaleA.getTime() - dateFinale.getTime();

        return diffDe >= 0 && diffA >= 0;
    }

}
