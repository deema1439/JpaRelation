package com.example.schoolmangment.Service;

import com.example.schoolmangment.Api.ApiException;
import com.example.schoolmangment.DTO.StudentDtoOut;
import com.example.schoolmangment.Model.Course;
import com.example.schoolmangment.Model.Student;
import com.example.schoolmangment.Repository.CourseRepository;
import com.example.schoolmangment.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDtoOut>getStudent(){
        List<Student>students=studentRepository.findAll();
        List<StudentDtoOut>studentDtoOuts=new ArrayList<>();
        for(Student s:students){
            StudentDtoOut studentDto=new StudentDtoOut(s.getName(),s.getMajor());
            studentDtoOuts.add(studentDto);
        }
        return studentDtoOuts;
    }

    public void addStudent(Student student){
         studentRepository.save(student);
    }


    public void assignStudentToCourse(Integer student_id,Integer course_id){
        Student student=studentRepository.findStudentById(student_id);
        Course course=courseRepository.findCourseById(course_id);
        if(student==null||course==null){
            throw new ApiException("Student Can't assign to this course");
        }
        student.getCourse().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);

    }

    public void updateStudent(Integer student_id,Student student){
        Student student1=studentRepository.findStudentById(student_id);
        if(student1==null){
            throw new ApiException("Student Not Found");
        }
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());
        student1.setName(student.getName());
         studentRepository.save(student1);

    }

    public void deleteStudent(Integer student_id){
    Student student=studentRepository.findStudentById(student_id);
    if(student==null){
        throw new ApiException("there is no Student found");
    }

    studentRepository.delete(student);
    }



    public void changeMajor(Integer student_id,String major){
      Student student=studentRepository.findStudentById(student_id);
        if(student == null){
            throw new ApiException("Student not found");
        }
        for(Course c:student.getCourse()){
            c.getStudents().remove(student);//انشال الطالب من عند الكورسات
        }

        student.getCourse().clear(); //انشال الكورس من عند الطالب

        student.setMajor(major);
        studentRepository.save(student);

    }



    public List<StudentDtoOut>getStudent(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        if(course == null){
            throw new ApiException("Course not found");
        }
        List<StudentDtoOut>studentDtoOuts=new ArrayList<>();
        for(Student s:course.getStudents()){
            StudentDtoOut studentDto=new StudentDtoOut(s.getName(),s.getMajor());
            studentDtoOuts.add(studentDto);
        }
        return studentDtoOuts;
    }





















}
