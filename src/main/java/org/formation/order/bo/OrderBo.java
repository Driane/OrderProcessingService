package org.formation.order.bo;

import java.sql.SQLException;

import org.formation.order.bo.exception.BOException;
import org.formation.order.dto.Order;

public interface OrderBo {

	public Boolean placeOrder(Order order) throws BOException, SQLException;
	
	public Boolean cancelOrder(int id) throws BOException, SQLException;
	
	public Boolean deleteOrder(int id) throws BOException, SQLException;	
}
