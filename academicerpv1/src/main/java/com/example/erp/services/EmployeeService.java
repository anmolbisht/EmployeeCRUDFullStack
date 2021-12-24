package com.example.erp.services;


import com.example.erp.bean.Employee;
import com.example.erp.dao.EmployeeDAO;
import com.example.erp.dao.impl.EmployeeDAOImpl;

import java.util.List;

public class EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public Employee verifyCredential(Employee employee){
        return employeeDAO.verifyCredential(employee);
    }

    public boolean addEmployee(Employee employee){
        return employeeDAO.addEmployee(employee);
    }

    public List<Employee> getEmployeeList(Integer emp_id) { return employeeDAO.getEmployees(emp_id); }
}
