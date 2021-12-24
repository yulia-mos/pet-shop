package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Role;
import edu.kpi.iasa.mmsa.petshop.model.Status;
import edu.kpi.iasa.mmsa.petshop.repository.RoleRepository;
import edu.kpi.iasa.mmsa.petshop.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;


    public RoleService (RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role saveRole(Role newRole) {
        return roleRepository.save(newRole);
    }

    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        throw new RoleNotFoundException();
    }

    public Role updateRoleById(Long id, Role updatedRole) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {;
            Role oldRole = role.get();
            updateRole(oldRole, updatedRole);
            return roleRepository.save(oldRole);

        }
        throw new RoleNotFoundException();
    }

    private void updateRole(Role oldRole, Role updatedRole) {
        oldRole.setName(updatedRole.getName());
    }

    public String deleteRoleById(Long id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
            return "Role was successfully deleted";
        }
        throw new RoleNotFoundException();
    }

}
