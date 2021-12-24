package edu.kpi.iasa.mmsa.petshop.repository;

import edu.kpi.iasa.mmsa.petshop.model.Role;
import edu.kpi.iasa.mmsa.petshop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findById(Long id);
}
