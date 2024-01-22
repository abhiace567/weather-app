package com.weatherapp.models;

public class City {
	String cityKey;
	String cityName;
	String administrativeArea;
	String country;

	public City(String cityKey, String cityName, String administrativeArea, String country) {
		super();
		this.cityKey = cityKey;
		this.cityName = cityName;
		this.administrativeArea = administrativeArea;
		this.country = country;
	}

	public String getCityKey() {
		return cityKey;
	}

	public void setCityKey(String cityKey) {
		this.cityKey = cityKey;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
