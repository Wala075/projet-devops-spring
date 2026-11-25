package tn.esprit.wesleti_walaeddine_4.Services;

import tn.esprit.wesleti_walaeddine_4.Domaine.Reservation;

import java.util.List;

public interface IReservationService {

    Reservation create(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    Reservation update(Long id, Reservation reservation);

    void deleteById(Long id);
}
