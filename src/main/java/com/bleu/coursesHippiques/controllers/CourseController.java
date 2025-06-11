package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Joueur;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.ChevalServices;
import com.bleu.coursesHippiques.services.CourseServices;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @PostMapping("creerListeCourses")
    public ResponseEntity<List<Course>> creerListeCourse(){
        int nbCourse = 3;
        List<Course> listeCourses = new ArrayList<>();
        for (int i = 0; i < nbCourse; i++){

            List<Terrain> listeTerrains = terrainRepository.findAll();
            Collections.shuffle(listeTerrains);
            Terrain terrainCourse = listeTerrains.getFirst();

            String nomCourse = "Course n°" + (i + 1) + "; Terrain = " + terrainCourse.getNomTerrain() + "; Meteo = " + terrainCourse.getMeteoEvenement();

            int nbChevaux = (int) (Math.random() * (16 - 6) )+ 6;
            List<Cheval> listeChevaux = chevalRepository.findAll();
            Collections.shuffle(listeChevaux);
            List<Cheval> listeChevauxCourse = new ArrayList<>();
            for (int k = 0; k < nbChevaux; k++){
                listeChevauxCourse.add(listeChevaux.get(k));
            }

            Course course = new Course(nomCourse,listeChevauxCourse,terrainCourse);
            courseRepository.save(course);
            listeCourses.add(course);
        }
        return ResponseEntity.ok(listeCourses);
    }
    @PostMapping("ajouterCourseFibre")
    public ResponseEntity<Course> ajouterCourseFibre() {
        Terrain terrain = new Terrain();
        terrain.setTypeDeTerrain(Terrain.typeTerrain.FIBRE);
        terrain.setLongueur((int) (Math.random()*(3000-2000) + 2000));
        //terrain.setConditionsAleatoires();
        terrainServices.setConditionsAleatoires(terrain);

        Course test = courseServices.ajouterCourse("Jesaisaps",chevalRepository.findAll(),terrain);

        return ResponseEntity.ok(test);
    }
    @PostMapping("ajouterCourseHerbe")
    public ResponseEntity<Course> ajouterCourseHerbe() {
        Terrain terrain = new Terrain();
        terrain.setTypeDeTerrain(Terrain.typeTerrain.HERBE);
        terrain.setLongueur((int) (Math.random()*(3000-2000) + 2000));
        //terrain.setConditionsAleatoires();
        terrainServices.setConditionsAleatoires(terrain);

        Course test = courseServices.ajouterCourse("Jesaisaps",chevalRepository.findAll(),terrain);

        return ResponseEntity.ok(test);
    }
    @PostMapping("ajouterCourseSable")
    public ResponseEntity<Course> ajouterCourseSable() {
        Terrain terrain = new Terrain();
        terrain.setTypeDeTerrain(Terrain.typeTerrain.SABLE);
        terrain.setLongueur((int) (Math.random()*(3000-2000) + 2000));
        //terrain.setConditionsAleatoires();
        terrainServices.setConditionsAleatoires(terrain);
        int nbChevaux = (int)(Math.random() * 7);
        List<Cheval> listeChevaux = new ArrayList<>();
        for (int i = 0; i<nbChevaux; i++){
            int numCheval = (int) (Math.random() * chevalRepository.findAll().size());
        }


        System.out.println(chevalRepository.findAll().size());


        Course test = courseServices.ajouterCourse("Jesaisaps",chevalRepository.findAll(),terrain);

        return ResponseEntity.ok(test);
    }

    @GetMapping("recupererCourse")
    public ResponseEntity<List<Course>> recupererCourse() {
        List<Course> listeCourses = courseServices.recupererCourse();
        return ResponseEntity.ok(listeCourses);
    }

    @PostMapping("testCourse")
    public ResponseEntity<Course> testCourse(@RequestBody int id) {
        courseServices.calculerMalus(id);
        courseServices.calculerCote(id);
        courseServices.calculerTempsRealise(id);
        courseServices.calculerBlessure(id);


        Course course = courseRepository.findById(id);

        return ResponseEntity.ok(course);
    }


}
