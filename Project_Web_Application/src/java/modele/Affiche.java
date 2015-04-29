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
public class Affiche {

    private String url;

    /**
     *
     */
    public Affiche() {
        this.url = "";
    }

    /**
     *
     * @param url
     */
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
}
