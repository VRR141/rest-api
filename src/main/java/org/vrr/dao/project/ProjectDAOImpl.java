package org.vrr.dao.project;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vrr.entity.Customer;
import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Project> getAllProject() {
        Session session = sessionFactory.getCurrentSession();
        Query<Project> query = session.createQuery("select project from Project project " +
                "join fetch project.customer", Project.class);
        List<Project> result = query.getResultList();
        return result;
    }

    @Override
    public void saveProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(project);
    }

    @Override
    public Project getProject(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.get(Project.class, id);
        return project;
    }

    @Override
    public void removeProject(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Project> query = session.createQuery("delete from Project where id =: projectId");
        query.setParameter("projectId", id);
        query.executeUpdate();
    }

    public List<Employee> getEmployeeFromProject(int id){
        Session session = sessionFactory.getCurrentSession();
        Project project = session.get(Project.class, id);
        List<Employee> result = project.getEmployeeList();
        Hibernate.initialize(result);
        return result;
    }
}
