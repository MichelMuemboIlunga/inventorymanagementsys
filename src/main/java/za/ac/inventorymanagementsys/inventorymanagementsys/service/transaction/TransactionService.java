package za.ac.inventorymanagementsys.inventorymanagementsys.service.transaction;

/*
Author : Honest Mpungu- 215072081
 */

import za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction.Transaction;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionModel;


public interface TransactionService {

    String CreateTransaction(TransactionModel transactionModel);

    String approveOrDeclineTransaction(TransactionDtoUpdate transactionDtoUpdate, Long transactionId);
    Transaction findTransactionByTransactionId(Long transactionId);

}