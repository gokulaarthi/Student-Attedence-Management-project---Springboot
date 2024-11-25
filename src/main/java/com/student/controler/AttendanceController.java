package com.student.controler;


import java.time.LocalDate; 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Attendance;
import com.student.model.Students;
import com.student.repositorry.AttendanceRepository;
import com.student.repositorry.StudentRepository;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Mark attendance for a student
    @PostMapping("/student/attendance")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }


    // Get attendance for a student within a date range
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(@PathVariable Long studentId, 
                                                   @RequestParam LocalDate startDate, 
                                                   @RequestParam LocalDate endDate) {
        return attendanceRepository.findByStudentIdAndDateBetween(studentId, startDate, endDate);
    }

    // Get attendance summary (e.g., daily, weekly, monthly)
    @GetMapping("/summary")
    public Map<String, Long> getAttendanceSummary(@RequestParam LocalDate date) {
        return attendanceRepository.countAttendanceSummaryByDate(date);
    }
}
