package org.example.springbootdemo.controller;

import org.example.springbootdemo.pojo.*;
import org.example.springbootdemo.utils.XmlParserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @RequestMapping("/listEmp")
    public Result list(){
        // 1. load and parse emp.xml
        // convert string to object, add to list then return list;
        // String file = "/Users/wulin/Desktop/WholeJourney/Java_web/springbootDemo/src/main/resources/emp.xml";
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        // 2. convert data
        empList.stream().forEach(emp -> {
            // gender;
            String gender = emp.getGender();
            if("1".equals(gender)) emp.setGender("Male");
            if("2".equals(gender)) emp.setGender("Female");

            // job;
            String job = emp.getJob();
            if("1".equals(job)) emp.setJob("Lecturer");
            if("2".equals(job)) emp.setJob("Associate Professor");
            if("3".equals(job)) emp.setJob("Professor");

        });
        // 3. response data
        return Result.success(empList);
    }

}
