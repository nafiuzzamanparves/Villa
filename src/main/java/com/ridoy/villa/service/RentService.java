package com.ridoy.villa.service;

import com.ridoy.villa.model.Customer;
import com.ridoy.villa.model.Rent;
import com.ridoy.villa.model.Room;
import com.ridoy.villa.repository.CustomerRepository;
import com.ridoy.villa.repository.RentRepository;
import com.ridoy.villa.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Get all Rent records for a specific Room
    public List<Rent> getRentsByRoomId(Long roomId) {
        return rentRepository.findByRoom_RoomId(roomId);
    }

    // Create a new Rent record with automatic status calculation
    public Rent createRent(Rent rent) {
        // Retrieve the Room based on roomId
        Room room = roomRepository.findById(rent.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + rent.getRoom().getRoomId()));

        // Set the totalRent from Room's rentAmount
        rent.setTotalRent(room.getRentAmount());

        // Calculate status based on the amountPaid
        calculateRentStatus(rent);

        // Save and return the Rent record
        return rentRepository.save(rent);
    }

    // Update an existing Rent record
    public Rent updateRent(Long rentId, Rent rentDetails) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new RuntimeException("Rent not found with id: " + rentId));

        // Retrieve the Room based on roomId
        Room room = roomRepository.findById(rentDetails.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + rentDetails.getRoom().getRoomId()));

        // Set the totalRent from Room's rentAmount
        rent.setTotalRent(room.getRentAmount());

        // Update fields
        rent.setYear(rentDetails.getYear());
        rent.setMonth(rentDetails.getMonth());
        rent.setAmountPaid(rentDetails.getAmountPaid());
        rent.setCustomer(rentDetails.getCustomer());

        // Calculate status based on the amountPaid
        calculateRentStatus(rent);

        return rentRepository.save(rent);
    }

    // Calculate Rent status based on the totalRent and amountPaid
    private void calculateRentStatus(Rent rent) {
        BigDecimal remainingAmount = rent.getTotalRent().subtract(rent.getAmountPaid());

        if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            // Full payment
            rent.setStatus("Paid");
        } else if (remainingAmount.compareTo(BigDecimal.ZERO) > 0 && rent.getAmountPaid().compareTo(BigDecimal.ZERO) > 0) {
            // Partial payment
            rent.setStatus("Partial");
        } else {
            // No payment or payment with security
            handleRentPayment(rent);
        }
    }

    // Logic for handling payment with security money
    private void handleRentPayment(Rent rent) {
        BigDecimal remainingAmount = rent.getTotalRent().subtract(rent.getAmountPaid());

        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0 && rent.getCustomer() != null) {
            Customer customer = rent.getCustomer();
            BigDecimal customerSecurityMoney = customer.getSecurityMoney();

            // Use security money if available
            if (customerSecurityMoney.compareTo(remainingAmount) >= 0) {
                // Deduct from security
                customer.setSecurityMoney(customerSecurityMoney.subtract(remainingAmount));
                rent.setSecurityMoneyUsed(remainingAmount);
                rent.setStatus("Paid");
            } else if (customerSecurityMoney.compareTo(BigDecimal.ZERO) > 0) {
                // Partially deduct
                rent.setSecurityMoneyUsed(customerSecurityMoney);
                rent.setAmountPaid(rent.getAmountPaid().add(customerSecurityMoney));
                rent.setStatus("Partial");
                customer.setSecurityMoney(BigDecimal.ZERO);
            } else {
                // Insufficient payment
                rent.setStatus("Unpaid");
            }

            // Save updated customer security amount
            customerRepository.save(customer);
        } else if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            rent.setStatus("Paid");
        }
    }

    // Delete a Rent record
    public void deleteRent(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new RuntimeException("Rent not found with id: " + rentId));
        rentRepository.delete(rent);
    }
}
