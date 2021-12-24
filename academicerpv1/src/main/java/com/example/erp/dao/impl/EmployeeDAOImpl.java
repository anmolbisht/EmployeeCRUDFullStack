package com.example.erp.dao.impl;

import com.example.erp.bean.Employee;
import com.example.erp.dao.EmployeeDAO;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean addEmployee(Employee employee) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Integer employee_id = (Integer)session.save(employee);
            System.out.println("Employee Created with Id: " + employee_id);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public Employee verifyCredential(Employee employee) {
        Session session = SessionUtil.getSession();
        try
        {
            Query query = session.createQuery("from Employee where email=:email and password=:password and department=:department");
            query.setParameter("email", employee.getEmail());
            query.setParameter("password", employee.getPassword());
            query.setParameter("department", employee.getDepartment());

            List<Employee> emp = query.getResultList();

            if(emp.size()==1){
                session.close();
                return emp.get(0);
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees(Integer emp_id) {
        Session session=SessionUtil.getSession();

        try {
            LocalDate currentdate = LocalDate.now();
            int currentMonth= currentdate.getMonthValue();
            int currentYear=currentdate.getYear();
            Query query=session.createQuery("from Employee as e where e.employee_id not in (select s.employee.employee_id from EmployeeSalary as s where Year(s.payment_date)=:year and Month(s.payment_date)=:month) and e.employee_id !=: emp_id");
            query.setParameter("year",currentYear);
            query.setParameter("month",currentMonth);
            query.setParameter("emp_id", emp_id);

            return query.getResultList();
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }
}
