package com.example.schoolmangment.Service;

import com.example.schoolmangment.Api.ApiException;
import com.example.schoolmangment.Model.Course;
import com.example.schoolmangment.Model.Student;
import com.example.schoolmangment.Model.Teacher;
import com.example.schoolmangment.Repository.CourseRepository;
import com.example.schoolmangment.Repository.StudentRepository;
import com.example.schoolmangment.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course>courses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
         courseRepository.save(course);
    }

    public void assignTeacherToCourse(Integer teacher_id,Integer course_id){
       Course course=courseRepository.findCourseById(course_id);
       Teacher teacher=teacherRepository.findTeacherById(teacher_id);

       if(course==null||teacher==null){
           throw new ApiException("Can't assign Teacher to Course");
       }
       course.setTeacher(teacher);
       courseRepository.save(course);
    }

    public void updateCourse(Integer course_id,Course course){
        Course oldCourse=courseRepository.findCourseById(course_id);
        if(oldCourse==null){
            throw new ApiException("Course not found");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if(course==null){
            throw  new ApiException("Course not found");
        }
        for (Student student:course.getStudents()){
            student.getCourse().remove(course);
        }
        studentRepository.saveAll(course.getStudents());
        courseRepository.delete(course);
    }


    public String getTeacherNameByCourse(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if(course==null){
            throw new ApiException("Course not found");
        }
       if(course.getTeacher()==null){
           throw new ApiException("This course does not have a teacher");
       }
       return course.getTeacher().getName();
    }





}
