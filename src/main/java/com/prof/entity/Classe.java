package com.prof.entity;

import jakarta.persistence.*;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String niveau;
    private String matiere;
    private int nombreEleves;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    public Classe() {}

    public Classe(String nom, String niveau, String matiere, int nombreEleves) {
        this.nom = nom;
        this.niveau = niveau;
        this.matiere = matiere;
        this.nombreEleves = nombreEleves;
    }

    /* ===== ID ===== */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /* ===== AUTRES CHAMPS ===== */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getMatiereKey() {
        return matiere.toLowerCase()
                .replace("é", "e")
                .replace("è", "e")
                .replace("ç", "c")
                .replace(" ", "");
    }

    public int getNombreEleves() {
        return nombreEleves;
    }

    public void setNombreEleves(int nombreEleves) {
        this.nombreEleves = nombreEleves;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
}
