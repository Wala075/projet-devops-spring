package tn.esprit.wesleti_walaeddine_4.Services.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.wesleti_walaeddine_4.Domaine.Paiement;
import tn.esprit.wesleti_walaeddine_4.Repository.IPaiementRepository;
import tn.esprit.wesleti_walaeddine_4.Services.IPaiementService;
import tn.esprit.wesleti_walaeddine_4.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}
