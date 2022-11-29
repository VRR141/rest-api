package org.vrr.service.customer;

import org.vrr.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void removeCustomer(int id);

    void setProjectToCustomer(int customerId, int projectId);

}
