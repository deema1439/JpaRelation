package com.example.schoolmangment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Teacher {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;


 @NotEmpty(message = "name of the teacher Should not be Empty")
 @Size(min = 2,max = 30,message = "The name of the teacher Should be between 2 and 30")
 @Column(columnDefinition = "varchar(30) not null")
 private String name;




 @NotNull(message = "age should not be Empty")
 @Min(value = 22,message = "age of the teacher should be larger than 21 ")
 @Column(columnDefinition = "int not null")
 private Integer age;



 @Email(message = "Email not valid")
 @NotEmpty(message = "Email Should not Empty")
 @Column(columnDefinition ="varchar(40) not null",unique=true)
 private String email;



 @Positive(message = "salary should be a positive number")
 @NotNull(message = "salary Should not be Empty ")
 @Column(columnDefinition = "double not null")
 private Double salary;

 @OneToOne(cascade = CascadeType.ALL,mappedBy = "teacher")
 @PrimaryKeyJoinColumn // شفت البرايمري key الي هنا حطه هنا في ال address
private Address address;




@OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
 private Set<Course>courses;









}
