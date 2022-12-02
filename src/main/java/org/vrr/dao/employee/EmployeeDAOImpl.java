package org.vrr.dao.employee;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("select emp from Employee emp join fetch emp.position", Employee.class);
        List<Employee> employeeList = query.getResultList();
        return employeeList;
    }


    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void removeEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee where id =: employeeId", Employee.class);
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

    @Override
    public List<Employee> getEmployeeByPositionsID(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("select emp from Employee emp join fetch emp.position " +
                "where emp.position.id =: id", Employee.class);
        query.setParameter("id", id);
        List<Employee> result = query.getResultList();
        return result;
    }

    @Override
    public List<Employee> getEmployeeByProjectID(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.get(Project.class, id);
        List<Employee> result = project.getEmployeeList();
        Hibernate.initialize(result);
        return result;
    }
}
