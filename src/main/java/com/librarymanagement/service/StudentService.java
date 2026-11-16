
package com.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.librarymanagement.entity.Student;
import com.librarymanagement.repository.StudentRepository;
import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.exception.DuplicateResourceException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add student
    public Student addStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists!");
        }

        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student not found with ID: " + id));
    }

    // Update student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        if (!existingStudent.getEmail().equals(updatedStudent.getEmail())
                && studentRepository.existsByEmail(updatedStudent.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists!");
        }

        existingStudent.setStudentName(updatedStudent.getStudentName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());

        return studentRepository.save(existingStudent);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }
}
