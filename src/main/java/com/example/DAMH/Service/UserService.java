package com.example.DAMH.Service;


import com.example.DAMH.Exception.CumtomException;
import com.example.DAMH.Model.CustomerUserDetails;
import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public IUserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User saveUser(User user){
        return userRepository.save(user);}

    public void deleteUser(Long id){
        userRepository.deleteById((id));
    }

    public void updateResetPasswordToken (String token, String email) throws CumtomException {
        User user = userRepository.getUserByEmail(email);
        if(user != null) {
            user.setTokenforgotpassword(token);
            user.setTimeexprired(null);
            userRepository.save(user);
        }else {
            throw new CumtomException(" Khong ton tai user co email" + email);
        }
    }

    public User getUserByTokenforgotpassWord (String token) {
        return userRepository.getUserByTokenforgotpassword(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodeedPassword);

        user.setTokenforgotpassword(null);
        userRepository.save(user);
    }

    public User getActiveUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomerUserDetails) {
                CustomerUserDetails customerUserDetails = (CustomerUserDetails) principal;
                return customerUserDetails.getUser();
            }
        }
        return null;
    }
}
