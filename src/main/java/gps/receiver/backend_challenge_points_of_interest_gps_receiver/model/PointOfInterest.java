package gps.receiver.backend_challenge_points_of_interest_gps_receiver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PointOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private Long x;

    private Long y;

    public PointOfInterest(String name, Long x, Long y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
