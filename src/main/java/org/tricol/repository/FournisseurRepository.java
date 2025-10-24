package org.tricol.repository;

import org.tricol.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    boolean existsByIce(String ice);

    boolean existsByEmail(String email);

    List<Fournisseur> findAllByOrderBySocieteAsc();

    List<Fournisseur> findAllByOrderBySocieteDesc();

}
