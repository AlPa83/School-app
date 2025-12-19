
package com.prof;

import com.prof.entity.Classe;
import com.prof.entity.Professeur;
import com.prof.repository.ClasseRepository;
import com.prof.repository.ProfesseurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			ProfesseurRepository professeurRepository,
			ClasseRepository classeRepository
	) {
		return args -> {

			// Éviter les doublons
			if (professeurRepository.count() == 0) {

				// Création des professeurs
				Professeur prof1 = new Professeur("Gibert", "Philippe", "phil.gibert@mail.com");
				Professeur prof2 = new Professeur("Ollivier", "Claire", "claire.ollivier@mail.com");

				professeurRepository.save(prof1);
				professeurRepository.save(prof2);

				// Création des classes
				Classe c1 = new Classe("Terminale A", "Terminale", "Mathematiques", 28);
				c1.setProfesseur(prof1);

				Classe c2 = new Classe("Premiere B", "Premiere", "Physique", 25);
				c2.setProfesseur(prof1);

				Classe c3 = new Classe("Seconde C", "Seconde", "Français", 30);
				c3.setProfesseur(prof2);

				classeRepository.save(c1);
				classeRepository.save(c2);
				classeRepository.save(c3);
			}
		};
	}
}
