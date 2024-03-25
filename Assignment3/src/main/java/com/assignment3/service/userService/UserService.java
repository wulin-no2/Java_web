package com.assignment3.service.userService;

import com.assignment3.entity.Assessment;
import com.assignment3.entity.Course;
import com.assignment3.entity.User;
import com.assignment3.entity.UserCourse;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class UserService {
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
        if (getUserCourseByCourseIdAndUserName(userName,courseId))
            return;

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
    public Boolean getUserCourseByCourseIdAndUserName(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();

        try{
            Long userId = getUserByUserName(userName).getUserId();
            String sql = "select count(*) from UserCourse " +
                    "where UserCourse.user_id = ? and course_id = ? ";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, userId);
            query.setParameter(2, courseId);
            Number count = (Number)query.getSingleResult();
            return count.intValue() > 0;
        }finally {
            em.close();
        }
    }


    /**
     * get  assessment results of a student.
     * @param userName
     * @param courseId
     * @return
     */
    public List<Assessment> getAssessmentByCourseIdAndUserName(String userName, Long courseId){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT * FROM Assessment a " +
                    "JOIN course c ON a.course_id = c.course_id " +
                    "JOIN user u ON u.user_id = a.user_id " +
                    "WHERE u.username = ? and a.course_id = ? and u.role = 'student'";
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
    public List<UserCourse> getStudentsByCourseId(Long courseId){
        EntityManager em = emf.createEntityManager();
        try {
            // Native SQL query
            String sql = "SELECT * FROM UserCourse uc " +
                    "join MY_DB.User u on uc.user_id = u.user_id " +
                    "WHERE u.role = 'student' and uc.course_id = ?";
            // Create a native query
            Query query = em.createNativeQuery(sql, UserCourse.class);
            query.setParameter(1, courseId);

            // Execute the query and get the result list
            @SuppressWarnings("unchecked")
            List<UserCourse> students = query.getResultList();
            return students;
        } finally {
            em.close();
        }
    }
    public void updateStudentAssessment(String userName, Long courseId, int assignmentMark, int quizMark, int examMark){
        EntityManager em = emf.createEntityManager();
        if (getAssessmentByCourseIdAndUserName(userName, courseId).isEmpty()){
            // Begin transaction
            em.getTransaction().begin();

            Assessment assessment1 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) assignmentMark,"assignment");
            Assessment assessment2 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) quizMark,"quiz");
            Assessment assessment3 = new Assessment(getUserByUserName(userName), getCourseByCourseId(courseId), (long) examMark,"exam");
            em.persist(assessment1);
            em.persist(assessment2);
            em.persist(assessment3);

            // commit
            em.getTransaction().commit();

        }
        else {
            // Begin transaction
            em.getTransaction().begin();

            // Native SQL query
            String sql =  "UPDATE Assessment a " +
                    "SET a.marks = CASE a.assessment_type " +
                    "                WHEN 'assignment' THEN ? " +
                    "                WHEN 'quiz' THEN ? " +
                    "                WHEN 'exam' THEN ? " +
                    "              END " +
                    "WHERE user_id = ? and course_id = ? " +
                    "AND a.assessment_type IN ('assignment', 'quiz', 'exam')";
//                String sql = "SELECT * FROM UserCourse uc " +
//                        "join MY_DB.User u on uc.user_id = u.user_id " +
//                        "WHERE u.role = 'student' and uc.course_id = ?";
            // Create a native query
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, assignmentMark);
            query.setParameter(2, quizMark);
            query.setParameter(3, examMark);
            query.setParameter(4, getUserByUserName(userName).getUserId());
            query.setParameter(5, courseId);

            query.executeUpdate();
            em.getTransaction().commit(); // Commit transaction
        }
        em.close();
    }
}