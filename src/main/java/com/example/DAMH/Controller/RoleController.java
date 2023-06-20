package com.example.DAMH.Controller;



import com.example.DAMH.Model.Role;
import com.example.DAMH.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String viewAllRole(Model model) {
        List<Role> listRole = roleService.getAllRoles();
        model.addAttribute("roles",listRole);
        return "role/list";
    }
    @GetMapping("/add")
    public String showNewRolePage(Model model){
        Role role = new Role();
        model.addAttribute("role",role);
        return "role/add";
    }
    @PostMapping("/add")
    public String saveRole(@ModelAttribute Role role){
        roleService.addRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String showEditRolePage(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id);

        if(role == null){
            return "notfound";
        }else {
            model.addAttribute("role",role);
            return "role/edit";
        }
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute Role role){
        roleService.addRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id){
        Role role = roleService.getRoleById(id);
        if(role == null){
            return "notfound";
        }else {
            roleService.deleteRole(id);
            return "redirect:/roles";
        }
    }
}
