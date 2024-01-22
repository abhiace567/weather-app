package com.weatherapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.models.City;
import com.weatherapp.service.WeatherService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherAppController {

	@Autowired
	WeatherService weatherService;

	@GetMapping("/weather/{cityKey}")
	private Map<String, String> getWeather(@PathVariable("cityKey") String cityKey) {
		return weatherService.getCityWeather(cityKey);
	}

	@GetMapping("/cities")
	private List<City> getCities() {
		return weatherService.getCities();
	}
}
