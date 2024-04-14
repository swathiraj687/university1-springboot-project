/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.model.Professor;
import com.example.university.service.CourseJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseJpaService courseJpaService;

    @GetMapping("/courses")
    public ArrayList<Course> getCourses() {
        return courseJpaService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return courseJpaService.addCourse(course);
    }

    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course course) {
        return courseJpaService.updateCourse(courseId, course);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int courseId) {
        courseJpaService.deleteCourse(courseId);
    }

    @GetMapping("/courses/{courseId}/professor")
    public Professor getcourseProfessor(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseProfessor(courseId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> getcourseStudents(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseStudents(courseId);
    }
}
