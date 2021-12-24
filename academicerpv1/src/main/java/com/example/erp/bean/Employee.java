package com.example.erp.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {
    @Override
    public String toString() {
        return "{" +
                "employee_id=" + getEmployee_id() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", department='" + getDepartment() +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    @Column(nullable = false)
    private String first_name;

    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeTitle title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeDepartment department;

    private String photograph_path;

    @JsonbTransient
    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<EmployeeSalary> employeeSalaries;

    public Employee(String first_name, String last_name, String email, String password, EmployeeTitle title, EmployeeDepartment department,String photograph_path, List<EmployeeSalary> employeeSalaries) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.title = title;
        this.department = department;
        this.photograph_path = photograph_path;
        this.employeeSalaries = employeeSalaries;
    }


}
