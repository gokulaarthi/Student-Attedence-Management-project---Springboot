package com.student.controler;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Students;
import com.student.repositorry.StudentRepository;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    @GetMapping("/getall")
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    // Add a new student
    @PostMapping("/add")
    public Students addStudent(@RequestBody Students student) {
        return studentRepository.save(student);
    }

    // Edit an existing student
    @PutMapping("/{id}")
    public Students updateStudent(@PathVariable Long id, @RequestBody Students studentDetails) {
//        Student student = studentRepository.findById(id).orElseThrow(null);
    	  Students student =   studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        student.setName(studentDetails.getName());
        student.setRollNo(studentDetails.getRollNo());
        return studentRepository.save(student);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        Students student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        
        studentRepository.delete(student);
    }
}
