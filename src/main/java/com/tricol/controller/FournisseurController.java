package com.tricol.controller;

import com.tricol.model.Fournisseur;
import com.tricol.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;


    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs(
            @RequestParam(value = "sort", required = false) String sort) {
        List<Fournisseur> fournisseurs;
        if (sort != null && !sort.isBlank()) {
            String normalized = sort.trim().toLowerCase();
            if ("asc".equals(normalized)) {
                fournisseurs = fournisseurService.getFournisseursTriesParNom(true);
            } else if ("desc".equals(normalized)) {
                fournisseurs = fournisseurService.getFournisseursTriesParNom(false);
            } else {
                fournisseurs = fournisseurService.getAllFournisseurs();
            }
        } else {
            fournisseurs = fournisseurService.getAllFournisseurs();
        }

        if (fournisseurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fournisseurs);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable("id") long id) {
        Optional<Fournisseur> optionalFournisseur = fournisseurService.getFournisseurById(id);
        return optionalFournisseur
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@RequestBody Fournisseur fournisseur,
                                                         @PathVariable("id") Long id) {
        Fournisseur updated = fournisseurService.updateFournisseur(fournisseur, id);
        if (updated != null) {
            return ResponseEntity.ok(updated); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }


    @PostMapping
    public ResponseEntity<Fournisseur> addFournisseur(@RequestBody Fournisseur fournisseur) {
        Fournisseur created = fournisseurService.addFournisseur(fournisseur);
        return ResponseEntity.status(201).body(created); 
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFournisseur(@PathVariable("id") Long id) {
        boolean deleted = fournisseurService.deleteFournisseur(id);
        if (deleted) {
            return ResponseEntity.ok("Fournisseur supprimé avec succès");
        } else {
            return ResponseEntity.status(404).body("Fournisseur introuvable");
        }
    }
}
