
package com.example.industrycodingassignment.controller;

import com.example.industrycodingassignment.model.Attendance;
import com.example.industrycodingassignment.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController
{
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService)
    {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<Attendance> checkIn(
            @RequestBody Map<String, String> request)
    {
        if(request == null || request.get("employeeId") == null
                || request.get("employeeId").isBlank())
        {
            throw new IllegalArgumentException("Employee ID is required");
        }

        Attendance attendance = attendanceService.checkIn(request.get("employeeId"));

        URI location = URI.create("/api/v1/attendance/" + attendance.getAttendanceId());

        return ResponseEntity.created(location).body(attendance);
    }

    @PatchMapping("/{attendanceId}/check-out")
    public ResponseEntity<Attendance> checkOut(
            @PathVariable String attendanceId)
    {
        return ResponseEntity.ok(attendanceService.checkOut(attendanceId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> findByEmployeeId(
            @PathVariable String employeeId)
    {
        return ResponseEntity.ok(
                attendanceService.findByEmployeeId(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> findAttendance(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
    {
        return ResponseEntity.ok(
                attendanceService.findByEmployeeIdAndDate(employeeId, date));
    }
}
