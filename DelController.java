package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DelController {

    private final DelService service;

    public DelController(DelService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String redirectToPaamelding() {
        return "redirect:/paamelding";
    }

    @GetMapping("/paamelding")
    public String showForm(Model model) {
        if (!model.containsAttribute("deltager")) {
            model.addAttribute("deltager", new Deltager());
        }
        return "paamelding_med_melding";
    }

    @PostMapping("/paamelding")
    public String processForm(@ModelAttribute("deltager") Deltager deltager, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (!service.addDeltager(deltager, result)) {
            model.addAttribute("deltager", deltager);
            model.addAttribute("feilMelding", "Validering mislyktes eller deltager allerede registrert!");
            return "paamelding_med_melding";
        }
        redirectAttributes.addFlashAttribute("deltager", deltager);
        return "redirect:/paameldt";
    }


    @GetMapping("/paameldt")
    public String showBekreftelse(Model model) {
        if (!model.containsAttribute("deltager")) {
            return "redirect:/paamelding";
        }
        return "paameldt";
    }

    @GetMapping("/deltagerliste")
    public String showDeltagerliste(Model model) {
        model.addAttribute("deltagere", service.getDeltagere());
        return "deltagerliste";
    }
}
