package org.vrr.dao.customer;

import org.vrr.entity.Customer;

import java.util.List;

public interface CustomerDAO {


    List<Customer> getAllCustomer();

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void removeCustomer(int id);

    void setProjectToCustomer(int customerId, int projectId);
}
