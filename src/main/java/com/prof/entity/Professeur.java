package com.prof.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    @OneToMany(
            mappedBy = "professeur",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Classe> classes = new ArrayList<>();

    public Professeur() {}

    public Professeur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public void addClasse(Classe classe) {
        classes.add(classe);
        classe.setProfesseur(this);
    }

    public void removeClasse(Classe classe) {
        classes.remove(classe);
        classe.setProfesseur(null);
    }
}
