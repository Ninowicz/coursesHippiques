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
            case SIMPLE_PLACE -> traitementPariSimplePlace(resultat, pari, classement);
            case COUPLE_GAGNANT -> traitementPariCoupleGagnant(resultat, pari, classement);
            case COUPLE_PLACE -> traitementPariCouplePlace(resultat, pari, classement);
            case COUPLE_ORDRE -> traitementPariCoupleOrdre(resultat, pari, classement);
            case TRIO_GAGNANT -> traitementPariTrioGagnant(resultat, pari, classement);
            default -> throw new IllegalArgumentException("Type de pari non pris en charge.");
        }

        return resultat;
    }

    private void traitementPariSimple(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 1; // Coefficient de gain pour un pari simple placé

        // Verification du classement
        if (classement == null || classement.isEmpty()) {
            throw new IllegalArgumentException("Le classement de la course est vide.");
        }

        Cheval gagnant = classement.getFirst();
        Cheval choisi = pari.getChevalChoisi().getFirst();



        if (choisi.getIdCheval() == gagnant.getIdCheval()) {
            double gain = pari.getMise() * choisi.getCote() * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

    private void traitementPariSimplePlace(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 0.5; // Coefficient de gain pour un pari simple placé

        // Verification de la validité du classement
        if (classement == null || classement.isEmpty()) {
            throw new IllegalArgumentException("Le classement de la course est vide.");
        }
        // Verification de la taille du classement
        if (classement.size() < 3) {
            throw new IllegalArgumentException("Le classement doit contenir au moins 3 chevaux pour un pari simple placé.");
        }

        // Comme le pari est simple placé, on vérifie si le cheval choisi est dans les 3 premiers
        Cheval chevalPremierePlace = classement.getFirst();
        Cheval chevalDeuxiemePlace = classement.get(1);
        Cheval chevalTroisiemePlace = classement.get(2);

        Cheval chevalChoisi = pari.getChevalChoisi().getFirst();



        if (chevalChoisi.getIdCheval() == chevalPremierePlace.getIdCheval() || chevalChoisi.getIdCheval() == chevalDeuxiemePlace.getIdCheval() || chevalChoisi.getIdCheval() == chevalTroisiemePlace.getIdCheval()) {
            double gain = pari.getMise() * chevalChoisi.getCote() * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

    private void traitementPariCoupleGagnant(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 2.5; // Coefficient de gain pour un pari couple gagnant

        // Verification de la validité du classement
        if (classement == null || classement.size() < 2) {
            throw new IllegalArgumentException("Le classement de la course doit contenir au moins 2 chevaux pour un pari couple gagnant.");
        }

        Cheval chevalPremierePlace = classement.getFirst();
        Cheval chevalDeuxiemePlace = classement.get(1);

        List<Cheval> chevauxChoisis = pari.getChevalChoisi();



        if (chevauxChoisis.size() == 2 &&
                ((chevauxChoisis.get(0).getIdCheval() == chevalPremierePlace.getIdCheval() && chevauxChoisis.get(1).getIdCheval() == chevalDeuxiemePlace.getIdCheval()) ||
                        (chevauxChoisis.get(0).getIdCheval() == chevalDeuxiemePlace.getIdCheval() && chevauxChoisis.get(1).getIdCheval() == chevalPremierePlace.getIdCheval()))) {
            double gain = pari.getMise() * (chevauxChoisis.get(0).getCote() + chevauxChoisis.get(1).getCote()) * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

    private void traitementPariCouplePlace(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 1.8; // Coefficient de gain pour un pari couple placé


        // Verification de la validité du classement
        if (classement == null || classement.size() < 3) {
            throw new IllegalArgumentException("Le classement de la course doit contenir au moins 3 chevaux pour un pari couple placé.");
        }

        Cheval chevalPremierePlace = classement.getFirst();
        Cheval chevalDeuxiemePlace = classement.get(1);
        Cheval chevalTroisiemePlace = classement.get(2);

        List<Cheval> chevauxChoisis = pari.getChevalChoisi();

        Cheval chevalChoisi1 = chevauxChoisis.get(0);
        Cheval chevalChoisi2 = chevauxChoisis.get(1);



        if (    (chevalChoisi1.getIdCheval() == chevalPremierePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalDeuxiemePlace.getIdCheval())  ||
                (chevalChoisi1.getIdCheval() == chevalDeuxiemePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalPremierePlace.getIdCheval())  ||
                (chevalChoisi1.getIdCheval() == chevalPremierePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalTroisiemePlace.getIdCheval()) ||
                (chevalChoisi1.getIdCheval() == chevalTroisiemePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalPremierePlace.getIdCheval()) ||
                (chevalChoisi1.getIdCheval() == chevalDeuxiemePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalTroisiemePlace.getIdCheval()) ||
                (chevalChoisi1.getIdCheval() == chevalTroisiemePlace.getIdCheval() && chevalChoisi2.getIdCheval() == chevalDeuxiemePlace.getIdCheval())) {
            double gain = pari.getMise() * (chevauxChoisis.get(0).getCote() + chevauxChoisis.get(1).getCote()) * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

    private void traitementPariCoupleOrdre(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 4; // Coefficient de gain pour un pari couple ordre

        // Verification de la validité du classement
        if (classement == null || classement.size() < 2) {
            throw new IllegalArgumentException("Le classement de la course doit contenir au moins 2 chevaux pour un pari couple ordre.");
        }

        Cheval chevalPremierePlace = classement.getFirst();
        Cheval chevalDeuxiemePlace = classement.get(1);

        List<Cheval> chevauxChoisis = pari.getChevalChoisi();



        if (chevauxChoisis.size() == 2 &&
                chevauxChoisis.get(0).getIdCheval() == chevalPremierePlace.getIdCheval() &&
                chevauxChoisis.get(1).getIdCheval() == chevalDeuxiemePlace.getIdCheval()) {
            double gain = pari.getMise() * (chevauxChoisis.get(0).getCote() + chevauxChoisis.get(1).getCote()) * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

    private void traitementPariTrioGagnant(Resultat resultat, Pari pari, List<Cheval> classement) {
        // Le pari etant plus simple, un coefficient de gain est appliqué pour réduire le gain par rapport à un pari simple gagnant
        double coefficientGain = 5; // Coefficient de gain pour un pari trio gagnant

        // Verification de la validité du classement
        if (classement == null || classement.size() < 3) {
            throw new IllegalArgumentException("Le classement de la course doit contenir au moins 3 chevaux pour un pari trio gagnant.");
        }

        Cheval chevalPremierePlace = classement.getFirst();
        Cheval chevalDeuxiemePlace = classement.get(1);
        Cheval chevalTroisiemePlace = classement.get(2);

        List<Cheval> chevauxChoisis = pari.getChevalChoisi();



        if (chevauxChoisis.size() == 3 &&
                ((chevauxChoisis.get(0).getIdCheval() == chevalPremierePlace.getIdCheval() && chevauxChoisis.get(1).getIdCheval() == chevalDeuxiemePlace.getIdCheval() && chevauxChoisis.get(2).getIdCheval() == chevalTroisiemePlace.getIdCheval()) ||
                        (chevauxChoisis.get(0).getIdCheval() == chevalDeuxiemePlace.getIdCheval() && chevauxChoisis.get(1).getIdCheval() == chevalTroisiemePlace.getIdCheval() && chevauxChoisis.get(2).getIdCheval() == chevalPremierePlace.getIdCheval()) ||
                        (chevauxChoisis.get(0).getIdCheval() == chevalTroisiemePlace.getIdCheval() && chevauxChoisis.get(1).getIdCheval() == chevalPremierePlace.getIdCheval() && chevauxChoisis.get(2).getIdCheval() == chevalDeuxiemePlace.getIdCheval()))) {
            double gain = pari.getMise() * (chevauxChoisis.get(0).getCote() + chevauxChoisis.get(1).getCote() + chevauxChoisis.get(2).getCote()) * coefficientGain;
            resultat.setPariGagne(true);
            resultat.setGainJoueur(gain);
        } else {
            resultat.setPariGagne(false);
            resultat.setGainJoueur(0);
        }
    }

}