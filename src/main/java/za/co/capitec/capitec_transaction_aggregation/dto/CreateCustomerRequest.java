package za.co.capitec.capitec_transaction_aggregation.dto;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class CreateCustomerRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String email;
}
