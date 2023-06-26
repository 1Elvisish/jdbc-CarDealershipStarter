package org.yearup.data;

import org.yearup.models.LeaseContract;
import org.yearup.models.SalesContract;

import java.util.List;

public interface SalesContractDao {

    SalesContract addContract(SalesContract contract);

    SalesContract create(SalesContract salesContract);

    SalesContract getById(int salesId);

    void update(SalesContract salesContract);

    void delete(int salesId);

    List<SalesContract> getAllSalesContracts();
}
