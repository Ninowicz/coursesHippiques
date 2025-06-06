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


    // Constructeurs

    public Joueur(String username, String password) {
        this.username = username;
        this.password = password;
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
        this.pari = pari;
    }
}
