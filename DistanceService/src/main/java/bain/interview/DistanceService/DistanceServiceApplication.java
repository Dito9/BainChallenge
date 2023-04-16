package bain.interview.DistanceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"bain.interview.DistanceService"})
public class DistanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistanceServiceApplication.class, args);
	}

}
