package com.weatherapp.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.models.City;
import com.weatherapp.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private List<City> cityList = new ArrayList<>();

	@Autowired
	public void setRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Value("${api_key}")
	private String apiKey;

	@Override
	public Map<String, String> getCityWeather(String cityKey) {
		String weatherUrl = String.format(
				"http://dataservice.accuweather.com/currentconditions/v1/%s?apikey=%s&details=true", cityKey, apiKey);
		String jsonData = restTemplate.getForObject(weatherUrl, String.class);
		Map<String, String> weatherData = new LinkedHashMap<>();

		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(jsonData);
			if (rootNode.get(0) == null) {
				return null;
			}
			rootNode = rootNode.get(0);
			String localObservationDateTime = rootNode.path("LocalObservationDateTime").asText();
			weatherData.put("LocalObservationDate", localObservationDateTime.split("T")[0]);
			weatherData.put("LocalObservationTime", localObservationDateTime.split("T")[1].split("\\+")[0]);
			weatherData.put("WeatherText", rootNode.path("WeatherText").asText());
			String icon = rootNode.path("WeatherIcon").asText();
			if (icon.length() == 1)
				icon = "0" + icon;
			weatherData.put("WeatherIcon", icon);
			weatherData.put("HasPrecipitation", rootNode.path("HasPrecipitation").asText());
			weatherData.put("Temperature", rootNode.at("/Temperature/Metric/Value").asText());
			weatherData.put("RealFeelTemperature", rootNode.at("/RealFeelTemperature/Metric/Value").asText());
			weatherData.put("IsDayTime", rootNode.path("IsDayTime").asText());
			weatherData.put("MinimumTemperature",
					rootNode.at("/TemperatureSummary/Past24HourRange/Minimum/Metric/Value").asText());
			weatherData.put("MaximumTemperature",
					rootNode.at("/TemperatureSummary/Past24HourRange/Maximum/Metric/Value").asText());
			weatherData.put("Humidity", rootNode.path("RelativeHumidity").asText());
			weatherData.put("UVIndex", rootNode.path("UVIndex").asText());
			weatherData.put("Visibility", rootNode.at("/Visibility/Metric/Value").asText());
			weatherData.put("WindDirection", rootNode.at("/Wind/Direction/Degrees").asText());
			weatherData.put("WindSpeed", rootNode.at("/Wind/Speed/Metric/Value").asText());
			weatherData.put("Pressure", rootNode.at("/Pressure/Metric/Value").asText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (City city : cityList) {
			if (cityKey.equals(city.getCityKey())) {
				weatherData.put("CityName", city.getCityName());
				weatherData.put("AdministrativeArea", city.getAdministrativeArea());
				weatherData.put("Country", city.getCountry());
				break;
			}
		}
		return weatherData;
	}

	@Override
	public List<City> getCities() {
		if (cityList.isEmpty()) {
			String cityUrl = String.format("http://dataservice.accuweather.com/locations/v1/topcities/150?apikey=%s",
					apiKey);
			String jsonData = restTemplate.getForObject(cityUrl, String.class);
			JsonNode rootNode;
			try {
				rootNode = objectMapper.readTree(jsonData);
				if (rootNode.get(0) != null) {
					for (JsonNode jsonNode : rootNode) {
						cityList.add(new City(jsonNode.path("Key").asText(), jsonNode.path("EnglishName").asText(),
								jsonNode.at("/AdministrativeArea/EnglishName").asText(),
								jsonNode.at("/Country/EnglishName").asText()));
					}
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cityList;
	}

}
