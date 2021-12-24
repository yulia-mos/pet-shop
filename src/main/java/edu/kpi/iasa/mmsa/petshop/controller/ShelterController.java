package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.model.Role;
import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import edu.kpi.iasa.mmsa.petshop.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShelterController {
    private final ShelterService shelterService;

    @Autowired
    public ShelterController(ShelterService shelterService){
        this.shelterService = shelterService;
    }
    @GetMapping(value="/shelter")
    public ResponseEntity<List<Shelter>> getShelters(){
        return ResponseEntity.ok(shelterService.getShelters());
    }
    @PostMapping(value = "/shelter")
    public ResponseEntity<Shelter> postShelter(@Valid @RequestBody Shelter newShelter) {
        return ResponseEntity.ok(shelterService.saveShelter(newShelter));
    }

    @GetMapping(value = "/shelter/{id}")
    public ResponseEntity<Shelter> getShelter(@PathVariable Long id) {
        return ResponseEntity.ok(shelterService.getShelterById(id));
    }

    @PutMapping(value = "/shelter/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @Valid @RequestBody Shelter updatedShelter) {
        return ResponseEntity.ok(shelterService.updateShelterById(id, updatedShelter));
    }

    @DeleteMapping(value = "/shelter/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable Long id) {
        return ResponseEntity.ok(shelterService.deleteShelterById(id));
    }
}
