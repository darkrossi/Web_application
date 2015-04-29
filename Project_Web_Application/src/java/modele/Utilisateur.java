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
public class Utilisateur {

    private String login;
    private String mdp;
    private String nom;
    private String prenom;

    /**
     *
     */
    public Utilisateur() {
    }

    /**
     *
     * @param login
     * @param mdp
     * @param nom
     * @param prenom
     */
    public Utilisateur(String login, String mdp, String nom, String prenom) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Utilisateur user;
        if(obj instanceof Utilisateur){
            user = (Utilisateur)obj;
        }
        else{
            return false;
        }
        return this.login == user.getLogin(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
