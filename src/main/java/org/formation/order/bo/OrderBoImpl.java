package org.formation.order.bo;

import java.sql.SQLException;

import org.formation.order.bo.exception.BOException;
import org.formation.order.dao.OrderDaoImpl;
import org.formation.order.dto.Order;

public class OrderBoImpl implements OrderBo {

	OrderDaoImpl dao = new OrderDaoImpl();

	public void setDao(OrderDaoImpl dao) {
		this.dao = dao;
	}

	public Boolean placeOrder(Order order) throws BOException {
		try {
			int result = dao.create(order);
			System.out.println("Du caca !");
			System.out.println("Coucou Josiane");
			System.out.println("Coucou Cyril");
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public Boolean cancelOrder(int id) throws BOException{
		try{Order order = dao.read(id);
		order.setStatus("cancelled");
		System.out.println("La superbe phrase de Jean");
		int result = dao.update(order);
		if(result == 0) {
			return false;
		}
	}
	catch(SQLException e)
	{
		throw new BOException(e);
	}return true;
	}

	public Boolean deleteOrder(int id) throws BOException {
		try{int result = dao.delete(id);
		if(result == 0) {
			return false;
		}
	}catch(SQLException e)
	{
		throw new BOException(e);
	}return true;
}



		
	}
