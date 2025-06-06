package com.bleu.coursesHippiques.controllers;


import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/hippiques")
public class ChevalController {

    private final ChevalRepository chevalRepository;

    public ChevalController(ChevalRepository chevalRepository) {
        this.chevalRepository = chevalRepository;

    }

    @PostMapping("ajouterCheval")
    public ResponseEntity<Cheval> ajouterCheval() {
        Cheval cheval = new Cheval();
        chevalRepository.save(cheval);
        System.out.println(cheval.getNom() + " a ete ajoute. ID : " + cheval.getIdCheval());
        return ResponseEntity.ok(cheval);
    }

    @GetMapping("recupererCheval")
    public ResponseEntity<List<Cheval>> recupererCheval() {
        List<Cheval> chevaux = chevalRepository.findAll();
        return ResponseEntity.ok(chevaux);
    }


    @PostMapping("test")
    public ResponseEntity<List<Cheval>> test() {
        ajouterCheval();
        List<Cheval> chevaux = chevalRepository.findAll();
        return ResponseEntity.ok(chevaux);
    }

    @GetMapping("testtime")
    public List<Integer> testtime() {

        Terrain terrain = new Terrain();
        terrain.setLongueur(2400);
        Course test = new Course();
        test.setListeCheval(chevalRepository.findAll());
        test.setTerrain(terrain);
        test.calculerMalus();
        List<Integer> temps = test.calculerTempsRealise();
        System.out.println(temps);

        return temps;
    }

    @PostMapping("test")
    public ResponseEntity<List<Cheval>> initBaseDeDonnee() {

        for (int i = 1; i<15; i++) {
            ajouterCheval();
        }
        for (int j = 1; j < 5; j++) {


        }

        List<Cheval> chevaux = chevalRepository.findAll();
        return ResponseEntity.ok(chevaux);
    }

}
