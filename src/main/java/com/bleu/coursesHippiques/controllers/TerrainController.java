package com.bleu.coursesHippiques.controllers;

import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import com.bleu.coursesHippiques.repositories.ChevalRepository;
import com.bleu.coursesHippiques.repositories.TerrainRepository;
import com.bleu.coursesHippiques.services.TerrainServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/courses/hippiques/terrain")
public class TerrainController {


    private final TerrainRepository terrainRepository;
    private final TerrainServices terrainServices;

    public TerrainController(TerrainRepository terrainRepository, TerrainServices terrainServices) {
        this.terrainRepository = terrainRepository;
        this.terrainServices = terrainServices;
    }

    @GetMapping("recupererTerrain")
    public ResponseEntity<List<Terrain>> recupererTerrain() {
        List<Terrain> listeTerrains = terrainRepository.findAll();
        return ResponseEntity.ok(listeTerrains);
    }

}
