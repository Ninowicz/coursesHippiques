package com.bleu.coursesHippiques.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resultat {

    // Attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultat;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cheval> classementListeCheval;
    private boolean pariGagne;
    private double gainJoueur;

    // Resultat a peut etre besoin d'une course dans ses attributs ?
    // private Course course;

    // Constructeurs

    public Resultat() {}




    // Methodes

    // Getter Setter


    public int getIdResultat() {
        return idResultat;
    }

    public List<Cheval> getClassementListeCheval() {
        return classementListeCheval;
    }

    public boolean isPariGagne() {
        return pariGagne;
    }

    public double getGainJoueur() {
        return gainJoueur;
    }

    public void setIdResultat(int idResultat) {
        this.idResultat = idResultat;
    }

    public void setClassementListeCheval(List<Cheval> classementListeCheval) {
        this.classementListeCheval = classementListeCheval;
    }

    public void setPariGagne(boolean pariGagne) {
        this.pariGagne = pariGagne;
    }

    public void setGainJoueur(double gainJoueur) {
        this.gainJoueur = gainJoueur;
    }
}
