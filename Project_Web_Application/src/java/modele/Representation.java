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

    public Representation() {
    }

    public Representation(int NR, String date, String heure, int NSp, int NSa, int NbP) {
        this.NR = NR;
        this.date = date;
        this.heure = heure;
        this.NSp = NSp;
        this.NSa = NSa;
        this.NbP = NbP;
    }

}
