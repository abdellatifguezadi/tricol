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
            supplier.setId(id);
            supplierRepository.update(supplier);
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
