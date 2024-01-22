import React from "react";
import "./TopBar.css";

function TopBar() {
  return (
    <div className="topbar">
      <h1 className="headding">Weather App</h1>
      <div className="gif-img">
        <img
          src="Pictures/IllustriousRecentKoala-size_restricted.gif"
          alt="earth"
          height="80px"
        />
      </div>
    </div>
  );
}

export default TopBar;
