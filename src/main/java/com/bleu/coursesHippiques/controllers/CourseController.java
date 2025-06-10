package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.CourseServices;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/courses/hippiques/course")
public class CourseController {

    private final ChevalRepository chevalRepository;
    private final ChevalServices chevalServices;
    private final CourseRepository courseRepository;
    private final CourseServices courseServices;
    private final TerrainServices terrainServices;
    private final TerrainRepository terrainRepository;

    public CourseController(ChevalRepository chevalRepository, ChevalServices chevalServices, CourseRepository courseRepository, CourseServices courseServices, TerrainServices terrainServices, TerrainRepository terrainRepository) {
        this.chevalRepository = chevalRepository;
        this.chevalServices = chevalServices;
        this.courseRepository = courseRepository;
        this.courseServices = courseServices;
        this.terrainServices = terrainServices;
        this.terrainRepository = terrainRepository;
    }

    @PostMapping("ajouterCourse")
    public ResponseEntity<Course> ajouterCourse() {
        Terrain terrain = new Terrain();
        terrain.setLongueur((int) (Math.random()*(3000-2000) + 2000));
        //terrain.setConditionsAleatoires();
        terrainServices.setConditionsAleatoires(terrain);

        Course test = courseServices.ajouterCourse("Jesaisaps",chevalRepository.findAll(),terrain);

        return ResponseEntity.ok(test);
    }

    @GetMapping("recupererCourse")
    public ResponseEntity<List<Course>> recupererCourse() {
        List<Course> listeCourses = courseServices.recupererCourse();
        return ResponseEntity.ok(listeCourses);
    }

    @GetMapping("testCourse")
    public ResponseEntity<Course> testCourse() {
        int id = 1;
        courseServices.calculerMalus(id);
        courseServices.calculerCote(id);
        courseServices.calculerTempsRealise(id);
        courseServices.calculerBlessure(id);
        List<Cheval> temps =  courseServices.podium(id);

        for (Cheval cheval : temps){
            chevalRepository.save(cheval);
        }

        Course course = courseRepository.findById(id);

        return ResponseEntity.ok(course);
    }


}
