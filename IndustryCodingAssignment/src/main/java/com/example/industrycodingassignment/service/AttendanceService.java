
package com.example.industrycodingassignment.service;

import com.example.industrycodingassignment.model.Attendance;
import com.example.industrycodingassignment.repository.AttendanceRepository;
import com.example.industrycodingassignment.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService
{
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             EmployeeRepository employeeRepository)
    {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Attendance checkIn(String employeeId)
    {
        if(employeeId == null || employeeId.isBlank())
        {
            throw new IllegalArgumentException("Employee ID is required");
        }

        if(!employeeRepository.existsById(employeeId))
        {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }

        LocalDate date = LocalDate.now();

        if(attendanceRepository.existsByEmployeeIdAndDate(employeeId, date))
        {
            throw new IllegalStateException("Employee has already checked in today");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(date);
        attendance.setCheckIn(LocalTime.now());
        attendance.setStatus("PRESENT");

        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(String attendanceId)
    {
        if(attendanceId == null || attendanceId.isBlank())
        {
            throw new IllegalArgumentException("Attendance ID is required");
        }

        Attendance attendance = attendanceRepository.findById(attendanceId);

        if(attendance == null)
        {
            throw new IllegalArgumentException("Attendance not found: " + attendanceId);
        }

        if(attendance.getCheckOut() != null)
        {
            throw new IllegalStateException("Employee has already checked out");
        }

        LocalTime checkOutTime = LocalTime.now();

        if(checkOutTime.isBefore(attendance.getCheckIn()))
        {
            throw new IllegalArgumentException("Check-out time cannot be before check-in time");
        }

        attendance.setCheckOut(checkOutTime);

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> findByEmployeeId(String employeeId)
    {
        if(employeeId == null || employeeId.isBlank())
        {
            throw new IllegalArgumentException("Employee ID is required");
        }

        if(!employeeRepository.existsById(employeeId))
        {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }

        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date)
    {
        if(employeeId != null && employeeId.isBlank())
        {
            throw new IllegalArgumentException("Employee ID cannot be blank");
        }

        if(employeeId != null && !employeeRepository.existsById(employeeId))
        {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }

        if(employeeId != null && date != null)
        {
            return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
        }

        if(employeeId != null)
        {
            return attendanceRepository.findByEmployeeId(employeeId);
        }

        if(date != null)
        {
            return attendanceRepository.findAll().stream()
                    .filter(a -> a.getDate().equals(date))
                    .toList();
        }

        return attendanceRepository.findAll();
    }
}
