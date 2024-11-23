package com.ridoy.villa.service;


import com.ridoy.villa.model.Customer;
import com.ridoy.villa.model.Room;
import com.ridoy.villa.model.enums.Status;
import com.ridoy.villa.repository.CustomerRepository;
import com.ridoy.villa.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        Room room = roomRepository.findById(customer.getRoom().getRoomId()).orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (!room.getStatus().equals(Status.AVAILABLE)) {
            throw new IllegalArgumentException("Room is not available. Room is already occupied.");
        }

        Customer savedCustomer = customerRepository.save(customer);

        // Update the room status to Occupied
        room.setStatus(Status.OCCUPIED);
        room.setCustomer(savedCustomer);
        roomRepository.save(room);

        return savedCustomer;
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findByCustomerId(id).orElseThrow();
        Room associatedRoom = customer.getRoom(); // Lazy loading

        Customer savedCustomer;
        Long givenRoomId = customerDetails.getRoom().getRoomId();

        if (customerDetails.getFirstName() != null) customer.setFirstName(customerDetails.getFirstName());
        if (customerDetails.getLastName() != null) customer.setLastName(customerDetails.getLastName());
        if (customerDetails.getEmail() != null) customer.setEmail(customerDetails.getEmail());
        if (customerDetails.getQid() != null) customer.setQid(customerDetails.getQid());
        if (customerDetails.getPassport() != null) customer.setPassport(customerDetails.getPassport());
        if (customerDetails.getSecurityMoney() != null) customer.setSecurityMoney(customerDetails.getSecurityMoney());
        if (customerDetails.getCollectionType() != null)
            customer.setCollectionType(customerDetails.getCollectionType());

        if (!Objects.equals(associatedRoom.getRoomId(), givenRoomId)) {
            // Update the existing room status to Available
            associatedRoom.setStatus(Status.AVAILABLE);
            associatedRoom.setCustomer(null);
            roomRepository.save(associatedRoom);

            savedCustomer = customerRepository.save(customer);

            // Update the given room status to Occupied
            Room givenRoom = roomRepository.findById(givenRoomId).orElseThrow(() -> new IllegalArgumentException("Given Room not found"));
            givenRoom.setStatus(Status.OCCUPIED);
            givenRoom.setCustomer(savedCustomer);
            roomRepository.save(givenRoom);
        } else {
            savedCustomer = customerRepository.save(customer);
        }

        return savedCustomer;
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
    }
}
