package org.vrr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vrr.entity.Employee;
import org.vrr.entity.Project;
import org.vrr.exception_handling.NoSuchEmployeeException;
import org.vrr.service.project.ProjectService;

import java.util.List;

@RestController
public class ProjectRestController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getAllProject(){
        List<Project> result = projectService.getAllProject();
        return result;
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Project getProject(@PathVariable int id){
        Project result = projectService.getProject(id);
        if (result == null){
            throw new NoSuchEmployeeException("There is no project with id " + id + " at Database");
        }
        return result;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        projectService.saveProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        projectService.saveProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProject(@PathVariable int id){
        if (projectService.getProject(id) == null){
            throw new NoSuchEmployeeException("There is no project with id " + id + " at Database");
        }
        projectService.removeProject(id);
        return new ResponseEntity<>(new String("Project with id " + id + " successfully delete"),HttpStatus.OK);
    }

    @RequestMapping(value = "/projectsEmployee/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable int id){
        if (projectService.getProject(id) == null){
            throw new NoSuchEmployeeException("There is no project with id " + id + " at Database");
        }
        List<Employee> result = projectService.getEmployeeFromProject(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
