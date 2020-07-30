package com.crud.traveller.service;

import com.crud.traveller.entity.User;
import com.crud.traveller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserDbService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll ();
    }
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById (userId);
    }
    public User saveUser(final User user){
        return userRepository.save (user);
    }
    public void deleteUser(Long userId){
        userRepository.deleteById (userId);
    }
    public boolean isExist(Long userId){
        return userRepository.existsById (userId);
    }
}