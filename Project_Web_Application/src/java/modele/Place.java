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
    private int isTaken;

    public Place() {
    }

    public Place(int NP, int isTaken) {
        this.NP = NP;
        this.isTaken = isTaken;
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
     * @return the isTaken
     */
    public int getIsTaken() {
        return isTaken;
    }

    /**
     * @param isTaken the isTaken to set
     */
    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }
}
