package com.example.erp.services;

import com.example.erp.bean.Employee;
import com.example.erp.bean.EmployeeSalary;
import com.example.erp.dao.EmployeeSalaryDAO;
import com.example.erp.dao.impl.EmployeeSalaryDAOImpl;

import java.util.List;

public class EmployeeSalaryService {
    EmployeeSalaryDAO empsaldao = new EmployeeSalaryDAOImpl();

    public boolean addEmployeeSalary(List<EmployeeSalary> empsalary) { return empsaldao.addEmployeeSalary(empsalary); }

    public List<EmployeeSalary> getAlreadyDisbursedSalList() { return empsaldao.getAlreadyDisbursedSalList(); }

    public List<EmployeeSalary> getSalRecords(Integer emp_id){ return empsaldao.getSalRecords(emp_id); }

    public  boolean addSalaryByRecords(EmployeeSalary empsal) { return empsaldao.addSalaryByRecords(empsal); }

    public boolean updateEmployeeSalary(EmployeeSalary empsal) { return empsaldao.updateEmployeeSalary(empsal);
    }
}
