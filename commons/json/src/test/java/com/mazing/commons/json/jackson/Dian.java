package com.mazing.commons.json.jackson;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dian implements Serializable {

	private static final long serialVersionUID = -8359918523259373062L;
	private double lat;
	private double lng;

	public double getLat() {
		return lat;
	}

	public Dian(@JsonProperty("lat") double lat, @JsonProperty("lng") double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Dian(lat,lng)=" + " {lat:" + lat + "," + "lng:" + lng + "}";
	}

}
