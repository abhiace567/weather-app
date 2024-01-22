package com.weatherapp.service;

import java.util.List;
import java.util.Map;

import com.weatherapp.models.City;

public interface WeatherService {

	List<City> getCities();

	Map<String, String> getCityWeather(String cityKey);

}
