package za.co.capitec.capitec_transaction_aggregation.source;

import za.co.capitec.capitec_transaction_aggregation.dto.TransactionDTO;

import java.util.List;

public interface TransactionSource {
    public List<TransactionDTO> fetchTransactions();
}
