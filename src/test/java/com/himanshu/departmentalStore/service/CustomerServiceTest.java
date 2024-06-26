package com.himanshu.departmentalStore.service;

import com.himanshu.departmentalStore.model.Customer;
import com.himanshu.departmentalStore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCustomers() {
        // Mocking behavior
        List<Customer> customers = Arrays.asList(
                createCustomerMock(1L, "John", "Delhi", "1234567890"),
                createCustomerMock(2L, "Rahul", "Gurgaon", "9876543210")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        // Test
        List<Customer> result = customerService.getAllCustomers();

        // Verification
        assertEquals(customers, result);
    }

    @Test
    void getCustomerById() {
        // Mocking behavior
        Long customerId = 1L;
        Customer customer = createCustomerMock(customerId, "John", "Delhi", "1234567890");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Test
        Customer result = customerService.getCustomerById(customerId);

        // Verification
        assertNotNull(result);
        assertEquals(customerId, result.getId());
    }

    @Test
    void saveCustomer() {
        // Mocking behavior
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFullName("John");
        customer.setAddress("Delhi");
        customer.setContactNumber("1234567890");
        when(customerRepository.save(customer)).thenReturn(customer);

        // Test
        Customer result = customerService.saveCustomer(customer);

        // Verification
        assertNotNull(result.getId());
    }

    @Test
    void updateCustomer() {
        // Mocking behavior
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFullName("John");
        customer.setAddress("Delhi");
        customer.setContactNumber("1234567890");
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.existsById(customerId)).thenReturn(true);


        customer.setFullName("Rahul");
        // Test
        Customer result = customerService.updateCustomer(customerId, customer);


        // Verification
        assertEquals(customerId, result.getId());
    }

    @Test
    void deleteCustomer() {
        // Mocking behavior
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));
        doNothing().when(customerRepository).deleteById(customerId);

        // Test
        Boolean result = customerService.deleteCustomer(customerId);

        // Verification
        assertTrue(result);
    }

    private Customer createCustomerMock(Long id, String fullName, String address, String contactNumber) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setContactNumber(contactNumber);
        return customer;
    }
}
