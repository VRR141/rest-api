package org.vrr.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vrr.dao.customer.CustomerDAO;
import org.vrr.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    @Autowired
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Transactional
    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> result = customerDAO.getAllCustomer();
        return result;
    }

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Transactional
    @Override
    public Customer getCustomer(int id) {
        Customer customer = customerDAO.getCustomer(id);
        return customer;
    }

    @Transactional
    @Override
    public void removeCustomer(int id) {
        customerDAO.removeCustomer(id);
    }

    @Transactional
    @Override
    public void setProjectToCustomer(int customerId, int projectId) {
        customerDAO.setProjectToCustomer(customerId, projectId);
    }


}
