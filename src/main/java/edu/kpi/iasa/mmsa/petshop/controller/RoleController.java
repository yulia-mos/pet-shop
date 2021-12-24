package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.model.Role;
import edu.kpi.iasa.mmsa.petshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Controller
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    @GetMapping(value="/role")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleService.getRoles());
    }
    @PostMapping(value = "/role")
    public ResponseEntity<Role> postRole(@Valid @RequestBody Role newRole) {
        return ResponseEntity.ok(roleService.saveRole(newRole));
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @Valid @RequestBody Role updatedRole) {
        return ResponseEntity.ok(roleService.updateRoleById(id, updatedRole));
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteRoleById(id));
    }
}
