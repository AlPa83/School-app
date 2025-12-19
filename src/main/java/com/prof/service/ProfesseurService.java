package com.prof.service;

import com.prof.entity.Professeur;
import com.prof.repository.ProfesseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {

    private final ProfesseurRepository repository;

    public ProfesseurService(ProfesseurRepository repository) {
        this.repository = repository;
    }

    //  TRI ALPHABÉTIQUE
    public List<Professeur> findAll() {
        return repository.findAllByOrderByNomAsc();
    }

    public Professeur findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Professeur save(Professeur professeur) {
        return repository.save(professeur);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    //  TOTAL D'ÉLÈVES
    public int calculerTotalEleves(Long professeurId) {

        Professeur professeur = findById(professeurId);

        if (professeur == null || professeur.getClasses() == null) {
            return 0;
        }

        // Le stream évite les erreurs liées aux boucles manuelles et
        //  sépare la transformation des données et le calcul final
        return professeur.getClasses()
                .stream()
                .mapToInt(c -> c.getNombreEleves())
                .sum();
    }

    //  MOYENNE D'ÉLÈVES PAR CLASSE
    public double calculerMoyenneEleves(Long professeurId) {

        Professeur professeur = findById(professeurId);

        if (professeur == null || professeur.getClasses().isEmpty()) {
            return 0;
        }

        int totalEleves = calculerTotalEleves(professeurId);
        int nombreClasses = professeur.getClasses().size();

        return (double) totalEleves / nombreClasses;
    }
}
