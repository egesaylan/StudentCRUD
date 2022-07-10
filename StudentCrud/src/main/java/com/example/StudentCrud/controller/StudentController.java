package com.example.StudentCrud.controller;

import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent",liststudent);
        System.out.println("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("student",new Student());
        return "new";
    }

    @RequestMapping(value = "/save", method= RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student){
        service.save(student);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deletestudent(@PathVariable(name="id") int id) {
        service.delete(id);
        return "redirect:/";
    }

}
