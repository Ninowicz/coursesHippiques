package com.bleu.coursesHippiques.beans;

import jakarta.persistence.*;

@Entity
public class Joueur {

    // Attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJoueur;
    private String username;
    private String password;
    private double argent;
    @OneToOne
    private Pari pari;
    private int nbPartiesGagnees;
    private int nbPartiesJouees;
    private int gainsGeneres;


    // Constructeurs

    public Joueur(String username, String password) {
        this.username = username;
        this.password = password;
        this.setArgent(1000);
    }

    public Joueur() {
    }

// Methodes

    // Getter Setter


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getArgent() {
        return argent;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void setNbPartiesGagnees(int nbPartiesGagnees) {
        this.nbPartiesGagnees = nbPartiesGagnees;
    }

    public int getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public void setNbPartiesJouees(int nbPartiesJouees) {
        this.nbPartiesJouees = nbPartiesJouees;
    }

    public int getGainsGeneres() {
        return gainsGeneres;
    }

    public void setGainsGeneres(int gainsGeneres) {
        this.gainsGeneres = gainsGeneres;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }

    public Pari getPari() {
        return pari;
    }

    public void setPari(Pari pari) {

        // Verification que la mise ne dépasse pas la banque du joueur
        if(pari.getMise() > this.getArgent()){
            pari.setMise(this.getArgent());
        }
        // Ni qu'elle soit négative
        else if(pari.getMise() < 0){
            pari.setMise(0);
        }
        // Si tout va bien, on ne change rien
        this.pari = pari;
    }
}
