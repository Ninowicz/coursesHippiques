package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/hippiques/cheval")
public class ChevalController {

    private final ChevalRepository chevalRepository;
    private final ChevalServices chevalServices;

    public ChevalController(ChevalRepository chevalRepository, ChevalServices chevalServices) {
        this.chevalRepository = chevalRepository;

        this.chevalServices = chevalServices;
    }


    @PostMapping("ajouterCheval")
    public ResponseEntity<Cheval> ajouterCheval() {
        Cheval cheval = chevalServices.ajouterCheval();
        return ResponseEntity.ok(cheval);
    }

    @GetMapping("recupererCheval")
    public ResponseEntity<List<Cheval>> recupererCheval() {
        List<Cheval> chevaux = chevalServices.recupererCheval();
        return ResponseEntity.ok(chevaux);
    }

//    @PostMapping("test")
//    public ResponseEntity<List<Cheval>> test() {
//        ajouterCheval();
//        List<Cheval> chevaux = chevalRepository.findAll();
//        return ResponseEntity.ok(chevaux);
//    }

    @GetMapping("testtime")
    public List<Cheval> testtime() {

        Terrain terrain = new Terrain();
        terrain.setLongueur(2400);
        Course test = new Course();
        test.setListeCheval(chevalRepository.findAll());
        test.setTerrain(terrain);
        test.calculerMalus();
        List<Cheval> temps = test.calculerTempsRealise();
        for (Cheval cheval : temps){
            chevalRepository.save(cheval);
        }
        System.out.println(temps);

        return temps;
    }

}
