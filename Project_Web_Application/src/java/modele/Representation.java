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
public class Representation {

    private int NR;
    private String date;
    private String heure;
    private int NSp;
    private int NSa;
    private int NbP;
    private String urlImg;

    public Representation() {
    }

    public Representation(int NR, String date, String heure, int NSp, int NSa, int NbP, String urlImg) {
        this.NR = NR;
        this.date = date;
        this.heure = heure;
        this.NSp = NSp;
        this.NSa = NSa;
        this.NbP = NbP;
        this.urlImg = urlImg;
    }
    
    public Representation(int NR, String date, String heure, int NSp, int NSa, int NbP) {
        this.NR = NR;
        this.date = date;
        this.heure = heure;
        this.NSp = NSp;
        this.NSa = NSa;
        this.NbP = NbP;
        this.urlImg = "";
    }

    /**
     * @return the NR
     */
    public int getNR() {
        return NR;
    }

    /**
     * @param NR the NR to set
     */
    public void setNR(int NR) {
        this.NR = NR;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the heure
     */
    public String getHeure() {
        return heure;
    }

    /**
     * @param heure the heure to set
     */
    public void setHeure(String heure) {
        this.heure = heure;
    }

    /**
     * @return the NSp
     */
    public int getNSp() {
        return NSp;
    }

    /**
     * @param NSp the NSp to set
     */
    public void setNSp(int NSp) {
        this.NSp = NSp;
    }

    /**
     * @return the NSa
     */
    public int getNSa() {
        return NSa;
    }

    /**
     * @param NSa the NSa to set
     */
    public void setNSa(int NSa) {
        this.NSa = NSa;
    }

    /**
     * @return the NbP
     */
    public int getNbP() {
        return NbP;
    }

    /**
     * @param NbP the NbP to set
     */
    public void setNbP(int NbP) {
        this.NbP = NbP;
    }

    /**
     * @return the urlImg
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * @param urlImg the urlImg to set
     */
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

}
