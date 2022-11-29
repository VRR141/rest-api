package org.vrr.service.project;

import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProject();

    void saveProject(Project project);

    Project getProject(int id);

    void removeProject(int id);

    int getNumberOfEmployees(int id);

    List<Employee> getEmployeeFromProject(int id);
}
