package com.student.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Marks;
import com.student.repositorry.MarksRepository;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "http://localhost:3000")
public class MarksController {

    @Autowired
    private MarksRepository marksRepository;

    // Add marks for a student
    @PostMapping
    public Marks addMarks(@RequestBody Marks marks) {
        return marksRepository.save(marks);
    }

    // Get marks for a student
    @GetMapping("/student/{studentId}")
    public List<Marks> getMarksByStudent(@PathVariable Long studentId) {
        return marksRepository.findByStudentId(studentId);
    }

    // Calculate overall percentage for a student
    @GetMapping("/percentage/{studentId}")
    public double calculatePercentage(@PathVariable Long studentId) {
        List<Marks> marks = marksRepository.findByStudentId(studentId);
        int totalMarks = marks.stream().mapToInt(Marks::getMarks).sum();
        return (double) totalMarks / marks.size();
    }
}
