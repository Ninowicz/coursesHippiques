package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.repositories.JoueurRepository;
import org.springframework.stereotype.Service;

@Service
public class JoueurServices {

    private final JoueurRepository joueurRepository;

    public JoueurServices(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public int tentativeConnectionJoueur(String username, String password) {
        Joueur joueur = joueurRepository.findByUsername(username);

        if (joueur != null && joueur.getPassword().equals(password)) {
            System.out.println("Connexion réussie !");
            return joueur.getIdJoueur();
        }
        else if (joueur == null) {
            Joueur nouveauJoueur = new Joueur(username, password);
            joueurRepository.save(nouveauJoueur);
            return nouveauJoueur.getIdJoueur();
        }
        else {
            System.out.println("Mauvais mot de passe");
            return -1;
        }
    }

}
