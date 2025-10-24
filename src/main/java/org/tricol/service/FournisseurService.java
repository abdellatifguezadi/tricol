package org.tricol.service;

import org.tricol.model.Fournisseur;
import java.util.List;
import java.util.Optional;

public interface FournisseurService {

    Fournisseur ajouterFournisseur(Fournisseur fournisseur);

    Fournisseur modifierFournisseur(Long id, Fournisseur fournisseur);

    void supprimerFournisseur(Long id);

    Optional<Fournisseur> getFournisseurById(Long id);

    List<Fournisseur> getAllFournisseurs();

    List<Fournisseur> getFournisseursTriesParNom(boolean ascending);

}