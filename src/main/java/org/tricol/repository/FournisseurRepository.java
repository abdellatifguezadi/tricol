package org.tricol.repository;


import org.tricol.model.Fournisseur;

import java.util.List;

public interface FournisseurRepository {
    List<Fournisseur> findAll();
    Fournisseur findById(Long id);
    void save(Fournisseur supplier);
    void update(Fournisseur supplier);
    void delete(Long id);
    long count();
}
