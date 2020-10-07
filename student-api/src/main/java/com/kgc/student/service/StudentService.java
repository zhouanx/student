package com.kgc.student.service;

import com.kgc.student.bean.Student;

import java.util.List;

public interface StudentService {
    public List<Student> STUDENT_LIST();

    public int STUDENT_ADD(Student student);
}
