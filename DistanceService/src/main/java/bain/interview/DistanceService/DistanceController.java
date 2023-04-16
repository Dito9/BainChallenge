package bain.interview.DistanceService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DistanceController {
 
    @Autowired
    private DistanceService distanceService;    
 
    /**
     * Api to calculate distance
     * @param source source address
     * @param destination source destination
     * @return Distance in Km.
     */
    @GetMapping("/distance")
    public ResponseEntity<String> getDistance(@RequestParam("source") String source, @RequestParam("destination") String destination) { 
        try {
            Double distance = distanceService.getDistance(source, destination);

            DistanceQuery distanceQuery = new DistanceQuery();
            distanceQuery.setSourceAddress(source);
            distanceQuery.setDestinationAddress(destination);
            distanceQuery.setDistance(distance);
            
            distanceService.saveQuery(distanceQuery);
            
            return ResponseEntity.ok(distance.toString()+" km");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<DistanceQuery>> getHistory() {
        List<DistanceQuery> history = distanceService.getHistory();
        return ResponseEntity.ok(history);
    }
 
}