/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author oswald
 */
public class Spectacle {

    private int id;
    private String titre;
    private String auteur;
    private String metteurEnScene;
    private String[] comediens;
    private int duree; // En minutes

    public Spectacle() {
    }

    public Spectacle(int id, String titre, String auteur, String metteurEnScene, String[] comediens, int duree) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = metteurEnScene;
        this.comediens = comediens;
        this.duree = duree;
    }
    
    public Spectacle(int id, String titre, String auteur){
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.metteurEnScene = "";
        //this.comediens = {};
        this.duree = 0;
    }

    public String getAuteur() {
        return auteur;
    }

    public String[] getComediens() {
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

    public void setComediens(String[] comediens) {
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
        if (this.getComediens().length != 0) {
            sortie = this.getComediens()[0];
            for (int i = 1; i < this.getComediens().length; i++) {
                String comedien = this.getComediens()[i];
                sortie += ", " + comedien;
            }
        }
        return sortie;
    }

    @Override
    public String toString() {
        return "La piéce " + this.getTitre() + " de " + this.getAuteur() + " et mise en scéne par " + this.getMetteurEnScene()
                + "regroupe les comédiens " + this.afficheComediens() + "."; //To change body of generated methods, choose Tools | Templates.
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

}
