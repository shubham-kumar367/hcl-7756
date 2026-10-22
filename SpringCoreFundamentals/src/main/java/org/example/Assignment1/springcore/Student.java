package org.example.Assignment1.springcore;


public class Student {
    private final int studentId;
    private final String studentName;
    private final Course course;

    public Student(int studentId, String studentName, Course course) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
    }

    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        course.displayCourseDetails();
    }
}
