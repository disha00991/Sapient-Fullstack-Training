package com.myapp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.spring.dao.OrderDAO;
import com.myapp.spring.hibernate.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private OrderDAO dao;
	
	@Override
	public List<Order> findAll() {
		
		return dao.findAll();
	}

	@Override
	public void save(Order order) {
		dao.save(order);
	}

	@Override
	public Order findById(long id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Order> findByON(String orderNumber) {
		return dao.findByON(orderNumber);
	}

}
