package com.bleu.coursesHippiques.controllers;


import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
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
    public String ajouterCheval() {
        Cheval cheval = new Cheval();
        chevalRepository.save(cheval);
        System.out.println(cheval.toString());
        return "Cheval " + cheval.getNom() + " sauvegardé avec l'ID " + cheval.getIdCheval();
    }

    @GetMapping("recupererCheval")
    public List<Cheval> recupererCheval() {
        return chevalRepository.findAll();
    }

}
