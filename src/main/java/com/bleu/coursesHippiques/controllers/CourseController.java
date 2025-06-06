package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.repositories.CourseRepository;

public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


}
