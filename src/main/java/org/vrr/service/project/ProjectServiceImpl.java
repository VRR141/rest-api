package org.vrr.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vrr.dao.project.ProjectDAO;
import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    private ProjectDAO projectDAO;

    @Autowired
    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Transactional
    @Override
    public List<Project> getAllProject() {
        List<Project> result = projectDAO.getAllProject();
        return result;
    }

    @Transactional
    @Override
    public void saveProject(Project project) {
        projectDAO.saveProject(project);
    }

    @Transactional
    @Override
    public Project getProject(int id) {
        Project project = projectDAO.getProject(id);
        return project;
    }

    @Transactional
    @Override
    public void removeProject(int id) {
        projectDAO.removeProject(id);
    }

    @Transactional
    @Override
    public int getNumberOfEmployees(int id) {
        Project project = projectDAO.getProject(id);
        int result = project.getEmployeeList().size();
        return result;
    }

    @Transactional
    @Override
    public List<Employee> getEmployeeFromProject(int id) {
        List<Employee> result = projectDAO.getEmployeeFromProject(id);
        return result;
    }
}
