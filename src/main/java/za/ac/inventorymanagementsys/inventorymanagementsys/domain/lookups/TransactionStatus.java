package za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups;

/*
Author : Honest Mpungu- 215072081
 */

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.transaction.Transaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tTransactionStatus")
@NoArgsConstructor
public class TransactionStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, updatable = false)
    private Long transactionStatusId;

    @Column(nullable = false, unique = true)
    private String description;

    @OneToMany(mappedBy="transactionId")
    private List<Transaction> transaction;
}
