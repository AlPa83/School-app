package com.prof.controller;

import com.prof.entity.Classe;
import com.prof.service.ClasseService;
import com.prof.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur Spring MVC responsable de la gestion des classes
 * (affichage, création, modification, suppression).
 */
@Controller
@RequestMapping("/classes")
public class ClasseController {

    // Service métier pour les classes
    private final ClasseService classeService;

    // Service métier pour les professeurs (utile pour les formulaires)
    private final ProfesseurService professeurService;

    // Injection des dépendances par constructeur
    public ClasseController(ClasseService classeService, ProfesseurService professeurService) {
        this.classeService = classeService;
        this.professeurService = professeurService;
    }

    /**
     * Page principale : liste de toutes les classes
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("classes", classeService.findAll());
        return "classes/index";
    }

    /**
     * Affiche le formulaire de création d'une nouvelle classe
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("classe", new Classe()); // objet vide pour le formulaire
        model.addAttribute("professeurs", professeurService.findAll()); // liste déroulante
        return "classes/form";
    }

    /**
     * Traitement du formulaire de création
     */
    @PostMapping("/new")
    public String create(@ModelAttribute Classe classe) {
        classeService.save(classe);
        return "redirect:/classes/";
    }

    /**
     * Affiche le détail d'une classe
     */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.findById(id));
        return "classes/detail";
    }

    /**
     * Formulaire de modification d'une classe existante
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.findById(id));
        model.addAttribute("professeurs", professeurService.findAll());
        return "classes/form";
    }

    /**
     * Traitement de la modification
     */
    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Classe classe) {
        classeService.save(classe);
        return "redirect:/classes/";
    }

    /**
     * Suppression d'une classe
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        classeService.deleteById(id);
        return "redirect:/classes/";
    }
}
