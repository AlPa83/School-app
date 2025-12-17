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

    public List<Professeur> findAll() {
        return repository.findAll();
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

    public int calculerTotalEleves(Long professeurId) {

        Professeur professeur = findById(professeurId);

        if (professeur == null || professeur.getClasses() == null) {
            return 0;
        }

        return professeur.getClasses()
                .stream()
                .mapToInt(c -> c.getNombreEleves())
                .sum();
    }
}
