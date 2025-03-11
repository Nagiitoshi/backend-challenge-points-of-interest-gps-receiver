package gps.receiver.backend_challenge_points_of_interest_gps_receiver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gps.receiver.backend_challenge_points_of_interest_gps_receiver.model.PointOfInterest;
import gps.receiver.backend_challenge_points_of_interest_gps_receiver.repository.PointOfInterestRepository;

@SpringBootApplication
public class BackendChallengePointsOfInterestGpsReceiverApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendChallengePointsOfInterestGpsReceiverApplication.class, args);
	}

	private PointOfInterestRepository pointOfInterestRepository;

	@Override
	public void run(String... args) throws Exception {
		pointOfInterestRepository.save(new PointOfInterest("Lanchonete", 27L, 12L));
		pointOfInterestRepository.save(new PointOfInterest("Posto", 31L, 18L));
		pointOfInterestRepository.save(new PointOfInterest("Joalheria ", 15L, 12L));
		pointOfInterestRepository.save(new PointOfInterest("Floricultura", 19L, 21L));
		pointOfInterestRepository.save(new PointOfInterest("Pub", 12L, 8L));
		pointOfInterestRepository.save(new PointOfInterest("Supermercado", 23L, 6L));
		pointOfInterestRepository.save(new PointOfInterest("Churrascaria", 28L, 2L));
	}

}
