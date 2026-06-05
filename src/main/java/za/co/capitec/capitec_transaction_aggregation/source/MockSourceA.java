package za.co.capitec.capitec_transaction_aggregation.source;

import org.springframework.stereotype.Component;
import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class MockSourceA implements TransactionSource {

    @Override
    public List<TransactionDTO> fetchTransactions() {

        return List.of(
                TransactionDTO.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .customerId(1L)
                        .merchant("Uber")
                        .amount(BigDecimal.valueOf(250))
                        .transactionDate(LocalDateTime.now())
                        .build()
        );
    }
}
