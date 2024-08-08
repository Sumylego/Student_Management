package net.javaguides.sms.controller;

import jakarta.validation.Valid;
import net.javaguides.sms.dto.StudentsDto;
import net.javaguides.sms.service.StudentsService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentsController {

    private StudentsService studentsService;


    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/student")
    public String getAllStudent(Model model){
        List<StudentsDto> studentsDtos = studentsService.getAllStudenrs();
        model.addAttribute("studentUser",studentsDtos);
        return "ShowDetailsStudent";
    }
//handler method to handle new student
    @GetMapping("/student/new")
    public String addNewStudent(Model model){
        StudentsDto studentsDto =new StudentsDto();
        model.addAttribute("student_add",studentsDto);

        return "AddStudent";
    }

    //handle method to save student details
//    @PostMapping("/student")
//    public String saveStudent(@Valid @ModelAttribute("student_add") StudentsDto studentsDto,
//                              BindingResult result,Model model){
//        if(result.hasErrors()){
//            model.addAttribute("student_add",studentsDto);
//            return "AddStudent";
//        }
//        studentsService.createStudent(studentsDto);
//        return "redirect:/student";
//    }
    @PostMapping("/student")
    public String saveStudent(@Valid @ModelAttribute("student_add") StudentsDto studentsDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student_add", studentsDto);

            return "AddStudent";
        }
        System.out.println(studentsDto);
        String encodePassword = BCrypt.hashpw(studentsDto.getPassword(),BCrypt.gensalt(12));
        studentsDto.setPassword(encodePassword);
        System.out.println(encodePassword);
        studentsService.createStudent(studentsDto);
        return "redirect:/student";
    }
    @GetMapping("/student/{studentId}/edit")
    public String studentEdit(@PathVariable("studentId") Long studentId,Model model){
        StudentsDto studentsDto =studentsService.getStudentById(studentId);
        model.addAttribute("studentedit",studentsDto);
        return "Student_edit";
    }

    @PostMapping("/student/{studentId}")
    public String studentUpdate(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("studentedit") StudentsDto studentsDto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("studentedit", studentsDto);
            return "Student_edit";
        }
        studentsDto.setId(studentId);
        studentsService.updateStudent(studentsDto);
        return "redirect:/student";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }
    @GetMapping("/student/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentsService.studentDeleteById(studentId);
        return "redirect:/student";
    }

//    GetMapping("/student/{studentId}/view")
//    public String viewStudent(@PathVariable("studentId") Long studentId,Model model){
//       StudentsDto studentsDto =  studentsService.getStudentById(studentId);
//       model.addAttribute("studentview",studentsDto);
//       return "view_student";
//    }
//    @GetMapping("/student/{student}/view")
//    public String viewStudent(@PathVariable("studentId") Long studentId,Model model){
//        StudentsDto studentsDto = studentsService.getStudentById(studentId);
//        model.addAttribute("student_view",studentsDto);
//
//        // Add logging
//        System.out.println("Student ID: " + studentId);
//        System.out.println("StudentsDto: " + studentsDto);
//
//        return "view_student";
//    }

    @GetMapping("/student/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentsDto studentsDto = studentsService.getStudentById(studentId);
        model.addAttribute("student_view", studentsDto);
        return "view_student";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        System.out.println("CHECK");
        System.out.println(username + " username");
        System.out.println(password + " password");

        // Find the user by username
        StudentsDto dbUser = studentsService.findByUsername(username);

        // Check if the user is found
        if (dbUser != null) {
            System.out.println("CheckUserName " + dbUser.getUsername());

            // Check if the provided password matches the stored password
            Boolean isPasswordMatch = BCrypt.checkpw(password, dbUser.getPassword());
            System.out.println("isPasswordMatch " + isPasswordMatch);
            System.out.println("dbUser " + dbUser);
            System.out.println("password " + password);
            System.out.println("dbUser.getPassword() " + dbUser.getPassword());

            if (isPasswordMatch) {
                return "redirect:/student";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    //{END}


//       Boolean isPasswordMatch =  BCrypt.checkpw(password,dbUser.getPassword());
//        System.out.println("isPasswordMatch "+isPasswordMatch);
//        System.out.println("dbUser "+dbUser);
//        System.out.println("password "+password);
//        System.out.println("dbUser.getPassword()"+dbUser.getPassword());
//        if (isPasswordMatch) {
//            return "redirect:/student";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }

//        if (dbUser != null && dbUser.getPassword().equals(password)) {
//            return "AddStudent";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
    }

}
