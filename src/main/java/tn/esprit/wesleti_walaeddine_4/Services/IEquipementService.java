package tn.esprit.wesleti_walaeddine_4.Services;

import tn.esprit.wesleti_walaeddine_4.Domaine.Equipement;

import java.util.List;

public interface IEquipementService {

    Equipement create(Equipement equipement);

    Equipement findById(Long id);

    List<Equipement> findAll();

    Equipement update(Long id, Equipement equipement);

    void deleteById(Long id);
}
