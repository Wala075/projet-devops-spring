package tn.esprit.wesleti_walaeddine_4.Services;

import tn.esprit.wesleti_walaeddine_4.Domaine.Employe;

import java.util.List;

public interface IEmployeService {

    Employe create(Employe employe);

    Employe findById(Long id);

    List<Employe> findAll();

    Employe update(Long id, Employe employe);

    void deleteById(Long id);
}
