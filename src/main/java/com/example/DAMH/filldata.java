package com.example.DAMH;

import com.example.DAMH.Model.Role;
import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.ICategoryRepository;
import com.example.DAMH.Repository.IRoleRepository;
import com.example.DAMH.Repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ComponentScan("com.example.DAMH.Security")
public class filldata {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    public IUserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Test
    public void testCreateUser(){
        Role roleUser = new Role();
        roleUser.setName("USER");
        roleRepository.save(roleUser);

        Role roleCreate = new Role();
        roleCreate.setName("CREATER");
        roleRepository.save(roleCreate);

        Role roleEditor = new Role();
        roleEditor.setName("EDITOR");
        roleRepository.save(roleEditor);

        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleAdmin);

        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail("user1@gmail.com");
        user1.setEnabled(true);
        user1.setAddress("diachi");
        user1.setPhonenumber("0123456789");
        user1.setPassword(passwordEncoder.encode("123456"));
        user1.addRole(roleUser);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail("user2@gmail.com");
        user2.setEnabled(true);
        user2.setAddress("diachi");
        user2.setPhonenumber("0123456789");
        user2.setPassword(passwordEncoder.encode("123456"));
        user2.addRole(roleUser);
        userRepository.save(user2);

        User creater1 = new User();
        creater1.setUsername("creater1");
        creater1.setEmail("creater1@gmail.com");
        creater1.setEnabled(true);
        creater1.setAddress("diachi");
        creater1.setPhonenumber("0123456789");
        creater1.setPassword(passwordEncoder.encode("123456"));
        creater1.addRole(roleCreate);
        userRepository.save(creater1);

        User creater2 = new User();
        creater2.setUsername("creater2");
        creater2.setEmail("creater1@gmail.com");
        creater2.setEnabled(true);
        creater2.setAddress("diachi");
        creater2.setPhonenumber("0123456789");
        creater2.setPassword(passwordEncoder.encode("123456"));
        creater2.addRole(roleCreate);
        userRepository.save(creater2);

        User editor1 = new User();
        editor1.setUsername("editor1");
        editor1.setEmail("editor1@gmail.com");
        editor1.setEnabled(true);
        editor1.setAddress("diachi");
        editor1.setPhonenumber("0123456789");
        editor1.setPassword(passwordEncoder.encode("123456"));
        editor1.addRole(roleEditor);
        userRepository.save(editor1);

        User editor2 = new User();
        editor2.setUsername("editor2");
        editor2.setEmail("editor2@gmail.com");
        editor2.setEnabled(true);
        editor2.setAddress("diachi");
        editor2.setPhonenumber("0123456789");
        editor2.setPassword(passwordEncoder.encode("123456"));
        editor2.addRole(roleEditor);
        userRepository.save(editor2);

        User admin1 = new User();
        admin1.setUsername("admin1");
        admin1.setEmail("admin1@gmail.com");
        admin1.setEnabled(true);
        admin1.setAddress("diachi");
        admin1.setPhonenumber("0123456789");
        admin1.setPassword(passwordEncoder.encode("123456"));
        admin1.addRole(roleAdmin);
        userRepository.save(admin1);

        User admin2 = new User();
        admin2.setUsername("admin2");
        admin2.setEmail("admin2@gmail.com");
        admin2.setEnabled(true);
        admin2.setAddress("diachi");
        admin2.setPhonenumber("0123456789");
        admin2.setPassword(passwordEncoder.encode("123456"));
        admin2.addRole(roleAdmin);
        userRepository.save(admin2);
    }
}
