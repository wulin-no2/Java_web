package org.example.springbootdemo.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create and persist an employee entity
        Student student = new Student("Tom", 100);
        em.persist(student);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        Student retrievedStudent = em.find(Student.class, student.getId());
        System.out.println("Student: " + retrievedStudent.getName() + ", " + retrievedStudent.getMarks());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}

