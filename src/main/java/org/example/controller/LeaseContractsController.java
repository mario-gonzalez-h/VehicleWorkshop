package org.example.controller;

import org.example.dao.LeaseDao;
import org.example.model.LeaseContract;

import java.sql.Date;
import java.util.List;

public class LeaseContractsController {

    private LeaseDao leaseDao;

    public LeaseContractsController() {
        this.leaseDao = new LeaseDao();
    }

    public LeaseContract getLeaseContractById(int id) {
        return leaseDao.getLeaseContractById(id);
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        leaseDao.addLeaseContract(leaseContract);
    }

    public List<LeaseContract> getLeaseInformationWithDateRange(int dealerID, Date startDate, Date endDate) {
        return leaseDao.getLeaseInformationWithDateRange(dealerID, startDate, endDate);
    }
}
