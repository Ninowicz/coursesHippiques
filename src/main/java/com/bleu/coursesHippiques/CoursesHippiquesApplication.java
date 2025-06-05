package com.bleu.coursesHippiques;

import com.bleu.coursesHippiques.beans.Course;
import com.bleu.coursesHippiques.beans.Terrain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesHippiquesApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoursesHippiquesApplication.class, args);


		System.out.println("programme lancé");
		// d

		Terrain terrain = new Terrain();
		terrain.setLongueur(1000);
		Course test = new Course();
		test.setTerrain(terrain);

		System.out.println(test.calculerTempsRealise());
	}
	// test tommy
	// test Git
	// test 2
	// Test3
}
