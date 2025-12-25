package com.supplynext.company_api.jobs;

import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OperationScheduler {
     //The main function of this class is to Schedule a job to store all the features/operations that
    // the various users of our application will be using
    // THe Operation table will be populated with the operations of the users when we run the application
    // for the first time! THe scheduler is generally used for repetitive tasks hence we have set the
    // fixed delay to Long.MAX_VALUE which means it will  never be  added in the database again
    // The scheduled job will execute 5 seconds after our application runs for safety

    @Autowired
    private OperationRepository operationRepository;

    @Scheduled(initialDelay = 5000, fixedDelay = Long.MAX_VALUE)
    public void insertOperations() {
        this.insertOperation("COMPANY_CREATE", "COMPANY");
        this.insertOperation("COMPANY_VIEW", "COMPANY");
        this.insertOperation("COMPANY_UPDATE", "COMPANY");
        this.insertOperation("COMPANY_DELETE", "COMPANY");
        this.insertOperation("COMPANY_ONBOARD", "COMPANY");
        this.insertOperation("COMPANY_VERIFY", "COMPANY");
        this.insertOperation("COMPANY_ACTIVATE", "COMPANY");
        this.insertOperation("COMPANY_DEACTIVATE", "COMPANY");
        this.insertOperation("PRODUCT_CREATE", "PRODUCT");
        this.insertOperation("PRODUCT_VIEW", "PRODUCT");
        this.insertOperation("PRODUCT_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_DELETE", "PRODUCT");
        this.insertOperation("PRODUCT_PRICE_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_STOCK_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_BULK_UPLOAD", "PRODUCT");
        this.insertOperation("PO_CREATE", "PURCHASE_ORDER");
        this.insertOperation("PO_VIEW", "PURCHASE_ORDER");
        this.insertOperation("PO_UPDATE", "PURCHASE_ORDER");
        this.insertOperation("PO_CANCEL", "PURCHASE_ORDER");
        this.insertOperation("PO_APPROVE", "PURCHASE_ORDER");
        this.insertOperation("PO_REJECT", "PURCHASE_ORDER");
        this.insertOperation("PO_CLOSE", "PURCHASE_ORDER");
        this.insertOperation("SO_CREATE", "SALES_ORDER");
        this.insertOperation("SO_VIEW", "SALES_ORDER");
        this.insertOperation("SO_UPDATE", "SALES_ORDER");
        this.insertOperation("SO_CANCEL", "SALES_ORDER");
        this.insertOperation("SO_DISPATCH", "SALES_ORDER");
        this.insertOperation("SO_DELIVER", "SALES_ORDER");
        this.insertOperation("INVENTORY_VIEW", "INVENTORY");
        this.insertOperation("INVENTORY_ADD_STOCK", "INVENTORY");
        this.insertOperation("INVENTORY_REMOVE_STOCK", "INVENTORY");
        this.insertOperation("INVENTORY_ADJUSTMENT", "INVENTORY");
        this.insertOperation("INVENTORY_TRANSFER", "INVENTORY");
        this.insertOperation("USER_CREATE", "USER");
        this.insertOperation("USER_VIEW", "USER");
        this.insertOperation("USER_UPDATE", "USER");
        this.insertOperation("USER_DELETE", "USER");
        this.insertOperation("USER_ASSIGN_ROLE", "USER");
        this.insertOperation("USER_REVOKE_ROLE", "USER");
        this.insertOperation("PAYMENT_INITIATE", "PAYMENT");
        this.insertOperation("PAYMENT_RECEIVE", "PAYMENT");
        this.insertOperation("PAYMENT_REFUND", "PAYMENT");
        this.insertOperation("PAYMENT_FAILED", "PAYMENT");
    }

    public void insertOperation(String operationName, String operationType){

        if(operationRepository.findByOperationName(operationName)!=null ){
            log.info("Information exists already ");
            return;
        }
        Operation operation = new Operation();
        operation.setOperationName(operationName);
        operation.setOperationType(operationType);
        operationRepository.save(operation);
    }


}
