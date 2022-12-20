package com.leolabs.springbootbackend.repository;

import com.leolabs.springbootbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
