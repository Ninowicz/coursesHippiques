package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerrainServices {
    private final TerrainRepository terrainRepository;

    public TerrainServices(TerrainRepository terrainRepository) {

        this.terrainRepository = terrainRepository;
    }

    public List<Terrain> initTerrains() {
        Terrain t1 = new Terrain("Longchamp", 1200, Terrain.typeTerrain.HERBE);
        t1.setConditionsAleatoires();

        Terrain t2 = new Terrain("Vincennes", 1600, Terrain.typeTerrain.SABLE);
        t2.setConditionsAleatoires();

        Terrain t3 = new Terrain("Chantilly", 1000, Terrain.typeTerrain.FIBRE);
        t3.setConditionsAleatoires();

        Terrain t4 = new Terrain("Deauville", 1400, Terrain.typeTerrain.HERBE);
        t4.setConditionsAleatoires();

        Terrain t5 = new Terrain("Desert Rouge", 1600, Terrain.typeTerrain.SABLE);
        t4.setConditionsAleatoires();

        List<Terrain> listeTerrains = new ArrayList<>();
        listeTerrains.add(t1);
        listeTerrains.add(t2);
        listeTerrains.add(t3);
        listeTerrains.add(t4);
        listeTerrains.add(t5);
        return terrainRepository.saveAll(listeTerrains);
    }
}
