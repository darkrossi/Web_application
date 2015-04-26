/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

import org.jdom2.*;

/**
 *
 * @author oswald
 */
public class Spectacle {

    private int id;
    private String titre;
    private String auteur;
    private String metteurEnScene;
    private ArrayList<String> comediens;
    private int duree; // En minutes
    private String url;
    private String infos;
    private int note;

    public Spectacle() {
    }

    public Spectacle(int id, String titre, String auteur, String metteurEnScene, int duree, String url) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = new ArrayList<>();
        this.duree = duree;
        this.url = url;
    }

    public Spectacle(int id, String titre, String auteur, String metteurEnScene, ArrayList<String> comediens, int duree) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = comediens;
        this.duree = duree;
    }
   
    public Spectacle(int id, String titre, String auteur, String metteurEnScene, int duree, String infos, String url) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.duree = duree;
        this.infos = infos;
        this.url= url;
    }
    
    public Spectacle(int id, String titre, String auteur, String metteurEnScene, int duree) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = new ArrayList<>();
        this.duree = duree;
    }

    public Spectacle(int id, String titre, String auteur) {
        this.id = id;
        this.titre = titre;
        this.auteur = "";
        this.metteurEnScene = "";
        this.comediens = new ArrayList<String>();
        this.duree = 0;
    }

    public Spectacle(int id, String titre) {
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
    
    public String getInfos(){
        return infos;
    }

    public int getNote(){
        return note;
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

    public void setInfos(String infos){
        this.infos = infos;
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
    
    public void setNote(int note) {
        this.note = note;
    }

    public String afficheComediens() {
        String sortie = "";
        if (!this.getComediens().isEmpty()) {
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
        return "La pièce " + this.getTitre() + " de " + this.getAuteur() + " et mise en scène par " + this.getMetteurEnScene()
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
}
