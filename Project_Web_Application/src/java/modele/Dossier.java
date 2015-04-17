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
    private int NSP;
    private int NSA;
    private int NR;

    public Dossier() {
    }

    public Dossier(int ND, int NbP, String LoginU, int NT, int NSP, int NSA, int NR) {
        this.ND = ND;
        this.NbP = NbP;
        this.LoginU = LoginU;
        this.NT = NT;
        this.NSP = NSP;
        this.NSA = NSA;
        this.NR = NR;
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
     * @return the NSP
     */
    public int getNSP() {
        return NSP;
    }

    /**
     * @param NSP the NSP to set
     */
    public void setNSP(int NSP) {
        this.NSP = NSP;
    }

    /**
     * @return the NSA
     */
    public int getNSA() {
        return NSA;
    }

    /**
     * @param NSA the NSA to set
     */
    public void setNSA(int NSA) {
        this.NSA = NSA;
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

}
