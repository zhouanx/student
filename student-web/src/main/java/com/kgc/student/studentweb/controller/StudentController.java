package com.kgc.student.studentweb.controller;

import com.kgc.student.bean.Classes;
import com.kgc.student.bean.Student;
import com.kgc.student.service.ClassesService;
import com.kgc.student.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {
    @Reference
    StudentService studentService;
    @Reference
    ClassesService classesService;

    @RequestMapping("/student/list")
    @ResponseBody
    public List<Student> STUDENT_LIST(){
        List<Student> students = studentService.STUDENT_LIST();
        return students;
    }

    @RequestMapping("/classes/list")
    @ResponseBody
    public List<Classes> Classes_LIST(){
        List<Classes> classes = classesService.CLASSES_LIST();
        return classes;
    }
    @RequestMapping("/student/add")
    @ResponseBody
    public int STUDENT_ADD(@RequestBody Student student){
        int i = studentService.STUDENT_ADD(student);
        return i;
    }
}
