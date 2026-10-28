package org.abes.hrmsdatabase.controller;

import org.abes.hrmsdatabase.model.Attendance;
import org.abes.hrmsdatabase.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // no trailing slash
@RequestMapping("/v1/user/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/markattendance")
    public String markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/today")
    public List<Attendance> getToday() {
        return attendanceService.getTodayAttendance();
    }

    @PutMapping("/checkout/{empId}")
    public String checkOut(@PathVariable long empId) {
        return attendanceService.checkOut(empId);
    }
}