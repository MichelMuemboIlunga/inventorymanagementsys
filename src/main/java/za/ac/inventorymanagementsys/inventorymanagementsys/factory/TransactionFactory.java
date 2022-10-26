package za.ac.inventorymanagementsys.inventorymanagementsys.factory;

/*
Author : Honest Mpungu- 215072081
 */


import za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups.Role;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction.TransactionModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper;

import javax.transaction.Transaction;
import java.sql.Timestamp;

import static org.springframework.security.web.server.header.StaticServerHttpHeadersWriter.builder;

public class TransactionFactory {
    public static Transaction createTransaction(
            String fullName,
            String email,
            String password,
            String createdBy,
            String modifiedBy,
            java.util.Date createdDate,
            java.util.Date modifiedDate,
            Role roles) {

        if(Helper.validate(email) || Helper.IsNullOrEmptyField(email)){
            throw new IllegalArgumentException("Wrong Email Address");
        }


        return Transaction.builder().fullName(fullName).email(email)

                .password(password).createdBy(createdBy).modifiedBy(modifiedBy).createdDate(createdDate)
                .modifiedDate(modifiedDate).roles((Role) roles).build();
    }

    public static TransactionModel createNewTransaction(
            String fullName,
            String email,
            String password,
            String createdBy,
            java.util.Date createdDate,
            Long roleId) {

        if(Helper.IsNullOrEmptyField(fullName)){
            throw new IllegalArgumentException("Full Name is required");
        }

        return TransactionModel.builder().fullName(fullName).email(email)
                .password(password).createdBy(createdBy).createdDate((Timestamp) createdDate)
                .roleId(roleId).build();
    }
}
