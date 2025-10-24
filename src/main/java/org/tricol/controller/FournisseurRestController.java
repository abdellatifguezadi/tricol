package org.tricol.controller;

import org.tricol.model.Fournisseur;
import org.tricol.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurRestController {

    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurRestController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs(
            @RequestParam(name = "sort", required = false) String sort) {

        List<Fournisseur> fournisseurs;

        if ("asc".equalsIgnoreCase(sort)) {
            fournisseurs = fournisseurService.getFournisseursTriesParNom(true);
        } else if ("desc".equalsIgnoreCase(sort)) {
            fournisseurs = fournisseurService.getFournisseursTriesParNom(false);
        } else {
            fournisseurs = fournisseurService.getAllFournisseurs();
        }

        return ResponseEntity.ok(fournisseurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(name = "id") Long id) {
        return fournisseurService.getFournisseurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> ajouterFournisseur(@Valid @RequestBody Fournisseur fournisseur) {
        try {
            Fournisseur nouveauFournisseur = fournisseurService.ajouterFournisseur(fournisseur);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauFournisseur);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifierFournisseur(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody Fournisseur fournisseur) {
        try {
            Fournisseur fournisseurModifie = fournisseurService.modifierFournisseur(id, fournisseur);
            return ResponseEntity.ok(fournisseurModifie);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerFournisseur(@PathVariable(name = "id") Long id) {
        try {
            fournisseurService.supprimerFournisseur(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Fournisseur supprimé avec succès");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
