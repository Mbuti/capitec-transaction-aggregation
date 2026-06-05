package za.co.capitec.capitec_transaction_aggregation.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.capitec.capitec_transaction_aggregation.Entity.Transaction;
import za.co.capitec.capitec_transaction_aggregation.dto.CreateCustomerRequest;
import za.co.capitec.capitec_transaction_aggregation.dto.CustomerResponse;
import za.co.capitec.capitec_transaction_aggregation.repository.TransactionRepository;
import za.co.capitec.capitec_transaction_aggregation.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse>
    createCustomer(

            @RequestBody
            CreateCustomerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>
    getCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers());
    }
}