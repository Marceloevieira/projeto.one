package com.example.projeto.one.repository;

import com.example.projeto.one.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
