package com.letshadow.api.repository;

import com.letshadow.api.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1")
    List<Person> findByMonthOfBirthday(int monthOfBirthday);

    @Query(value = "select * from Person person where person.name = :name",nativeQuery = true)
    Person findByPersonName(@Param("name") String name);

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}
