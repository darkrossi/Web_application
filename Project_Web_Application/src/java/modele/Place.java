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

    public Place() {
    }

    public Place(int NP, int NumPl) {
        this.NP = NP;
        this.NumPl = NumPl;
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

}
