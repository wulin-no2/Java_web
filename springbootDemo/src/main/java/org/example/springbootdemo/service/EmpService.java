package org.example.springbootdemo.service;

import org.example.springbootdemo.pojo.Emp;

import java.util.List;

public interface EmpService {
    // get emp list
    public List<Emp> listEmp();
}
