import './App.css';

import React, { useState } from 'react';
import axios from 'axios';

function App() {

  const [source, setSource] = useState('');
  const [destination, setDestination] = useState('');
  const [distance, setDistance] = useState(null);
  const [error, setError] = useState("");

  const handleCalculateClick = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/distance?source=${source}&destination=${destination}`
      );
      
      setDistance(response.data);
      setError("");
    } catch (error) {
      console.log(error);
      setDistance("");
      setError("There was an error calculating the distance. Please retry in a minute");
    }
  };

  const handleHistoryClick = async () => {
    window.location.href = "http://localhost:8080/api/history";
  };

  return (
    <div>
      <label>Source:</label>
      <input type="text" value={source} onChange={(e) => setSource(e.target.value)} />
      <br />
      <label>Destination:</label>
      <input type="text" value={destination} onChange={(e) => setDestination(e.target.value)} />
      <br />
      <button onClick={handleCalculateClick}>Calculate Distance</button>
      <br />
      <label>Distance:</label>
      <div>{distance}</div>
      {error && <div style={{ color: "red" }}>{error}</div>}
      <br />
      <button onClick={handleHistoryClick}>Show History</button>
      <br />

    </div>
  );
}

export default App;
