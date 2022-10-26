package za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Long orderId;
    @NotEmpty
    private Long orderStatusId;
    @NotEmpty
    private double price;
    @NotEmpty
    private double quantity;
    @NotEmpty
    private Long productId;
    @NotEmpty
    private Long supplierId;
    @NotEmpty
    private Long transactionStatusId;

    private String createdBy;

    private Timestamp createdDate;

}