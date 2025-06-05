package com.bleu.coursesHippiques.beans;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Course {
    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String typeDeCourse;
    private int nbTours;
    private int nbParticipants;
    private List<Cheval> listeCheval;
    private Terrain terrain;

    // Getters & Setters
    public String getTypeDeCourse() {
        return typeDeCourse;
    }
    public void setTypeDeCourse(String typeDeCourse) {
        this.typeDeCourse = typeDeCourse;
    }
    public int getNbTours() {
        return nbTours;
    }
    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
    public int getNbParticipants() {
        return nbParticipants;
    }
    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }
    public List<Cheval> getListeCheval() {
        return listeCheval;
    }
    public void setListeCheval(List<Cheval> listeCheval) {
        this.listeCheval = listeCheval;
    }
    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    // Constructeurs
    public Course() {
    }
    public Course(String typeDeCourse, int nbTours, int nbParticipants, List<Cheval> listeCheval, Terrain terrain) {
        this.typeDeCourse = typeDeCourse;
        this.nbTours = nbTours;
        this.nbParticipants = nbParticipants;
        this.listeCheval = listeCheval;
        this.terrain = terrain;
    }

    // Methodes
    public void calculerCote(){
    }
    public void calculerMalus(){
    }
    public int calculerTempsRealise(){
        return 0;
    }
    public int gainRealise(int mise){
        return 0;
    }
    public void calculerBlessure(){
    }

}
