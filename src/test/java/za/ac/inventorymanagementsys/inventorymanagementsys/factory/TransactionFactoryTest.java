package za.ac.inventorymanagementsys.inventorymanagementsys.factory;

import org.junit.jupiter.api.Test;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups.Role;

import javax.transaction.Transaction;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionFactoryTest {

    /** Passed Test **/
@Test
void createUserPassedTest(){

    java.util.Date dt = new java.util.Date();
    Role roleId = new Role();
    //roleId.setRoleId(1L);

    Transaction transaction = TransactionFactory.createTransaction("Honest mpungu",
            "mpunguh@gmail.com","Honestdd","SYSTEM",
            "", dt,dt, roleId);
    System.out.println("Transaction created :" + transaction);
    assertNotNull(transaction);
}

    /** Failed Test **/
    @Test
    void createUserFailedTest(){
        java.util.Date dt = new java.util.Date();
        Role roleId = new Role();
        //roleId.setRoleId(1L);

        Transaction transaction = TransactionFactory.createTransaction("Mpungu Honest",
                "","Honestdddd","SYSTEM",
                "", dt,dt, roleId);
        System.out.println("User Not Created :" + transaction);
        assertNotNull(transaction);
    }

}

