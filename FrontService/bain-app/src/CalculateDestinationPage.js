import { useState } from "react";
import { useNavigate  } from "react-router-dom";
import axios from 'axios';

function CalculateDistancePage() {

    const [source, setSource] = useState('');
    const [destination, setDestination] = useState('');
    const [distance, setDistance] = useState(null);
    const [error, setError] = useState("");
    const navigate = useNavigate();
  
    const handleHistoryClick = async () => {    
      try {      
        const response = await axios.get(`https://afternoon-escarpment-95639.herokuapp.com/api/history`);
        navigate('/history', { state: { historicalData: response.data } });
      } catch (error) {
        console.log(error);
      }
    };
  
    const handleCalculateClick = async () => {
      try {
        const response = await axios.get(`https://afternoon-escarpment-95639.herokuapp.com/api/distance?source=${source}&destination=${destination}`
        );
        
        setDistance(response.data);
        setError("");
      } catch (error) {
        console.log(error);
        setDistance("");
        setError("There was an error calculating the distance. Please retry in a minute");
      }
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
    </div>
    )
  
  }

export default CalculateDistancePage;