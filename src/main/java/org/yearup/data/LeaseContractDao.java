package org.yearup.data;

import org.yearup.models.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

    LeaseContract addContract(LeaseContract contract);

    LeaseContract create(LeaseContract leaseContract);

    LeaseContract getById(int leaseId);

    void update(LeaseContract leaseContract);

    void delete(int leaseId);

    List<LeaseContract> getAllLeaseContracts();
}
