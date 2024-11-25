package org.xmlBased.entity;

import java.util.Set;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private String teacherQualification;
    private int experienceOfTeaching;
    private Subject subject;
    private Set<Student> students;

    // Getters and setters
    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public String getTeacherQualification() { return teacherQualification; }
    public void setTeacherQualification(String teacherQualification) {
        this.teacherQualification = teacherQualification;
    }
    public int getExperienceOfTeaching() { return experienceOfTeaching; }
    public void setExperienceOfTeaching(int experienceOfTeaching) {
        this.experienceOfTeaching = experienceOfTeaching;
    }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}