package org.abes.hrmsdatabase.repository;

import org.abes.hrmsdatabase.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT COUNT(a) > 0 FROM Attendance a " +
            "WHERE a.employee.emp_id = :empId AND a.attendancedate = :date")
    boolean existsByEmployeeAndDate(@Param("empId") long empId,
                                    @Param("date") LocalDate date);

    List<Attendance> findByAttendancedate(LocalDate date);

    @Query("SELECT a FROM Attendance a " +
            "WHERE a.employee.emp_id = :empId AND a.attendancedate = :date")
    Optional<Attendance> findByEmployeeAndDate(@Param("empId") long empId,
                                               @Param("date") LocalDate date);
}