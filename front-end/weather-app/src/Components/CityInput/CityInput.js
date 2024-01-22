import axios from "axios";
import React, { useEffect, useState } from "react";
import Select from "react-select";

import { Variables } from "../Vriables";
import Weather from "../Weather/Weather";

import "./CityInput.css";

function CityInput(props) {
  const [cities, setCities] = useState([]);

  const [weatherData, setWeatherData] = useState();

  const optionsChangeHandler = (e) => {
    getWeatherData(e.value);
  };

  const getWeatherData = (cityKey) => {
    axios
      .get(Variables.WEATHER_URL + cityKey)
      .then((response) => setWeatherData(response.data));
  };

  const refreshCities = () => {
    axios.get(Variables.CITIES_URL).then((response) =>
      setCities(
        response.data
          .sort((a, b) => a.cityName.localeCompare(b.cityName))
          .map((city) => {
            return { label: city.cityName, value: city.cityKey };
          })
      )
    );
  };

  useEffect(refreshCities, []);

  return (
    <div id="city-form">
      <div id="indiv">
        <h2 className="sub-hedding">Select Your City</h2>
        <Select options={cities} onChange={optionsChangeHandler} />
        {weatherData !== undefined ? <Weather data={weatherData} /> : null}
      </div>
    </div>
  );
}

export default CityInput;
