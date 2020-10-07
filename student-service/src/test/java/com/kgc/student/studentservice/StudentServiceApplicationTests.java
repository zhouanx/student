package com.kgc.student.studentservice;

import com.kgc.student.bean.Classes;
import com.kgc.student.bean.Student;
import com.kgc.student.studentservice.mapper.ClassesMapper;
import com.kgc.student.studentservice.mapper.StudentMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class StudentServiceApplicationTests {
    @Resource
    StudentMapper studentMapper;
    @Resource
    JestClient jestClient;
    @Resource
    ClassesMapper classesMapper;
    @Test
    void setStudent_ES() {
        List<Student> allstudent = studentMapper.selectByExample(null);
        System.out.println("list:"+allstudent);
        for (Student stu : allstudent) {
            Index index=new Index.Builder(stu).index("student").type("studentinfo").id(stu.getId()+"").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void setClasses_ES() {
        List<Classes> allclassess = classesMapper.selectByExample(null);
        System.out.println("list:"+allclassess);
        for (Classes classes : allclassess) {
            Index index=new Index.Builder(classes).index("classes").type("classesinfo").id(classes.getId()+"").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
