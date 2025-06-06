package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import org.springframework.stereotype.Service;

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
}
