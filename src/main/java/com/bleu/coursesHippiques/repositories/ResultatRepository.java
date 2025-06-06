package com.bleu.coursesHippiques.repositories;

import com.bleu.coursesHippiques.beans.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Integer> {
}
