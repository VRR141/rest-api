package org.vrr.dao.employee;

import org.vrr.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void removeEmployee(int id);

    List<Employee> getEmployeeByPositionsID(int id);

    List<Employee> getEmployeeByProjectID(int id);

}
