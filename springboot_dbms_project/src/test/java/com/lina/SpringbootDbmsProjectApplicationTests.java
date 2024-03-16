package com.lina;

import com.lina.mapper.DeptMapper;
import com.lina.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class SpringbootDbmsProjectApplicationTests {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void testDeptList() {
        List<Dept> list = deptMapper.list();
        list.stream().forEach(System.out::println);
    }
    @Test
    void testDeptListById(){
        Dept dept = deptMapper.listById(1);
        System.out.println(dept);
    }
    @Test
    void testUuid(){
        for (int i= 0 ; i<=10;i++){
            String string = UUID.randomUUID().toString();
            System.out.println(string);
        }

    }

}
