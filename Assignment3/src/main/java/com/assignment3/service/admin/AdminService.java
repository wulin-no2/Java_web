package com.assignment3.service.admin;

import com.assignment3.Entity.Course;
import com.assignment3.Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdminService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
    EntityManager em = emf.createEntityManager();
    public void addCourse(String courseName, int semester) {
        // Begin transaction
        em.getTransaction().begin();
        // Create and persist an employee entity
        Course course = new Course(courseName, semester);
        em.persist(course);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        Course retrievedCourse = em.find(Course.class, course.getCourseId());
        System.out.println("Course: " + retrievedCourse.getCourseName() + ", " + retrievedCourse.getSemester());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }

    public void addUser(String userName, String password, String name, String phone, String role) {

        // Begin transaction
        em.getTransaction().begin();
        // Create and persist an employee entity
        User user = new User(userName, password, name, phone, role);
        em.persist(user);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        User retrievedUser = em.find(User.class, user.getUserId());
        System.out.println("User: " + retrievedUser.getUsername() + ", " + retrievedUser.getName() +
                ", " + retrievedUser.getPhone() + ", " + retrievedUser.getRole());

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
