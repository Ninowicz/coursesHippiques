package com.bleu.coursesHippiques.DTO;

import com.bleu.coursesHippiques.beans.Pari;

import java.util.List;

public class PariEtIdJoueurDTO {
    private int idJoueur;
    private double mise;
    private String typePari; // correspond à l'énumération TypeDePari
    private List<Integer> idChevaux;

    public String getTypePari() {
        return typePari;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public double getMise() {
        return mise;
    }

    public List<Integer> getIdChevaux() {
        return idChevaux;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setMise(double mise) {
        this.mise = mise;
    }

    public void setIdChevaux(List<Integer> idChevaux) {
        this.idChevaux = idChevaux;
    }
}
