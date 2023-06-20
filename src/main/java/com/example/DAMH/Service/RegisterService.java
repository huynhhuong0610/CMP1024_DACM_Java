package com.example.DAMH.Service;

import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.IRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private IRegisterRepository registerRepository;

    public List<User> getAllRegister() {
        return registerRepository.findAll();
    }

    public User saveRegister(User user) {
        return registerRepository.save(user);
    }

    public void addRegister(User user) {
        registerRepository.save(user);
    }
}
