package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses/hippiques/terrain")
public class TerrainController {


    private final TerrainRepository terrainRepository;

    public TerrainController(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }


    @PostMapping("ajouterTerrain")
    public ResponseEntity<List<Terrain>> creerTerrains() {

        List<Terrain> terrains = new ArrayList<>();

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

        terrains.add(t1);
        terrains.add(t2);
        terrains.add(t3);
        terrains.add(t4);
        terrains.add(t5);

        terrainRepository.saveAll(terrains);

        return ResponseEntity.ok(terrains);
    }
}
