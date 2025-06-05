package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Random;


@Entity
public class Terrain {

    public enum typeTerrain{
        herbe,
        sable,
        fibre
    }
    public enum meteo {
        normale,                    // aucun debuff
        grandSoleil,                // debuff yeux bleu
        pluie,                      // debuff acceleration pour tous les chevaux
        orage                       // facteur aleatoire de ne pas finir la course
    }

    // Attributs

        // invaraiable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTerrain;

    private String nomTerrain;
    private int longueur;
    private typeTerrain typeDeTerrain;

        // variable
    private meteo meteoCourse;
    private int tauxDeBlessures; // Valeur entre 0 et 100


    // Constructeurs


    // public Terrain() {}
    // Pas besoin de constructeur par defaut ?


    // Un terrain a son nom, sa longueur et son type de terrain,
    // La météo et le taux de blessures seront parametrés plus tard.

    public Terrain(String nomTerrain, int longueur, typeTerrain typeDeTerrain) {
        this.nomTerrain = nomTerrain;
        this.longueur = longueur;
        this.typeDeTerrain = typeDeTerrain;
    }

    // Methodes

    public void setConditionsAleatoires(){

        // Fonction qui permet de generer aléatoirement la météo ainsi que le taux de bléssures associé.

        Random rand = new Random();
        int n = rand.nextInt(4);
        switch(n){
            case 0:
                this.setMeteoCourse(meteo.valueOf("normale"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 1:
                this.setMeteoCourse(meteo.valueOf("grandSoleil"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 2:
                this.setMeteoCourse(meteo.valueOf("pluie"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 3:
                this.setMeteoCourse(meteo.valueOf("orage"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            default:
                System.out.println("Erreur parametrage de la meteo de " + this.nomTerrain);
        }
    }

    private void setTauxDeBlessuresSelonMeteo(){

        if(this.meteoCourse == meteo.normale){
            if(this.typeDeTerrain == typeTerrain.herbe){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.sable){
                this.setTauxDeBlessures(15);
            }
            else if(this.typeDeTerrain == typeTerrain.fibre){
                this.setTauxDeBlessures(5);
            }
            else{
                this.setTauxDeBlessures(15);
            }
        }

        if(this.meteoCourse == meteo.grandSoleil){
            if(this.typeDeTerrain == typeTerrain.herbe){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.sable){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.fibre){
                this.setTauxDeBlessures(5);
            }
            else{
                this.setTauxDeBlessures(10);
            }
        }

        if(this.meteoCourse == meteo.pluie){
            if(this.typeDeTerrain == typeTerrain.herbe){
                this.setTauxDeBlessures(25);
            }
            else if(this.typeDeTerrain == typeTerrain.sable){
                this.setTauxDeBlessures(30);
            }
            else if(this.typeDeTerrain == typeTerrain.fibre){
                this.setTauxDeBlessures(10);
            }
            else{
                this.setTauxDeBlessures(15);
            }
        }

        if(this.meteoCourse == meteo.orage){
            if(this.typeDeTerrain == typeTerrain.herbe){
                this.setTauxDeBlessures(40);
            }
            else if(this.typeDeTerrain == typeTerrain.sable){
                this.setTauxDeBlessures(45);
            }
            else if(this.typeDeTerrain == typeTerrain.fibre){
                this.setTauxDeBlessures(15);
            }
            else{
                this.setTauxDeBlessures(40);
            }
        }

    }



    // Getter Setter


    public int getIdTerrain() {
        return idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public meteo getMeteoCourse() {
        return meteoCourse;
    }

    public typeTerrain getTypeDeTerrain() {
        return typeDeTerrain;
    }

    public int getTauxDeBlessures() {
        return tauxDeBlessures;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setMeteoCourse(meteo meteoCourse) {
        this.meteoCourse = meteoCourse;
    }

    public void setTypeDeTerrain(typeTerrain typeDeTerrain) {
        this.typeDeTerrain = typeDeTerrain;
    }

    public void setTauxDeBlessures(int tauxDeBlessures) {
        this.tauxDeBlessures = tauxDeBlessures;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }
}
