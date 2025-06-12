package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.DTO.PasswordUpdateDTO;
import com.bleu.coursesHippiques.DTO.PseudoUpdateDTO;
import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.repositories.JoueurRepository;
import com.bleu.coursesHippiques.services.JoueurServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("affichageprofil2")
    public ResponseEntity<Joueur> affichageProfil2(@RequestBody Integer idDuJoueur) {
        Joueur joueur = joueurServices.recuperationInfoJoueur(idDuJoueur);

        if (joueur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(joueur);
    }

    @PostMapping("saveJoueur")
    public ResponseEntity<Joueur> saveJoueur(@RequestBody Joueur joueur) {
        joueurRepository.save(joueur);
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

    @PostMapping("modifPassword")
    public ResponseEntity<Map<String, String>> modifPassword(@RequestBody PasswordUpdateDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String newPassword = dto.getNewPassword();
        int id = joueurServices.modifPassword(username, password, newPassword);

        Map<String, String> response = new HashMap<>();

        if (id == -1) {
            response.put("message", "Mot de passe incorrect");
            return ResponseEntity.status(401).body(response);
        }

        if (id == -2) {
            response.put("message", "Pseudo incorrect");
            return ResponseEntity.status(401).body(response);
        }

        response.put("message", "Mot de passe mis à jour !");
        return ResponseEntity.ok(response);
    }

    @PostMapping("modifPseudo")
    public ResponseEntity<Map<String, String>> modifPseudo(@RequestBody PseudoUpdateDTO dto) {
        String username = dto.getUsername();
        String newUsername = dto.getNewUsername();
        String password = dto.getPassword();
        int id = joueurServices.modifPseudo(username, password, newUsername);

        Map<String, String> response = new HashMap<>();

        if (id == -1) {
            response.put("message", "Mot de passe incorrect");
            return ResponseEntity.status(401).body(response);
        }

        if (id == -2) {
            response.put("message", "Pseudo incorrect");
            return ResponseEntity.status(401).body(response);
        }

        response.put("message", "Pseudo mis à jour !");
        return ResponseEntity.ok(response);
    }

}
