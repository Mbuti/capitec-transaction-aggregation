package za.co.capitec.capitec_transaction_aggregation.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.capitec.capitec_transaction_aggregation.dto.CreateTransactionRequest;
import za.co.capitec.capitec_transaction_aggregation.service.TransactionService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Map<String, String>>
    createTransaction(
            @RequestBody
            CreateTransactionRequest request) {

        String transactionId =
                service.createTransaction(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        Map.of(
                                "transactionId",
                                transactionId,
                                "status",
                                "PUBLISHED"));
    }
}
