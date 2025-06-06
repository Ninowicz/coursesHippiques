package com.bleu.coursesHippiques.repositories;

import com.bleu.coursesHippiques.beans.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

