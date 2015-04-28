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
public class Rang {

    private int NRa;
    private String NomCT;
    private int PrixCT;

    public Rang() {
    }

    public Rang(int NRa, String NomCT, int PrixCT) {
        this.NRa = NRa;
        this.NomCT = NomCT;
        this.PrixCT = PrixCT;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rang)) {
            return false;
        } else {
            Rang rang = (Rang) obj;
            return rang.getNRa() == this.getNRa();
        }
    }

    @Override
    public int hashCode() {
        return this.getNRa(); //To change body of generated methods, choose Tools | Templates.
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

    /**
     * @return the PrixCT
     */
    public int getPrixCT() {
        return PrixCT;
    }

    /**
     * @param PrixCT the PrixCT to set
     */
    public void setPrixCT(int PrixCT) {
        this.PrixCT = PrixCT;
    }

    /**
     * @return the NomCT
     */
    public String getNomCT() {
        return NomCT;
    }

    /**
     * @param NomCT the NomCT to set
     */
    public void setNomCT(String NomCT) {
        this.NomCT = NomCT;
    }

}
