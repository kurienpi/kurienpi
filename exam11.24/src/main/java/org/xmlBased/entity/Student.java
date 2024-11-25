package org.xmlBased.entity;

public class Student {
    private int studentId;
    private String studName;
    private int studRollNumber;
    private Subject subject;

    // Getters and setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }
    public int getStudRollNumber() { return studRollNumber; }
    public void setStudRollNumber(int studRollNumber) { this.studRollNumber = studRollNumber; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}
