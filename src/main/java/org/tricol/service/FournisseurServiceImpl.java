package org.tricol.service;

import org.tricol.model.Fournisseur;
import org.tricol.repository.FournisseurRepository;

import java.util.List;

public class FournisseurServiceImpl implements FournisseurService {


    private FournisseurRepository supplierRepository;

    public FournisseurServiceImpl(FournisseurRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Fournisseur> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Fournisseur findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void save(Fournisseur supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void update(Long id, Fournisseur supplier) {
        Fournisseur existing = supplierRepository.findById(id);
        if (existing != null) {
            // Copy only non-null fields from the incoming object to the existing entity (partial update)
            if (supplier.getSociete() != null) existing.setSociete(supplier.getSociete());
            if (supplier.getAdresse() != null) existing.setAdresse(supplier.getAdresse());
            if (supplier.getContact() != null) existing.setContact(supplier.getContact());
            if (supplier.getTelephone() != null) existing.setTelephone(supplier.getTelephone());
            if (supplier.getEmail() != null) existing.setEmail(supplier.getEmail());
            if (supplier.getVille() != null) existing.setVille(supplier.getVille());
            if (supplier.getIce() != null) existing.setIce(supplier.getIce());

            supplierRepository.update(existing);
        }
    }

    @Override
    public void delete(Long id) {
        supplierRepository.delete(id);
    }

    @Override
    public long count(){
        return supplierRepository.count();
    }

}
