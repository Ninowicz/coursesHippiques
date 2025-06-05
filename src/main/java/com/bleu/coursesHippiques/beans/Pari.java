package com.bleu.coursesHippiques.beans;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pari {

    public enum TypeDePari{
        SIMPLE
        // À venir : COUPLE, TRIO, QUINTE...
    }

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPari;
    private TypeDePari typePari;
    private int mise;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cheval> chevalChoisi;

    // Constructeurs

    public Pari() {}

    private Pari(TypeDePari typePari, int mise, List<Cheval> chevalChoisi) {
        this.typePari = typePari;
        this.mise = mise;
        this.chevalChoisi = chevalChoisi;
    }

    // Factory method pour créer des paris differents (simple, couple, trio...)

        // utilisation dans le "main" :
        // Pari monPari = Pari.creerPariSimple(50, cheval1);
        //

    public static Pari creerPariSimple(int mise, Cheval cheval) {
        return new Pari(TypeDePari.SIMPLE, mise, List.of(cheval));
    }
    //public static Pari creerPariDouble(int mise, Cheval cheval){}



    // Methodes


    // Getter Setter


    public int getIdPari() {
        return idPari;
    }

    public TypeDePari getTypePari() {
        return typePari;
    }

    public int getMise() {
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

    public void setMise(int mise) {
        this.mise = mise;
    }

    public void setChevalChoisi(List<Cheval> chevalChoisi) {
        this.chevalChoisi = chevalChoisi;
    }
}
