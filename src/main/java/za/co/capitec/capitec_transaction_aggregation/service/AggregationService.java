package za.co.capitec.capitec_transaction_aggregation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;
import za.co.capitec.capitec_transaction_aggregation.kafka.TransactionProducer;
import za.co.capitec.capitec_transaction_aggregation.source.TransactionSource;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregationService {

    private final List<TransactionSource> sources;
    private final TransactionProducer producer;

    public Integer aggregate() {

        List<TransactionDTO> transactions =
                sources.stream()
                        .flatMap(source ->
                                source.fetchTransactions().stream())
                        .toList();

        transactions.forEach(producer::publish);

        return transactions.size();
    }
}