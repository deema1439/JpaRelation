package com.example.schoolmangment.Controller;

import com.example.schoolmangment.Api.ApiResponse;
import com.example.schoolmangment.Model.Course;
import com.example.schoolmangment.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;



    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.status(200).body(courseService.courses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course) {
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }

    @PutMapping("/assign/{teacher_id}/{course_id}")
    public ResponseEntity<?> assignTeacherToCourse(@PathVariable Integer teacher_id, @PathVariable Integer course_id) {
        courseService.assignTeacherToCourse(teacher_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher assigned to course"));
    }

    @PutMapping("/update/{course_id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer course_id, @RequestBody @Valid Course course) {
        courseService.updateCourse(course_id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated"));
    }

    @DeleteMapping("/delete/{course_id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer course_id) {
        courseService.deleteCourse(course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));
    }

    @GetMapping("/teacher-name/{course_id}")
    public ResponseEntity<?> getTeacherNameByCourse(@PathVariable Integer course_id) {
        String teacherName = courseService.getTeacherNameByCourse(course_id);
        return ResponseEntity.status(200).body(new ApiResponse(teacherName));
    }
}
