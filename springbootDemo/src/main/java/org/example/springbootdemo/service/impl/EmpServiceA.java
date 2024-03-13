package org.example.springbootdemo.service.impl;

import org.example.springbootdemo.DAO.EmpDAO;
import org.example.springbootdemo.DAO.impl.EmpDaoA;
import org.example.springbootdemo.pojo.Emp;
import org.example.springbootdemo.service.EmpService;

import java.util.List;

public class EmpServiceA implements EmpService {
    private EmpDAO empDaoA = new EmpDaoA();
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
