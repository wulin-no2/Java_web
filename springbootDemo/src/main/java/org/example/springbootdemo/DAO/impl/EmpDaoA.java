package org.example.springbootdemo.DAO.impl;

import org.example.springbootdemo.DAO.EmpDAO;
import org.example.springbootdemo.pojo.Emp;
import org.example.springbootdemo.utils.XmlParserUtils;

import java.util.List;

public class EmpDaoA implements EmpDAO {
    @Override
    public List<Emp> listEmp() {
        // 1. load and parse emp.xml
        // convert string to object, add to list then return list;
        // String file = "/Users/wulin/Desktop/WholeJourney/Java_web/springbootDemo/src/main/resources/emp.xml";
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
