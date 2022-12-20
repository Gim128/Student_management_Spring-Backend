package com.leolabs.springbootbackend.controller;

import com.leolabs.springbootbackend.exception.ResourceNotFoundException;
import com.leolabs.springbootbackend.model.Student;
import com.leolabs.springbootbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

//    create rest API
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

//    get employee by id
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable long id){
        Student student = studentRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Student not exist with this id"+ id));
        return ResponseEntity.ok(student);
    }

//    update
    @PutMapping("{id}")
    public ResponseEntity<Student> updatedStudent(@PathVariable("id") long id, @RequestBody Student studentDetails){
        Student updateStudent = studentRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Student not exist with this id"+id));
        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());
        updateStudent.setEmail(studentDetails.getEmail());

        studentRepository.save(updateStudent);
        return ResponseEntity.ok(updateStudent);
    }

//delete

    public ResponseEntity<Student> deleteStudent(@PathVariable long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Exists with this id"+id));

        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
