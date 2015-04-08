/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author oswald
 */
public class Spectacle {

    static org.jdom2.Document document;
    static Element racine;
    static String login;

    private int id;
    private String titre;
    private String auteur;
    private String metteurEnScene;
    private ArrayList<String> comediens;
    private int duree; // En minutes

    public Spectacle() {
    }

    public Spectacle(int id, String titre, String auteur, String metteurEnScene, ArrayList<String> comediens, int duree) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = comediens;
        this.duree = duree;
    }

    public Spectacle(int id, String titre, String auteur, String metteurEnScene, int duree) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = new ArrayList<String>();
        this.duree = duree;
    }

    public Spectacle(int id, String titre, String auteur) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = "";
        this.comediens = new ArrayList<String>();
        this.duree = 0;
    }

    public String getAuteur() {
        return auteur;
    }

    public ArrayList<String> getComediens() {
        return comediens;
    }

    public int getDuree() {
        return duree;
    }

    public int getId() {
        return id;
    }

    public String getMetteurEnScene() {
        return metteurEnScene;
    }

    public String getTitre() {
        return titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setComediens(ArrayList<String> comediens) {
        this.comediens = comediens;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMetteurEnScene(String metteurEnScene) {
        this.metteurEnScene = metteurEnScene;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String afficheComediens() {
        String sortie = "";
        if (this.getComediens().size() != 0) {
            sortie = this.getComediens().get(0);
            for (int i = 1; i < this.getComediens().size(); i++) {
                String comedien = this.getComediens().get(i);
                sortie += ", " + comedien;
            }
        }
        return sortie;
    }

    @Override
    public String toString() {
        return "La piéce " + this.getTitre() + " de " + this.getAuteur() + " et mise en scéne par " + this.getMetteurEnScene()
                + " regroupe les comédiens " + this.afficheComediens() + "."; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Spectacle) {
            Spectacle spect = (Spectacle) obj;
            return this.getId() == spect.getId();
        } else {
            return false;
        }
    }

    //Ajouter cette méthodes à la classe JDOM2
//    static void afficheALL() {
//        //On crée une List contenant tous les noeuds "etudiant" de l'Element racine
//        List listEtudiants = racine.getChildren("etudiant");
//
//        //On crée un Iterator sur notre liste
//        Iterator i = listEtudiants.iterator();
//        while (i.hasNext()) {
//            //On recrée l'Element courant à chaque tour de boucle afin de
//            //pouvoir utiliser les méthodes propres aux Element comme :
//            //sélectionner un nœud fils, modifier du texte, etc...
//            Element courant = (Element) i.next();
//            //On affiche le nom de l’élément courant
//            System.out.println(courant.getChild("nom").getText());
//        }
//    }
}
