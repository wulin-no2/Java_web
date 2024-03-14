package org.example.springbootdemo.controller;

import org.example.springbootdemo.DAO.impl.EmpDaoA;
import org.example.springbootdemo.pojo.*;
import org.example.springbootdemo.service.EmpService;
import org.example.springbootdemo.service.impl.EmpServiceA;
import org.example.springbootdemo.utils.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired // dependency injection. IOC will produce the bean with the type that it needs;
    // private EmpService empService = new EmpServiceA();
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result list(){
        List<Emp> empList = empService.listEmp();

        // 3. response data
        return Result.success(empList);
    }

}
