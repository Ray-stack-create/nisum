package com.nisum.controller;
import com.nisum.model.Student;
import com.nisum.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listAllStudents() {
        return studentService.listAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getById(id);
    }


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        studentService.save(student);
        return student;
    }


    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentService.save(student);
        return "Student updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.delete(id);
        return "Student deleted successfully!";
    }
}