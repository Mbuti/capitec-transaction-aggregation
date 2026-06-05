package za.co.capitec.capitec_transaction_aggregation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CustomerResponse {

    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime createdDate;
}