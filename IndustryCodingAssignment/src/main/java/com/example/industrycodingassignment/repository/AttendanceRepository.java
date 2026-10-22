
package com.example.industrycodingassignment.repository;

import com.example.industrycodingassignment.model.Attendance;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class AttendanceRepository
{
    private final Map<String, Attendance> attendanceRecords = new LinkedHashMap<>();

    public Attendance save(Attendance attendance)
    {
        if(attendance.getAttendanceId() == null)
        {
            attendance.setAttendanceId(UUID.randomUUID().toString());
        }

        attendanceRecords.put(attendance.getAttendanceId(), attendance);
        return attendance;
    }

    public Attendance findById(String attendanceId)
    {
        return attendanceRecords.get(attendanceId);
    }

    public List<Attendance> findAll()
    {
        return new ArrayList<>(attendanceRecords.values());
    }

    public List<Attendance> findByEmployeeId(String employeeId)
    {
        return attendanceRecords.values().stream()
                .filter(a -> a.getEmployeeId().equals(employeeId))
                .toList();
    }

    public List<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date)
    {
        return attendanceRecords.values().stream()
                .filter(a -> a.getEmployeeId().equals(employeeId)
                        && a.getDate().equals(date))
                .toList();
    }

    public boolean existsByEmployeeIdAndDate(String employeeId, LocalDate date)
    {
        return attendanceRecords.values().stream()
                .anyMatch(a -> a.getEmployeeId().equals(employeeId)
                        && a.getDate().equals(date));
    }
}
