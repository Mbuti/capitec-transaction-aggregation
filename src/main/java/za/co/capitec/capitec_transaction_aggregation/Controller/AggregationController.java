package za.co.capitec.capitec_transaction_aggregation.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.capitec.capitec_transaction_aggregation.service.AggregationService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class AggregationController {

    private final AggregationService service;

    @PostMapping("/aggregate")
    public ResponseEntity<Map<String, Object>> aggregate() {

        Integer count = service.aggregate();

        return ResponseEntity.ok(
                Map.of(
                        "status", "SUCCESS",
                        "recordsProcessed", count
                )
        );
    }
}
