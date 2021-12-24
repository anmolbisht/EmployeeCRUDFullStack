package com.example.erp.dao;

import com.example.erp.bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    public boolean addEmployee(Employee employee);
    public Employee verifyCredential(Employee employee);
    public List<Employee> getEmployees(Integer emp_id);
}
