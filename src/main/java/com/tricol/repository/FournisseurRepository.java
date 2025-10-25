package com.tricol.repository;

import com.tricol.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    // Derived query methods to return sorted results by 'societe'
    List<Fournisseur> findAllByOrderBySocieteAsc();
    List<Fournisseur> findAllByOrderBySocieteDesc();

}