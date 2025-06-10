package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.*;
import com.bleu.coursesHippiques.repositories.*;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.PariServices;
import com.bleu.coursesHippiques.services.ResultatServices;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/courses/hippiques/main")
public class MainController {

    // Repository
    private final CourseRepository courseRepository;
    private final ChevalRepository chevalRepository;
    private final TerrainRepository terrainRepository;
    private final PariRepository pariRepository;
    private final ResultatRepository resultatRepository;
    // Services
    private final ChevalServices chevalServices;
    private final TerrainServices terrainsServices;
    private final ResultatServices resultatServices;
    private final PariServices pariServices;

    public MainController(CourseRepository courseRepository, ChevalRepository chevalRepository, TerrainRepository terrainRepository, ChevalServices chevalServices, TerrainServices terrainsServices, PariRepository pariRepository, ResultatRepository resultatRepository, ResultatServices resultatServices, PariServices pariServices) {

        // Repository
        this.courseRepository = courseRepository;
        this.chevalRepository = chevalRepository;
        this.terrainRepository = terrainRepository;
        this.pariRepository = pariRepository;
        this.resultatRepository = resultatRepository;
        // Services
        this.chevalServices = chevalServices;
        this.terrainsServices = terrainsServices;
        this.resultatServices = resultatServices;
        this.pariServices = pariServices;
    }

    @PostMapping("initBaseDeDonnee")
    public ResponseEntity<List<Cheval>> initBaseDeDonnee() {

        for (int i = 0; i<15; i++) {
            chevalServices.ajouterCheval();
        }
        List<Cheval> chevaux = chevalRepository.findAll();

        terrainsServices.initTerrains();
        List<Terrain> tousLesTerrains = terrainRepository.findAll();
        tousLesTerrains.forEach(terrain -> {
            System.out.println("ID : " + terrain.getIdTerrain());
            System.out.println("Nom : " + terrain.getNomTerrain());
            System.out.println("Longueur : " + terrain.getLongueur());
            System.out.println("Type de terrain : " + terrain.getTypeDeTerrain());
            System.out.println("Météo : " + terrain.getMeteoEvenement());
            System.out.println("Taux de blessures : " + terrain.getTauxDeBlessures());
            System.out.println("-----------------------------------");
        });
        return ResponseEntity.ok(chevaux);
    }

    @PostMapping("simulationPariEtResultat")
    public ResponseEntity<String> simulationPariEtResultat() {

        // 1. Récupérer un cheval existant
        Joueur monjoueur = new Joueur("admin", "password");
        monjoueur.setArgent(1000);
        Cheval cheval = chevalRepository.findById(1).orElse(null);
        if (cheval == null) {
            return ResponseEntity.badRequest().body("Cheval ID 1 manquant");
        }

        // 2. Créer un pari SIMPLE avec ce cheval
        cheval.setCote(2);
        Pari pari = Pari.creerPariSimple(50, cheval);
        monjoueur.setPari(pari);
        pariServices.traitementMiseJoueur(monjoueur);
        pariRepository.save(pari); // optionnel ici, juste pour trace

        // 3. Simuler un classement (le cheval gagne)
        List<Cheval> classement = List.of(cheval);

        // 4. Générer le résultat via le service
        Resultat resultat = resultatServices.genererResultat(classement, pari);

        // 5. (Optionnel) Enregistrer le résultat en base
        // resultatRepository.save(resultat);

        // 6. Retourner le résultat sous forme de message

        resultatServices.traitementArgentJoueur(resultat, monjoueur);
        return ResponseEntity.ok("Pari gagné ? " + resultat.isPariGagne()
                + " - Gain : " + resultat.getGainJoueur() + " nouvelle banque : " + monjoueur.getArgent());
    }
}
