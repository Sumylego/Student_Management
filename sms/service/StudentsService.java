package net.javaguides.sms.service;

import net.javaguides.sms.dto.StudentsDto;
import net.javaguides.sms.entity.Students;

import java.util.List;

public interface StudentsService {
    public List<StudentsDto> getAllStudenrs();

     void createStudent(StudentsDto studentsDto);


    StudentsDto getStudentById(Long studentId);

    void updateStudent(StudentsDto studentsDto);

//    Students findByEmail(String email);

    void studentDeleteById(Long studentId);


    StudentsDto findByUsername(String username);
}
