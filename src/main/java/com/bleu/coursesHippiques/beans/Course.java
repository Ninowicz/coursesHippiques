package com.bleu.coursesHippiques.beans;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Course {

    public enum typeDeCourse{
        plat,
        trot,
        obstacle
    }


    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String typeDeCourse;
    private int nbTours;
    private int nbParticipants;

    //Rajout OneToMany
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        for (Cheval cheval : listeCheval){
            double a = 0.001;
            double b = 1.2;
            double malusBlessure = 0;
            double malusCouleurYeuxTerrain = 0;
            double malusFerTerrain = 0;
            switch (cheval.getEtatDuCheval()){
                case Fatigue -> malusBlessure = 0.1;
                case Blesse -> malusBlessure = 0.2;
                case Mort -> malusBlessure = 100;
            }
            if (cheval.getTypeDeFer() == Cheval.TypeDeFer.Avec){
                if (terrain.getTypeDeTerrain() == Terrain.typeTerrain.fibre ||
                        terrain.getTypeDeTerrain() == Terrain.typeTerrain.sable ){
                    malusFerTerrain = 0.05;
                }
            }
            else {
                if (terrain.getTypeDeTerrain() == Terrain.typeTerrain.herbe){
                    malusFerTerrain = 0.05;
                }
            }
            if (cheval.getCouleurDesYeux() == Cheval.CouleurDesYeux.Bleu &&
                terrain.getMeteoCourse() == Terrain.meteo.grandSoleil){
                malusCouleurYeuxTerrain = 0.5;
            }

            //* Premiere ligne malus en fonction de l'age, max age = 10 => malus max
            //* Deuxieme ligne malus en fonction du ration Poids/Taille (fct affine ax + b)
            //* Troisieme ligne malus globaux
            double malus =  (Math.exp((double) cheval.getAge() /10)-Math.exp((double) 2 /10))/10 +
                    Math.abs(a*cheval.getPoids() + b - cheval.getTaille())/10  +
                    malusBlessure + malusCouleurYeuxTerrain  + malusFerTerrain;
            cheval.setMalus(malus);
        }
    }
    public List<Integer> calculerTempsRealise(){
        List<Integer> listeTemps = new ArrayList<>();
        for (Cheval cheval : listeCheval){
            List<Integer> listeDisanceParcourue = new ArrayList<>();
            listeDisanceParcourue.add(0);
            double rdAcceleration = Math.random();
            double accelerationReelle = cheval.getAcceleration() - rdAcceleration - cheval.getMalus();
            int i = 1;
            while ( (terrain.getLongueur() > Collections.max(listeDisanceParcourue) ) ){
                listeDisanceParcourue.add((int) ( Math.min(i*accelerationReelle,cheval.getVitesseMax())
                        + Collections.max(listeDisanceParcourue) ));
                i++;
            }
            listeTemps.add(listeDisanceParcourue.size());
        }
        return listeTemps;
    }
    public int gainRealise(int mise){
        return 0;
    }
    public void calculerBlessure(){
    }

}
