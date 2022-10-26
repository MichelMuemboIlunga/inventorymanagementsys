package za.ac.inventorymanagementsys.inventorymanagementsys.repository.transaction;

/*
Author : Honest Mpungu- 215072081
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionByTransactionId(Long transactionId);
}
