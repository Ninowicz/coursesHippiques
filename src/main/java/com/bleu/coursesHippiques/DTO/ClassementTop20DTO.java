package com.bleu.coursesHippiques.DTO;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Joueur;

import java.util.List;

public class ClassementTop20DTO {

    private List<Joueur> joueursParGains;
    private List<Joueur> joueursParParties;
    private List<Cheval> chevauxParCourses;


    public ClassementTop20DTO(List<Joueur> joueursParGains, List<Joueur> joueursParParties, List<Cheval> chevauxParCourses) {
        this.joueursParGains = joueursParGains;
        this.joueursParParties = joueursParParties;
        this.chevauxParCourses = chevauxParCourses;
    }

    public List<Joueur> getJoueursParGains() {
        return joueursParGains;
    }

    public void setJoueursParGains(List<Joueur> joueursParGains) {
        this.joueursParGains = joueursParGains;
    }

    public List<Joueur> getJoueursParParties() {
        return joueursParParties;
    }

    public void setJoueursParParties(List<Joueur> joueursParParties) {
        this.joueursParParties = joueursParParties;
    }

    public List<Cheval> getChevauxParCourses() {
        return chevauxParCourses;
    }

    public void setChevauxParCourses(List<Cheval> chevauxParCourses) {
        this.chevauxParCourses = chevauxParCourses;
    }
}
