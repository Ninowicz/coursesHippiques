package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.repositories.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoueurServices {

    private final JoueurRepository joueurRepository;

    public JoueurServices(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public Joueur recuperationInfoJoueur(int id) {
        return joueurRepository.findById(id).orElse(null);
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

    public int modifPassword(String username, String password, String newPassword) {
        Joueur joueur = joueurRepository.findByUsername(username);

        if (joueur != null && joueur.getPassword().equals(password)) {
            System.out.println("connexion correcte !");
            joueur.setPassword(newPassword);
            joueurRepository.save(joueur);
            System.out.println("Mot de passe mis à jour !");
            return 1;
        }
        else if (joueur == null) {
            System.out.println("Mauvais pseudo");
            return -2;
        }
        else {
            System.out.println("Mauvais mot de passe");
            return -1;
        }
    }

    public int modifPseudo(String username, String password, String newUsername) {
        Joueur joueur = joueurRepository.findByUsername(username);

        if (joueur != null && joueur.getPassword().equals(password)) {
            System.out.println("connexion correcte !");
            joueur.setUsername(newUsername);
            joueurRepository.save(joueur);
            System.out.println("Pseudo mis à jour !");
            return 1;
        }
        else if (joueur == null) {
            System.out.println("Mauvais pseudo");
            return -2;
        }
        else {
            System.out.println("Mauvais mot de passe");
            return -1;
        }
    }

    public List<Joueur> recuperationTop3Joueurs () {
        List<Joueur> joueur = joueurRepository.findAll();
        return joueur.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getNbPartiesGagnees(), j1.getNbPartiesGagnees()))
                .limit(3)
                .toList();
    }

    public List<Joueur> recuperationTop20JoueursParties () {
        List<Joueur> joueur = joueurRepository.findAll();
        return joueur.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getNbPartiesGagnees(), j1.getNbPartiesGagnees()))
                .limit(20)
                .toList();
    }

    public List<Joueur> recuperationTop20JoueursGains () {
        List<Joueur> joueur = joueurRepository.findAll();
        return joueur.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getGainsGeneres(), j1.getGainsGeneres()))
                .limit(20)
                .toList();
    }
}
