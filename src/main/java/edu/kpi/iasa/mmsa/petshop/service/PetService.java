package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.dto.PetDto;
import edu.kpi.iasa.mmsa.petshop.exception.AccountNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.PetNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.ShelterNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.TypeNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Pet;
import edu.kpi.iasa.mmsa.petshop.repository.AccountRepository;
import edu.kpi.iasa.mmsa.petshop.repository.PetRepository;
import edu.kpi.iasa.mmsa.petshop.repository.ShelterRepository;
import edu.kpi.iasa.mmsa.petshop.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PetService {
    private final PetRepository petRepository;
    private final TypeRepository typeRepository;
    private final ShelterRepository shelterRepository;
    private final AccountRepository accountRepository;


    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    public Pet savePet(PetDto newPet) {
        Pet pet=Pet.builder()
                .type(typeRepository.findById(newPet.getTypeId()).orElseThrow(TypeNotFoundException::new))
                .name(newPet.getName())
                .description(newPet.getDescription())
                .price(newPet.getPrice())
                .seller(accountRepository.findById(newPet.getSellerId()).orElseThrow(AccountNotFoundException::new))
                .shelter(shelterRepository.findById(newPet.getShelterId()).orElseThrow(ShelterNotFoundException::new))
                .build();
        return petRepository.save(pet);
    }

    public Pet getPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return pet.get();
        }
        throw new PetNotFoundException();
    }

    public Pet updatePetById(Long id, PetDto updatedPet) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {;
            Pet oldPet = pet.get();
            updatePet(oldPet, updatedPet);
            return petRepository.save(oldPet);

        }
        throw new PetNotFoundException();
    }

    private void updatePet(Pet oldPet, PetDto updatedPet) {
        oldPet.setName(updatedPet.getName());
        oldPet.setDescription(updatedPet.getDescription());
        oldPet.setPrice(updatedPet.getPrice());
        oldPet.setShelter(shelterRepository.findById(updatedPet.getShelterId()).orElseThrow(ShelterNotFoundException::new));
        oldPet.setSeller(accountRepository.findById(updatedPet.getSellerId()).orElseThrow(AccountNotFoundException::new));
        oldPet.setType(typeRepository.findById(updatedPet.getTypeId()).orElseThrow(TypeNotFoundException::new));
    }

    public String deletePetById(Long id) {
        if (petRepository.findById(id).isPresent()) {
            petRepository.deleteById(id);
            return "Pet was successfully deleted";
        }
        throw new PetNotFoundException();
    }
}
