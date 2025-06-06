package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TerrainServices {
    private final TerrainRepository terrainRepository;
    public TerrainServices(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }


    public List<Terrain> initTerrains() {
        Terrain t1 = new Terrain("Longchamp", 1000, Terrain.typeTerrain.HERBE);
        Terrain t2 = new Terrain("Vincennes", 1600, Terrain.typeTerrain.SABLE);
        Terrain t3 = new Terrain("Chantilly", 1000, Terrain.typeTerrain.FIBRE);
        Terrain t4 = new Terrain("Deauville", 1400, Terrain.typeTerrain.HERBE);
        Terrain t5 = new Terrain("Desert Rouge", 1600, Terrain.typeTerrain.SABLE);

        List<Terrain> listeTerrains = new ArrayList<>();
        listeTerrains.add(t1);
        listeTerrains.add(t2);
        listeTerrains.add(t3);
        listeTerrains.add(t4);
        listeTerrains.add(t5);
        return terrainRepository.saveAll(listeTerrains);
    }

    public void setConditionsAleatoires(Terrain terrain){

        // Fonction qui permet de generer aléatoirement la météo ainsi que le taux de bléssures associé.

        Random rand = new Random();
        int n = rand.nextInt(4);
        switch(n){
            case 0:
                terrain.setMeteoEvenement(Terrain.meteo.valueOf("NORMALE"));
                setTauxDeBlessuresSelonMeteo(terrain);
                break;
            case 1:
                terrain.setMeteoEvenement(Terrain.meteo.valueOf("GRAND_SOLEIL"));
                setTauxDeBlessuresSelonMeteo(terrain);
                break;
            case 2:
                terrain.setMeteoEvenement(Terrain.meteo.valueOf("PLUIE"));
                setTauxDeBlessuresSelonMeteo(terrain);
                break;
            case 3:
                terrain.setMeteoEvenement(Terrain.meteo.valueOf("ORAGE"));
                setTauxDeBlessuresSelonMeteo(terrain);
                break;
            default:
                System.out.println("Erreur parametrage de la meteo de " + terrain.getNomTerrain());
        }
    }

    public void setTauxDeBlessuresSelonMeteo(Terrain terrain){

        if(terrain.getMeteoEvenement() == Terrain.meteo.NORMALE){
            if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.HERBE){
                terrain.setTauxDeBlessures(10);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.SABLE){
                terrain.setTauxDeBlessures(15);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.FIBRE){
                terrain.setTauxDeBlessures(5);
            }
            else{
                terrain.setTauxDeBlessures(15);
            }
        }

        if(terrain.getMeteoEvenement() == Terrain.meteo.GRAND_SOLEIL){
            if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.HERBE){
                terrain.setTauxDeBlessures(10);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.SABLE){
                terrain.setTauxDeBlessures(10);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.FIBRE){
                terrain.setTauxDeBlessures(5);
            }
            else{
                terrain.setTauxDeBlessures(10);
            }
        }

        if(terrain.getMeteoEvenement() == Terrain.meteo.PLUIE){
            if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.HERBE){
                terrain.setTauxDeBlessures(25);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.SABLE){
                terrain.setTauxDeBlessures(30);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.FIBRE){
                terrain.setTauxDeBlessures(10);
            }
            else{
                terrain.setTauxDeBlessures(15);
            }
        }

        if(terrain.getMeteoEvenement() == Terrain.meteo.ORAGE){
            if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.HERBE){
                terrain.setTauxDeBlessures(40);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.SABLE){
                terrain.setTauxDeBlessures(45);
            }
            else if(terrain.getTypeDeTerrain() == Terrain.typeTerrain.FIBRE){
                terrain.setTauxDeBlessures(15);
            }
            else{
                terrain.setTauxDeBlessures(40);
            }
        }

    }
}
