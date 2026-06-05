package za.co.capitec.capitec_transaction_aggregation.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.Entity.Transaction;
import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;
import za.co.capitec.capitec_transaction_aggregation.repository.TransactionRepository;
import za.co.capitec.capitec_transaction_aggregation.service.CategorizationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionConsumer {

    private final TransactionRepository repository;
    private final CategorizationService categorizationService;

    @KafkaListener(
            topics = "${aggregation.kafka.topic}",
            groupId = "aggregation-group"
    )
    public void consume(TransactionDTO dto) {

        Transaction transaction =
                Transaction.builder()
                        .transactionId(dto.getTransactionId())
                        .customerId(dto.getCustomerId())
                        .merchant(dto.getMerchant())
                        .amount(dto.getAmount())
                        .transactionDate(dto.getTransactionDate())
                        .category(
                                categorizationService
                                        .categorize(dto.getMerchant())
                        )
                        .build();

        repository.save(transaction);

        log.info(
                "Transaction persisted: {}",
                transaction.getTransactionId()
        );
    }
}
