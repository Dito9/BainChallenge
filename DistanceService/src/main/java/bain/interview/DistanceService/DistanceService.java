package bain.interview.DistanceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DistanceService {

    @Autowired
    private DistanceQueryRepository distanceQueryRepository;
 
    public Double getDistance(String source, String destination) throws Exception {
        String url = "https://nominatim.openstreetmap.org/search?q=" + source + "&format=json&limit=1";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode sourceNode = objectMapper.readTree(response).get(0);
        double sourceLat = sourceNode.get("lat").asDouble();
        double sourceLon = sourceNode.get("lon").asDouble();
 
        url = "https://nominatim.openstreetmap.org/search?q=" + destination + "&format=json&limit=1";
        response = restTemplate.getForObject(url, String.class);
        JsonNode destinationNode = objectMapper.readTree(response).get(0);
        double destLat = destinationNode.get("lat").asDouble();
        double destLon = destinationNode.get("lon").asDouble();
 
        return calculateDistance(sourceLat, sourceLon, destLat, destLon);
    }
 
    //Calculate distance using 2 points in latitude/longitude using haversine Formula
    //https://en.wikipedia.org/wiki/Haversine_formula
    //https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4
    private Double calculateDistance(double sourceLat, double sourceLon, double destLat, double destLon) {
        final int R = 6371; // Radius of the earth
        
        double latDistance = Math.toRadians(destLat - sourceLat);
        double lonDistance = Math.toRadians(destLon - sourceLon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(destLat))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        
        return distance;
    }

    public List<DistanceQuery> getHistory() {
        return distanceQueryRepository.findAll();   
    }

    public void saveQuery(DistanceQuery distanceQuery) {
        distanceQueryRepository.save(distanceQuery);
    }
}