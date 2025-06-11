package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.repositories.JoueurRepository;
import com.bleu.coursesHippiques.services.JoueurServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/courses/hippiques/joueur")
public class JoueurController {

    private final JoueurRepository joueurRepository;
    private final JoueurServices joueurServices;

    public JoueurController(JoueurRepository joueurRepository, JoueurServices joueurServices) {
        this.joueurRepository = joueurRepository;
        this.joueurServices = joueurServices;
    }

    @PostMapping("affichageprofil")
    public ResponseEntity<Joueur> affichageProfil(@RequestBody Map<String, Integer> idDuJoueur) {
        int id = idDuJoueur.get("id");
        Joueur joueur = joueurServices.recuperationInfoJoueur(id);

        if (joueur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(joueur);
    }

    @PostMapping("tentativeconnectionjoueur")
    public ResponseEntity<String> tentativeConnectionJoueur(@RequestBody Joueur joueur) {

        String username = joueur.getUsername();
        String password = joueur.getPassword();
        int id = joueurServices.tentativeConnectionJoueur(username, password);

        if (id == -1) {
            return ResponseEntity.status(401).body("Mot de passe incorrect");
        }

        return ResponseEntity.ok(String.valueOf(id));
    }
}
