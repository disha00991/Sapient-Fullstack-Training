package com.myapp.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.myapp.spring.Flight;

@Component
public class FlightDAOImpl implements FlightDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Flight> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from flight", new BeanPropertyRowMapper(Flight.class));
	}
}
