package tn.esprit.wesleti_walaeddine_4.Services;

import tn.esprit.wesleti_walaeddine_4.Domaine.Contrat;

import java.util.List;

public interface IContratService {

    Contrat create(Contrat contrat);

    Contrat findById(Long id);

    List<Contrat> findAll();

    Contrat update(Long id, Contrat contrat);

    void deleteById(Long id);
}
