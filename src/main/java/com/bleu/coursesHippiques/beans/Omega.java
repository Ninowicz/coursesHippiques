package com.bleu.coursesHippiques.beans;

import jakarta.persistence.*;

@Entity
public class Omega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOmega;

    @OneToOne
    @JoinColumn(name = "id_joueur")
    private Joueur joueur;

    @OneToOne
    @JoinColumn(name = "id_pari")
    private Pari pari;

    @OneToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @OneToOne
    @JoinColumn(name = "id_resultat")
    private Resultat resultat;

    // Constructeurs
    public Omega() {
    }

    public Omega(Joueur joueur, Pari pari, Course course, Resultat resultat) {
        this.joueur = joueur;
        this.pari = pari;
        this.course = course;
        this.resultat = resultat;
    }

    // Getters et setters
    public int getIdOmega() {
        return idOmega;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Pari getPari() {
        return pari;
    }

    public void setPari(Pari pari) {
        this.pari = pari;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }
}
