package com.example.studentmanagement.config;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load sample data only if no students exist
        if (studentRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Sample Student 1
        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@example.com");
        student1.setPhoneNumber("1234567890");
        student1.setDateOfBirth(LocalDate.of(2000, 5, 15));
        student1.setAddress("123 Main St, City, State 12345");
        student1.setDepartment("Computer Science");
        student1.setEnrollmentYear(2022);
        student1.setIsActive(true);
        student1.setCreatedAt(LocalDate.now());
        student1.setUpdatedAt(LocalDate.now());
        studentRepository.save(student1);

        // Sample Student 2
        Student student2 = new Student();
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@example.com");
        student2.setPhoneNumber("9876543210");
        student2.setDateOfBirth(LocalDate.of(1999, 8, 22));
        student2.setAddress("456 Oak Ave, Town, State 67890");
        student2.setDepartment("Mathematics");
        student2.setEnrollmentYear(2021);
        student2.setIsActive(true);
        student2.setCreatedAt(LocalDate.now());
        student2.setUpdatedAt(LocalDate.now());
        studentRepository.save(student2);

        // Sample Student 3
        Student student3 = new Student();
        student3.setFirstName("Michael");
        student3.setLastName("Johnson");
        student3.setEmail("michael.johnson@example.com");
        student3.setPhoneNumber("5551234567");
        student3.setDateOfBirth(LocalDate.of(2001, 3, 10));
        student3.setAddress("789 Pine Rd, Village, State 11111");
        student3.setDepartment("Physics");
        student3.setEnrollmentYear(2023);
        student3.setIsActive(true);
        student3.setCreatedAt(LocalDate.now());
        student3.setUpdatedAt(LocalDate.now());
        studentRepository.save(student3);

        // Sample Student 4
        Student student4 = new Student();
        student4.setFirstName("Sarah");
        student4.setLastName("Williams");
        student4.setEmail("sarah.williams@example.com");
        student4.setPhoneNumber("4449876543");
        student4.setDateOfBirth(LocalDate.of(2000, 12, 5));
        student4.setAddress("321 Elm St, Borough, State 22222");
        student4.setDepartment("Computer Science");
        student4.setEnrollmentYear(2022);
        student4.setIsActive(true);
        student4.setCreatedAt(LocalDate.now());
        student4.setUpdatedAt(LocalDate.now());
        studentRepository.save(student4);

        // Sample Student 5
        Student student5 = new Student();
        student5.setFirstName("David");
        student5.setLastName("Brown");
        student5.setEmail("david.brown@example.com");
        student5.setPhoneNumber("3334567890");
        student5.setDateOfBirth(LocalDate.of(1998, 7, 18));
        student5.setAddress("654 Maple Dr, County, State 33333");
        student5.setDepartment("Chemistry");
        student5.setEnrollmentYear(2020);
        student5.setIsActive(false);
        student5.setCreatedAt(LocalDate.now());
        student5.setUpdatedAt(LocalDate.now());
        studentRepository.save(student5);

        System.out.println("Sample data loaded successfully!");
    }
}
