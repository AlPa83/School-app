package com.prof.controller;

import com.prof.entity.Professeur;
import com.prof.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur responsable de la gestion des professeurs
 */
@Controller
@RequestMapping("/professeurs")
public class ProfesseurController {

    private final ProfesseurService service;

    public ProfesseurController(ProfesseurService service) {
        this.service = service;
    }

    /**
     * Liste des professeurs (triée alphabétiquement)
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("professeurs", service.findAll());
        return "professeurs/index";
    }

    /**
     * Détail d'un professeur + statistiques
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {

        Professeur professeur = service.findById(id);
        if (professeur == null) {
            return "redirect:/professeurs/";
        }

        // Calculs métiers
        int totalEleves = service.calculerTotalEleves(id);
        double moyenneEleves = service.calculerMoyenneEleves(id);

        model.addAttribute("professeur", professeur);
        model.addAttribute("totalEleves", totalEleves);
        model.addAttribute("moyenneEleves", moyenneEleves);

        return "professeurs/detail";
    }

    /**
     * Formulaire de création
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("professeur", new Professeur());
        return "professeurs/form";
    }

    /**
     * Traitement création
     */
    @PostMapping("/new")
    public String create(@ModelAttribute Professeur professeur) {
        service.save(professeur);
        return "redirect:/professeurs/";
    }

    /**
     * Formulaire modification
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("professeur", service.findById(id));
        return "professeurs/form";
    }

    /**
     * Traitement modification
     */
    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Professeur professeur) {
        service.save(professeur);
        return "redirect:/professeurs/";
    }

    /**
     * Suppression
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/professeurs/";
    }
}
