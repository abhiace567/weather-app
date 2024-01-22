import "./App.css";
import CityInput from "./Components/CityInput/CityInput";
import TopBar from "./Components/TopBar/TopBar";

function App() {
  return (
    <div className="App">
      <TopBar />
      <div className="container">
      <CityInput />
      </div>
    </div>
  );
}

export default App;
