package com.Sophos.SophosUniversity.controllers;


import com.Sophos.SophosUniversity.entities.Student;
import com.Sophos.SophosUniversity.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StudentController {

    @Autowired
    private IStudentService service;


    @GetMapping("/api/v1/students")
    public List<Student> getAllStudents() throws Exception {
        return service.getAllStudents();
    }


    @GetMapping("/api/v1/students/id/{searchId}")
    public List<Student> searchAllStudentsById(@PathVariable Long searchId) throws Exception {
        return service.searchAllStudentsById(searchId);
    }

    @GetMapping("/api/v1/students/name/{searchName}")
    public List<Student> searchAllStudentsByName(@PathVariable String searchName) throws Exception {
        return service.searchAllStudentsByName(searchName);
    }


    @GetMapping("/api/v1/students/{id}")
    public Student getStudentById(@PathVariable Long id) throws Exception {
        return service.getStudentById(id);
    }

    @GetMapping("/api/v1/multipleStudents")
    public List<Student> getMultipleStudentsByid(@RequestBody Iterable<Long> id) throws Exception {
        return service.getMultipleStudentsById(id);
    }

    @DeleteMapping("/api/v1/students/{id}")
    public String deleteStudent(@PathVariable Long id) throws Exception{
        return service.deleteStudent(id);
    }

    @PostMapping("/api/v1/students")
    public String addNewStudent(@RequestBody Student student) throws Exception{
        return service.addNewStudent(student);
    }

    @PutMapping("/api/v1/students")
    public String updateStudent(@RequestBody Student student) throws Exception{
        return service.updateStudent(student);
    }


}
