import { useNavigate, useLocation } from 'react-router-dom';

function HistoryPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const handleGoBack = () => {
        navigate('/');
    };
    if (location.state == null) {
        return (
            <div>
              <h2>No Historical Queries Available</h2>
              <button onClick={handleGoBack}>Go to Distance Calculation</button>
            </div>
          ); 
    }
    const historicalData = location.state.historicalData || {};
    

    return (
        <div>
            <table>
            <thead>
                <tr>
                <th>Id</th>
                <th>sourceAddress</th>
                <th>destinationAddress</th>
                <th>distance</th>
                </tr>
            </thead>
            <tbody>
                {historicalData.map((item) => (
                <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.sourceAddress}</td>
                    <td>{item.destinationAddress}</td>
                    <td>{item.distance}</td>
                </tr>
                ))}
            </tbody>
            </table>
            <button onClick={handleGoBack}>Go Back</button>
        </div>
    );    
}

export default HistoryPage;