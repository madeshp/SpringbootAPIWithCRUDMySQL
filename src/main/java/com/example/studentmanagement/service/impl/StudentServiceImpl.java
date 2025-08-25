package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        // Check if email already exists
        if (studentRepository.existsByEmail(studentRequestDTO.getEmail())) {
            throw new RuntimeException("Student with email " + studentRequestDTO.getEmail() + " already exists");
        }

        // Create new student entity
        Student student = new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        student.setAddress(studentRequestDTO.getAddress());
        student.setDepartment(studentRequestDTO.getDepartment());
        student.setEnrollmentYear(studentRequestDTO.getEnrollmentYear());
        student.setIsActive(true);
        student.setCreatedAt(LocalDate.now());
        student.setUpdatedAt(LocalDate.now());

        Student savedStudent = studentRepository.save(student);
        return convertToResponseDTO(savedStudent);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return convertToResponseDTO(student);
    }

    @Override
    public StudentResponseDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + email));
        return convertToResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByDepartment(String department) {
        List<Student> students = studentRepository.findByDepartment(department);
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getStudentsByEnrollmentYear(Integer enrollmentYear) {
        List<Student> students = studentRepository.findByEnrollmentYear(enrollmentYear);
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getActiveStudents() {
        List<Student> students = studentRepository.findByIsActiveTrue();
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> searchStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Check if email is being changed and if the new email already exists
        if (!existingStudent.getEmail().equals(studentRequestDTO.getEmail()) &&
            studentRepository.existsByEmail(studentRequestDTO.getEmail())) {
            throw new RuntimeException("Student with email " + studentRequestDTO.getEmail() + " already exists");
        }

        // Update student fields
        existingStudent.setFirstName(studentRequestDTO.getFirstName());
        existingStudent.setLastName(studentRequestDTO.getLastName());
        existingStudent.setEmail(studentRequestDTO.getEmail());
        existingStudent.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        existingStudent.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        existingStudent.setAddress(studentRequestDTO.getAddress());
        existingStudent.setDepartment(studentRequestDTO.getDepartment());
        existingStudent.setEnrollmentYear(studentRequestDTO.getEnrollmentYear());
        existingStudent.setUpdatedAt(LocalDate.now());

        Student updatedStudent = studentRepository.save(existingStudent);
        return convertToResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO deactivateStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setIsActive(false);
        student.setUpdatedAt(LocalDate.now());
        Student deactivatedStudent = studentRepository.save(student);
        return convertToResponseDTO(deactivatedStudent);
    }

    @Override
    public StudentResponseDTO activateStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setIsActive(true);
        student.setUpdatedAt(LocalDate.now());
        Student activatedStudent = studentRepository.save(student);
        return convertToResponseDTO(activatedStudent);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByDepartmentAndEnrollmentYear(String department, Integer enrollmentYear) {
        List<Student> students = studentRepository.findByDepartmentAndEnrollmentYear(department, enrollmentYear);
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getStudentsByEnrollmentYearRange(Integer startYear, Integer endYear) {
        List<Student> students = studentRepository.findByEnrollmentYearBetween(startYear, endYear);
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long getStudentCountByDepartment(String department) {
        return studentRepository.countByDepartment(department);
    }

    @Override
    public boolean isEmailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    // Helper method to convert Student entity to StudentResponseDTO
    private StudentResponseDTO convertToResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getDepartment(),
                student.getEnrollmentYear(),
                student.getIsActive(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }
}
