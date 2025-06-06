package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses/hippiques/main")
public class MainController {

    private final CourseRepository courseRepository;
    private final ChevalRepository chevalRepository;
    private final TerrainRepository terrainRepository;
    private final ChevalServices chevalServices;
    private final TerrainServices terrainsServices;

    public MainController(CourseRepository courseRepository, ChevalRepository chevalRepository, TerrainRepository terrainRepository, ChevalServices chevalServices, TerrainServices terrainsServices) {
        this.courseRepository = courseRepository;
        this.chevalRepository = chevalRepository;
        this.terrainRepository = terrainRepository;
        this.chevalServices = chevalServices;
        this.terrainsServices = terrainsServices;
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
}
