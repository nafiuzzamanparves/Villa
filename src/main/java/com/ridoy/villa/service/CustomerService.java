package com.ridoy.villa.service;


import com.ridoy.villa.model.Customer;
import com.ridoy.villa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (customerDetails.getFirstName() != null)
            customer.setFirstName(customerDetails.getFirstName());
        if (customerDetails.getLastName() != null)
            customer.setLastName(customerDetails.getLastName());
        if (customerDetails.getEmail() != null)
            customer.setEmail(customerDetails.getEmail());
        if (customerDetails.getQid() != null)
            customer.setQid(customerDetails.getQid());
        if (customerDetails.getPassport() != null)
            customer.setPassport(customerDetails.getPassport());
        if (customerDetails.getSecurityMoney() != null)
            customer.setSecurityMoney(customerDetails.getSecurityMoney());
        if (customerDetails.getCollectionType() != null)
            customer.setCollectionType(customerDetails.getCollectionType());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
    }
}
