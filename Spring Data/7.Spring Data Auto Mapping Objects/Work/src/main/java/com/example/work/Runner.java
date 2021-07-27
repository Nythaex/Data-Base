package com.example.work;

import com.example.work.services.interfaces.EmployeeService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {
    private final EmployeeService employeeService;

    public Runner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        employeeService.getManagers().forEach(s->{
            System.out.println(s.getFirstName()+" "+s.getLastName()+" | Employees: "+s.getEmployees().size());
            s.getEmployees().forEach(employee -> System.out.println("    - "+employee.getFirstName()+" "+employee.getLastName()+" "+employee.getSalary()));
        });

    }
}
