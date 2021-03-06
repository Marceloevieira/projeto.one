package com.example.projeto.one.controller;

import com.example.projeto.one.dto.request.PersonDTO;
import com.example.projeto.one.exception.PersonNotFoundException;
import com.example.projeto.one.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO create(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }

    @GetMapping
    public List<PersonDTO>  listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable  Long id) throws PersonNotFoundException {

        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public PersonDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {

        return personService.updateById(id,personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }
}
