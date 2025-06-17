package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.DTO.*;
import com.bleu.coursesHippiques.beans.*;
import com.bleu.coursesHippiques.repositories.*;
import com.bleu.coursesHippiques.services.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private final JoueurRepository joueurRepository;
    // Services
    private final ChevalServices chevalServices;
    private final TerrainServices terrainsServices;
    private final ResultatServices resultatServices;
    private final PariServices pariServices;
    private final CourseServices courseServices;
    private final JoueurServices joueurServices;

    public MainController(CourseRepository courseRepository, ChevalRepository chevalRepository, TerrainRepository terrainRepository, JoueurRepository joueurRepository, ChevalServices chevalServices, TerrainServices terrainsServices, PariRepository pariRepository, ResultatRepository resultatRepository, ResultatServices resultatServices, PariServices pariServices, CourseServices courseServices, JoueurServices joueurServices) {

        // Repository
        this.courseRepository = courseRepository;
        this.chevalRepository = chevalRepository;
        this.terrainRepository = terrainRepository;
        this.joueurRepository = joueurRepository;
        this.pariRepository = pariRepository;
        this.resultatRepository = resultatRepository;
        // Services
        this.chevalServices = chevalServices;
        this.terrainsServices = terrainsServices;
        this.resultatServices = resultatServices;
        this.pariServices = pariServices;
        this.courseServices = courseServices;
        this.joueurServices = joueurServices;
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

    @GetMapping("initjoueur/")
    public ResponseEntity<String> setJoueur() {
        Joueur monJoueur = new Joueur("admin", "password");
        monJoueur.setArgent(10000);
        joueurRepository.save(monJoueur);
        String response = String.format("id Joueur créé : %d", monJoueur.getIdJoueur());
        return ResponseEntity.ok(response);
    }


    @PostMapping("initPariAvecIdJoueur/")
    public ResponseEntity<String> initPariOmega(@RequestBody PariEtIdJoueurDTO pariEtIdJoueurDTO) {

        // Récupérer le joueur a partir de son ID
        Joueur joueur = joueurRepository.findById(pariEtIdJoueurDTO.getIdJoueur()).orElse(null);
        if(joueur == null){
            return ResponseEntity.badRequest().body("Joueur introuvable");
        }

        // Récupérer tous les chevaux à partir de leurs IDs
        List<Cheval> chevaux = chevalRepository.findAllById(pariEtIdJoueurDTO.getIdChevaux());
        if (chevaux.size() != pariEtIdJoueurDTO.getIdChevaux().size()) {
            return ResponseEntity.badRequest().body("Un ou plusieurs chevaux sont introuvables");
        }

        // Créer le Pari selon le nombre de chevaux
        Pari pari;
        switch (pariEtIdJoueurDTO.getTypePari()) {
            case "SIMPLE": // Simple
                pari = Pari.creerPariSimple(pariEtIdJoueurDTO.getMise(), chevaux.get(0));
                break;
            case "SIMPLE_PLACE": // Simple Place
                pari = Pari.creerPariSimplePlace(pariEtIdJoueurDTO.getMise(), chevaux.get(0));
                break;
            case "COUPLE_GAGNANT": // Couple Gagnant
                pari = Pari.creerPariCoupleGagnant(pariEtIdJoueurDTO.getMise(), chevaux);
                break;
            case "COUPLE_PLACE": // Couple Place
                pari = Pari.creerPariCouplePlace(pariEtIdJoueurDTO.getMise(), chevaux);
                break;
            case "COUPLE_ORDRE": // Couple Ordre
                pari = Pari.creerPariCoupleOrdre(pariEtIdJoueurDTO.getMise(), chevaux);
                break;
            case "TRIO_GAGNANT": // Trio Gagnant
                pari = Pari.creerPariTrioGagnant(pariEtIdJoueurDTO.getMise(), chevaux);
                break;
            default:
                return ResponseEntity.badRequest().body("Type de pari inconnu");
        }

        pariRepository.save(pari);

        joueur.setPari(pari);
        joueurRepository.save(joueur);

        return ResponseEntity.ok("Pari créé et assigné avec succès à Omega et au Joueur" + pari.getIdPari());
    }

    @GetMapping("recuperation/top/3/joueurs")
    public ResponseEntity<List<Joueur>> recuperationTop3Joueurs() {
        List<Joueur> top3Joueurs = joueurServices.recuperationTop3Joueurs();
        return ResponseEntity.ok(top3Joueurs);
    }

    @GetMapping("recuperation/top/20/joueurs/chevaux")
    public ResponseEntity<ClassementTop20DTO> recuperationTop20JoueursChevaux() {
        List<Joueur> top20JoueursGains = joueurServices.recuperationTop20JoueursGains();
        List<Joueur> top20JoueursParties = joueurServices.recuperationTop20JoueursParties();
        List<Cheval> top20ChevalCourses = chevalServices.recuperationTop20ChevalCourses();

        ClassementTop20DTO top20 = new ClassementTop20DTO(
                top20JoueursGains,
                top20JoueursParties,
                top20ChevalCourses
        );

        return ResponseEntity.ok(top20);
    }

    @PostMapping("setNbVictoire")
    public void setNbVictoire(){
        int nbCourseJouees = 60;

        for (int i = 0; i < nbCourseJouees; i++) {

            List<Terrain> listeTerrains = terrainRepository.findAll();
            Collections.shuffle(listeTerrains);
            Terrain terrainCourse = listeTerrains.getFirst();
            terrainsServices.setConditionsAleatoires(terrainCourse);
            String nomCourse = "Course n°" + (i + 1);

            int nbChevaux = (int) (Math.random() * (9 - 4) )+ 4;
            List<Cheval> listeChevaux = chevalRepository.findAll();
            Collections.shuffle(listeChevaux);
            List<Cheval> listeChevauxCourse = new ArrayList<>();
            for (int k = 0; k < nbChevaux; k++){
                listeChevauxCourse.add(listeChevaux.get(k));
            }

            Course course = new Course(nomCourse,listeChevauxCourse,terrainCourse);

            courseRepository.save(course);
            courseServices.calculerCote(course.getID());
            courseServices.calculerMalus(course.getID());
            courseServices.calculerTempsRealise(course.getID());
            courseServices.podium(course.getID());
            for (Cheval c : course.getListeCheval()) {
                chevalRepository.save(c);
            }
        }
    }

}
