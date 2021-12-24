package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.dto.PurchaseDto;
import edu.kpi.iasa.mmsa.petshop.exception.AccountNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.PetNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.PurchaseNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Purchase;
import edu.kpi.iasa.mmsa.petshop.repository.AccountRepository;
import edu.kpi.iasa.mmsa.petshop.repository.PetRepository;
import edu.kpi.iasa.mmsa.petshop.repository.PurchaseRepository;
import edu.kpi.iasa.mmsa.petshop.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;
    private final StatusRepository statusRepository;


    public List<Purchase> getPurchases(){
        return purchaseRepository.findAll();
    }

    public Purchase savePurchase(PurchaseDto newPurchase) {
        Purchase purchase=Purchase.builder()
                .buyer(accountRepository.findById(newPurchase.getBuyerId()).orElseThrow(AccountNotFoundException::new))
                .dateTime(newPurchase.getDateTime())
                .pet(petRepository.findById(newPurchase.getPetId()).orElseThrow(PetNotFoundException::new))
                .status(statusRepository.findById(newPurchase.getStatusId()).orElseThrow(StatusNotFoundException::new))
                .build();
        return purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            return purchase.get();
        }
        throw new PurchaseNotFoundException();
    }

    public Purchase updatePurchaseById(Long id, PurchaseDto updatedPurchase) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {;
            Purchase oldPurchase = purchase.get();
            updatePurchase(oldPurchase, updatedPurchase);
            return purchaseRepository.save(oldPurchase);

        }
        throw new PurchaseNotFoundException();
    }

    private void updatePurchase(Purchase oldPurchase, PurchaseDto updatedPurchase) {
        oldPurchase.setBuyer(accountRepository.findById(updatedPurchase.getBuyerId()).orElseThrow(AccountNotFoundException::new));
        oldPurchase.setStatus(statusRepository.findById(updatedPurchase.getStatusId()).orElseThrow(StatusNotFoundException::new));
        oldPurchase.setPet(petRepository.findById(updatedPurchase.getPetId()).orElseThrow(PetNotFoundException::new));
        oldPurchase.setDateTime(updatedPurchase.getDateTime());
    }

    public String deletePurchaseById(Long id) {
        if (purchaseRepository.findById(id).isPresent()) {
            purchaseRepository.deleteById(id);
            return "Purchase was successfully deleted";
        }
        throw new PurchaseNotFoundException();
    }
}
