package com.example.schoolmangment.Controller;

import com.example.schoolmangment.Api.ApiResponse;
import com.example.schoolmangment.Model.Student;
import com.example.schoolmangment.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.status(200).body(studentService.getStudent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/assign/{student_id}/{course_id}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Integer student_id, @PathVariable Integer course_id){
        studentService.assignStudentToCourse(student_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Student assigned to course"));
    }

    @PutMapping("/update/{student_id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer student_id, @RequestBody @Valid Student student){
        studentService.updateStudent(student_id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }

    @DeleteMapping("/delete/{student_id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer student_id){
        studentService.deleteStudent(student_id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
    }

    @PutMapping("/change-major/{student_id}/{major}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer student_id, @PathVariable String major){
        studentService.changeMajor(student_id, major);
        return ResponseEntity.status(200).body(new ApiResponse("Major changed and all courses dropped"));
    }

    @GetMapping("/by-course/{course_id}")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(studentService.getStudent(course_id));
    }
}