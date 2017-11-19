package cz.kucharo2.data.dao.impl;

import cz.kucharo2.data.dao.BillDao;
import cz.kucharo2.data.entity.Bill;
import cz.kucharo2.data.entity.BillItem;
import cz.kucharo2.data.enums.BillStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Roman Kuchár <kucharrom@gmail.com>.
 */
@ApplicationScoped
public class BillDaoImpl extends AbstractGenericDaoImpl<Bill> implements BillDao {

    public BillDaoImpl() {
        super(Bill.class);
    }

    public List<Bill> getUnpaidBillOnTable(int tableID) {
        String query = Bill.BILL_ID + " in (select " + BillItem.ID_COLUMN + " from " + BillItem.TABLE_NAME +
                " where " + BillItem.PAID + " = :paid) and " + Bill.TABLE_ID + " = :tableId";

        Map<String, Object> params = new HashMap<>();
        params.put("paid", false);
        params.put("tableId", tableID);

        return getByWhereCondition(query, params);
    }

    @Override
    public Bill getCreatedBillByTableAndUser(int tableId, int accountId) {
        Query query = getEntityManager().createQuery("select b from Bill b left join fetch b.billItems " +
                "where b.table.id = :tableId and b.account.id = :accountId and b.status = :status")
                .setParameter("tableId", tableId)
                .setParameter("accountId", accountId)
                .setParameter("status", BillStatus.CREATED);

        try {
            return (Bill) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Bill getBillWithItems(int billId) {
        Query query = getEntityManager().createQuery("select b from Bill b left join fetch b.billItems where b.id = :billId")
                .setParameter("billId", billId);
        try {
            return (Bill) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
