/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import dao.DAOException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static modele.Affiche.login;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author oswald
 */
public class Affiche {

    static org.jdom2.Document document;
    static Element racine;
    static String login;

    private String url;

    public Affiche() {
        this.url = "";
    }

    public Affiche(String url) {
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url; //To change body of generated methods, choose Tools | Templates.
    }

    public List<Affiche> getListeAffiches() throws DAOException, ClassNotFoundException, IOException, Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        ArrayList<Affiche> affiches = new ArrayList<Affiche>();
        this.getLoginPerso();
        try (Connection Connexion = DriverManager.getConnection("jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1", login, login)) {
            Statement State = Connexion.createStatement();
            ResultSet resultat = State.executeQuery("SELECT NSP, Affiche FROM Spectacle");
            while (resultat.next()) {
                if (resultat.getInt("NSP") != 0) {
                    String url = resultat.getString("Affiche");
                    affiches.add(new Affiche(url));
                }
            }
        } catch (SQLException e) {
        }
        if (affiches.isEmpty()) {
            affiches.add(new Affiche("empty.png"));
        }
        return affiches;
    }

    public void getLoginPerso() throws IOException {

        //On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        File temp = File.createTempFile("i-am-a-temp-file", ".tmp");
        String absolutePath = temp.getAbsolutePath();
        String path = absolutePath.split("/Web_application/")[0];
        path += "/Web_application/Project_Web_Application/web/META-INF/context.xml";
        try {
            //On crée un nouveau document JDOM avec en argument le fichier XML
            //Le parsing est terminé ;)
//            String path = absolutePath.split("");
            document = sxb.build(new File(path));

            //On initialise un nouvel élément racine avec l'élément racine du document.
            racine = document.getRootElement();

            login = racine.getChild("Resource").getAttribute("username").getValue();
        } catch (Exception e) {
            login = path;
        }

    }

}
