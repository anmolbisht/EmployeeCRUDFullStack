package com.example.erp.controller;

import com.example.erp.bean.Employee;
import com.example.erp.bean.EmployeeSalary;
import com.example.erp.services.EmployeeSalaryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("employeesalary")
public class EmployeeSalaryController {
    EmployeeSalaryService employeeService = new EmployeeSalaryService();

    @POST
    @Path("/addsalary")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginEmployee(List<EmployeeSalary> empsal) throws URISyntaxException {
        if(employeeService.addEmployeeSalary(empsal)) {
            return Response.status(200).build();
        }
        else {
            return Response.status(203).build();
        }
    }
    @POST
    @Path("/updatesalary")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployeeSalary(EmployeeSalary empsal) throws URISyntaxException {
        if(employeeService.updateEmployeeSalary(empsal)) {
            return Response.status(200).build();
        }
        else {
            return Response.status(203).build();
        }
    }

    @GET
    @Path("/alreadyDisbursedSalary")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlreadyDisbursedSal() throws URISyntaxException {
        List<EmployeeSalary> empSal = employeeService.getAlreadyDisbursedSalList();;
        return Response.ok().entity(empSal).build();
    }


    @POST
    @Path("/getSalaryRecords")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmployees(Employee employee) {
        List<EmployeeSalary> sallist = employeeService.getSalRecords(Integer.parseInt(String.valueOf(employee.getEmployee_id())));

        return Response.ok().entity(sallist).build();
    }


    @POST
    @Path("/addSalaryByRecord")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSalaryByRecords(EmployeeSalary empsal) throws URISyntaxException {
        if(employeeService.addSalaryByRecords(empsal)) {
            return Response.status(200).build();
        }
        else {
            return Response.status(203).build();
        }
    }

}
