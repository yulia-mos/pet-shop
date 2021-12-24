package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.dto.PurchaseDto;
import edu.kpi.iasa.mmsa.petshop.model.Purchase;
import edu.kpi.iasa.mmsa.petshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }
    @GetMapping(value="/purchase")
    public ResponseEntity<List<Purchase>> getPurchases(){
        return ResponseEntity.ok(purchaseService.getPurchases());
    }
    @PostMapping(value = "/purchase")
    public ResponseEntity<Purchase> postPurchase(@RequestBody PurchaseDto newPurchase) {
        return ResponseEntity.ok(purchaseService.savePurchase(newPurchase));
    }

    @GetMapping(value = "/purchase/{id}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    @PutMapping(value = "/purchase/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @Valid @RequestBody PurchaseDto updatedPurchase) {
        return ResponseEntity.ok(purchaseService.updatePurchaseById(id, updatedPurchase));
    }

    @DeleteMapping(value = "/purchase/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.deletePurchaseById(id));
    }
}
