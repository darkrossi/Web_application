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
public class Dossier {

    private int ND;
    private int NbP;
    private String LoginU;
    private int NT;
    private int NR;
    private String nomSpectacle;
    private String date;
    private String heure;

    public Dossier() {
    }

    public Dossier(int ND, int NbP, String LoginU, int NT, int NR, String nomSpectacle, String date, String heure) {
        this.ND = ND;
        this.NbP = NbP;
        this.LoginU = LoginU;
        this.NT = NT;
        this.NR = NR;
        this.nomSpectacle = nomSpectacle;
        this.date = date;
        this.heure = heure;
    }

    /**
     * @return the ND
     */
    public int getND() {
        return ND;
    }

    /**
     * @param ND the ND to set
     */
    public void setND(int ND) {
        this.ND = ND;
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
     * @return the LoginU
     */
    public String getLoginU() {
        return LoginU;
    }

    /**
     * @param LoginU the LoginU to set
     */
    public void setLoginU(String LoginU) {
        this.LoginU = LoginU;
    }

    /**
     * @return the NT
     */
    public int getNT() {
        return NT;
    }

    /**
     * @param NT the NT to set
     */
    public void setNT(int NT) {
        this.NT = NT;
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
     * @return the nomSpectacle
     */
    public String getNomSpectacle() {
        return nomSpectacle;
    }

    /**
     * @param nomSpectacle the nomSpectacle to set
     */
    public void setNomSpectacle(String nomSpectacle) {
        this.nomSpectacle = nomSpectacle;
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

}
