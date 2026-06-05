package za.co.capitec.capitec_transaction_aggregation.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.Entity.Customer;
import za.co.capitec.capitec_transaction_aggregation.dto.CreateCustomerRequest;
import za.co.capitec.capitec_transaction_aggregation.dto.CustomerResponse;
import za.co.capitec.capitec_transaction_aggregation.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse createCustomer(
            CreateCustomerRequest request) {

        repository.findByEmail(request.getEmail())
                .ifPresent(customer -> {
                    throw new RuntimeException(
                            "Customer already exists");
                });

        Customer customer =
                Customer.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .createdDate(LocalDateTime.now())
                        .build();

        Customer saved =
                repository.save(customer);

        return CustomerResponse.builder()
                .customerId(saved.getCustomerId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .createdDate(saved.getCreatedDate())
                .build();
    }

    public List<CustomerResponse> getAllCustomers() {

        return repository.findAll()
                .stream()
                .map(customer ->
                        CustomerResponse.builder()
                                .customerId(customer.getCustomerId())
                                .firstName(customer.getFirstName())
                                .lastName(customer.getLastName())
                                .email(customer.getEmail())
                                .createdDate(customer.getCreatedDate())
                                .build())
                .toList();
    }
}
