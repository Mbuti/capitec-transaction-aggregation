package za.co.capitec.capitec_transaction_aggregation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.capitec.capitec_transaction_aggregation.Entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);

}
