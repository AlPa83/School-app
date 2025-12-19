package com.prof.controller;

import com.prof.entity.Classe;
import com.prof.service.ClasseService;
import com.prof.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class ClasseController {

    private final ClasseService classeService;
    private final ProfesseurService professeurService;

    public ClasseController(ClasseService classeService, ProfesseurService professeurService) {
        this.classeService = classeService;
        this.professeurService = professeurService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("classes", classeService.findAll());
        return "classes/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("classe", new Classe());
        model.addAttribute("professeurs", professeurService.findAll());
        return "classes/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Classe classe) {
        classeService.save(classe);
        return "redirect:/classes/";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.findById(id));
        return "classes/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("classe", classeService.findById(id));
        model.addAttribute("professeurs", professeurService.findAll());
        return "classes/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute Classe classe) {
        classeService.save(classe);
        return "redirect:/classes/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        classeService.deleteById(id);
        return "redirect:/classes/";
    }
}