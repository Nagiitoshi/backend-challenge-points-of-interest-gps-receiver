package gps.receiver.backend_challenge_points_of_interest_gps_receiver.controller;

import org.springframework.web.bind.annotation.RestController;

import gps.receiver.backend_challenge_points_of_interest_gps_receiver.controller.dto.CreatePointOfInterestDTO;
import gps.receiver.backend_challenge_points_of_interest_gps_receiver.model.PointOfInterest;
import gps.receiver.backend_challenge_points_of_interest_gps_receiver.repository.PointOfInterestRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PointOfInterestController {

    private final PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterestController(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }
    
    @PostMapping("/points-of-interest")
    public ResponseEntity<Void> createPoi(@RequestBody CreatePointOfInterestDTO createPointOfInterestDTO) {
        pointOfInterestRepository.save(new PointOfInterest(createPointOfInterestDTO.name(), createPointOfInterestDTO.x(), createPointOfInterestDTO.y()));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/points-of-interest")
    public ResponseEntity<Page<PointOfInterest>> listPoi(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {

        var body = pointOfInterestRepository.findAll(PageRequest.of(page,pageSize));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(@RequestParam("x") Long x, @RequestParam("y") Long y, @RequestParam("dmax")Long dmax) {

        var xMin = x - dmax;
        var xMax = x + dmax;
        var yMin = y - dmax;
        var yMax = y + dmax;
        
        var body = pointOfInterestRepository.findNearMe(xMin, xMax, yMin, yMax)
        .stream()
        .filter(p -> distanceBetweenPoints(x, y, p.getX(), p.getY()) <= dmax)
        .toList();
        return ResponseEntity.ok(body);
    }

    private Double distanceBetweenPoints(Long x1, Long y1, Long x2, Long y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
