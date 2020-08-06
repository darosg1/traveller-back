package com.crud.traveller.repository;

import com.crud.traveller.entity.Excursion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ExcursionRepository extends CrudRepository<Excursion, Long> {
    @Override
    List<Excursion> findAll();

    @Override
    Optional<Excursion> findById(Long excursionId);

    @Override
    Excursion save(Excursion excursion);

    @Override
    void deleteById(Long excursionId);
}
