package com.kgc.student.studentservice.service;

import com.kgc.student.bean.Student;
import com.kgc.student.bean.Student;
import com.kgc.student.service.StudentService;
import com.kgc.student.studentservice.mapper.StudentMapper;
import com.kgc.student.util.RedisUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;
    @Resource
    JestClient jestClient;
    @Resource
    RedissonClient redissonClient;
    @Resource
    RedisUtil redisUtil;
    
    @Override
    public List<Student> STUDENT_LIST() {
        List<Student> list=new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        String dsl=searchSourceBuilder.toString();
        Search search=new Search.Builder(dsl).addIndex("student").addType("studentinfo").build();
        try {
            SearchResult searchResult=jestClient.execute(search);
            List<SearchResult.Hit<Student,Void>> hits=searchResult.getHits(Student.class);
            for (SearchResult.Hit<Student,Void> hit: hits){
                list.add(hit.source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int STUDENT_ADD(Student student) {
        int result=studentMapper.insertSelective(student);
        if(result>0){
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
        return result;
    }
}
