package com.assignment3.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Long assessmentId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "marks")
    private Long marks;
    @Column(name = "assessment_type")
    private String assessmentType;

    public Assessment() {
    }

    public Assessment(Long userId, Long courseId, Long marks, String assessmentType) {
        this.userId = userId;
        this.courseId = courseId;
        this.marks = marks;
        this.assessmentType = assessmentType;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", marks=" + marks +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
