package org.formation.order.bo;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.formation.order.bo.exception.BOException;
import org.formation.order.dao.OrderDaoImpl;
import org.formation.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderBoImplTest {

	private static final int ORDER_ID = 123;
	
	@Mock
	OrderDaoImpl dao;
	private OrderBoImpl bo;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBoImpl();
		bo.setDao(dao);
	}
	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws BOException, SQLException {
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(dao).create(order);
	}
	
	@Test (expected = BOException.class)
	public void placeOrder_Should_Throw_A_BOException() throws SQLException, BOException {
		Order order = new Order(); 
		when(dao.create(order)).thenThrow(new SQLException());
		bo.placeOrder(order);
		verify(dao).create(order);
		
	}
	

	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(anyInt())).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer (ORDER_ID));
		
		boolean result = bo.cancelOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order); 
	}
	
	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer (0));
		
		boolean result = bo.cancelOrder(ORDER_ID);
		assertFalse(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order); 
	}
	@Test (expected = BOException.class)
	public void cancelOrder_Should_Throw_An_Exception() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenThrow(new SQLException());
		boolean result = bo.cancelOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order); 
		
	}

	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_An_BOException_On_Update() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenThrow(new SQLException());
		boolean result = bo.cancelOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
	}

	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		when(dao.delete(ORDER_ID)).thenReturn(new Integer(ORDER_ID));
		
		boolean result = bo.deleteOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).delete(ORDER_ID);
		
	}
	
}
