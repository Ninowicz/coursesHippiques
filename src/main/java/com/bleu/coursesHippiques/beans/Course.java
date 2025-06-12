package com.bleu.coursesHippiques.beans;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    public enum typeCourse{
        PLAT,
        TROT,
        OBSTACLE
    }

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String nomCourse;
    private typeCourse typeDeCourse;
    private int nbTours;
    private int nbParticipants;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cheval> listeCheval;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Terrain terrain;

    // Getters & Setters
    public String getNomCourse() {
        return nomCourse;
    }
    public void setNomCourse(String nomCourse) {
        this.nomCourse = nomCourse;
    }
    public typeCourse getTypeDeCourse() {
        return typeDeCourse;
    }
    public void setTypeDeCourse(typeCourse typeDeCourse) {
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
    public int getID() {
        return ID;
    }

    // Constructeurs
    public Course() {
    }
    public Course(String nomCourse, List<Cheval> listeCheval, Terrain terrain) {
        this.nomCourse = nomCourse;
        this.listeCheval = listeCheval;
        this.terrain = terrain;
        this.nbParticipants = listeCheval.size();
        this.nbTours = 1;
        this.typeDeCourse = typeCourse.PLAT;
    }
    public Course(String nomCourse, typeCourse typeDeCourse, int nbTours,List<Cheval> listeCheval, Terrain terrain) {
        this.nomCourse = nomCourse;
        this.typeDeCourse = typeDeCourse;
        this.nbTours = nbTours;
        this.nbParticipants = listeCheval.size();
        this.listeCheval = listeCheval;
        this.terrain = terrain;
    }

    // Methodes
    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", typeDeCourse='" + typeDeCourse + '\'' +
                ", nbTours=" + nbTours +
                ", nbParticipants=" + nbParticipants +
                ", listeCheval=" + listeCheval +
                ", terrain=" + terrain +
                '}';
    }
}
