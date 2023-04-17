import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import React from 'react';
import HistoryPage from './HistoryPage';
import CalculateDistancePage from './CalculateDestinationPage';

function App() {
  
  return (    
    <div>
      <Router>
      <Routes>
        <Route path="/" element={<CalculateDistancePage />} />
        <Route path="/history" element={<HistoryPage />}>
        </Route>
      </Routes>
    </Router>
    </div>
  );

}

export default App;
