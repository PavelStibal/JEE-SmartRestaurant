package cz.kucharo2.service.impl;

import cz.kucharo2.data.dao.BillDao;
import cz.kucharo2.data.dao.BillItemDao;
import cz.kucharo2.data.entity.Bill;
import cz.kucharo2.data.entity.BillItem;
import cz.kucharo2.data.entity.RestaurantTable;
import cz.kucharo2.service.CashDeskService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Roman on 12/2/2014.
 */
@ApplicationScoped
public class CashDeskServiceImpl implements CashDeskService {

	@Inject
	private BillDao billDao;

	@Inject
	private BillItemDao billItemDao;

	@Override
	public Bill getBillById(int id) {
		return billDao.getById(id);
	}

	@Override
	public Bill createBillOnTable(RestaurantTable table) {
		Bill bill = new Bill();
		bill.setTable(table);
		return bill;
	}


	@Override
	public void createBillItem(List<BillItem> billItem) {
		for(BillItem billItemFood : billItem) {
			billItemDao.createOrUpdate(billItemFood);
		}
	}

	@Override
	public void createBillItem(BillItem billItemFoods) {
		billItemDao.createOrUpdate(billItemFoods);
	}

	@Override
	public List<BillItem> getUnpaidBillItemFoodByBill(Bill bill) {
		return billItemDao.getUnpaidBillItemByBill(bill.getId());
	}

	@Override
	public void pay(List<BillItem> billItems) {
		if(billItems != null && billItems.size() > 0) {
			for(BillItem billItemFood: billItems) {
				billItemFood.setPaid(true);
				billItemDao.createOrUpdate(billItemFood);
			}
		}
	}


}