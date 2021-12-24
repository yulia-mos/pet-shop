package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import edu.kpi.iasa.mmsa.petshop.model.Type;
import edu.kpi.iasa.mmsa.petshop.service.ShelterService;
import edu.kpi.iasa.mmsa.petshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }
    @GetMapping(value="/type")
    public ResponseEntity<List<Type>> getTypes(){
        return ResponseEntity.ok(typeService.getTypes());
    }
    @PostMapping(value = "/type")
    public ResponseEntity<Type> postShelter(@Valid @RequestBody Type newType) {
        return ResponseEntity.ok(typeService.saveType(newType));
    }

    @GetMapping(value = "/type/{id}")
    public ResponseEntity<Type> getType(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.getTypeById(id));
    }

    @PutMapping(value = "/type/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @Valid @RequestBody Type updatedType) {
        return ResponseEntity.ok(typeService.updateTypeById(id, updatedType));
    }

    @DeleteMapping(value = "/type/{id}")
    public ResponseEntity<String> deleteType(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.deleteTypeById(id));
    }
}
