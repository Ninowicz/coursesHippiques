package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Cheval;
import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.CourseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CourseServices {

    private final CourseRepository courseRepository;

    public CourseServices(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course ajouterCourse() {
        Course course = new Course();
        courseRepository.save(course);
        System.out.println( "La course a été ajouté. ID : " + course.getID());
        return course;
    }

    public Course ajouterCourse(String nomCourse, List<Cheval> listeCheval, Terrain terrain) {
        Course course = new Course(nomCourse,  listeCheval, terrain);
        courseRepository.save(course);
        System.out.println( "La course a été ajouté. ID : " + course.getID());
        return course;
    }

    public List<Course> recupererCourse() {
        return courseRepository.findAll();
    }

    public void calculerCote(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        List<Cheval> listeCheval = course.getListeCheval();

        double cote = 0;
        double coteRace = 0;
        double coteCoursesGagneesAge = 0;
        double cotePedigree = 0;
        double coteEtat = 0;

        int moyenneNbCourseGagnees = listeCheval.stream()
                .mapToInt(Cheval::getNbCourseGagnees).sum()/ listeCheval.size();

        for (Cheval cheval : listeCheval){
            switch (cheval.getRace()){
                case PurSang -> coteRace = 0;
                case AQPS -> coteRace = 1;
                case TrotteurFrancais ->  coteRace = 1;
            }
            switch (cheval.getEtatDuCheval()){
                case Mort -> coteEtat = 1000;
                case Fatigue -> coteEtat = 1;
                case Blesse -> coteEtat = 2;
                case EnForme -> coteEtat = 0;
            }
            switch (cheval.getPedigree()){
                case 2 -> cotePedigree = 0;
                case 1 -> cotePedigree = 0.5;
                case 0 -> cotePedigree = 1;
            }
            coteCoursesGagneesAge = Math.min(moyenneNbCourseGagnees,
                    cheval.getAge()/((double) cheval.getNbCourseGagnees() / 2 + cheval.getAge())) * 3;
            cote = 1 + coteRace + cotePedigree +coteCoursesGagneesAge + coteEtat;
            cheval.setCote(cote);
        }
    }

    public void calculerMalus(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        Terrain terrain = course.getTerrain();
        List<Cheval> listeCheval = course.getListeCheval();
        for (Cheval cheval : listeCheval){
            double a = 0.001;
            double b = 1.2;
            double malusBlessure = 0;
            double malusCouleurYeuxTerrain = 0;
            double malusFerTerrain = 0;
            switch (cheval.getEtatDuCheval()){
                case Fatigue -> malusBlessure = 0.1;
                case Blesse -> malusBlessure = 0.2;
                case Mort -> malusBlessure = 100;
            }
            if (cheval.getTypeDeFer() == Cheval.TypeDeFer.Avec){
                if (terrain.getTypeDeTerrain() == Terrain.typeTerrain.FIBRE ||
                        terrain.getTypeDeTerrain() == Terrain.typeTerrain.SABLE ){
                    malusFerTerrain = 0.05;
                }
            }
            else {
                if (terrain.getTypeDeTerrain() == Terrain.typeTerrain.HERBE){
                    malusFerTerrain = 0.05;
                }
            }
            if (cheval.getCouleurDesYeux() == Cheval.CouleurDesYeux.Bleu &&
                    terrain.getMeteoEvenement() == Terrain.meteo.GRAND_SOLEIL){
                malusCouleurYeuxTerrain = 0.05;
            }

            //* Premiere ligne malus en fonction de l'age, max age = 10 => malus max
            //* Deuxieme ligne malus en fonction du ratio Poids/Taille (fct affine ax + b)
            //* Troisieme ligne malus globaux
            double malus =  (Math.exp((double) cheval.getAge() /10)-Math.exp((double) 2 /10))/10 +
                    Math.abs(a*cheval.getPoids() + b - cheval.getTaille())/10  +
                    malusBlessure + malusCouleurYeuxTerrain  + malusFerTerrain;
            cheval.setMalus(malus);
        }
    }

    public void calculerTempsRealise(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        Terrain terrain = course.getTerrain();
        List<Cheval> listeCheval = course.getListeCheval();
        for (Cheval cheval : listeCheval ){
            List<Integer> listeDistanceParcourue = new ArrayList<>();
            listeDistanceParcourue.add(0);
            int i = 1;
            while ( (terrain.getLongueur()*course.getNbTours() > Collections.max(listeDistanceParcourue) ) ){
                double rdAcceleration = (Math.random() * 0.2) ;
                double rdAleaVMax = (Math.random() * cheval.getMalus()/10)+(1-cheval.getMalus()/10) ;
                double accelerationReelle = cheval.getAcceleration() - rdAcceleration - cheval.getMalus();
                double distanceSup = Math.min(i*accelerationReelle,cheval.getVitesseMax())*1000/(60*60)*rdAleaVMax;
                listeDistanceParcourue.add((int) ( distanceSup + Collections.max(listeDistanceParcourue) ));
                i++;
            }

            int sec = listeDistanceParcourue.size()-1;
            int diff1 = listeDistanceParcourue.getLast()-terrain.getLongueur()*course.getNbTours();
            int diff2 = terrain.getLongueur()*course.getNbTours() - listeDistanceParcourue.get(listeDistanceParcourue.size()-2);
            double reste = (double) diff2 /(diff2+diff1);

            cheval.setDernierTemps(reste + sec);
            cheval.setTempsRealise(listeDistanceParcourue);
        }
    }

    public void calculerBlessure(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        Terrain terrain = course.getTerrain();
        List<Cheval> listeCheval = course.getListeCheval();
        for (Cheval cheval : listeCheval){
            int rd = (int) (Math.random() * 100);
            if (rd <= terrain.getTauxDeBlessures()){
                cheval.setEtatDuCheval(Cheval.EtatDuCheval.Blesse);
            }
        }
    }
    public void calculerVainqueur(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        List<Cheval> listeCheval = course.getListeCheval();
        long idVainqueur = listeCheval.getFirst().getIdCheval();
        for (Cheval c : course.getListeCheval()){
            if (c.getIdCheval()==idVainqueur){
                c.setNbCourseGagnees(c.getNbCourseGagnees()+1);
            }
        }

    }

    public List<Cheval> podium(Integer ID){
        Course course = courseRepository.getReferenceById(ID);
        calculerVainqueur(ID);
        List<Cheval> listeCheval = course.getListeCheval();
        listeCheval.sort(Comparator.comparingDouble(Cheval::getDernierTemps));

        return listeCheval;
    }



}
