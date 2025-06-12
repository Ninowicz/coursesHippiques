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
    private final OmegaRepository omegaRepository;
    // Services
    private final ChevalServices chevalServices;
    private final TerrainServices terrainsServices;
    private final ResultatServices resultatServices;
    private final PariServices pariServices;
    private final CourseServices courseServices;
    private final JoueurServices joueurServices;

    public MainController(CourseRepository courseRepository, ChevalRepository chevalRepository, TerrainRepository terrainRepository, JoueurRepository joueurRepository, OmegaRepository omegaRepository, ChevalServices chevalServices, TerrainServices terrainsServices, PariRepository pariRepository, ResultatRepository resultatRepository, ResultatServices resultatServices, PariServices pariServices, CourseServices courseServices, JoueurServices joueurServices) {

        // Repository
        this.courseRepository = courseRepository;
        this.chevalRepository = chevalRepository;
        this.terrainRepository = terrainRepository;
        this.joueurRepository = joueurRepository;
        this.omegaRepository = omegaRepository;
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

    @PostMapping("simulationPariEtResultat")
    public ResponseEntity<String> simulationPariEtResultat() {

        // 1. Récupérer un cheval existant
        Joueur monjoueur = new Joueur("admin", "password");
        monjoueur.setArgent(1000);
        Cheval cheval = chevalRepository.findById(1).orElse(null);
        if (cheval == null) {
            return ResponseEntity.badRequest().body("Cheval ID 1 manquant");
        }
        Cheval cheval2 = chevalRepository.findById(2).orElse(null);
        if (cheval2 == null) {
            return ResponseEntity.badRequest().body("Cheval ID 2 manquant");
        }
        cheval.setCote(2);

        // 2. Créer un pari SIMPLE avec ce cheval
        Pari pari = Pari.creerPariSimple(50, cheval2);
        monjoueur.setPari(pari);
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


    @GetMapping("initjoueur/")
    public ResponseEntity<String> setJoueur() {
        Joueur monJoueur = new Joueur("admin", "password");
        monJoueur.setArgent(10000);
        joueurRepository.save(monJoueur);
        String response = String.format("id Joueur créé : %d", monJoueur.getIdJoueur());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("init-cote-chevaux-pour-test/")
    public ResponseEntity<String> setChevaux() {
        Cheval cheval = chevalRepository.findById(1).orElse(null);
        if (cheval == null) {
            return ResponseEntity.badRequest().body("Cheval ID 1 manquant");
        }
        Cheval cheval2 = chevalRepository.findById(2).orElse(null);
        if (cheval2 == null) {
            return ResponseEntity.badRequest().body("Cheval ID 2 manquant");
        }
        cheval.setCote(2);
        cheval2.setCote(4);

        chevalRepository.save(cheval);
        chevalRepository.save(cheval2);

        String response = String.format("cote modifiée");
        return ResponseEntity.ok(response);
    }


    @PostMapping("setOmega/")
    public ResponseEntity<String> setOmega() {
        // Créez un nouvel Omega
        Omega monOmega = new Omega();
        // Vous pouvez définir d'autres champs ici si nécessaire
        // Par exemple, si vous avez besoin de définir des valeurs par défaut ou spécifiques
        // monOmega.setIdJoueur(1); // Exemple si vous avez un ID de joueur spécifique
        // monOmega.setIdPari(1); // Exemple si vous avez un ID de pari spécifique
        // monOmega.setIdCourse(1); // Exemple si vous avez un ID de course spécifique
        // monOmega.setIdResultat(1); // Exemple si vous avez un ID de résultat spécifique

        omegaRepository.save(monOmega);

        return ResponseEntity.ok("Omega créé avec succès avec l'ID: " + monOmega.getIdOmega());
    }

    @PostMapping("initjoueuromega/")
    public ResponseEntity<String> setJoueurOmega(@RequestBody Joueur monJoueurBody) {
        Omega monOmega = omegaRepository.findById(1).orElse(null);
        if (monOmega == null) {
            return ResponseEntity.ok("Omega 1 n'existe pas !");
        }

        Joueur monJoueur = new Joueur(monJoueurBody.getUsername(), monJoueurBody.getPassword());
        monJoueur.setArgent(1000);
        joueurRepository.save(monJoueur);

        monOmega.setJoueur(monJoueur);
        omegaRepository.save(monOmega);

        return ResponseEntity.ok("Joueur lié à Omega avec ID: " + monJoueur.getIdJoueur() + "\nIdJoueurOmega : " + monOmega.getJoueur().getIdJoueur());
    }

    @PostMapping("setJoueurIdOmega/")
    public ResponseEntity<String> setJoueurAvecIdOmega(@RequestBody int IdJoueur) {
        Omega monOmega = omegaRepository.findById(1).orElse(null);
        if (monOmega == null) {
            return ResponseEntity.ok("Omega 1 n'existe pas !");
        }

        Joueur monJoueur = joueurRepository.findById(IdJoueur).orElse(null);
        if (monJoueur == null) {
            return ResponseEntity.ok("Joueur n'existe pas !");
        }

        monOmega.setJoueur(monJoueur);
        omegaRepository.save(monOmega);

        return ResponseEntity.ok("Joueur lié à Omega avec ID: " + monJoueur.getIdJoueur() + "\nIdJoueurOmega : " + monOmega.getJoueur().getIdJoueur());
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
        if (chevaux.size() == 1) {
            pari = Pari.creerPariSimple(pariEtIdJoueurDTO.getMise(), chevaux.get(0));
        } else {
            // A RETIRER C EST JUSTE POUR QUE CA FONCTIONNE !!
            pari = Pari.creerPariSimple(pariEtIdJoueurDTO.getMise(), chevaux.get(0));
            //pari = new Pari(TypeDePari.MULTI, pariDTO.getMise(), chevaux); // À adapter si tu as une méthode dédiée
        }

        pariRepository.save(pari);

        joueur.setPari(pari);
        joueurRepository.save(joueur);

        return ResponseEntity.ok("Pari créé et assigné avec succès à Omega et au Joueur" + pari.getIdPari());
    }

    @GetMapping("omega/{id}/")
    public ResponseEntity<Omega> getOmega(@PathVariable int id) {
        Optional<Omega> optionalOmega = omegaRepository.findById(id);
        if (optionalOmega.isPresent()) {
            Omega omega = optionalOmega.get();
            return ResponseEntity.ok(omega);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("initCourseOmega/")
    public ResponseEntity<?> initCourseOmega(@RequestBody CourseDTO courseDTO) {
        Omega omega = omegaRepository.findById(1).orElse(null); // temporaire
        Course course = courseRepository.findById(courseDTO.getIdCourse()).orElse(null);
        if (course == null || omega == null) {
            return ResponseEntity.badRequest().body("Course ou Omega non trouvé");
        }

        omega.setCourse(course);
        omegaRepository.save(omega);
        return ResponseEntity.ok("Course associée à Omega");
    }


    @PostMapping("lancerCourseOmega/")
    public ResponseEntity<Map<String, Object>> lancerOmegaSimulation() {
        try {
            // 1. Charger Omega (id = 1)
            Omega omega = omegaRepository.findById(1).orElse(null);
            if (omega == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Omega introuvable"));

            Course course = omega.getCourse();
            if (course == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Course absente dans Omega"));

            int idCourse = course.getID();

            // 2. Simulation de la course via CourseServices
            courseServices.calculerMalus(idCourse);
            courseServices.calculerCote(idCourse);
            courseServices.calculerTempsRealise(idCourse);
            courseServices.calculerBlessure(idCourse);

            // 3. Récupération du classement
            List<Cheval> classement = courseServices.podium(idCourse);

            // 4. Génération du résultat avec ResultatServices
            Pari pari = omega.getPari();
            Joueur joueur = omega.getJoueur();

            Resultat resultat = resultatServices.genererResultat(classement, pari);
            resultatServices.traitementArgentJoueur(resultat, joueur);

            // 5. Construction de la réponse
            Map<String, Object> reponse = new LinkedHashMap<>();
            reponse.put("joueur", joueur.getUsername());
            reponse.put("argentFinal", joueur.getArgent());
            reponse.put("nomCourse", course.getNomCourse());
            reponse.put("terrain", course.getTerrain().getTypeDeTerrain().toString());

            reponse.put("chevalParie", pari.getChevalChoisi().getFirst().getNom());
            reponse.put("mise", pari.getMise());
            reponse.put("pariGagne", resultat.isPariGagne());
            reponse.put("gain", resultat.getGainJoueur());

            List<Map<String, Object>> classementInfos = new ArrayList<>();
            for (int i = 0; i < classement.size(); i++) {
                Cheval c = classement.get(i);
                classementInfos.add(Map.of(
                        "position", i + 1,
                        "nom", c.getNom(),
                        "temps", c.getDernierTemps(),
                        "cote", c.getCote(),
                        "etat", c.getEtatDuCheval().toString()
                ));
            }
            reponse.put("classement", classementInfos);

            return ResponseEntity.ok(reponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur dans la simulation Omega : " + e.getMessage()));
        }
    }


    @PostMapping("setParisJoueurRd")
    public ResponseEntity<Joueur> setParisJoueurRd(@RequestBody int idDuJoueur) {
        Joueur joueur = joueurServices.recuperationInfoJoueur(idDuJoueur);
        Pari pari = Pari.creerPariSimple(50, chevalRepository.findById(1).orElse(null));
        pariRepository.save(pari);
        joueur.setPari(pari);
        joueurRepository.save(joueur);

        return ResponseEntity.ok(joueur);
    }









}
