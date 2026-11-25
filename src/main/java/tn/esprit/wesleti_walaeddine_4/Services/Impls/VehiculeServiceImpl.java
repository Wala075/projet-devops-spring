package tn.esprit.wesleti_walaeddine_4.Services.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.wesleti_walaeddine_4.Domaine.Agence;
import tn.esprit.wesleti_walaeddine_4.Domaine.Equipement;
import tn.esprit.wesleti_walaeddine_4.Domaine.Vehicule;
import tn.esprit.wesleti_walaeddine_4.Repository.IAgenceRepository;
import tn.esprit.wesleti_walaeddine_4.Repository.IEquipementRepository;
import tn.esprit.wesleti_walaeddine_4.Repository.IVehiculeRepository;
import tn.esprit.wesleti_walaeddine_4.Services.IVehiculeService;
import tn.esprit.wesleti_walaeddine_4.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existant = findById(id);
        verifier(vehicule);
        // recopie des attributs modifiables, sans toucher à l'identifiant ni aux associations
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule assignToAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findById(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
        // Vehicule est le cote proprietaire (FK agence_id_agence) : c'est lui qu'on modifie puis qu'on sauvegarde
        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    // Règle de gestion : appelée à la création et à la modification
    private void verifier(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().signum() < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }
    @Override
    public Vehicule assignToEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().add(equipement);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule unassignToAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = findById(idVehicule);
        if (!agenceRepository.existsById(idAgence)) {
            throw new ResourceNotFoundException("Agence", idAgence);
        }
        // Regle : on ne retire un vehicule que de l'agence a laquelle il est reellement affecte
        if (vehicule.getAgence() == null || !idAgence.equals(vehicule.getAgence().getIdAgence())) {
            throw new IllegalArgumentException("Le vehicule " + idVehicule + " n'est pas affecte a l'agence " + idAgence);
        }
        vehicule.setAgence(null);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule unassignToEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().remove(equipement);
        return vehiculeRepository.save(vehicule);
    }


}
