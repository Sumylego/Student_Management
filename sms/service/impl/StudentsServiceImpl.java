package net.javaguides.sms.service.impl;


import net.javaguides.sms.dto.StudentsDto;
import net.javaguides.sms.entity.Students;
import net.javaguides.sms.mapper.StudentsMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentsServiceImpl implements StudentsService {
    private StudentRepository studentRepository;

    public StudentsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentsDto> getAllStudenrs() {
        List<Students> students = studentRepository.findAll();
        List<StudentsDto> studentsDtos = students.stream().map((students1) -> StudentsMapper.mapToStudentsDto(students1)).collect(Collectors.toList());
        return studentsDtos;
    }

    @Override
    public void createStudent(StudentsDto studentsDto) {
        Students students = StudentsMapper.mapToStudents(studentsDto);
        studentRepository.save(students);
    }

    @Override
    public StudentsDto getStudentById(Long studentId) {
        Students students =studentRepository.findById(studentId).get();
        StudentsDto studentsDto = StudentsMapper.mapToStudentsDto(students);

        return studentsDto;
    }

    @Override
    public void updateStudent(StudentsDto studentsDto) {
        studentRepository.save(StudentsMapper.mapToStudents(studentsDto));

    }



    @Override
    public void studentDeleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentsDto findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

//    @Override
//    public StudentsDto findByUsername(String username) {
//        return studentRepository.findByUsername(username);
//    }

//    public Students findByEmail(String email) {
////        return studentRepository.findByEmail(email);
//
//        System.out.println("Looking for user with email: " + email);
//        Students user = studentRepository.findByEmail(email);
//        if (user != null) {
//            System.out.println("Found user: " + user.getEmail());
//        } else {
//            System.out.println("No user found with email: " + email);
//        }
//        return user;
//    }

}
