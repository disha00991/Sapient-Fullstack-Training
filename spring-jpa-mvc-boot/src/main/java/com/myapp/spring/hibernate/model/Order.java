package com.myapp.spring.hibernate.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@NamedQueries({ @NamedQuery(name = "OrderFindByStatus", query = "select o from Order as o where o.status=:STATUS"),
				@NamedQuery(name = "OrderFindByOrder", query = "select o FROM Order o where o.timeOrderPlaced<:TIMEPLACED"),
				@NamedQuery(name = "OrderFindByOrderPlaceDate", query = "select o FROM Order o where o.status=? order by o.timeOrderPlaced"),
				@NamedQuery(name = "OrderFindByOrderNumber", query = "select o FROM Order o where o.orderNumber=:ORDER_NUMBER"),
				@NamedQuery(name = "OrderFindAll", query = "select o from Order as o JOIN FETCH o.customer", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true")) })
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ORDER")//.READ for save and find
@Entity
@Table(name = "my_new_orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedEntityGraph(name="order.customer.graph", attributeNodes=@NamedAttributeNode("customer"))
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private long id;

	@Column(name = "ORDER_NUMBER", nullable = false)
	private String orderNumber;

	@Column(name = "ORDER_TIME_PLACED", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOrderPlaced;

	@Column(name = "ORDER_LAST_UPDATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@Column(name = "ORDER_STATUS", nullable = false)
	private String status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = false)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "ORDER_CUSTOMER")
	//@JsonManagedReference
	private Customer customer;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderNumber, Date timeOrderPlaced, Date lastUpdate, String status) {
		this.orderNumber = orderNumber;
		this.timeOrderPlaced = timeOrderPlaced;
		this.lastUpdate = lastUpdate;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getTimeOrderPlaced() {
		return timeOrderPlaced;
	}

	public void setTimeOrderPlaced(Date timeOrderPlaced) {
		this.timeOrderPlaced = timeOrderPlaced;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", orderNumber=");
		builder.append(orderNumber);
		builder.append(", timeOrderPlaced=");
		builder.append(timeOrderPlaced);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public void addOrdertoCustomer(Customer customer) {
		if (customer == null) {
			return;
		}
		this.customer = customer;
		customer.getOrders().add(this);
	}

}
