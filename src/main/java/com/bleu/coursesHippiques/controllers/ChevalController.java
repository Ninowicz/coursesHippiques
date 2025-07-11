package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

}
