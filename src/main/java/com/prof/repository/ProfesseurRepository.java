package com.prof.repository;

import com.prof.entity.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

    // Tri alphabétique par nom
    List<Professeur> findAllByOrderByNomAsc();
}
