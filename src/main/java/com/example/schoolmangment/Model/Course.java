package com.example.schoolmangment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

 @NotEmpty(message = "name Should not be empty ")
 @Size(min = 3,max = 50,message = "name should be between 3 and 50")
 @Column(columnDefinition = "varchar(50) not null")
 private String name;

 @ManyToOne
 @JoinColumn(name ="teacher_id" ,referencedColumnName = "id" )
 @JsonIgnore
 private Teacher teacher;

@ManyToMany(mappedBy = "course")
private Set<Student> students;



}
