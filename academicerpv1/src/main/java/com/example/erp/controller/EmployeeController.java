package com.example.erp.controller;

import com.example.erp.bean.Employee;
import com.example.erp.bean.EmployeeDepartment;
import com.example.erp.bean.EmployeeTitle;
import com.example.erp.services.EmployeeService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("employee")
public class EmployeeController {
    EmployeeService employeeService = new EmployeeService();

    @GET
    @Path("/titles")
    public Response getTitle() {
        List<String> titles = new ArrayList<>();
        for (EmployeeTitle et : EmployeeTitle.values()) {
            titles.add(et.toString());
        }
        return Response.ok().entity(titles).build();
    }

    @GET
    @Path("/departments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartments() {
        List<String> departments = new ArrayList<>();
        for (EmployeeDepartment ed : EmployeeDepartment.values()) {
            departments.add(ed.toString());
        }
        return Response.ok().entity(departments).build();
    }


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginEmployee(Employee employee) throws URISyntaxException {
        Employee emp = employeeService.verifyCredential(employee);

        if(emp != null) {
            return Response.ok().entity(emp).build();
        }else{
            return Response.status(203).build();
        }
    }

    @POST
    @Path("/addEmployee")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws  URISyntaxException {
        if(employeeService.addEmployee(employee)){
            return Response.ok().build();
        }else{
            return Response.status(203).build();
        }
    }

    @POST
    @Path("/getEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmployees(Employee employee) {
        List<Employee> emplist = employeeService.getEmployeeList(Integer.parseInt(String.valueOf(employee.getEmployee_id())));

        return Response.ok().entity(emplist).build();
    }

}
