package com.assignment3.Entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "semester")
    private int semester;

    public Course() {
    }

    public Course(String courseName, int semester) {
        this.courseName = courseName;
        this.semester = semester;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", semester=" + semester +
                '}';
    }
}
