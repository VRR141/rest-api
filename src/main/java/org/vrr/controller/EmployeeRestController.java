package org.vrr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vrr.entity.Employee;
import org.vrr.exception_handling.NoSuchEmployeeException;
import org.vrr.service.employee.EmployeeService;
import org.vrr.service.position.PositionService;

import java.util.List;

@RestController
@Api(tags = {"Employees info"})
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all employees")
    public List<Employee> getAllEmployees(){
        List<Employee> result = employeeService.getAllEmployees();
        return result;
    }

    @ApiOperation(value = "Get employee by id")
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployee(@PathVariable int id){
        Employee result = employeeService.getEmployee(id);
        if (result == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id + " at Database");
        }
        return result;
    }

    @ApiOperation(value = "Add employee")
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Update employee")
    @RequestMapping(value = "/employees", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete employee by id")
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        if (employeeService.getEmployee(id) == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id + " at Database");
        }
        employeeService.removeEmployee(id);
        return new ResponseEntity<>(new String("Employee with id " + id + " successfully delete"),HttpStatus.OK);
    }
}
