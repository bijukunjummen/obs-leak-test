package leaktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class ObsLeakTest {

    public static void main(String[] args) {
        SpringApplication.run(ObsLeakTest.class, args);
    }
}
