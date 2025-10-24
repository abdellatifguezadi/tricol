package org.tricol.service;

import org.tricol.model.Fournisseur;
import org.tricol.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public Fournisseur ajouterFournisseur(Fournisseur fournisseur) {
        if (fournisseurRepository.existsByIce(fournisseur.getIce())) {
            throw new IllegalArgumentException("Un fournisseur avec cet ICE existe déjà");
        }

        if (fournisseurRepository.existsByEmail(fournisseur.getEmail())) {
            throw new IllegalArgumentException("Un fournisseur avec cet email existe déjà");
        }

        return fournisseurRepository.save(fournisseur);
    }

    public Fournisseur modifierFournisseur(Long id, Fournisseur fournisseur) {
        Fournisseur existant = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fournisseur non trouvé avec l'id: " + id));

        if (fournisseur.getIce() != null) {
            if (!fournisseur.getIce().equals(existant.getIce()) &&
                    fournisseurRepository.existsByIce(fournisseur.getIce())) {
                throw new IllegalArgumentException("Un fournisseur avec cet ICE existe déjà");
            }
            existant.setIce(fournisseur.getIce());
        }

        if (fournisseur.getEmail() != null) {
            if (!fournisseur.getEmail().equals(existant.getEmail()) &&
                    fournisseurRepository.existsByEmail(fournisseur.getEmail())) {
                throw new IllegalArgumentException("Un fournisseur avec cet email existe déjà");
            }
            existant.setEmail(fournisseur.getEmail());
        }

        if (fournisseur.getSociete() != null) existant.setSociete(fournisseur.getSociete());
        if (fournisseur.getAdresse() != null) existant.setAdresse(fournisseur.getAdresse());
        if (fournisseur.getContact() != null) existant.setContact(fournisseur.getContact());
        if (fournisseur.getTelephone() != null) existant.setTelephone(fournisseur.getTelephone());
        if (fournisseur.getVille() != null) existant.setVille(fournisseur.getVille());

        return fournisseurRepository.save(existant);
    }

    @Override
    public void supprimerFournisseur(Long id) {
        if (!fournisseurRepository.existsById(id)) {
            throw new IllegalArgumentException("Fournisseur non trouvé avec l'id: " + id);
        }
        fournisseurRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Fournisseur> getFournisseurById(Long id) {
        return fournisseurRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fournisseur> getFournisseursTriesParNom(boolean ascending) {
        return ascending ?
                fournisseurRepository.findAllByOrderBySocieteAsc() :
                fournisseurRepository.findAllByOrderBySocieteDesc();
    }


}
