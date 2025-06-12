package com.bleu.coursesHippiques.repositories;

import com.bleu.coursesHippiques.beans.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Integer> {


    Joueur findByUsername(String username);
    //Joueur findById(int id);
}
