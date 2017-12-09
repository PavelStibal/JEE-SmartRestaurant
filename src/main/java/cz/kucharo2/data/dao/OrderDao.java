package cz.kucharo2.data.dao;


import cz.kucharo2.data.entity.Order;

import java.util.List;

/**
 * @Author Roman Kuchár <kucharrom@gmail.com>.
 */
public interface OrderDao extends AbstractGenericDao<Order> {

	/**
	 *  Get all unpaid orders for specific table.
	 *
	 * @param tableID table id
	 * @return list of unpaid orders
	 */
	List<Order> getUnpaidOrderOnTable(int tableID);

	/**
	 * Get CREATED order on table.
	 *
	 * @param tableId table id
	 */
	Order getCreatedOrderByTableAndUser(int tableId, int accountId);

	/**
	 * Returns order with loaded order items.
	 *
	 * @param orderId order id
	 * @return order by id
	 */
	Order getOrderWithItems(int orderId);
}
