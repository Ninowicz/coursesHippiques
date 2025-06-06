package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
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

    public MainController(CourseRepository courseRepository, ChevalRepository chevalRepository, TerrainRepository terrainRepository, ChevalServices chevalServices) {
        this.courseRepository = courseRepository;
        this.chevalRepository = chevalRepository;
        this.terrainRepository = terrainRepository;
        this.chevalServices = chevalServices;
    }

    @PostMapping("initBaseDeDonnee")
    public ResponseEntity<List<Cheval>> initBaseDeDonnee() {

        for (int i = 0; i<15; i++) {
            chevalServices.ajouterCheval();
        }
//        for (int j = 1; j < 5; j++) {
//
//        }
        List<Cheval> chevaux = chevalRepository.findAll();
        return ResponseEntity.ok(chevaux);
    }
}
