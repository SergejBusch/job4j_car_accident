package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.type left join fetch a.rules")
    Collection<Accident> findAllAccident();

    @Query("select distinct a from Accident a join fetch a.type full join fetch a.rules where a.id =:Id")
    Accident findAccidentById(@Param("Id") int id);
}