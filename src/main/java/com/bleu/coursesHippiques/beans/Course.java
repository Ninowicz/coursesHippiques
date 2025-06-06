package com.bleu.coursesHippiques.beans;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Course {

    public enum typeCourse{
        PLAT,
        TROT,
        OBSTACLE
    }

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String nomCourse;
    private typeCourse typeDeCourse;
    private int nbTours;
    private int nbParticipants;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cheval> listeCheval;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Terrain terrain;

    // Getters & Setters
    public String getNomCourse() {
        return nomCourse;
    }
    public void setNomCourse(String nomCourse) {
        this.nomCourse = nomCourse;
    }
    public typeCourse getTypeDeCourse() {
        return typeDeCourse;
    }
    public void setTypeDeCourse(typeCourse typeDeCourse) {
        this.typeDeCourse = typeDeCourse;
    }
    public int getNbTours() {
        return nbTours;
    }
    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
    public int getNbParticipants() {
        return nbParticipants;
    }
    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }
    public List<Cheval> getListeCheval() {
        return listeCheval;
    }
    public void setListeCheval(List<Cheval> listeCheval) {
        this.listeCheval = listeCheval;
    }
    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    public int getID() {
        return ID;
    }

    // Constructeurs
    public Course() {
    }

    public Course(String nomCourse, List<Cheval> listeCheval, Terrain terrain) {
        this.nomCourse = nomCourse;
        this.listeCheval = listeCheval;
        this.terrain = terrain;
        this.nbParticipants = listeCheval.size();
        this.nbTours = 1;
        this.typeDeCourse = typeCourse.PLAT;
    }

    public Course(String nomCourse, typeCourse typeDeCourse, int nbTours,List<Cheval> listeCheval, Terrain terrain) {
        this.nomCourse = nomCourse;
        this.typeDeCourse = typeDeCourse;
        this.nbTours = nbTours;
        this.nbParticipants = listeCheval.size();
        this.listeCheval = listeCheval;
        this.terrain = terrain;
    }

    // Methodes

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", typeDeCourse='" + typeDeCourse + '\'' +
                ", nbTours=" + nbTours +
                ", nbParticipants=" + nbParticipants +
                ", listeCheval=" + listeCheval +
                ", terrain=" + terrain +
                '}';
    }

    public double coteAgeNbCoursesGagnees(double age, int nbCourseGagnees){
        return age/((double) nbCourseGagnees /2+age)*5;
    }
    public void calculerCote(){
        double cote = 0;
        double coteRace = 0;
        double coteCoursesGagneesAge = 0;
        double cotePedigree = 0;
        double coteEtat = 0;

        for (Cheval cheval : listeCheval){
            switch (cheval.getRace()){
                case PurSang -> coteRace = 0;
                case AQPS -> coteRace = 2;
                case TrotteurFrancais ->  coteRace = 2;
            }
            switch (cheval.getEtatDuCheval()){
                case Mort -> coteEtat = 1000;
                case Fatigue -> coteEtat = 3;
                case Blesse -> coteEtat = 5;
                case EnForme -> coteEtat = 0;
            }
            switch (cheval.getPedigree()){
                case 2 -> cotePedigree = 0;
                case 1 -> cotePedigree = 1;
                case 0 -> cotePedigree = 2;
            }
            coteCoursesGagneesAge = coteAgeNbCoursesGagnees(cheval.getAge(), cheval.getNbCourseGagnees());
            cote = 1 + coteRace + cotePedigree +coteCoursesGagneesAge + coteEtat;
            cheval.setCote(cote);
        }
    }
    public void calculerMalus(){
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
                malusCouleurYeuxTerrain = 0.5;
            }

            //* Premiere ligne malus en fonction de l'age, max age = 10 => malus max
            //* Deuxieme ligne malus en fonction du ration Poids/Taille (fct affine ax + b)
            //* Troisieme ligne malus globaux
            double malus =  (Math.exp((double) cheval.getAge() /10)-Math.exp((double) 2 /10))/10 +
                    Math.abs(a*cheval.getPoids() + b - cheval.getTaille())/10  +
                    malusBlessure + malusCouleurYeuxTerrain  + malusFerTerrain;
            cheval.setMalus(malus);
        }
    }
    public void calculerTempsRealise(){
        for (Cheval cheval : listeCheval){
            List<Integer> listeDistanceParcourue = new ArrayList<>();
            listeDistanceParcourue.add(0);
            double rdAcceleration = (Math.random() * 0.2) ;
            double accelerationReelle = cheval.getAcceleration() - rdAcceleration - cheval.getMalus();
            int i = 1;
            while ( (terrain.getLongueur() > Collections.max(listeDistanceParcourue) ) ){
                listeDistanceParcourue.add((int) ( Math.min(i*accelerationReelle,cheval.getVitesseMax())
                        + Collections.max(listeDistanceParcourue) ));
                i++;
            }
            cheval.setTempsRealise(listeDistanceParcourue);
        }
    }
    public void calculerBlessure(){
        for (Cheval cheval : listeCheval){
            int rd = (int) (Math.random() * 100);
            if (rd <= terrain.getTauxDeBlessures()){
                cheval.setEtatDuCheval(Cheval.EtatDuCheval.Blesse);
            }
        }
    }
    public List<Cheval> podium(){
        Comparator<Cheval> comparatorDistanceParcourues = (c1, c2) -> {
            return Collections.max(c1.getTempsRealise()) -
                    Collections.max(c2.getTempsRealise());
        };
        Comparator<Cheval> comparatorTemps = (c1, c2) -> {
            return c1.getTempsRealise().size() - c2.getTempsRealise().size();
        };
        listeCheval.sort(comparatorDistanceParcourues);
        listeCheval.sort(comparatorTemps);

        return listeCheval;
    }


}
