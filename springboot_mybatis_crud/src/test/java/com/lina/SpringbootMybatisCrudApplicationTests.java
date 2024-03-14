package com.lina;

import com.lina.mapper.EmpMapper;
import com.lina.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {
    @Autowired
    private EmpMapper empMapper;

    @Test
    void testEmpList() {
        List<Emp> list = empMapper.List();
        list.stream().forEach(System.out::println);
    }
    @Test
    void testEmpDelete(){
        empMapper.delete(17);
    }
    @Test
    void testEmpInsert(){
        Emp emp = new Emp();
        emp.setUsername("Jerry");
        emp.setName("Jerry");
        emp.setImage("30.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2022,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        System.out.println(emp.getId());
    }
    @Test
    void testEmpUpdate(){
        Emp emp = new Emp();
        emp.setId(19);
        emp.setUsername("Jerry1");
        emp.setName("Jerry1");
//        emp.setImage("19.jpg");
        emp.setGender((short)1);
//        emp.setJob((short)2);
//        emp.setEntrydate(LocalDate.of(2021,1,1));
        emp.setUpdateTime(LocalDateTime.now());
//        emp.setDeptId(2);
        empMapper.update(emp);
    }
    @Test
    void testGetEmpById(){
        Emp byId = empMapper.getById(5);
        System.out.println(byId);
    }
    @Test
    void testEmpSearchByCondition(){
        List<Emp> list = empMapper.searchByCondition("u", (short)1, LocalDate.of(1999, 1, 1), LocalDate.of(2020, 1, 1));
        List<Emp> list1 = empMapper.searchByCondition(null, (short)1, LocalDate.of(1999, 1, 1), LocalDate.of(2020, 1, 1));
        System.out.println(list);
        System.out.println(list1);
    }
    @Test
    void testEmpDeleteByIds(){
        List<Integer> ids = Arrays.asList(13, 14, 15);
        empMapper.deleteByIds(ids);
    }
}
