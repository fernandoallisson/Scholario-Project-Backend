package com.scholario.scholario_demo.dto.teacher;

import com.scholario.scholario_demo.entiity.Teacher;

public record TeacherCreationDto(
    String name, String email, String password,
    String role, String phone, String address,
    String birthdate, Long enrollment, String department, String hireDate) {

    public Teacher toEntity(){
      return new Teacher(
          name, email, password, role, phone, address, birthdate, department, hireDate
      );
    };
}
