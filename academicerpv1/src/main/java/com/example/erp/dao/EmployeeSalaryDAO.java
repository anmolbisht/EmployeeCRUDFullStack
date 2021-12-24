package com.example.erp.dao;

import com.example.erp.bean.Employee;
import com.example.erp.bean.EmployeeSalary;

import java.util.List;

public interface EmployeeSalaryDAO {
    public boolean addEmployeeSalary(List<EmployeeSalary> employeesalary);

    public List<EmployeeSalary> getAlreadyDisbursedSalList();

    public List<EmployeeSalary> getSalRecords(Integer emp_id);

    public boolean addSalaryByRecords(EmployeeSalary empsal);
    public boolean updateEmployeeSalary(EmployeeSalary empsal);
}
