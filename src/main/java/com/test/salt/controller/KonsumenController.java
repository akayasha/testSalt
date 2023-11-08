package com.test.salt.controller;

import com.test.salt.domain.Konsumen;
import com.test.salt.repository.KonsumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/konsumen")
public class KonsumenController {

    @Autowired
    private KonsumenRepository konsumenRepository;

    @GetMapping
    public List<Konsumen> getAllKonsumen(){
        return konsumenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Konsumen> getKonsumenById(@PathVariable Long id){
        Optional<Konsumen> konsumen = konsumenRepository.findById(id);
        if (konsumen.isPresent()) {
            return ResponseEntity.ok(konsumen.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Konsumen> updateKonsumen(@PathVariable Long id, @RequestBody Konsumen updatedKonsumen) {
        Optional<Konsumen> existingKonsumen = konsumenRepository.findById(id);

        if (existingKonsumen.isPresent()) {
            Konsumen konsumen = existingKonsumen.get();
            konsumen.setNama(updatedKonsumen.getNama());
            konsumen.setAlamat(updatedKonsumen.getAlamat());
            konsumen.setKota(updatedKonsumen.getKota());
            konsumen.setProvinsi(updatedKonsumen.getProvinsi());
            // Update other properties as needed

            // Save the updated Konsumen entity
            Konsumen updatedEntity = konsumenRepository.save(konsumen);

            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Konsumen createKonsumen(@RequestBody Konsumen konsumen){
        return konsumenRepository.save(konsumen);
    }

    @DeleteMapping("/{id}")
    public void deleteKonsumen(@PathVariable Long id){
        konsumenRepository.deleteById(id);
    }


}
