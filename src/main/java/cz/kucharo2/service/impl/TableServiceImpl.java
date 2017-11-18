package cz.kucharo2.service.impl;

import cz.kucharo2.data.dao.RestaurantTableDao;
import cz.kucharo2.data.entity.RestaurantTable;
import cz.kucharo2.service.TableService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by Roman on 12/3/2014.
 */
@ApplicationScoped
@Transactional
public class TableServiceImpl implements TableService {

	@Inject
	private RestaurantTableDao tableDao;

	@Override
	public RestaurantTable getTable(int id) {
		return tableDao.getById(id);
	}
}
