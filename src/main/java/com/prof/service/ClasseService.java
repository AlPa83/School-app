package com.prof.service;

import com.prof.entity.Classe;
import com.prof.entity.Professeur;
import com.prof.repository.ClasseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository repository;

    public ClasseService(ClasseRepository repository) {
        this.repository = repository;
    }

    public List<Classe> findAll() {
        return repository.findAll();
    }

    public Classe findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Classe save(Classe classe) {
        return repository.save(classe);
    }

    @Transactional
    public void deleteById(Long id) {
        Classe classe = repository.findById(id).orElse(null);

        if (classe != null) {
            Professeur professeur = classe.getProfesseur();

            if (professeur != null) {
                professeur.getClasses().remove(classe);
                classe.setProfesseur(null);
            }

            repository.delete(classe);
        }
    }
}
