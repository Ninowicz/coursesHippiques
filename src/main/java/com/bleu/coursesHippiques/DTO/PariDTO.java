package com.bleu.coursesHippiques.DTO;

import java.util.List;

public class PariDTO {
    private double mise;
    private List<Integer> idChevaux;

    public double getMise() {
        return mise;
    }

    public void setMise(double mise) {
        this.mise = mise;
    }

    public List<Integer> getIdChevaux() {
        return idChevaux;
    }

    public void setIdChevaux(List<Integer> idChevaux) {
        this.idChevaux = idChevaux;
    }
}
