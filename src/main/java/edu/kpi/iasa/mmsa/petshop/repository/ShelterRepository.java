package edu.kpi.iasa.mmsa.petshop.repository;

import edu.kpi.iasa.mmsa.petshop.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findById(Long id);
}
