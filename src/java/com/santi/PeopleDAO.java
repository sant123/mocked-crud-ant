/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.santi;

import java.util.ArrayList;

/**
 *
 * @author sant821
 */
public class PeopleDAO {

    private int currentID;
    private final ArrayList<Person> people;
    static private PeopleDAO singleton;

    public static PeopleDAO getInstance() {
        if (singleton == null) {
            singleton = new PeopleDAO();
        }

        return singleton;
    }

    public PeopleDAO() {
        this.people = new ArrayList<>();
        this.initData();
    }

    private void initData() {
        Person p1 = new Person("Santi", 28);
        Person p2 = new Person("Ginis", 27);
        Person p3 = new Person("Andrés", 28);

        this.create(p1);
        this.create(p2);
        this.create(p3);
    }

    private int nextID() {
        return ++this.currentID;
    }

    // CRUD
    // Create
    public void create(Person person) {
        person.setId(this.nextID());
        this.people.add(person);
    }

    // Read
    public ArrayList<Person> getAll() {
        return this.people;
    }

    public Person getByID(int id) {
        return this.people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    // Update
    public void update(Person person) throws Exception {
        Person p = getByID(person.getId());

        if (p == null) {
            throw new Exception("The person with id " + person.getId() + " does not exists!");
        }

        p.setName(person.getName());
        p.setAge(person.getAge());
    }

    // Delete
    public void destroy(int id) throws Exception {
        Person p = getByID(id);

        if (p == null) {
            throw new Exception("The person with id " + id + " does not exists!");
        }

        this.people.remove(p);
    }
}
