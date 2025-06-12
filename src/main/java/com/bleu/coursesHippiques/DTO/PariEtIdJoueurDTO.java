package com.bleu.coursesHippiques.DTO;

import java.util.List;

public class PariEtIdJoueurDTO {
    private int idJoueur;
    private double mise;
    private List<Integer> idChevaux;

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
