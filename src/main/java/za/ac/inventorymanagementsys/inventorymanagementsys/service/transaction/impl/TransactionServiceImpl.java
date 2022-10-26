package za.ac.inventorymanagementsys.inventorymanagementsys.service.transaction.impl;

/*
Author : Honest Mpungu- 215072081
 */

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction.Transaction;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.lookups.TransactionStatusRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.transaction.TransactionRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.service.transaction.TransactionService;
import za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper.declineCode;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionStatusRepository transactionStatusRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionStatusRepository transactionStatusRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionStatusRepository = transactionStatusRepository;
    }


    @Override
    public String CreateTransaction(TransactionModel transactionModel) {
        Transaction transaction = new Transaction();

        // global var
        String approveResponse;
        String declineResponse;

        // validate Entry Field Before Saving

        if(transactionModel.getCustomerId() > 0 && transactionModel.getOrderId() > 0 &&
                transactionModel.getQuantity() > 0 && transactionModel.getPrice() > 0

        ){
            transaction.setCustomerId(transactionModel.getCustomerId());
            transaction.setOrderId(transactionModel.getOrderId());
            transaction.setOrderStatusId(transactionModel.getOrderStatusId());
            transaction.setPrice(transactionModel.getPrice());
            transaction.setQuantity(transactionModel.getQuantity());
            transaction.setProductId(transactionModel.getProductId());
            transaction.setSupplierId(transactionModel.getSupplierId());

            TransactionStatus statusId = transactionStatusRepository.findByTransactionStatusId(transactionModel.getTransactionStatusId());

            transaction.setTransactionStatus(statusId);

            transaction.setCreatedBy(transaction.getCreatedBy());
            java.util.Date dt = new java.util.Date();
            transaction.setCreatedDate(dt);
            transactionRepository.save(transaction);
            approveResponse = Helper.approvedCode();
            return approveResponse;
        }else{
            declineResponse = declineCode();
            return declineResponse;
        }
    }

    @Override
    public String approveOrDeclineTransaction(TransactionDtoUpdate transactionDtoUpdate, Long transactionId) {
        // global var
        String approveResponse;
        String declineResponse;

        if(transactionId <= 0){
            declineResponse = Helper.declineCode();
            return declineResponse;
        }else{

            Transaction result = findTransactionByTransactionId(transactionId);

            if(result == null){
                declineResponse = Helper.declineCode();
                return declineResponse;
            }else{
                org.springframework.transaction.TransactionStatus updateStatus = transactionStatusRepository.findByTransactionStatusId(transactionDtoUpdate.getTransactionStatusId());
                result.setTransactionStatus(updateStatus);
                transactionRepository.save(result);

                approveResponse = Helper.approvedCode();
                return approveResponse;
            }

        }
    }

    @Override
    public Transaction findTransactionByTransactionId(Long transactionId) {
        return transactionRepository.findTransactionByTransactionId(transactionId);
    }

    public List<TransactionModel> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map((transaction) -> domainToModelConverter(transaction)).collect(Collectors.toList());
    }

    private TransactionModel domainToModelConverter(Transaction transaction){

        TransactionModel transactionModel = new TransactionModel();

        transactionModel.setCustomerId(transaction.getCustomerId());
        transactionModel.setOrderId(transaction.getOrderId());
        transactionModel.setOrderStatusId(transaction.getOrderStatusId());
        transactionModel.setPrice(transaction.getPrice());
        transactionModel.setProductId(transaction.getProductId());
        transactionModel.setQuantity(transaction.getQuantity());
        transactionModel.setSupplierId(transaction.getSupplierId());
        transactionModel.setTransactionStatusId(transaction.getTransactionStatus().getTransactionStatusId());;
        transactionModel.setCreatedBy(transaction.getCreatedBy());
        transactionModel.setCreatedDate((Timestamp) transaction.getCreatedDate());

        return transactionModel;
    }

}