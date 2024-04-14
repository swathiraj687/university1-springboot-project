/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;

import com.example.university.repository.CourseRepository;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.repository.ProfessorJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseJpaService implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private ProfessorJpaRepository professorJpaRepository;

    @Override
    public ArrayList<Course> getCourses() {
        List<Course> courseList = courseJpaRepository.findAll();
        ArrayList<Course> courses = new ArrayList<>(courseList);
        return courses;
    }

    @Override
    public Course getCourseById(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Course addCourse(Course course) {
        List<Integer> studentIds = new ArrayList<>();
        for (Student student : course.getStudents()) {
            studentIds.add(student.getStudentId());
        }

        List<Student> students = studentJpaRepository.findAllById(studentIds);

        if (students.size() != studentIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        course.setStudents(students);

        return courseJpaRepository.save(course);
    }

    @Override
    public Course updateCourse(int courseId, Course course) {
        try {
            Course newCourse = courseJpaRepository.findById(courseId).get();
            if (course.getCourseName() != null) {
                newCourse.setCourseName(course.getCourseName());
            }
            if (course.getCredits() != null) {
                newCourse.setCredits(course.getCredits());
            }

            if (course.getProfessor() != null) {
                int professorId = course.getProfessor().getProfessorId();
                Professor professor = professorJpaRepository.findById(professorId).get();
                newCourse.setProfessor(professor);
            }

            if (course.getStudents() != null) {
                List<Integer> studentIds = new ArrayList<>();
                for (Student student : course.getStudents()) {
                    studentIds.add(student.getStudentId());
                }
                List<Student> students = studentJpaRepository.findAllById(studentIds);
                if (students.size() != studentIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                newCourse.setStudents(students);
            }
            return courseJpaRepository.save(newCourse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            courseJpaRepository.deleteById(courseId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Professor getCourseProfessor(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course.getProfessor();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> getCourseStudents(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course.getStudents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
