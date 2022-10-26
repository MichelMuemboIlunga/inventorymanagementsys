package za.ac.inventorymanagementsys.inventorymanagementsys.controller.transaction;

/*
Author : Honest Mpungu- 215072081
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction.Transaction;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.service.transaction.impl.TransactionServiceImpl;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/Transaction")
public class TransactionController {

    @Autowired
    private final TransactionServiceImpl transactionServiceImpl;
    private Object TransactionDtoUpdate;

    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @RequestMapping(value = "/find/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId, Model model) {
        Transaction transaction = transactionServiceImpl.findTransactionByTransactionId(transactionId);
        model.addAttribute("transaction", transaction);

        if(transaction == null) {
            return new ResponseEntity("No Transaction Found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(transaction, HttpStatus.OK);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions(Model model) {
        List<TransactionModel> transactions = transactionServiceImpl.findAllTransactions();
        model.addAttribute("transactions", transactions);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionModel transactionModel,
                                                         BindingResult bindingResult,
                                                         Model model) {

        String result = transactionServiceImpl.CreateTransaction(transactionModel);

        if(Objects.equals(result, "DEC01")){
            return new ResponseEntity("Transaction Not Created", HttpStatus.BAD_REQUEST);
        }else{

            return new ResponseEntity(result, HttpStatus.CREATED);
        }
    }

    @PutMapping("/approvedOrDeclined/{transactionId}")
    public ResponseEntity<Transaction> approveOrDeclineTransaction(@RequestBody TransactionDtoUpdate transactionDtoUpdate,
                                                                   @PathVariable Long transactionId){

        Transaction transaction = transactionServiceImpl.findTransactionByTransactionId(transactionId);

        String result = transactionServiceImpl.approveOrDeclineTransaction((za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionDtoUpdate) TransactionDtoUpdate, transactionId);

        if (Objects.equals(result, "DEC01"))
            return new ResponseEntity("Transaction was Not Found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity("Transaction Successfully Approved/Decline", HttpStatus.OK);

    }

}