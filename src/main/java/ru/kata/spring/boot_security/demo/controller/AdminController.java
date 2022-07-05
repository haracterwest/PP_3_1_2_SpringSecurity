package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping()
//    public String index(Model model, Principal principal) {
//        List<User> users = userService.findAll();
//        String username = principal.getName();
//        User user = userService.getUserByName(username);
//        model.addAttribute("users", users);
//        model.addAttribute("user", user);
//        model.addAttribute("allroles", roleService.getRoleSet());
//        return "admin";
//    }

    @GetMapping("/admin")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }



    @GetMapping("/")
    public String createUserForm(User user){
        return "admin";
    }

//    @PostMapping("/")
//    public String createUser(User user){
//        userService.saveUser(user);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/user-delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteById(id);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/user-update/{id}")
//    public String updateUseForm(@PathVariable("id") Long id, Model model){
//        User user = userService.findById(id);
//        model.addAttribute("user", user);
//        return "admin";
//    }
//
//    @PostMapping("/user-update")
//    public String updateUser(User user){
//        userService.saveUser(user);
//        return "redirect:/user";
//    }
}
