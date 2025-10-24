package com.tricol.service;

import com.tricol.model.Fournisseur;
import com.tricol.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseus() {
        return fournisseurRepository.findAll();
    }

    public Optional<Fournisseur> getFournisseurById( long id){
        return fournisseurRepository.findById(id);
    }

    public  Fournisseur  updateFournisseur( Fournisseur fournisseur,  Long id){
        Optional<Fournisseur> fournisseur1 = fournisseurRepository.findById(id);
        Fournisseur fournisseur_find = fournisseur1.orElseThrow(() ->
                new IllegalArgumentException("Fournisseur not found with id: " + id));

        if (fournisseur.getAdresse() != null) {
            fournisseur_find.setAdresse(fournisseur.getAdresse());
        }
        if (fournisseur.getContact() != null) {
            fournisseur_find.setContact(fournisseur.getContact());
        }
        if (fournisseur.getEmail() != null) {
            fournisseur_find.setEmail(fournisseur.getEmail());
        }
        if (fournisseur.getIce() != null) {
            fournisseur_find.setIce(fournisseur.getIce());
        }
        if (fournisseur.getSociete() != null) {
            fournisseur_find.setSociete(fournisseur.getSociete());
        }
        if (fournisseur.getTelephone() != null) {
            fournisseur_find.setTelephone(fournisseur.getTelephone());
        }
        if (fournisseur.getVille() != null) {
            fournisseur_find.setVille(fournisseur.getVille());
        }

        return fournisseurRepository.save(fournisseur_find);

    }

    public  String  deleteFournisseur( Long id){
        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(id);
        if(fournisseurOptional.isPresent()){
            fournisseurRepository.delete(fournisseurOptional.get());
            return "delete avec succes";
        }else {
            return "aucun fournisseur avec cet id";
        }

    }

    public  Fournisseur addFournisseur(Fournisseur fournisseur){
        return fournisseurRepository.save(fournisseur);
    }
}