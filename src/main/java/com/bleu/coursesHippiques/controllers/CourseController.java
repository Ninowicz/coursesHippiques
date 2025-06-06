package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.CourseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses/hippiques/course")
public class CourseController {

    private final ChevalRepository chevalRepository;
    private final ChevalServices chevalServices;
    private final CourseRepository courseRepository;
    private final CourseServices courseServices;

    public CourseController(ChevalRepository chevalRepository, ChevalServices chevalServices, CourseRepository courseRepository, CourseServices courseServices) {
        this.chevalRepository = chevalRepository;
        this.chevalServices = chevalServices;
        this.courseRepository = courseRepository;
        this.courseServices = courseServices;
    }

    @PostMapping("ajouterCourse")
    public ResponseEntity<Course> ajouterCourse() {
        Terrain terrain = new Terrain();
        terrain.setLongueur((int) (Math.random()*(3000-2000) + 2000));
        terrain.setConditionsAleatoires();

        Course test = courseServices.ajouterCourse("Jesaisaps",chevalRepository.findAll(),terrain);

        return ResponseEntity.ok(test);
    }

    @GetMapping("recupererCourse")
    public ResponseEntity<List<Course>> recupererCourse() {
        List<Course> listeCourses = courseServices.recupererCourse();
        return ResponseEntity.ok(listeCourses);
    }

    @GetMapping("testCourse")
    public List<Cheval> testCourse() {

        courseServices.calculerMalus(1);
        courseServices.calculerCote(1);
        courseServices.calculerTempsRealise(1);
        courseServices.calculerBlessure(1);
        List<Cheval> temps =  courseServices.podium(1);

        for (Cheval cheval : temps){
            chevalRepository.save(cheval);
        }
        System.out.println(temps);

        return temps;
    }


}
