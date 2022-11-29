package org.vrr.dao.customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vrr.entity.Customer;
import org.vrr.entity.Employee;
import org.vrr.entity.Project;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getAllCustomer() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("select customer from Customer customer " +
                "join fetch customer.project", Customer.class);
        List<Customer> result = query.getResultList();
        return result;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void removeCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("delete from Customer where id =: customerId");
        query.setParameter("customerId",id);
        query.executeUpdate();
    }

    public void setProjectToCustomer(int customerId, int projectId){
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("update Customer set project.id =: projId where id =: custId");
        query.setParameter("projId", projectId);
        query.setParameter("custId", customerId);
        query.executeUpdate();
    }
}
