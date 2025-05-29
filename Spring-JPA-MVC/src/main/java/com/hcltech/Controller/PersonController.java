package com.hcltech.Controller;

import com.hcltech.Service.PersonService;
import com.hcltech.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personservice/v1/persons")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<PersonDto> getAll(){
        return personService.getAll();
    }
    @GetMapping("/{id}")
    public PersonDto getByID(@PathVariable("id") Integer id){
        return personService.getByID(id);
    }
    @PostMapping
    public PersonDto create(@RequestBody PersonDto persondto){
        return personService.create(persondto);
    }
    @PutMapping
    public PersonDto update(@RequestBody PersonDto persondto){
        return personService.update(persondto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        personService.delete(id);
    }

}
