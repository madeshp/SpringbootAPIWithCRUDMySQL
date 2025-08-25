package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE - Add a new student
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        try {
            StudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // READ - Get all students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        try {
            List<StudentResponseDTO> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        try {
            StudentResponseDTO student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ - Get student by email
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDTO> getStudentByEmail(@PathVariable String email) {
        try {
            StudentResponseDTO student = studentService.getStudentByEmail(email);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ - Get students by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByDepartment(@PathVariable String department) {
        try {
            List<StudentResponseDTO> students = studentService.getStudentsByDepartment(department);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get students by enrollment year
    @GetMapping("/enrollment-year/{year}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByEnrollmentYear(@PathVariable Integer year) {
        try {
            List<StudentResponseDTO> students = studentService.getStudentsByEnrollmentYear(year);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get active students
    @GetMapping("/active")
    public ResponseEntity<List<StudentResponseDTO>> getActiveStudents() {
        try {
            List<StudentResponseDTO> students = studentService.getActiveStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Search students by name
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> searchStudentsByName(@RequestParam String name) {
        try {
            List<StudentResponseDTO> students = studentService.searchStudentsByName(name);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE - Update student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, 
                                                         @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        try {
            StudentResponseDTO updatedStudent = studentService.updateStudent(id, studentRequestDTO);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // SOFT DELETE - Deactivate student
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponseDTO> deactivateStudent(@PathVariable Long id) {
        try {
            StudentResponseDTO deactivatedStudent = studentService.deactivateStudent(id);
            return new ResponseEntity<>(deactivatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ACTIVATE - Activate student
    @PatchMapping("/{id}/activate")
    public ResponseEntity<StudentResponseDTO> activateStudent(@PathVariable Long id) {
        try {
            StudentResponseDTO activatedStudent = studentService.activateStudent(id);
            return new ResponseEntity<>(activatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ - Get students by department and enrollment year
    @GetMapping("/department/{department}/enrollment-year/{year}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByDepartmentAndEnrollmentYear(
            @PathVariable String department, @PathVariable Integer year) {
        try {
            List<StudentResponseDTO> students = studentService.getStudentsByDepartmentAndEnrollmentYear(department, year);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get students by enrollment year range
    @GetMapping("/enrollment-year-range")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByEnrollmentYearRange(
            @RequestParam Integer startYear, @RequestParam Integer endYear) {
        try {
            List<StudentResponseDTO> students = studentService.getStudentsByEnrollmentYearRange(startYear, endYear);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - Get student count by department
    @GetMapping("/count/department/{department}")
    public ResponseEntity<Long> getStudentCountByDepartment(@PathVariable String department) {
        try {
            long count = studentService.getStudentCountByDepartment(department);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CHECK - Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        try {
            boolean exists = studentService.isEmailExists(email);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
