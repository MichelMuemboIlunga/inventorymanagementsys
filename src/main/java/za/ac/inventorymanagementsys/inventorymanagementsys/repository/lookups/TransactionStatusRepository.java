package za.ac.inventorymanagementsys.inventorymanagementsys.repository.lookups;

/*
Author : Honest Mpungu- 215072081
 */

import org.springframework.transaction.TransactionStatus;

public interface TransactionStatusRepository {
    TransactionStatus findByTransactionStatusId(Long transactionStatusId);
}
