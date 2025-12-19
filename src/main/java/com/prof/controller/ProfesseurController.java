package com.prof.controller;

import com.prof.entity.Professeur;
import com.prof.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professeurs")
public class ProfesseurController {

    private final ProfesseurService service;

    public ProfesseurController(ProfesseurService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("professeurs", service.findAll()); // trié alphabétiquement
        return "professeurs/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {

        Professeur professeur = service.findById(id);
        if (professeur == null) {
            return "redirect:/professeurs/";
        }

        int totalEleves = service.calculerTotalEleves(id);
        double moyenneEleves = service.calculerMoyenneEleves(id);

        model.addAttribute("professeur", professeur);
        model.addAttribute("totalEleves", totalEleves);
        model.addAttribute("moyenneEleves", moyenneEleves);

        return "professeurs/detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("professeur", new Professeur());
        return "professeurs/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Professeur professeur) {
        service.save(professeur);
        return "redirect:/professeurs/";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("professeur", service.findById(id));
        return "professeurs/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Professeur professeur) {
        service.save(professeur);
        return "redirect:/professeurs/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/professeurs/";
    }
}
