package com.bleu.coursesHippiques.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
public class Cheval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Attributs
    private long idCheval;
    private String nom;
    private int age;
    private Race race;
    private double taille;
    private int poids;
    private double vitesseMax;
    private double acceleration;
    private int nbCourseGagnees;
    private CouleurDeLaRobe couleurDeLaRobe;
    private int pedigree;
    private CouleurDesYeux couleurDesYeux;
    private TypeDeFer typeDeFer;
    private EtatDuCheval etatDuCheval;
    private double malus;
    private double cote;
    private List<Integer> tempsRealise = new ArrayList<>();
    private double dernierTemps;


    // Methodes
    private int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    private double randomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    private String randomNom (){
        List<String> noms = Arrays.asList(
                "Tonnerre", "Flamme", "Éclair", "Brume", "Soleil",
                "Dragon", "Comète", "Mirage", "Fantôme", "Vent",
                "Rubis", "Saphir", "Prince", "Baron", "Corsaire",
                "Lame", "Galop", "Cyclone", "Spectre", "Flèche"
        );

        List<String> adjectifs = Arrays.asList(
                "Sauvage", "Silencieux", "Ardent", "Céleste", "Nocturne",
                "Pourpre", "Hurlant", "Agile", "Foudroyant", "Sombre",
                "Radieux", "Ténébreux", "Vif", "Doré", "Glacial",
                "Vaillant", "Brillant", "Rapide", "Rouge", "Loyal"
        );

        Random rand = new Random();
        String nomRandom = noms.get(rand.nextInt(noms.size()));
        String adjectifRandom = adjectifs.get(rand.nextInt(adjectifs.size()));

        return nomRandom + " " + adjectifRandom;
    }


    // to String

    @Override
    public String toString() {
        return "Cheval{" +
                "idCheval=" + idCheval +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                ", race=" + race +
                ", taille=" + taille +
                ", poids=" + poids +
                ", vitesseMax=" + vitesseMax +
                ", acceleration=" + acceleration +
                ", nbCourseGagnees=" + nbCourseGagnees +
                ", couleurDeLaRobe=" + couleurDeLaRobe +
                ", pedigree=" + pedigree +
                ", couleurDesYeux=" + couleurDesYeux +
                ", typeDeFer=" + typeDeFer +
                ", tempsRealise=" + tempsRealise +
                ", etatDuCheval=" + etatDuCheval +
                ", malus=" + malus +
                ", cote=" + cote +
                '}';
    }

    // Enums
    public enum CouleurDesYeux {
        Bleu, Marron;

        private static final CouleurDesYeux[] VALEURS = values();
        private static final Random RANDOM = new Random();

        public static CouleurDesYeux couleurAleatoire() {
            return VALEURS[RANDOM.nextInt(VALEURS.length)];
        }
    }

    public enum Race {
        PurSang, TrotteurFrancais, AQPS;

        private static final Race[] VALEURS = values();
        private static final Random RANDOM = new Random();

        public static Race raceAleatoire() {
            return VALEURS[RANDOM.nextInt(VALEURS.length)];
        }
    }

    public enum CouleurDeLaRobe {
        Noir, Bai, Alezan, Souris, Isabelle, Blanc;

        private static final CouleurDeLaRobe[] VALEURS = values();
        private static final Random RANDOM = new Random();

        public static CouleurDeLaRobe couleurDeLaRobeAleatoire() {
            return VALEURS[RANDOM.nextInt(VALEURS.length)];
        }
    }

    public enum EtatDuCheval {
        Blesse, Mort, EnForme, Fatigue;
    }

    public enum TypeDeFer {
        Avec, Sans;

        private static final TypeDeFer[] VALEURS = values();
        private static final Random RANDOM = new Random();

        public static TypeDeFer typeDeFerAleatoire() {
            return VALEURS[RANDOM.nextInt(VALEURS.length)];
        }
    }


    // Constructeurs

    public Cheval() {
        this.nom = randomNom();
        this.age = randomInt(2, 10);
        this.race = Race.raceAleatoire();
        this.taille = randomDouble(1.5, 1.8);
        this.poids = randomInt(300, 800);
        this.vitesseMax = randomDouble(55, 60);
        this.acceleration = randomDouble(1.7, 2);
        this.nbCourseGagnees = 0; //randomInt(0, 62)
        this.couleurDeLaRobe = CouleurDeLaRobe.couleurDeLaRobeAleatoire();
        this.pedigree = randomInt(0, 2);
        this.couleurDesYeux = CouleurDesYeux.couleurAleatoire();
        this.typeDeFer = TypeDeFer.typeDeFerAleatoire();
        this.etatDuCheval = EtatDuCheval.EnForme;
    }

    // Getter Setters


    public double getDernierTemps() {
        return dernierTemps;
    }
    public void setDernierTemps(double dernierTemps) {
        this.dernierTemps = dernierTemps;
    }

    public long getIdCheval() {
        return idCheval;
    }

    public void setIdCheval(long idCheval) {
        this.idCheval = idCheval;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public double getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public int getNbCourseGagnees() {
        return nbCourseGagnees;
    }

    public void setNbCourseGagnees(int nbCourseGagnees) {
        this.nbCourseGagnees = nbCourseGagnees;
    }

    public int getPedigree() {
        return pedigree;
    }

    public void setPedigree(int pedigree) {
        this.pedigree = pedigree;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setCouleurDeLaRobe(CouleurDeLaRobe couleurDeLaRobe) {
        this.couleurDeLaRobe = couleurDeLaRobe;
    }

    public List<Integer> getTempsRealise() {
        return tempsRealise;
    }

    public void setTempsRealise(List<Integer> tempsRealise) {
        this.tempsRealise = tempsRealise;
    }

    public void setTypeDeFer(TypeDeFer typeDeFer) {
        this.typeDeFer = typeDeFer;
    }

    public double getMalus() {
        return malus;
    }

    public void setMalus(double malus) {
        this.malus = malus;
    }

    public double getCote() {
        return cote;
    }

    public void setCote(double cote) {
        this.cote = cote;
    }

    public Race getRace() {
        return race;
    }

    public void setRaceAleatoire() {
        this.race = Race.raceAleatoire();
    }

    public CouleurDeLaRobe getCouleurDeLaRobe() {
        return couleurDeLaRobe;
    }

    public void setCouleurDeLaRobeAleatoire() {
        this.couleurDeLaRobe = CouleurDeLaRobe.couleurDeLaRobeAleatoire();
    }

    public CouleurDesYeux getCouleurDesYeux() {
        return couleurDesYeux;
    }

    public void setCouleurDesYeux(CouleurDesYeux couleurDesYeux) {
        this.couleurDesYeux = couleurDesYeux;
    }

    public void setCouleurDesYeuxAleatoire() {
        this.couleurDesYeux = CouleurDesYeux.couleurAleatoire();
    }

    public TypeDeFer getTypeDeFer() {
        return typeDeFer;
    }

    public void setTypeDeFerAleatoire() {
        this.typeDeFer = TypeDeFer.typeDeFerAleatoire();
    }

    public EtatDuCheval getEtatDuCheval() {
        return etatDuCheval;
    }

    public void setEtatDuCheval(EtatDuCheval etatDuCheval) {
        this.etatDuCheval = etatDuCheval;
    }
}
