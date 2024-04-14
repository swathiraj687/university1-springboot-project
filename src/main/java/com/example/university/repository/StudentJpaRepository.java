/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.data.jpa.repository.JpaRepository;
 * import org.springframework.stereotype.Repository;
 * 
 */

// Write your code here
package com.example.university.repository;

import com.example.university.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

}