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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pari pari;

    private boolean pariGagne;
    private double gainJoueur;


    // Constructeurs

    public Resultat() {}

    public Resultat(List<Cheval> classementDeCourseId, Pari pariJoueur){
        this.classementListeCheval = classementDeCourseId;
        this.pari = pariJoueur;
        this.gainJoueur = 0;
        this.pariGagne = false;
    }


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

    public Pari getPari() {
        return pari;
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

    public void setPari(Pari pari) {
        this.pari = pari;
    }
}
