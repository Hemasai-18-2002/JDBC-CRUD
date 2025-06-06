package com.hecltech;

import com.hecltech.model.Person;
import com.hecltech.service.PersonService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Delayed;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
    private PersonService personService=new PersonService() ;
    public static void main(String[] args) throws SQLException {
        new Application().go();
    }
    private void go() throws SQLException {
        // All Persons
        List<Person> all = personService.getAll();
        System.out.ptint("Hello");
        System.out.println(all);

        //Peron by id
        Person oneByID = personService.getOneByID(2);
        System.out.println(oneByID);

        //insert
//        final Person person=new Person(null,"Surya","Reddy");
//
//        Person newPerson = personService.create(person);
//        System.out.println(newPerson);

        //Delete person

        personService.deleteOneByID(4);
        System.out.println(personService.getAll());


        //update

        final Person personToUpdate=new Person(7,"Ganesh","Reddy");
        Person updatedPerson = personService.update(personToUpdate);
        System.out.println(updatedPerson);

    }
}
