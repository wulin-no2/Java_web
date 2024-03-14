package org.example.springbootdemo.service.impl;

import org.example.springbootdemo.DAO.EmpDAO;
import org.example.springbootdemo.DAO.impl.EmpDaoA;
import org.example.springbootdemo.pojo.Emp;
import org.example.springbootdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
//@Component // inversion of control. now it's a bean;
@Service
public class EmpServiceA implements EmpService {
    @Autowired // dependency injection. IOC will produce the bean with the type that it needs;
    // private EmpDAO empDaoA = new EmpDaoA();
    private EmpDAO empDaoA;
    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDaoA.listEmp();
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
        return empList;
    }
}
