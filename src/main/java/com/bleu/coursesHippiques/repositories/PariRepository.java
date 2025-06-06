package com.bleu.coursesHippiques.repositories;

import com.bleu.coursesHippiques.beans.Pari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PariRepository extends JpaRepository<Pari, Integer> {
}
