package com.example.erp.dao.impl;

import com.example.erp.bean.Employee;
import com.example.erp.bean.EmployeeSalary;
import com.example.erp.dao.EmployeeSalaryDAO;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {

    @Override
    public boolean addEmployeeSalary(List<EmployeeSalary> employeesalary) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            for(EmployeeSalary sal: employeesalary) {
                sal.setPayment_date(Date.valueOf(LocalDate.now()));
                session.save(sal);
            }
            session.getTransaction().commit();
            session.close();

            return true;
        }
        catch (HibernateException e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<EmployeeSalary> getAlreadyDisbursedSalList() {
        Session session=SessionUtil.getSession();
        try {
            LocalDate currentdate = LocalDate.now();
            int currentMonth= currentdate.getMonthValue();
            int currentYear=currentdate.getYear();
            System.out.println(currentYear + " " +currentMonth);
            Query query=session.createQuery("from EmployeeSalary where Year(payment_date)=:year and Month(payment_date)=:month");

            query.setParameter("year",currentYear);
            query.setParameter("month",currentMonth);

            return query.getResultList();
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }

    public List<EmployeeSalary> getSalRecords(Integer emp_id){
        Session session=SessionUtil.getSession();
        try {
            System.out.println(emp_id);
            Query query=session.createQuery("from EmployeeSalary where employee.employee_id=:emp_id order by date(payment_date) desc");
            query.setParameter("emp_id",emp_id);

            return query.getResultList();
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }

    public boolean addSalaryByRecords(EmployeeSalary empsal){
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            empsal.setPayment_date(Date.valueOf(LocalDate.now()));
            session.save(empsal);
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
    public boolean updateEmployeeSalary(EmployeeSalary empsal) {
        try (Session session = SessionUtil.getSession()) {
            Transaction tx = null;
            tx=session.beginTransaction();
            System.out.println("In Update Txn");
            Query query = session.createQuery("update EmployeeSalary set amount=:amount, description=:des"+" where id=:id");
            query.setParameter("id", empsal.getId());
            query.setParameter("amount", empsal.getAmount());
            query.setParameter("des",empsal.getDescription());
//            query.setParameter("cn",empsal.getCollege_name());
//            query.setParameter("adr",empsal.);
            int result = query.executeUpdate();
           tx.commit();
            if(result>=1) return true;
            else return false;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }


    }

}
