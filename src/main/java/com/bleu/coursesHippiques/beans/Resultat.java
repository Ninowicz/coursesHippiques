package com.bleu.coursesHippiques.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resultat {

    // Attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultat;

    // A VOIR : Mettre un attribut Course directement ? a la place de la liste ?
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Integer> classementListeIdCheval;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pari pari;


    private boolean pariGagne;
    private double gainJoueur;

    // Resultat a peut etre besoin d'une course dans ses attributs ?
    // private Course course;

    // Constructeurs

    public Resultat() {}

    public Resultat(List<Integer> classementDeCourseId, Pari pariJoueur){
        this.classementListeIdCheval = classementDeCourseId;
        this.pari = pariJoueur;
        this.gainJoueur = 0;
        this.pariGagne = false;
    }



    // Methodes

    public void traitementGain(){
        switch (this.pari.getTypePari()){
            case SIMPLE:
                traitementPariSimple();
                break;
            default:
                System.out.println("ERREUR TYPE DE PARI dans classe Resultat méthodes traitementGain() ");
        }
    }

    private void traitementPariSimple(){
        if(this.pari.getChevalChoisi().getFirst().getIdCheval() == this.classementListeIdCheval.getFirst()){

            //gain = mise * cote cheval choisi
            this.gainJoueur = this.pari.getMise()*this.getPari().getChevalChoisi().getFirst().getCote();
        }
    }

    // Getter Setter


    public int getIdResultat() {
        return idResultat;
    }

    public List<Integer> getClassementListeIdCheval() {
        return classementListeIdCheval;
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

    public void setClassementListeIdCheval(List<Integer> classementListeIdCheval) {
        this.classementListeIdCheval = classementListeIdCheval;
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
