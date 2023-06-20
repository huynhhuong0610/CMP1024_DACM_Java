package com.example.DAMH.Controller;



import com.example.DAMH.Model.Product;
import com.example.DAMH.Model.Role;
import com.example.DAMH.Model.User;
import com.example.DAMH.Service.PasswordService;
import com.example.DAMH.Service.RoleService;
import com.example.DAMH.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public String viewAllUser(Model model) {
        List<User> listUser = userService.getAllUsers();
        model.addAttribute("users",listUser);
        return "user/list";
    }
    /*@GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "user/list";
    }*/


    @GetMapping("/add")
    public String showNewUserPage(Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "user/add";
    }

    /*@PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user)
            throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userService.addUser(user);
        return "redirect:/users";
    }*/
    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile image)
            throws IOException {
        user.setEnabled(true);
        User savedUser = userService.saveUser(user);

        if (!image.isEmpty()) {
            Path imagePath = Paths.get("static/images");
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            Path file = imagePath.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            user.setPhotourl(fileName);
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if(user == null){
            return "notfound";
        }else {
            String pass = null;
            model.addAttribute("user",user);
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("pass", pass);
            return "user/edit";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile image, @RequestParam("pass") String pass)
            throws IOException {
        User user2 = userService.getUserById(user.getId());
        if (!image.isEmpty()) {
            Path imagePath = Paths.get("static/image");
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            Path file = imagePath.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            user2.setPhotourl(fileName);
        } else {
            if (!pass.isEmpty()) {
                user2.setPassword(passwordEncoder.encode(pass));
            }
            user2.setRoles(user.getRoles());
            user2.setUsername(user.getUsername());
            user2.setEmail(user.getEmail());

        }
        userService.addUser(user2);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        if(user == null){
            return "notfound";
        }else {
            userService.deleteUser(id);
            return "redirect:/users";
        }
    }

    @GetMapping("/password/{id}")
    public String editpassword(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if(user == null) {
            return "not-found";
        }else {
            model.addAttribute("user", user);
            return "user/password";
        }
    }
    @PostMapping("/password")
    public String savePassword(@ModelAttribute("user") User user, @RequestParam("newPassword") String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        passwordService.savePassword(user);
        return "redirect:/users";
    }

}
