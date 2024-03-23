package com.assignment3.Entity;

import javax.persistence.*;

@Entity
@Table(name = "UserCourse")
public class UserCourse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_course_id")
    private Long userCourseId;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "user_id")
    private Long userId;

    public UserCourse(Long courseId, Long userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public UserCourse() {
    }

    public Long getUserCourseId() {
        return userCourseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "userCourseId=" + userCourseId +
                ", courseId=" + courseId +
                ", userId=" + userId +
                '}';
    }
}
