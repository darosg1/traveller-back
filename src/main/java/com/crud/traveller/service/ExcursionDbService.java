package com.crud.traveller.service;

import com.crud.traveller.entity.Excursion;
import com.crud.traveller.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcursionDbService{
    @Autowired
    ExcursionRepository excursionRepository;

    public List<Excursion> findAllExcursion(){
        return excursionRepository.findAll ();
    }
    public Optional<Excursion> getExcursion(final Long excursionId) {
        return excursionRepository.findById (excursionId);
    }
    public Excursion saveExcursion(final Excursion excursion){
        return excursionRepository.save (excursion);
    }
    public void deleteUser(Long excursionId){
        excursionRepository.deleteById (excursionId);
    }
    public boolean isExist(Long excursionId){
        return excursionRepository.existsById (excursionId);
    }
}
