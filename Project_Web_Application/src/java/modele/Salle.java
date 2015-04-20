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
    private int CatTarif;
    private int NbRa;
    private int NbP;

    public Salle() {
    }

    public Salle(int NSa, int CatTarif, int NbRa, int NbP) {
        this.NSa = NSa;
        this.CatTarif = CatTarif;
        this.NbRa = NbRa;
        this.NbP = NbP;
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
     * @return the CatTarif
     */
    public int getCatTarif() {
        return CatTarif;
    }

    /**
     * @param CatTarif the CatTarif to set
     */
    public void setCatTarif(int CatTarif) {
        this.CatTarif = CatTarif;
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

}
