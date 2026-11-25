package tn.esprit.wesleti_walaeddine_4.Services;

import tn.esprit.wesleti_walaeddine_4.Domaine.Maintenance;
import tn.esprit.wesleti_walaeddine_4.WebController.MaintenanceDTO;

import java.util.List;

public interface IMaintenanceService {

    Maintenance create(Maintenance maintenance);

    MaintenanceDTO findById(Long id);

    List<Maintenance> findAll();

    Maintenance update(Long id, Maintenance maintenance);

    void deleteById(Long id);
}
