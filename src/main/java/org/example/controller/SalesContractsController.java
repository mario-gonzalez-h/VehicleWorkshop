package org.example.controller;

import org.example.dao.SalesDao;
import org.example.model.SalesContract;

import java.sql.Date;
import java.util.List;

public class SalesContractsController {

    private SalesDao salesDao;

    public SalesContractsController() {
        this.salesDao = new SalesDao();
    }

    public SalesContract getSalesContractById(int id) {
        return salesDao.getSalesContractById(id);
    }

    public void addSalesContract(SalesContract salesContract) {
        salesDao.addSalesContract(salesContract);
    }

    public List<SalesContract> getSalesInformationWithDateRange(int dealerID, Date startDate, Date endDate) {
        return salesDao.getSalesInformationWithDateRange(dealerID, startDate, endDate);
    }
}
