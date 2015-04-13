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

    private String date;
    private int NSp;
    private int NSa;
    private int NbP;

    public Representation() {
    }

    public Representation(String date, int NSp, int NSa, int NbP) {
        this.date = date;
        this.NSp = NSp;
        this.NSa = NSa;
        this.NbP = NbP;
    }

}
