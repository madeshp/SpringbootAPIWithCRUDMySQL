package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {

    // Create a new student
    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    // Get all students
    List<StudentResponseDTO> getAllStudents();

    // Get student by ID
    StudentResponseDTO getStudentById(Long id);

    // Get student by email
    StudentResponseDTO getStudentByEmail(String email);

    // Get students by department
    List<StudentResponseDTO> getStudentsByDepartment(String department);

    // Get students by enrollment year
    List<StudentResponseDTO> getStudentsByEnrollmentYear(Integer enrollmentYear);

    // Get active students
    List<StudentResponseDTO> getActiveStudents();

    // Search students by name
    List<StudentResponseDTO> searchStudentsByName(String name);

    // Update student
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);

    // Delete student
    void deleteStudent(Long id);

    // Soft delete student (deactivate)
    StudentResponseDTO deactivateStudent(Long id);

    // Activate student
    StudentResponseDTO activateStudent(Long id);

    // Get students by department and enrollment year
    List<StudentResponseDTO> getStudentsByDepartmentAndEnrollmentYear(String department, Integer enrollmentYear);

    // Get students by enrollment year range
    List<StudentResponseDTO> getStudentsByEnrollmentYearRange(Integer startYear, Integer endYear);

    // Count students by department
    long getStudentCountByDepartment(String department);

    // Check if email exists
    boolean isEmailExists(String email);
}
