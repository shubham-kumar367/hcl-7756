package org.abes.hrmsdatabase.service;

import org.abes.hrmsdatabase.model.Attendance;
import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.repository.AttendanceRepository;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public String markAttendance(Attendance attendance) {
        if (attendance == null || attendance.getEmployee() == null) {
            return "Attendance not marked: Employee information missing";
        }

        long empId = attendance.getEmployee().getEmp_id();

        Employee employee = employeeRepository.findById(empId).orElse(null);
        if (employee == null) {
            return "Attendance not marked: Employee does not exist";
        }

        LocalDate today = LocalDate.now();

        if (attendanceRepository.existsByEmployeeAndDate(empId, today)) {
            return "Attendance already marked for today";
        }

        attendance.setAttendanceId(null);
        attendance.setEmployee(employee);
        attendance.setAttendancedate(today);
        if (attendance.getInTime() == null) {
            attendance.setInTime(LocalDateTime.now());
        }

        try {
            attendanceRepository.save(attendance);
        } catch (DataIntegrityViolationException e) {
            return "Attendance already marked for today";
        }
        return "Attendance Marked Successfully";
    }

    public List<Attendance> getTodayAttendance() {
        return attendanceRepository.findByAttendancedate(LocalDate.now());
    }

    @Transactional
    public String checkOut(long empId) {
        Attendance attendance = attendanceRepository
                .findByEmployeeAndDate(empId, LocalDate.now())
                .orElse(null);

        if (attendance == null) {
            return "Check-out failed: attendance not marked today";
        }
        if (attendance.getOutTime() != null) {
            return "Already checked out";
        }
        attendance.setOutTime(LocalDateTime.now());
        attendanceRepository.save(attendance);
        return "Checked out successfully";
    }
}