package com.test.salt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.test.salt.domain.Konsumen;
import com.test.salt.repository.KonsumenRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private final KonsumenRepository konsumenRepository;

    public ViewController(KonsumenRepository konsumenRepository) {
        this.konsumenRepository = konsumenRepository;
    }

    @GetMapping("/konsumen-list")
    public String showKonsumenList(Model model) {
        List<Konsumen> konsumenList = konsumenRepository.findAll();
        model.addAttribute("konsumenList", konsumenList);
        return "konsumen-list"; // Thymeleaf template name for displaying the data table
    }

    @GetMapping("/add-konsumen")
    public String showAddKonsumenForm(Model model) {
        model.addAttribute("konsumen", new Konsumen());
        return "add-konsumen"; // Thymeleaf template name for the add data form
    }

    @PostMapping("/add-konsumen")
    public String addKonsumen(@ModelAttribute Konsumen konsumen, @RequestParam(value = "status", required = false) boolean status) {
        if (status) {
            konsumen.setStatus("Aktif");
        } else {
            konsumen.setStatus("Non-Aktif");
        }
        konsumenRepository.save(konsumen);
        return "redirect:/konsumen-list";
    }

    @PostMapping("/edit-konsumen/{id}")
    public String editKonsumen(@PathVariable Long id, @ModelAttribute Konsumen konsumen, @RequestParam(value = "status", required = false) boolean status) {
        // Retrieve the existing Konsumen, update its properties, and save it
        Optional<Konsumen> existingKonsumen = konsumenRepository.findById(id);
        if (existingKonsumen.isPresent()) {
            Konsumen oldKonsumen = existingKonsumen.get();
            oldKonsumen.setNama(konsumen.getNama());
            oldKonsumen.setAlamat(konsumen.getAlamat());
            oldKonsumen.setKota(konsumen.getKota());
            oldKonsumen.setProvinsi(konsumen.getProvinsi());
            if (status) {
                oldKonsumen.setStatus("Aktif"); // Update the status of oldKonsumen
            } else {
                oldKonsumen.setStatus("Non-Aktif"); // Update the status of oldKonsumen
            }
            konsumenRepository.save(oldKonsumen);
        }
        return "redirect:/konsumen-list"; // Redirect to the list page after editing
    }



    @GetMapping("/edit-konsumen/{id}")
    public String showEditKonsumenForm(@PathVariable Long id, Model model) {
        Konsumen konsumen = konsumenRepository.findById(id).orElse(null);
        model.addAttribute("konsumen", konsumen);
        return "edit-konsumen"; // Thymeleaf template name for the edit data form
    }


    @GetMapping("/delete-konsumen/{id}")
    public String deleteKonsumen(@PathVariable Long id) {
        konsumenRepository.deleteById(id);
        return "redirect:/konsumen-list"; // Redirect to the data table page after deleting data
    }
}
