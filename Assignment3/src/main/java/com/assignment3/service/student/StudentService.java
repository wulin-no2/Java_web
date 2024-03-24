package com.assignment3.service.student;

import com.assignment3.Entity.Assessment;
import com.assignment3.Entity.Course;
import com.assignment3.Entity.User;
import com.assignment3.Entity.UserCourse;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    // JPA:
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
    public List<Course> getEnrolledCoursesByUserName(String userName) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT c.* FROM course c " +
                    "JOIN usercourse uc ON c.course_id = uc.course_id " +
                    "JOIN user u ON uc.user_id = u.user_id " +
                    "WHERE u.username = ?";

            // Create a native query
            Query query = em.createNativeQuery(sql, Course.class);
            query.setParameter(1, userName);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Course> courses = query.getResultList();
            return courses;
        } finally {
            em.close();
        }
        // JDBC
//        // Step 1: Get the user_id from the 'user' table based on the provided username.
//        String getUserIdSql = "SELECT user_id FROM user WHERE username = ${userName}";
//
//        // Step 2: Get the list of courses the user is enrolled in from the 'usercourse' table.
//        String getUserCoursesSql = "SELECT c.course_id, c.course_name, c.semester "
//                + "FROM course c "
//                + "JOIN usercourse uc ON c.course_id = uc.course_id "
//                + "WHERE uc.user_id = ${getUserIdSql}";
//
//        // get connection from JDBCOps;
//        Connection connection = JDBCOps.jdbcConnection();
//        // Get the user_id
//            try (PreparedStatement getUserIdStmt = connection.prepareStatement(getUserIdSql)) {
//                getUserIdStmt.setString(1, userName);
//                ResultSet userIdRs = getUserIdStmt.executeQuery();
//                if (userIdRs.next()) {
//                    Long userId = userIdRs.getLong("user_id");
//                    // Get the courses using the retrieved user_id
//                    try (PreparedStatement getUserCoursesStmt = connection.prepareStatement(getUserCoursesSql)) {
//                        getUserCoursesStmt.setLong(1, userId);
//                        ResultSet coursesRs = getUserCoursesStmt.executeQuery();
//                        while (coursesRs.next()) {
//                            Course course = new Course(coursesRs.getString("course_name"),coursesRs.getInt("semester"));
//                            courses.add(course);
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//            // Handle exceptions, possibly logging them and/or rethrowing as appropriate
//            e.printStackTrace();
//        }
//        return courses;
    }
    public List<Course> getAvailableCoursesByUserName(String userName){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT c.* FROM course c " +
                    "WHERE c.course_id NOT IN (" +
                    "    SELECT uc.course_id " +
                    "    FROM usercourse uc " +
                    "    INNER JOIN user u ON uc.user_id = u.user_id " +
                    "    WHERE u.username = ?" +
                    ")";
            // Create a native query
            Query query = em.createNativeQuery(sql, Course.class);
            query.setParameter(1, userName);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Course> courses = query.getResultList();
            return courses;
        } finally {
            em.close();
        }
    }
    public void registerCourse(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();
        // Begin transaction
        em.getTransaction().begin();
        User user = getUserByUserName(userName);
        Course course = getCourseByCourseId(courseId);
        // Create and persist an employee entity
        UserCourse usercourse = new UserCourse();
        usercourse.setUser(user);
        usercourse.setCourse(course);
        System.out.println("UserCourse is: " + usercourse);

        em.persist(usercourse);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve an employee by ID
        UserCourse retrievedUserCourse = em.find(UserCourse.class, usercourse.getUserCourseId());
        System.out.println("UserCourse: " + retrievedUserCourse.getUser() + ", " + retrievedUserCourse.getCourse());

        // Close EntityManager and EntityManagerFactory
        em.close();
    }
    // used to get user and course for other methods to use;
    public User getUserByUserName(String userName){
        EntityManager em = emf.createEntityManager();
        try {
            // Create a sql find the user
            String sql = "SELECT * FROM user WHERE username = ?";
            Query query = em.createNativeQuery(sql, User.class);
            query.setParameter(1, userName);
            User user = (User) query.getSingleResult();
            System.out.println("=================" + user + "=======================");
            return user;
        } finally {
            em.close(); // Only close the EntityManager here
        }
    }
    public Course getCourseByCourseId(Long courseId){
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, courseId);
        // Close EntityManager and EntityManagerFactory
        em.close();
        return course;
    }
    public List<Assessment> getAssessmentByCourseIdAndUserName(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT * FROM Assessment a " +
                    "JOIN course c ON a.course_id = c.course_id " +
                    "JOIN user u ON u.user_id = a.user_id " +
                    "WHERE u.username = ? and a.course_id = ?";
            // Create a native query
            Query query = em.createNativeQuery(sql, Assessment.class);
            query.setParameter(1, userName);
            query.setParameter(2, courseId);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<Assessment> assessments = query.getResultList();
            return assessments;
        } finally {
            em.close();
        }
    }
}
