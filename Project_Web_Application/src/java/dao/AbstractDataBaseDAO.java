/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author oswald
 */
public abstract class AbstractDataBaseDAO {

    /**
     *
     */
    protected final DataSource dataSource;

    /**
     *
     */
    protected AbstractDataBaseDAO() {
        this.dataSource = null;
    }

    /**
     *
     * @param ds
     */
    protected AbstractDataBaseDAO(DataSource ds) {
        this.dataSource = ds;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /* fermeture d'une connexion
     * @param c la connexion à fermer
     * @throws DAOException si problème lors de la fermeture de la connexion
     */

    /**
     *
     * @param c
     * @throws DAOException
     */
    
    protected void closeConnection(Connection c) throws DAOException {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException sqle) {
                throw new DAOException("Problème fermeture de connexion avec la BD ", sqle);
            }
        }
    }
}
