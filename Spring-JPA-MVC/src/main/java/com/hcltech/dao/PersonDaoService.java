package com.hcltech.dao;

import com.hcltech.model.Person;
import com.hcltech.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonDaoService {
    private PersonRepository personRepository;

    public PersonDaoService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }
    public Person getByID(Integer id){
        return personRepository.findById(id).orElse(null);
    }
    public Person create(Person person){
        personRepository.save(person);
        return person;
    }
    public Person update(Person person){
        personRepository.save(person);
        return person;
    }
    public void delete(Integer id){
        personRepository.deleteById(id);
    }

}
