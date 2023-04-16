package bain.interview.DistanceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DistanceService {    

    @Autowired
    private DistanceQueryRepository distanceQueryRepository;

    private GeoPoint getGeoPoint(String address) throws JsonMappingException, JsonProcessingException {
        String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json&limit=1";
        
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode addressNode = objectMapper.readTree(response).get(0);

        double lat = addressNode.get("lat").asDouble();
        double lon = addressNode.get("lon").asDouble();
        return new GeoPoint(lat, lon);

    }
    public Double getDistance(String source, String destination) throws Exception {        

        
        GeoPoint sourcePoint = getGeoPoint(source);
        GeoPoint destinationPoint = getGeoPoint(destination);

        return sourcePoint.calculateDistance(destinationPoint);
    }

    public List<DistanceQuery> getHistory() {
        return distanceQueryRepository.findAll();   
    }

    public void saveQuery(DistanceQuery distanceQuery) {
        distanceQueryRepository.save(distanceQuery);
    }
}