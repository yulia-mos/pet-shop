package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.exception.PurchaseNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.ShelterNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import edu.kpi.iasa.mmsa.petshop.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;



    public ShelterService(ShelterRepository shelterRepository){
        this.shelterRepository =shelterRepository;
    }

    public List<Shelter> getShelters(){
        return shelterRepository.findAll();
    }

    public Shelter saveShelter(Shelter newShelter) {
        return shelterRepository.save(newShelter);
    }

    public Shelter getShelterById(Long id) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isPresent()) {
            return shelter.get();
        }
        throw new ShelterNotFoundException();
    }

    public Shelter updateShelterById(Long id, Shelter updatedShelter) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isPresent()) {;
            Shelter oldShelter = shelter.get();
            updateShelter(oldShelter, updatedShelter);
            return shelterRepository.save(oldShelter);

        }
        throw new ShelterNotFoundException();
    }

    private void updateShelter(Shelter oldShelter, Shelter updatedShelter) {
        oldShelter.setName(updatedShelter.getName());
        oldShelter.setDescription(updatedShelter.getDescription());
    }

    public String deleteShelterById(Long id) {
        if (shelterRepository.findById(id).isPresent()) {
            shelterRepository.deleteById(id);
            return "Shelter was successfully deleted";
        }
        throw new ShelterNotFoundException();
    }
}
