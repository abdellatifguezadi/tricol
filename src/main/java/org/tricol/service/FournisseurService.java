package org.tricol.service;


import org.tricol.model.Fournisseur;

import java.util.List;

public interface FournisseurService {
    List<Fournisseur> findAll();
    Fournisseur findById(Long id);
    void save(Fournisseur supplier);
    void update(Long id, Fournisseur supplier);
    void delete(Long id);
    long count();
}
