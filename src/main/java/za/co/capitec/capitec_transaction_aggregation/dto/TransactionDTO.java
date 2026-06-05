package za.co.capitec.capitec_transaction_aggregation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    private String transactionId;

    private Long customerId;

    private String merchant;

    private BigDecimal amount;

    private LocalDateTime transactionDate;
}
