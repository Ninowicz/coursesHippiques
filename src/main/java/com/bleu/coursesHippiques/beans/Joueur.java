package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Joueur {

    // Attributs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJoueur;
    private String username;
    private String password;

    private int argent;


    // Constructeurs

    public Joueur(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // Methodes

    // Getter Setter


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getArgent() {
        return argent;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }
}
