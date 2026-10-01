package com.example.schoolmangment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Student {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@NotEmpty(message = "name Should not be empty")
@Size(min = 2,max = 30,message = "name should be between 2 and 30")
@Column(columnDefinition = "varchar(30) not null")
private String name;

@NotNull(message = "age Should not be empty")
@Positive(message = "age should be a positive number")
@Min(value = 5, message = "age should be at least 5")
@Column(columnDefinition = "int not null")
private Integer age;

@NotEmpty(message = "major Should not be empty")
@Size(min = 2, max = 30, message = "major should be between 2 and 30")
@Column(columnDefinition = "varchar(30) not null")
private String major;

@ManyToMany
@JsonIgnore
private Set<Course> course;













}
