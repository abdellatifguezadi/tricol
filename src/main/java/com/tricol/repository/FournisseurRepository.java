package com.tricol.repository;

import com.tricol.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {


}