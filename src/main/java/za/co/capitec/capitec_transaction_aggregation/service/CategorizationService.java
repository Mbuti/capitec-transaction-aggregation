package za.co.capitec.capitec_transaction_aggregation.service;

import org.springframework.stereotype.Service;
import za.co.capitec.capitec_transaction_aggregation.Enum.TransactionCategory;

@Service
public class CategorizationService {

    public TransactionCategory categorize(
            String merchant) {

        merchant = merchant.toLowerCase();

        if (merchant.contains("uber"))
            return TransactionCategory.TRANSPORT;

        if (merchant.contains("checkers"))
            return TransactionCategory.FOOD;

        if (merchant.contains("netflix"))
            return TransactionCategory.ENTERTAINMENT;

        return TransactionCategory.OTHER;
    }
}
