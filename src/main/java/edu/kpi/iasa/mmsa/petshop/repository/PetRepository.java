package edu.kpi.iasa.mmsa.petshop.repository;

import edu.kpi.iasa.mmsa.petshop.model.Pet;
import edu.kpi.iasa.mmsa.petshop.model.Role;
import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(Long id);

    List<Pet> findAllByShelter(Shelter shelter);
}
