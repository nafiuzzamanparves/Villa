package com.ridoy.villa.service;

import com.ridoy.villa.dto.RoomDTO;
import com.ridoy.villa.model.Customer;
import com.ridoy.villa.model.Rent;
import com.ridoy.villa.model.enums.PaidStatus;
import com.ridoy.villa.repository.CustomerRepository;
import com.ridoy.villa.repository.RentRepository;
import com.ridoy.villa.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    // Get all Rent records for a specific Room
    public List<Rent> getRentsByRoomId(Long roomId) {
        return rentRepository.findByRoom_RoomId(roomId);
    }

    public List<Rent> getRentsByRoomIdAndYear(Long roomId, Integer year) {
        return rentRepository.findByYearAndRoom_RoomId(year, roomId);
    }

    // Create a new Rent record with automatic status calculation
    @Transactional
    public Rent createRent(Rent rent) {
        // Retrieve the Room based on roomId
        RoomDTO room = roomRepository.findByRoomId(rent.getRoom().getRoomId()).orElseThrow(() -> new RuntimeException("Room not found with id: " + rent.getRoom().getRoomId()));
        BigDecimal rentAmount = room.getRentAmount();
        Long customerId = room.getCustomerId();
        Customer customer;

        if (customerId != null) {
            customer = customerRepository.findByCustomerId(customerId).orElseThrow();
            rent.setCustomer(customer);
        } else {
            throw new RuntimeException("No customer found for this room with id: " + room.getRoomId());
        }

        // Set the totalRent from Room's rentAmount
        rent.setTotalRent(rentAmount);

        // Calculate status based on the amountPaid
        calculateRentStatus(rent, customer);

//        return null;
        // Save and return the Rent record
        return rentRepository.save(rent);
    }

    // Update an existing Rent record
    public Rent updateRent(Long rentId, Rent rentDetails) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new RuntimeException("Rent not found with id: " + rentId));

        // Retrieve the Room based on roomId
        RoomDTO room = roomRepository.findByRoomId(rentDetails.getRoom().getRoomId()).orElseThrow(() -> new RuntimeException("Room not found with id: " + rentDetails.getRoom().getRoomId()));
        Long customerId = room.getCustomerId();
        Customer customer = customerRepository.findByCustomerId(customerId).orElseThrow();

        // Set the totalRent from Room's rentAmount
        rent.setTotalRent(room.getRentAmount());

        // Update fields
        rent.setYear(rentDetails.getYear());
        rent.setMonth(rentDetails.getMonth());
        rent.setAmountPaid(rentDetails.getAmountPaid());
        rent.setCustomer(rentDetails.getCustomer());

        // Calculate status based on the amountPaid
        calculateRentStatus(rent, customer);

        Rent save = rentRepository.save(rent);
        if (save.getPaidStatus() == null) {
            throw new RuntimeException("Your payable rent has not been paid. Please contact the villa manager.");
        } else {
            return save;
        }
    }

    // Calculate Rent status based on the totalRent and amountPaid
    private void calculateRentStatus(Rent rent, Customer customer) {
        BigDecimal remainingAmount = rent.getTotalRent().subtract(rent.getAmountPaid());
        boolean securityMoneyUsed = rent.isSecurityMoneyUsed();

        if (securityMoneyUsed) {
            // Deduct from security
            boolean securityMoneyDeduction = handleSecurityMoneyDeduction(rent, customer);
            if (!securityMoneyDeduction) {
                throw new RuntimeException("Your payable rent has not been paid. you don't have sufficient security money. Please contact the villa manager.");
            }
        } else {
            if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                rent.setPaidStatus(PaidStatus.PAID);
            } else if (remainingAmount.compareTo(BigDecimal.ZERO) > 0 && rent.getAmountPaid().compareTo(BigDecimal.ZERO) > 0) {
                rent.setPaidStatus(PaidStatus.PARTIAL);
            }
        }
    }

    // Logic for handling payment with security money
    private boolean handleSecurityMoneyDeduction(Rent rent, Customer customer) {
        BigDecimal totalRent = rent.getTotalRent();
        BigDecimal customerSecurityMoney = customer.getSecurityMoney();

        // Check if customer has sufficient security money to cover the total rent
        if (customerSecurityMoney.compareTo(totalRent) >= 0) {
            customer.setSecurityMoney(customerSecurityMoney.subtract(totalRent));
            rent.setPaidStatus(PaidStatus.PAID);

            customerRepository.save(customer);

            return true;
        } else if (customerSecurityMoney.compareTo(BigDecimal.ZERO) > 0) {
            // Subtract available security money and mark the rent as paid
            rent.setAmountPaid(customerSecurityMoney);
            customer.setSecurityMoney(BigDecimal.ZERO);
            rent.setPaidStatus(PaidStatus.PARTIAL);

            customerRepository.save(customer);

            return true;
        } else {
            return false;
        }
    }


    @SuppressWarnings("unused")
    public void deleteRent(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow(() -> new RuntimeException("Rent not found with id: " + rentId));
        rentRepository.delete(rent);
    }

    public List<Integer> getDistinctYears() {
        List<Integer> years = rentRepository.findDistinctYears();

        // If the list is empty, add the current year
        if (years.isEmpty()) {
            int currentYear = LocalDate.now().getYear();
            years.add(currentYear);
        }

        return years;
    }

}
