package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.*;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.repositories.JoueurRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.CourseServices;
import com.bleu.coursesHippiques.services.JoueurServices;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    private final JoueurServices joueurServices;
    private final JoueurRepository joueurRepository;

    public CourseController(ChevalRepository chevalRepository, ChevalServices chevalServices, CourseRepository courseRepository, CourseServices courseServices, TerrainServices terrainServices, TerrainRepository terrainRepository, JoueurServices joueurServices, JoueurRepository joueurRepository) {
        this.chevalRepository = chevalRepository;
        this.chevalServices = chevalServices;
        this.courseRepository = courseRepository;
        this.courseServices = courseServices;
        this.terrainServices = terrainServices;
        this.terrainRepository = terrainRepository;
        this.joueurServices = joueurServices;
        this.joueurRepository = joueurRepository;
    }

    @GetMapping("creerListeCourses")
    public ResponseEntity<List<Course>> creerListeCourse(){
        int nbCourse = 3;
        List<Course> listeCourses = new ArrayList<>();
        for (int i = 0; i < nbCourse; i++){

            List<Terrain> listeTerrains = terrainRepository.findAll();
            Collections.shuffle(listeTerrains);
            Terrain terrainCourse = listeTerrains.getFirst();
            terrainServices.setConditionsAleatoires(terrainCourse);
            String nomCourse = "Course n°" + (i + 1) + "; Terrain = " + terrainCourse.getNomTerrain() + "; Meteo = " + terrainCourse.getMeteoEvenement();

            int nbChevaux = (int) (Math.random() * (9 - 4) )+ 4;
            List<Cheval> listeChevaux = chevalRepository.findAll();
            Collections.shuffle(listeChevaux);
            List<Cheval> listeChevauxCourse = new ArrayList<>();
            for (int k = 0; k < nbChevaux; k++){
                listeChevauxCourse.add(listeChevaux.get(k));
            }

            Course course = new Course(nomCourse,listeChevauxCourse,terrainCourse);
            courseRepository.save(course);
            courseServices.calculerCote(course.getID());
            courseServices.calculerMalus(course.getID());

            listeCourses.add(course);
        }
        return ResponseEntity.ok(listeCourses);
    }

    @GetMapping("recupererCourse")
    public ResponseEntity<List<Course>> recupererCourse() {
        List<Course> listeCourses = courseServices.recupererCourse();
        return ResponseEntity.ok(listeCourses);
    }

    @PostMapping("recuperer1Course")
    public ResponseEntity<Optional<Course>> recuperer1Course(@RequestBody int idCourse) {
        Optional<Course> course = courseRepository.findById(idCourse);
        return ResponseEntity.ok(course);
    }

    @PostMapping("testCourse")
    public ResponseEntity<Course> testCourse(@RequestBody int id) {

        courseServices.calculerCote(id);
        courseServices.calculerMalus(id);
        courseServices.calculerTempsRealise(id);
        courseServices.calculerBlessure(id);
        courseServices.calculerVainqueur(id);
        Course course = courseRepository.findById(id).orElse(null);
        for (Cheval c : course.getListeCheval()) {
            chevalRepository.save(c);
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping("podium")
    public ResponseEntity<List<Cheval>> podium(@RequestBody int idCourse) {
        List<Cheval> podium = courseServices.podium(idCourse);
        return ResponseEntity.ok(podium);
    }




}
