package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cheval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //
    // Attributs
    private int idCheval;
    private String nom;
    private int age;
    private String taille;
    private int poids;
    private int vitesseMax;
    private int acceleration;
    private int nbCourseGagnees;
    private String couleurDeLaRobe;
    private int pedigree;
    private String couleurDesYeux;
    private int tempsRealise;
    private String etatDuCheval;
    private int malus;
    private int cote;


    // Constructeurs

    public Cheval(String nom, int age, String taille, int poids, int vitesseMax, int acceleration, int nbCourseGagnees, String couleurDeLaRobe, int pedigree, String couleurDesYeux) {
        this.nom = nom;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.vitesseMax = vitesseMax;
        this.acceleration = acceleration;
        this.nbCourseGagnees = nbCourseGagnees;
        this.couleurDeLaRobe = couleurDeLaRobe;
        this.pedigree = pedigree;
        this.couleurDesYeux = couleurDesYeux;
    }

    public Cheval() {
    }

    // Getter Setters

    public int getIdCheval() {
        return idCheval;
    }

    public void setIdCheval(int idCheval) {
        this.idCheval = idCheval;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getNbCourseGagnees() {
        return nbCourseGagnees;
    }

    public void setNbCourseGagnees(int nbCourseGagnees) {
        this.nbCourseGagnees = nbCourseGagnees;
    }

    public String getCouleurDeLaRobe() {
        return couleurDeLaRobe;
    }

    public void setCouleurDeLaRobe(String couleurDeLaRobe) {
        this.couleurDeLaRobe = couleurDeLaRobe;
    }

    public int getPedigree() {
        return pedigree;
    }

    public void setPedigree(int pedigree) {
        this.pedigree = pedigree;
    }

    public String getCouleurDesYeux() {
        return couleurDesYeux;
    }

    public void setCouleurDesYeux(String couleurDesYeux) {
        this.couleurDesYeux = couleurDesYeux;
    }

    public int getTempsRealise() {
        return tempsRealise;
    }

    public void setTempsRealise(int tempsRealise) {
        this.tempsRealise = tempsRealise;
    }

    public String getEtatDuCheval() {
        return etatDuCheval;
    }

    public void setEtatDuCheval(String etatDuCheval) {
        this.etatDuCheval = etatDuCheval;
    }

    public int getMalus() {
        return malus;
    }

    public void setMalus(int malus) {
        this.malus = malus;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }
}
