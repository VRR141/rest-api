package org.vrr.dao.project;

import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

public interface ProjectDAO {

    List<Project> getAllProject();

    void saveProject(Project project);

    Project getProject(int id);

    void removeProject(int id);

    List<Employee> getEmployeeFromProject(int id);

    void setProjectToEmployee(int projectId, int employeeId);
}
