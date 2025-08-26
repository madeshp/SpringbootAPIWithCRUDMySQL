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

    // Get active students
    List<StudentResponseDTO> getActiveStudents();

    // Update student
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);

    // Delete student
    void deleteStudent(Long id);

    // Soft delete student (deactivate)
    StudentResponseDTO deactivateStudent(Long id);

    // Activate student
    StudentResponseDTO activateStudent(Long id);
}
