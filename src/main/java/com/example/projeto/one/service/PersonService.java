package com.example.projeto.one.service;

import com.example.projeto.one.dto.request.PersonDTO;
import com.example.projeto.one.entity.Person;
import com.example.projeto.one.exception.PersonNotFoundException;
import com.example.projeto.one.mapper.PersonMapper;
import com.example.projeto.one.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonDTO create(PersonDTO personDTO){

        var personToSave = personMapper.toModel(personDTO);
        var savedPerson =  personRepository.save(personToSave);

        return  personMapper.toDTO(savedPerson);
    }

    public List<PersonDTO> listAll() {

        List<Person> allPerson = personRepository.findAll();

        return allPerson
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {

        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public PersonDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);
        var personToUpdate = personMapper.toModel(personDTO);
        var updatedPerson = personRepository.save(personToUpdate);
        return personMapper.toDTO(updatedPerson);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {

        return  personRepository.findById(id)
                .orElseThrow(()->new PersonNotFoundException(id));
    }
}
