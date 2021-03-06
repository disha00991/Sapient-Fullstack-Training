package com.myapp.spring;

public class Flight {
private long id;
private String fromCity;
private String toCity;
public Flight() {
	
}

public Flight(String fromCity, String toCity) {
	this.fromCity = fromCity;
	this.toCity = toCity;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFromCity() {
	return fromCity;
}
public void setFromCity(String fromCity) {
	this.fromCity = fromCity;
}
public String getToCity() {
	return toCity;
}
public void setToCity(String toCity) {
	this.toCity = toCity;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof Flight))
		return false;
	Flight other = (Flight) obj;
	if (id != other.id)
		return false;
	return true;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Flight [id=");
	builder.append(id);
	builder.append(", fromCity=");
	builder.append(fromCity);
	builder.append(", toCity=");
	builder.append(toCity);
	builder.append("]");
	return builder.toString();
}


}
