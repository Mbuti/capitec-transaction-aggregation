package za.co.capitec.capitec_transaction_aggregation.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Data
public class CreateTransactionRequest {

    @NotNull
    private Long customerId;

    @NotBlank
    private String merchant;

    @NotNull
    private BigDecimal amount;
}