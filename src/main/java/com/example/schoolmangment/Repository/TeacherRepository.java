package com.example.schoolmangment.Repository;

import com.example.schoolmangment.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
 Teacher findTeacherById(Integer id);


















}
