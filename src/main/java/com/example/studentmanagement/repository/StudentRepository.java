package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by email
    Optional<Student> findByEmail(String email);

    // Find students by department
    List<Student> findByDepartment(String department);

    // Find students by enrollment year
    List<Student> findByEnrollmentYear(Integer enrollmentYear);

    // Find active students
    List<Student> findByIsActiveTrue();

    // Find students by department and enrollment year
    List<Student> findByDepartmentAndEnrollmentYear(String department, Integer enrollmentYear);

    // Find students by first name or last name containing the given string
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);

    // Find students by department and active status
    List<Student> findByDepartmentAndIsActive(String department, Boolean isActive);

    // Count students by department
    long countByDepartment(String department);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find students by enrollment year range
    @Query("SELECT s FROM Student s WHERE s.enrollmentYear BETWEEN :startYear AND :endYear")
    List<Student> findByEnrollmentYearBetween(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
}
