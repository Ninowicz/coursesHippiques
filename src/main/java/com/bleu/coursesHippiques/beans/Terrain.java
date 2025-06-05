package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Terrain {

    public enum typTerrain{
        herbe,
        sable,
        fibre
    }


    // Attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String meteo; // (enum ?)
    private String typeTerrain; // (enum ?)
    private int tauxDeBlessure;
    private int longueur;

    // type :
    // type : Plat - Trot - Haies - Obstacles (peut etre en prendre que 3)

    // Constructeurs


    public Terrain() {}

    public Terrain(String type, int tauxDeBlessure, int longueur) {
        this.type = type;
        this.tauxDeBlessure = tauxDeBlessure;
        this.longueur = longueur;
    }


    // Methodes





    // Getter Setter

    public String getMeteo() {
        return meteo;
    }

    public String getType() {
        return type;
    }

    public int getTauxDeBlessure() {
        return tauxDeBlessure;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setMeteo(String meteo) {
        this.meteo = meteo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTauxDeBlessure(int tauxDeBlessure) {
        this.tauxDeBlessure = tauxDeBlessure;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }
}
