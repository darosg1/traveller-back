package com.crud.traveller.service;

import com.crud.traveller.entity.Excursion;
import com.crud.traveller.entity.User;
import com.crud.traveller.exception.ExcursionNotFoundException;
import com.crud.traveller.exception.UserNotFoundException;
import com.crud.traveller.repository.ExcursionRepository;
import com.crud.traveller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserDbService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExcursionRepository excursionRepository;

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
    public void setExcursionToUser(Long userId, Long excursionId) throws UserNotFoundException, ExcursionNotFoundException {
        User userExcursion = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Excursion excursionToUser = excursionRepository.findById(excursionId).orElseThrow(ExcursionNotFoundException::new);
        userExcursion.getExcursion ().add(excursionToUser);
        excursionToUser.setUser(userExcursion);
        userRepository.save(userExcursion);
        excursionRepository.save(excursionToUser);
    }

}