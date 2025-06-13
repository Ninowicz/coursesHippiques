package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChevalServices {

    private final ChevalRepository chevalRepository;

    public ChevalServices(ChevalRepository chevalRepository) {
        this.chevalRepository = chevalRepository;
    }

    public Cheval ajouterCheval() {
        Cheval cheval = new Cheval();
        chevalRepository.save(cheval);
        System.out.println(cheval.getNom() + " a été ajouté. ID : " + cheval.getIdCheval());
        return cheval;
    }

    public List<Cheval> recupererCheval() {
        return chevalRepository.findAll();
    }

    public List<Cheval> recuperationTop20ChevalCourses () {
        List<Cheval> cheval = chevalRepository.findAll();
        return cheval.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getNbCourseGagnees(), j1.getNbCourseGagnees()))
                .limit(20)
                .toList();
    }


}
