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
public class Place {

    private int NP;
    private int NumPl;
    private int NRa;

    /**
     *
     */
    public Place() {
    }

    /**
     *
     * @param NP
     * @param NumPl
     * @param NRa
     */
    public Place(int NP, int NumPl, int NRa) {
        this.NP = NP;
        this.NumPl = NumPl;
        this.NRa = NRa;
    }

    /**
     * @return the NP
     */
    public int getNP() {
        return NP;
    }

    /**
     * @param NP the NP to set
     */
    public void setNP(int NP) {
        this.NP = NP;
    }

    /**
     * @return the NumPl
     */
    public int getNumPl() {
        return NumPl;
    }

    /**
     * @param NumPl the NumPl to set
     */
    public void setNumPl(int NumPl) {
        this.NumPl = NumPl;
    }

    /**
     * @return the NRa
     */
    public int getNRa() {
        return NRa;
    }

    /**
     * @param NRa the NRa to set
     */
    public void setNRa(int NRa) {
        this.NRa = NRa;
    }

}
