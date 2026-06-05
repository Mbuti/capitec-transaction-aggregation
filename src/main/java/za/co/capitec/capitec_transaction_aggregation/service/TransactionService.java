package za.co.capitec.capitec_transaction_aggregation.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.dto.CreateTransactionRequest;
import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;
import za.co.capitec.capitec_transaction_aggregation.kafka.TransactionProducer;
import za.co.capitec.capitec_transaction_aggregation.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final CustomerRepository customerRepository;

    private final TransactionProducer producer;

    public String createTransaction(
            CreateTransactionRequest request) {

        customerRepository.findById(
                        request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found"));

        TransactionDTO dto =
                TransactionDTO.builder()
                        .transactionId(
                                UUID.randomUUID().toString())
                        .customerId(
                                request.getCustomerId())
                        .merchant(
                                request.getMerchant())
                        .amount(
                                request.getAmount())
                        .transactionDate(
                                LocalDateTime.now())
                        .build();

        producer.publish(dto);

        return dto.getTransactionId();
    }
}