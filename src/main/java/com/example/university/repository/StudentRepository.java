/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.university.repository;

import com.example.university.model.Course;
import com.example.university.model.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student getStudentById(int courseId);

    Student addStudent(Student student);

    Student updateStudent(int studentId, Student student);

    void deleteStudent(int studentId);

    List<Course> getStudentCourses(int studentId);
}