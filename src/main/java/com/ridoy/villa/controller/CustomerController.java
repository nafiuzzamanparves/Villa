package com.ridoy.villa.controller;

import com.ridoy.villa.model.Customer;
import com.ridoy.villa.service.CustomerService;
import com.ridoy.villa.util.ApiResponse;
import com.ridoy.villa.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get all customers
    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            return ResponseEntity.ok(ResponseUtil.success("Customers retrieved successfully", customers));
        } else {
            return ResponseEntity.status(404).body(ResponseUtil.failed("No customers found", null));
        }
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(ResponseUtil.success("Customer found", customer)))
                .orElse(ResponseEntity.status(404).body(ResponseUtil.failed("Customer not found", null)));
    }

    // Create a new customer
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            if (createdCustomer != null) {
                return ResponseEntity.ok(ResponseUtil.success("Customer created successfully", createdCustomer));
            } else {
                return ResponseEntity.status(400).body(ResponseUtil.failed("Customer creation failed", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ResponseUtil.failed("Customer creation failed", e.getMessage()));
        }
    }

    // Update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
            return ResponseEntity.ok(ResponseUtil.success("Customer updated successfully", updatedCustomer));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Customer not found for update", null));
        }
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok(ResponseUtil.success("Customer deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ResponseUtil.failed("Customer not found for deletion", null));
        }
    }
}
