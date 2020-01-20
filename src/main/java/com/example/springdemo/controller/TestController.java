package com.example.springdemo.controller;

/**
 * @Auther: wjc
 * @Date: 2019/11/11 15:38
 * @Description:
 */

import com.example.springdemo.common.jsonReturn.JsonFieldFilter;
import com.example.springdemo.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class TestController {

    @RequestMapping
    @JsonFieldFilter(type = Map.class,exclude = "name")
    public Result<List<Map<String, Object>>> test1(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name","张氏");
        map1.put("age","23");
        map1.put("phone","15805654587");
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name","王室");
        map2.put("age","45");
        map2.put("phone","13525623214");
        list.add(map2);
        Result<List<Map<String, Object>>> r = new Result<>();
        r.success(list);
        return r;
    }




}
