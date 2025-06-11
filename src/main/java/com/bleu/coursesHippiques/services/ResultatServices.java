package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.beans.Pari;
import com.bleu.coursesHippiques.beans.Resultat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatServices {

    public void traitementArgentJoueur(Resultat monResultat, Joueur monjoueur){

        double banqueJoueur = monjoueur.getArgent();
        double gainJoueur = monResultat.getGainJoueur();
        double miseJoueur = monjoueur.getPari().getMise();

        // Le gain peut etre 0
        // La mise est soustraite au moment du traitement et plus au moment du pari
        monjoueur.setArgent(banqueJoueur - miseJoueur + gainJoueur);

    }

    public Resultat genererResultat(List<Cheval> classement, Pari pari) {
        Resultat resultat = new Resultat(classement, pari);

        switch (pari.getTypePari()) {
            case SIMPLE -> traitementPariSimple(resultat, pari, classement);
            default -> throw new IllegalArgumentException("Type de pari non pris en charge.");
        }

        return resultat;
    }

    private void traitementPariSimple(Resultat resultat, Pari pari, List<Cheval> classement) {
        if (classement == null || classement.isEmpty()) {
            throw new IllegalArgumentException("Le classement de la course est vide.");
        }

        Cheval gagnant = classement.getFirst();
        Cheval choisi = pari.getChevalChoisi().getFirst();

        if (choisi.getIdCheval() == gagnant.getIdCheval()) {
            double gain = pari.getMise() * choisi.getCote();
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }
}