package com.hcltech.Service;

import com.hcltech.dao.PersonDaoService;
import com.hcltech.dto.AddressDto;
import com.hcltech.dto.PersonDto;
import com.hcltech.dto.ProjectDto;
import com.hcltech.model.Person;
import com.hcltech.model.Address;
import com.hcltech.model.Project;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDaoService personDaoService;
    private final ProjectService projectService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    public PersonService(PersonDaoService personDaoService,
                         ProjectService projectService,
                         AddressService addressService,
                         ModelMapper modelMapper) {
        this.personDaoService = personDaoService;
        this.projectService = projectService;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    public List<PersonDto> getAll(){
        List<Person> allPeople = personDaoService.getAll();
        return toDtoList(allPeople);
    }

    public PersonDto getByID(Integer id){
        Person person = personDaoService.getByID(id);
        return toDto(person);
    }

    public PersonDto create(PersonDto personDto){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(personDto);

        final Person personToSave = toEntity(personDto);
        Person savedPerson = personDaoService.create(personToSave);
        return toDto(savedPerson);
    }

    public PersonDto update(PersonDto personDto){
        final Person personToUpdate = toEntity(personDto);
        // Assuming personDaoService has an update method
        Person updatedPerson = personDaoService.update(personToUpdate);
        return toDto(updatedPerson);
    }

    public void delete(Integer id){
        personDaoService.delete(id);
    }

    // --- Helper Methods for DTO/Entity Conversion ---

    private List<PersonDto> toDtoList(List<Person> persons){
        return persons.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PersonDto toDto(Person person) {
        // ModelMapper will handle direct field mapping (id, firstName, lastName, fullName)
        PersonDto personDto = modelMapper.map(person, PersonDto.class);

        // Map the single associated project
        if (person.getProject() != null) {
            ProjectDto projectDto = projectService.toDto(person.getProject());
            personDto.setProjectDto(projectDto);
        }

        // Map associated addresses
        if (person.getAddresses() != null && !person.getAddresses().isEmpty()) {
            Set<AddressDto> addressDtos = addressService.toDto(person.getAddresses());
            personDto.setAddressDtos(addressDtos);
        }

        return personDto;
    }

    private Person toEntity(PersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);

        // Map the single associated project
        if (personDto.getProjectDto() != null) {
            Project project = modelMapper.map(personDto.getProjectDto(), Project.class);
            person.setProject(project);
        }

        // Map associated addresses and SET THE PERSON FOR EACH ADDRESS
        if (personDto.getAddressDtos() != null && !personDto.getAddressDtos().isEmpty()) {
            Set<Address> addresses = personDto.getAddressDtos().stream()
                    .map(addressDto -> {
                        Address address = modelMapper.map(addressDto, Address.class);
                        // IMPORTANT: Set the person reference on each address
                        address.setPerson(person); // This is the crucial line for bidirectional mapping
                        return address;
                    })
                    .collect(Collectors.toSet());
            person.setAddresses(addresses);
        }

        return person;
    }
}