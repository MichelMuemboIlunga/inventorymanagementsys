package za.ac.inventorymanagementsys.inventorymanagementsys.model.transaction;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoUpdate {

    @NotEmpty
    private Long TransactionStatusId;

    private String modifiedBy;

    private Timestamp modifiedDate;
}
