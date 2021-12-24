package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.dto.PetDto;
import edu.kpi.iasa.mmsa.petshop.model.Pet;
import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import edu.kpi.iasa.mmsa.petshop.service.PetService;
import edu.kpi.iasa.mmsa.petshop.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService){
        this.petService = petService;
    }
    @GetMapping(value="/pet")
    public ResponseEntity<List<Pet>> getPets(){
        return ResponseEntity.ok(petService.getPets());
    }
    @PostMapping(value = "/pet")
    public ResponseEntity<Pet> postPet(@Valid @RequestBody PetDto newPet) {
        return ResponseEntity.ok(petService.savePet(newPet));
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PutMapping(value = "/pet/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody PetDto updatedPet) {
        return ResponseEntity.ok(petService.updatePetById(id, updatedPet));
    }

    @DeleteMapping(value = "/pet/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.deletePetById(id));
    }
}
