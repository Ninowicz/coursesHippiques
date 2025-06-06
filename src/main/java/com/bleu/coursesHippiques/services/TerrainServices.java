package com.bleu.coursesHippiques.services;

import com.bleu.coursesHippiques.repositories.TerrainRepository;
import org.springframework.stereotype.Service;

@Service
public class TerrainServices {
    private final TerrainRepository terrainRepository;

    public TerrainServices(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }
}
