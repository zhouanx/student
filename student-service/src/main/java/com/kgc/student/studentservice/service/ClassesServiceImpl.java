package com.kgc.student.studentservice.service;

import com.kgc.student.bean.Classes;
import com.kgc.student.service.ClassesService;
import com.kgc.student.studentservice.mapper.ClassesMapper;
import com.kgc.student.util.RedisUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Resource
    ClassesMapper classesMapper;
    @Resource
    JestClient jestClient;
    @Resource
    RedissonClient redissonClient;
    @Resource
    RedisUtil redisUtil;
    
    @Override
    public List<Classes> CLASSES_LIST() {
        List<Classes> list=new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        String dsl=searchSourceBuilder.toString();
        Search search=new Search.Builder(dsl).addIndex("classes").addType("classesinfo").build();
        try {
            SearchResult searchResult=jestClient.execute(search);
            List<SearchResult.Hit<Classes,Void>> hits=searchResult.getHits(Classes.class);
            for (SearchResult.Hit<Classes,Void> hit: hits){
                list.add(hit.source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
