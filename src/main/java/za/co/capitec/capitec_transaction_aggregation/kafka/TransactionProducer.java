package za.co.capitec.capitec_transaction_aggregation.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    @Value("${aggregation.kafka.topic}")
    private String topic;

    public void publish(TransactionDTO transaction) {

        kafkaTemplate.send(
                topic,
                transaction.getTransactionId(),
                transaction
        );
    }
}
