
package com.tricol.controller;


import com.tricol.model.Fournisseur;
import com.tricol.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping

    public List<Fournisseur> getAllFournisseus(){
        return fournisseurService.getAllFournisseus();
    }
    @GetMapping("/{id}")
    public Fournisseur  getFournisseurById(@PathVariable("id") long id){
        Optional<Fournisseur> optionalFournisseur= fournisseurService.getFournisseurById(id);
        if (optionalFournisseur.isPresent()){
            return optionalFournisseur.get();
        }else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public  Fournisseur  updateFournisseur(@RequestBody Fournisseur fournisseur,@PathVariable("id") Long id){
        return fournisseurService.updateFournisseur(fournisseur,id);
    }


    @PostMapping
    public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur){
        return  fournisseurService.addFournisseur(fournisseur);
    }



    @DeleteMapping("/{id}")
    public  String deleteFournisseur(@PathVariable("id") Long id){
        return fournisseurService.deleteFournisseur(id);

    }


}