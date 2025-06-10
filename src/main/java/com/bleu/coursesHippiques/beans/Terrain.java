package com.bleu.coursesHippiques.beans;

import jakarta.persistence.*;


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

    @Enumerated(EnumType.STRING)
    private typeTerrain typeDeTerrain;

        // variable
    @Enumerated(EnumType.STRING)
    private meteo meteoEvenement;
    private int tauxDeBlessures; // Valeur entre 0 et 100
    //Test


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
