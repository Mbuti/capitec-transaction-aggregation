package za.co.capitec.capitec_transaction_aggregation.Entity;

import jakarta.persistence.*;
import lombok.*;
import za.co.capitec.capitec_transaction_aggregation.Enum.TransactionCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    private String transactionId;

    private Long customerId;

    private String merchant;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionCategory category;
}