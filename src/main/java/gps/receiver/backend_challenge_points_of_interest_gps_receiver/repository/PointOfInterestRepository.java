package gps.receiver.backend_challenge_points_of_interest_gps_receiver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gps.receiver.backend_challenge_points_of_interest_gps_receiver.model.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

        @Query("""
                        SELECT p FROM PointOfInterest p
                        WHERE (p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)
                        """)
        List<PointOfInterest> findNearMe(@Param("xMin") long xMin, @Param("xMax") long xMax, @Param("yMin") long yMin,
                        @Param("yMax") long yMax);
}