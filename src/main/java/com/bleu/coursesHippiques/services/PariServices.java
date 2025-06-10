package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.beans.Pari;
import com.bleu.coursesHippiques.repositories.PariRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PariServices {

    public void traitementMiseJoueur(Joueur monJoueur){
        monJoueur.setArgent(monJoueur.getArgent()-monJoueur.getPari().getMise());
    }

    private final PariRepository pariRepository;

    public PariServices(PariRepository pariRepository) {
        this.pariRepository = pariRepository;
    }

    public Pari enregistrerPari(Pari pari) {
        return pariRepository.save(pari);
    }

    public Optional<Pari> trouverPariParId(int id) {
        return pariRepository.findById(id);
    }

    public void supprimerPari(int id) {
        pariRepository.deleteById(id);
    }
}
