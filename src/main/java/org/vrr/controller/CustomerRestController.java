
package org.vrr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vrr.entity.Customer;
import org.vrr.entity.Project;
import org.vrr.exception_handling.NoSuchEmployeeException;
import org.vrr.service.customer.CustomerService;
import org.vrr.service.project.ProjectService;

import java.util.List;

@RestController
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        List<Customer> result = customerService.getAllCustomer();
        return result;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id){
        Customer result = customerService.getCustomer(id);
        if (result == null){
            throw new NoSuchEmployeeException("There is no customer with id " + id + " at Database");
        }
        return result;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        if (customerService.getCustomer(id) == null){
            throw new NoSuchEmployeeException("There is no customer with id " + id + " at Database");
        }
        customerService.removeCustomer(id);
        return new ResponseEntity<>(new String("Customer with id " + id + " successfully delete"),HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}add{projectId}", method = RequestMethod.PATCH)
    public ResponseEntity<String> setProjectToCustomer(@PathVariable int customerId,
                                                       @PathVariable int projectId){

        customerService.setProjectToCustomer(customerId, projectId);
        return new ResponseEntity<>(new String("Customer with id " + customerId +
                " successfully get project " + projectId),HttpStatus.OK);
    }
}
