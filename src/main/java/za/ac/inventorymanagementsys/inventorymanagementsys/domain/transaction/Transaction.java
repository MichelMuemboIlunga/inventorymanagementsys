package za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction;

/*
Author : Honest Mpungu- 215072081
 */

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tTransaction")
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, updatable = false)
    private Long transactionId;
    private Long customerId;
    private Long productId;
    private Long orderId;
    private Long orderStatusId;
    private double price;
    private double quantity;
    private Long supplierId;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date modifiedDate;
    private String createdBy;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name="transactionStatusId", referencedColumnName="transactionStatusId")
    private TransactionStatus transactionStatus;


}