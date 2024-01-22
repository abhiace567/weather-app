import React from "react";

import "./Weather.css";

export default function Weather(props) {
  const data = props.data;
  console.log(data);
  if (data.IsDayTime === "true") {
    document.body.style.backgroundImage = "url(Pictures/day.jpg)";
  } else {
    document.body.style.backgroundImage = "url(Pictures/night.jpg)";
  }

  return (
    <div className="displayweather">
      <div className="maincard">
        <div className="temp">
          <h1>
            {data.Temperature} <sup>o</sup>C
          </h1>
          <h2>
            Feels Like {data.RealFeelTemperature} <sup>o</sup>C
          </h2>
          <span>
            {data.CityName}, {data.AdministrativeArea}, {data.Country}
          </span>
          <br />
          <span>
            As of {data.LocalObservationTime}, {data.LocalObservationDate}
          </span>
        </div>
        <div className="icon">
          <img
            src={`https://developer.accuweather.com/sites/default/files/${data.WeatherIcon}-s.png`}
            alt=""
            height="150px"
          />
          <div className="weather-description">{data.WeatherText}</div>
        </div>
      </div>
      <div className="weatherdetails">
        <div className="section1">
          <table width="100%">
            <tbody>
              <tr>
                <td>
                  <h4>High/Low:</h4>
                </td>
                <td>
                  <span>
                    {data.MaximumTemperature}/{data.MinimumTemperature} °C
                  </span>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>Humidity:</h4>
                </td>
                <td>
                  <span>{data.Humidity} %</span>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>Pressure:</h4>
                </td>
                <td>
                  <span>{data.Pressure} mb</span>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>Visibility:</h4>
                </td>
                <td>
                  <span>{data.Visibility} Km</span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div className="section2">
          <table width="100%">
            <tbody>
              <tr>
                <td>
                  <h4>Wind:</h4>
                </td>
                <td>{data.WindSpeed} Km/h</td>
              </tr>
              <tr>
                <td>
                  <h4>Wind Direction:</h4>
                </td>
                <td>
                  <span>
                    {data.WindDirection}
                    <sup>o</sup> deg
                  </span>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>UV Index:</h4>
                </td>
                <td>
                  <span>{data.UVIndex}</span>
                </td>
              </tr>
              <tr>
                <td>
                  <h4>Rain:</h4>
                </td>
                <td>
                  <span>{data.HasPrecipitation === true ? "Yes" : "No"}</span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
