package com.bleu.coursesHippiques.repositories;

import com.bleu.coursesHippiques.beans.Omega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmegaRepository extends JpaRepository<Omega, Integer> {
}
