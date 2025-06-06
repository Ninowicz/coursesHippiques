package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Random;


@Entity
public class Terrain {

    public enum typeTerrain{
        HERBE,
        SABLE,
        FIBRE
    }
    public enum meteo {
        NORMALE,                    // aucun debuff
        GRAND_SOLEIL,                // debuff yeux bleu
        PLUIE,                      // debuff acceleration pour tous les chevaux
        ORAGE                       // facteur aleatoire de ne pas finir la course
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
    private meteo meteoEvenement;
    private int tauxDeBlessures; // Valeur entre 0 et 100


    // Constructeurs


    public Terrain() {}

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
                this.setMeteoEvenement(meteo.valueOf("NORMALE"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 1:
                this.setMeteoEvenement(meteo.valueOf("GRAND_SOLEIL"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 2:
                this.setMeteoEvenement(meteo.valueOf("PLUIE"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            case 3:
                this.setMeteoEvenement(meteo.valueOf("ORAGE"));
                this.setTauxDeBlessuresSelonMeteo();
                break;
            default:
                System.out.println("Erreur parametrage de la meteo de " + this.nomTerrain);
        }
    }

    private void setTauxDeBlessuresSelonMeteo(){

        if(this.meteoEvenement == meteo.NORMALE){
            if(this.typeDeTerrain == typeTerrain.HERBE){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.SABLE){
                this.setTauxDeBlessures(15);
            }
            else if(this.typeDeTerrain == typeTerrain.FIBRE){
                this.setTauxDeBlessures(5);
            }
            else{
                this.setTauxDeBlessures(15);
            }
        }

        if(this.meteoEvenement == meteo.GRAND_SOLEIL){
            if(this.typeDeTerrain == typeTerrain.HERBE){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.SABLE){
                this.setTauxDeBlessures(10);
            }
            else if(this.typeDeTerrain == typeTerrain.FIBRE){
                this.setTauxDeBlessures(5);
            }
            else{
                this.setTauxDeBlessures(10);
            }
        }

        if(this.meteoEvenement == meteo.PLUIE){
            if(this.typeDeTerrain == typeTerrain.HERBE){
                this.setTauxDeBlessures(25);
            }
            else if(this.typeDeTerrain == typeTerrain.SABLE){
                this.setTauxDeBlessures(30);
            }
            else if(this.typeDeTerrain == typeTerrain.FIBRE){
                this.setTauxDeBlessures(10);
            }
            else{
                this.setTauxDeBlessures(15);
            }
        }

        if(this.meteoEvenement == meteo.ORAGE){
            if(this.typeDeTerrain == typeTerrain.HERBE){
                this.setTauxDeBlessures(40);
            }
            else if(this.typeDeTerrain == typeTerrain.SABLE){
                this.setTauxDeBlessures(45);
            }
            else if(this.typeDeTerrain == typeTerrain.FIBRE){
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

    public meteo getMeteoEvenement() {
        return meteoEvenement;
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

    public void setMeteoEvenement(meteo meteoEvenement) {
        this.meteoEvenement = meteoEvenement;
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
