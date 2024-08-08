package net.javaguides.sms.mapper;

import net.javaguides.sms.dto.StudentsDto;
import net.javaguides.sms.entity.Students;

public class StudentsMapper {
    public static StudentsDto mapToStudentsDto(Students students){
        StudentsDto studentsDto =new StudentsDto(
                students.getId(),
                students.getFirstName(),
                students.getLastName(),
                students.getEmail(),
                students.getPassword(),
                students.getUsername()
        );
        return studentsDto;
    }
    public static Students mapToStudents(StudentsDto studentsDto){
        Students students = new Students(

                studentsDto.getId(),
                studentsDto.getFirstName(),
                studentsDto.getLastName(),
                studentsDto.getEmail(),
                studentsDto.getPassword(),
                studentsDto.getUsername()
        );
        return students;
    }
}
