package com.bleu.coursesHippiques.beans;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pari {

    public enum TypeDePari{
        SIMPLE,         // cheval doit finir premier
        SIMPLE_PLACE,   // cheval doit etre dans les 3 premiers
        COUPLE_GAGNANT, // 2 chevaux doivent etre dans les 2 premiers
        COUPLE_PLACE,   // 2 chevaux doivent etre dans les 3 premiers
        COUPLE_ORDRE,   // 2 chevaux doivent etre dans les 2 premiers dans l'ordre
        TRIO_GAGNANT,   // 3 chevaux doivent etre dans les 3 premiers

    }

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPari;

    @Enumerated(EnumType.STRING)
    private TypeDePari typePari;
    private double mise;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Cheval> chevalChoisi;

    // Constructeurs

    public Pari() {}

    private Pari(TypeDePari typePari, double mise, List<Cheval> chevalChoisi) {
        this.typePari = typePari;
        this.mise = mise;
        this.chevalChoisi = chevalChoisi;
    }

    // Factory method pour créer des paris differents (simple, couple, trio...)

        // utilisation dans le "main" :
        // Pari monPari = Pari.creerPariSimple(50, cheval1);
        //

    public static Pari creerPariSimple(double mise, Cheval cheval) {
        return new Pari(TypeDePari.SIMPLE, mise, List.of(cheval));
    }

    public static Pari creerPariSimplePlace(double mise, Cheval cheval) {
        return new Pari(TypeDePari.SIMPLE_PLACE, mise, List.of(cheval));
    }

    public static Pari creerPariCoupleGagnant(double mise, List<Cheval> chevaux) {
        return new Pari(TypeDePari.COUPLE_GAGNANT, mise, chevaux);
    }

    public static Pari creerPariCouplePlace(double mise, List<Cheval> chevaux) {
        return new Pari(TypeDePari.COUPLE_PLACE, mise, chevaux);
    }
    public static Pari creerPariCoupleOrdre(double mise, List<Cheval> chevaux) {
        return new Pari(TypeDePari.COUPLE_ORDRE, mise, chevaux);
    }
    public static Pari creerPariTrioGagnant(double mise, List<Cheval> chevaux) {
        return new Pari(TypeDePari.TRIO_GAGNANT, mise, chevaux);
    }



    // Methodes


    // Getter Setter


    public int getIdPari() {
        return idPari;
    }

    public TypeDePari getTypePari() {
        return typePari;
    }

    public double getMise() {
        return mise;
    }

    public List<Cheval> getChevalChoisi() {
        return chevalChoisi;
    }

    public void setIdPari(int idPari) {
        this.idPari = idPari;
    }

    public void setTypePari(TypeDePari typePari) {
        this.typePari = typePari;
    }

    public void setMise(double mise) {
        this.mise = mise;
    }

    public void setChevalChoisi(List<Cheval> chevalChoisi) {
        this.chevalChoisi = chevalChoisi;
    }
}
