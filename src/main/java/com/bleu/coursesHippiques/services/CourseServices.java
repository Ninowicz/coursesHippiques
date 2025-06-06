package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseServices {

    private final CourseRepository courseRepository;

    public CourseServices(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


}
