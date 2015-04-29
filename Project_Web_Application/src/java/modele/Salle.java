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
public class Salle {

    private int NSa;
    private int NbRa;
    private int NbP;
    private String NomSa;

    /**
     *
     */
    public Salle() {
    }

    /**
     *
     * @param NSa
     * @param NbRa
     * @param NbP
     * @param NomSa
     */
    public Salle(int NSa, int NbRa, int NbP, String NomSa) {
        this.NSa = NSa;
        this.NbRa = NbRa;
        this.NbP = NbP;
        this.NomSa = NomSa;
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
     * @return the NbRa
     */
    public int getNbRa() {
        return NbRa;
    }

    /**
     * @param NbRa the NbRa to set
     */
    public void setNbRa(int NbRa) {
        this.NbRa = NbRa;
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
     * @return the NomSa
     */
    public String getNomSa() {
        return NomSa;
    }

    /**
     * @param NomSa the NomSa to set
     */
    public void setNomSa(String NomSa) {
        this.NomSa = NomSa;
    }

}
