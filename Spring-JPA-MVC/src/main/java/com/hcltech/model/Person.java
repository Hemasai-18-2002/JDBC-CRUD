package com.hcltech.model; // This is its package

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name",length = 20,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 20,nullable = false)
    private String lastName;
    @OneToOne(targetEntity = Project.class,cascade = CascadeType.ALL)
    @JoinColumn(name="project_id",referencedColumnName = "id")
    private Project project;
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    Set<Address> addresses ;
    public Person() {
    }

    public Person( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return String.format("Person [id=%s, FirstName=%s, LastName=%s]", id, firstName, lastName);
    }
}