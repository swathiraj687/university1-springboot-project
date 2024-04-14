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
import com.example.university.model.Professor;

import java.util.ArrayList;
import java.util.List;

public interface CourseRepository {
    ArrayList<Course> getCourses();

    Course getCourseById(int courseId);

    Course addCourse(Course course);

    Course updateCourse(int courseId, Course course);

    void deleteCourse(int courseId);

    Professor getCourseProfessor(int courseId);

    List<Student> getCourseStudents(int courseId);
}