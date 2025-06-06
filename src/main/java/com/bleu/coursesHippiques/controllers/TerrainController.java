package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses/hippiques/terrain")
public class TerrainController {


    private final TerrainRepository terrainRepository;

    public TerrainController(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }


    @PostMapping("ajouterTerrain")
    public ResponseEntity<Terrain> ajouterTerrain() {
        Terrain terrain = new Terrain();
        terrainRepository.save(terrain);
        System.out.println(terrain.getNomTerrain() + " a ete ajoute. ID : " + terrain.getIdTerrain());
        return ResponseEntity.ok(terrain);
    }
}
