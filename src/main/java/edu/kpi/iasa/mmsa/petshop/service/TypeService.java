package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.TypeNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Status;
import edu.kpi.iasa.mmsa.petshop.model.Type;
import edu.kpi.iasa.mmsa.petshop.repository.StatusRepository;
import edu.kpi.iasa.mmsa.petshop.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;


    public TypeService (TypeRepository typeRepository){
        this.typeRepository=typeRepository;
    }

    public List<Type> getTypes(){
        return typeRepository.findAll();
    }

    public Type saveType(Type newType) {
        return typeRepository.save(newType);
    }

    public Type getTypeById(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        if (type.isPresent()) {
            return type.get();
        }
        throw new TypeNotFoundException();
    }

    public Type updateTypeById(Long id, Type updatedType) {
        Optional<Type> type = typeRepository.findById(id);
        if (type.isPresent()) {;
            Type oldType = type.get();
            updateType(oldType, updatedType);
            return typeRepository.save(oldType);

        }
        throw new TypeNotFoundException();
    }

    private void updateType(Type oldType, Type updatedType) {
        oldType.setName(updatedType.getName());
    }

    public String deleteTypeById(Long id) {
        if (typeRepository.findById(id).isPresent()) {
            typeRepository.deleteById(id);
            return "Type was successfully deleted";
        }
        throw new TypeNotFoundException();
    }
}
